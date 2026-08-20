package com.example.ui.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.GamePhase
import com.example.ui.screens.GameDashboardScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.NightModeratorScreen
import com.example.ui.screens.PassAndPlayScreen
import com.example.ui.screens.RolesEncyclopediaScreen
import com.example.ui.screens.SetupScreen
import com.example.ui.theme.PaperWarm
import com.example.ui.theme.WerewolfTheme
import com.example.viewmodel.GameViewModel

@Composable
fun WerewolfApp(
    viewModel: GameViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsStateWithLifecycle()

    WerewolfTheme {
        // دعم اتجاه النص من اليمين لليسار RTL لتجربة عربية أصيلة ومثالية
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                containerColor = PaperWarm
            ) { innerPadding ->
                Crossfade(
                    targetState = gameState.currentPhase,
                    animationSpec = tween(400),
                    label = "GamePhaseTransition",
                    modifier = Modifier.padding(innerPadding)
                ) { phase ->
                    when (phase) {
                        GamePhase.HOME -> {
                            HomeScreen(
                                onStartNewGame = { viewModel.navigateToPhase(GamePhase.SETUP) },
                                onOpenRolesGuide = { viewModel.navigateToPhase(GamePhase.ROLES_DIRECTORY) },
                                onOpenModeratorGuide = { viewModel.startNightPhase() }
                            )
                        }

                        GamePhase.SETUP -> {
                            SetupScreen(
                                gameState = gameState,
                                onPlayerCountChanged = { viewModel.setPlayerCount(it) },
                                onPlayerNameChanged = { idx, name -> viewModel.updatePlayerName(idx, name) },
                                onRoleCountChanged = { roleId, count -> viewModel.updateRoleCount(roleId, count) },
                                onApplyPreset = { viewModel.applyRecommendedRolePreset() },
                                onStartGame = { viewModel.startPassAndPlay() },
                                onBackToHome = { viewModel.navigateToPhase(GamePhase.HOME) }
                            )
                        }

                        GamePhase.PASS_AND_PLAY -> {
                            PassAndPlayScreen(
                                gameState = gameState,
                                onUnlockPrivacyShield = { viewModel.onPlayerUnlockPrivacyShield() },
                                onToggleCardFlip = { viewModel.toggleCardFlip() },
                                onNextPlayerClicked = { viewModel.nextPlayerPassAndPlay() },
                                onQuitGame = { viewModel.navigateToPhase(GamePhase.SETUP) }
                            )
                        }

                        GamePhase.GAME_DASHBOARD -> {
                            GameDashboardScreen(
                                gameState = gameState,
                                onStartNightPhase = { viewModel.startNightPhase() },
                                onOpenRolesGuide = { viewModel.navigateToPhase(GamePhase.ROLES_DIRECTORY) },
                                onTogglePlayerAlive = { viewModel.togglePlayerStatus(it) },
                                onSetMayor = { viewModel.setMayor(it) },
                                onResetGame = { viewModel.resetGame() }
                            )
                        }

                        GamePhase.NIGHT_MODERATOR -> {
                            NightModeratorScreen(
                                gameState = gameState,
                                onNextStep = { viewModel.nextNightStep() },
                                onPrevStep = { viewModel.prevNightStep() },
                                onClose = {
                                    if (gameState.players.isNotEmpty()) {
                                        viewModel.navigateToPhase(GamePhase.GAME_DASHBOARD)
                                    } else {
                                        viewModel.navigateToPhase(GamePhase.HOME)
                                    }
                                }
                            )
                        }

                        GamePhase.ROLES_DIRECTORY -> {
                            RolesEncyclopediaScreen(
                                onBack = {
                                    if (gameState.players.isNotEmpty() && gameState.allPlayersSeenCards) {
                                        viewModel.navigateToPhase(GamePhase.GAME_DASHBOARD)
                                    } else if (gameState.players.isNotEmpty()) {
                                        viewModel.navigateToPhase(GamePhase.SETUP)
                                    } else {
                                        viewModel.navigateToPhase(GamePhase.HOME)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
