package com.example.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.model.GamePhase
import com.example.data.model.GameState
import com.example.data.model.Player
import com.example.data.model.Role
import com.example.data.model.RolesRegistry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    fun navigateToPhase(phase: GamePhase) {
        _gameState.update { it.copy(currentPhase = phase) }
    }

    /**
     * تحديث عدد اللاعبين وإعادة ضبط الأسماء والتوزيع المقترح
     */
    fun setPlayerCount(count: Int) {
        val clampedCount = count.coerceIn(4, 35)
        val currentNames = _gameState.value.playerNames
        val newNames = List(clampedCount) { index ->
            if (index < currentNames.size && currentNames[index].isNotBlank()) {
                currentNames[index]
            } else {
                "لاعب ${index + 1}"
            }
        }
        val recommendedRoles = RolesRegistry.getRecommendedRoleCounts(clampedCount)

        _gameState.update {
            it.copy(
                playerCount = clampedCount,
                playerNames = newNames,
                selectedRoleCounts = recommendedRoles
            )
        }
    }

    /**
     * تحديث اسم لاعب معين
     */
    fun updatePlayerName(index: Int, name: String) {
        _gameState.update { current ->
            val updatedNames = current.playerNames.toMutableList()
            if (index in updatedNames.indices) {
                updatedNames[index] = name
            }
            current.copy(playerNames = updatedNames)
        }
    }

    /**
     * تعديل عدد بطاقات دور معين في التشكيلة
     */
    fun updateRoleCount(roleId: String, count: Int) {
        _gameState.update { current ->
            val updated = current.selectedRoleCounts.toMutableMap()
            if (count <= 0) {
                updated.remove(roleId)
            } else {
                updated[roleId] = count
            }
            current.copy(selectedRoleCounts = updated)
        }
    }

    /**
     * تطبيق توزيع متوازن تلقائي بناءً على عدد اللاعبين
     */
    fun applyRecommendedRolePreset() {
        val count = _gameState.value.playerCount
        val recommended = RolesRegistry.getRecommendedRoleCounts(count)
        _gameState.update { it.copy(selectedRoleCounts = recommended) }
    }

    /**
     * بدء توزيع الأدوار بنظام Pass & Play
     * يتم خلط البطاقات عشوائياً وتعيينها للاعبين مع تفعيل حظر استراق النظر
     */
    fun startPassAndPlay(): Boolean {
        val state = _gameState.value
        if (!state.isRoleCountValid) return false

        // إنشاء مصفوفة البطاقات وخلطها عشوائياً
        val cardPool = mutableListOf<Role>()
        state.selectedRoleCounts.forEach { (roleId, count) ->
            val role = RolesRegistry.getRoleById(roleId)
            repeat(count) {
                cardPool.add(role)
            }
        }
        cardPool.shuffle()

        // ربط كل لاعب ببطاقته السرية
        val playersList = state.playerNames.mapIndexed { index, name ->
            val assignedRole = cardPool.getOrElse(index) { RolesRegistry.VILLAGER }
            Player(
                id = index + 1,
                name = name.ifBlank { "لاعب ${index + 1}" },
                role = assignedRole,
                isAlive = true,
                hasSeenCard = false
            )
        }

        _gameState.update {
            it.copy(
                players = playersList,
                currentPlayerIndex = 0,
                isPrivacyShieldActive = true,
                isCardFaceUp = false,
                allPlayersSeenCards = false,
                currentPhase = GamePhase.PASS_AND_PLAY
            )
        }
        return true
    }

    /**
     * اللاعب يضغط على زر "أنا فلان" لإزالة شاشة الخصوصية وإظهار بطاقته المغلقة
     */
    fun onPlayerUnlockPrivacyShield() {
        _gameState.update {
            it.copy(
                isPrivacyShieldActive = false,
                isCardFaceUp = false
            )
        }
    }

    /**
     * قلب البطاقة للكشف عن الدور (3D Flip)
     */
    fun toggleCardFlip() {
        _gameState.update { current ->
            val newFaceUp = !current.isCardFaceUp
            // تحديد أن اللاعب الحالي قد رأى بطاقته
            val updatedPlayers = current.players.toMutableList()
            if (current.currentPlayerIndex in updatedPlayers.indices) {
                updatedPlayers[current.currentPlayerIndex] =
                    updatedPlayers[current.currentPlayerIndex].copy(hasSeenCard = true)
            }
            current.copy(
                isCardFaceUp = newFaceUp,
                players = updatedPlayers
            )
        }
    }

    /**
     * الانتقال إلى اللاعب التالي مع إعادة تفعيل شاشة الخصوصية وإخفاء البطاقة
     */
    fun nextPlayerPassAndPlay() {
        _gameState.update { current ->
            val nextIndex = current.currentPlayerIndex + 1
            if (nextIndex < current.players.size) {
                current.copy(
                    currentPlayerIndex = nextIndex,
                    isPrivacyShieldActive = true,
                    isCardFaceUp = false
                )
            } else {
                // انتهى جميع اللاعبين من رؤية بطاقاتهم
                current.copy(
                    allPlayersSeenCards = true,
                    isCardFaceUp = false,
                    isPrivacyShieldActive = true,
                    currentPhase = GamePhase.GAME_DASHBOARD
                )
            }
        }
    }

    /**
     * المشرف يبدأ طور الليل
     */
    fun startNightPhase() {
        _gameState.update {
            it.copy(
                currentPhase = GamePhase.NIGHT_MODERATOR,
                currentNightStepIndex = 0
            )
        }
    }

    fun nextNightStep() {
        _gameState.update { current ->
            val nightRoles = RolesRegistry.NIGHT_ROLES_ORDERED
            val nextStep = current.currentNightStepIndex + 1
            if (nextStep < nightRoles.size) {
                current.copy(currentNightStepIndex = nextStep)
            } else {
                // نهاية خطوات الليل
                current.copy(
                    currentPhase = GamePhase.GAME_DASHBOARD,
                    nightNumber = current.nightNumber + 1
                )
            }
        }
    }

    fun prevNightStep() {
        _gameState.update { current ->
            val prevStep = (current.currentNightStepIndex - 1).coerceAtLeast(0)
            current.copy(currentNightStepIndex = prevStep)
        }
    }

    fun togglePlayerStatus(playerId: Int) {
        _gameState.update { current ->
            val updatedPlayers = current.players.map { player ->
                if (player.id == playerId) {
                    player.copy(isAlive = !player.isAlive)
                } else player
            }
            current.copy(players = updatedPlayers)
        }
    }

    fun setMayor(playerId: Int) {
        _gameState.update { current ->
            val updatedPlayers = current.players.map { player ->
                player.copy(isMayor = (player.id == playerId))
            }
            current.copy(players = updatedPlayers)
        }
    }

    fun resetGame() {
        val defaultCount = 8
        _gameState.update {
            GameState(
                playerCount = defaultCount,
                playerNames = List(defaultCount) { "لاعب ${it + 1}" },
                selectedRoleCounts = RolesRegistry.getRecommendedRoleCounts(defaultCount),
                currentPhase = GamePhase.HOME
            )
        }
    }
}
