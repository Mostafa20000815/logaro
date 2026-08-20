package com.example.data.model

enum class GamePhase {
    HOME,
    SETUP,
    PASS_AND_PLAY,
    GAME_DASHBOARD,
    NIGHT_MODERATOR,
    ROLES_DIRECTORY
}

data class GameState(
    val currentPhase: GamePhase = GamePhase.HOME,
    val playerCount: Int = 8,
    val playerNames: List<String> = List(8) { "لاعب ${it + 1}" },
    val selectedRoleCounts: Map<String, Int> = RolesRegistry.getRecommendedRoleCounts(8),
    val players: List<Player> = emptyList(),
    val extraCards: List<Role> = emptyList(),
    
    // Pass and Play State
    val currentPlayerIndex: Int = 0,
    val isPrivacyShieldActive: Boolean = true, // شاشة الحماية لمنع استراق النظر
    val isCardFaceUp: Boolean = false, // حالة قلب البطاقة ثلاثية الأبعاد
    val allPlayersSeenCards: Boolean = false,
    
    // Night Moderator Assistant State
    val nightNumber: Int = 1,
    val currentNightStepIndex: Int = 0,
    val nightLog: List<String> = emptyList()
) {
    val totalSelectedCards: Int
        get() = selectedRoleCounts.values.sum()

    val isRoleCountValid: Boolean
        get() = totalSelectedCards == playerCount

    val currentPlayer: Player?
        get() = players.getOrNull(currentPlayerIndex)

    val passAndPlayProgress: Float
        get() = if (players.isNotEmpty()) (currentPlayerIndex.toFloat() / players.size.toFloat()) else 0f
}
