package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GameState
import com.example.ui.components.FlipRoleCard
import com.example.ui.components.NeoBadge
import com.example.ui.components.NeoButton
import com.example.ui.components.PrivacyShieldOverlay
import com.example.ui.components.WerewolfBackground
import com.example.ui.theme.InkBlack
import com.example.ui.theme.InkWhite
import com.example.ui.theme.PaperAged
import com.example.ui.theme.PaperBorder
import com.example.ui.theme.PaperCard
import com.example.ui.theme.PaperDark
import com.example.ui.theme.PaperRustRed
import com.example.ui.theme.PaperSageGreen
import com.example.ui.theme.PaperSand

@Composable
fun PassAndPlayScreen(
    gameState: GameState,
    onUnlockPrivacyShield: () -> Unit,
    onToggleCardFlip: () -> Unit,
    onNextPlayerClicked: () -> Unit,
    onQuitGame: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentPlayer = gameState.currentPlayer
    val playerIndex = gameState.currentPlayerIndex
    val totalPlayers = gameState.players.size
    val isLastPlayer = playerIndex >= totalPlayers - 1

    WerewolfBackground(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // شريط التقدم والتحكم العلوي
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(PaperCard, shape = RoundedCornerShape(8.dp))
                            .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                            .clickable { onQuitGame() }
                            .testTag("quit_game_button"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "إنهاء",
                            tint = InkBlack
                        )
                    }

                    NeoBadge(
                        text = "اللاعب ${playerIndex + 1} من $totalPlayers",
                        backgroundColor = PaperSand,
                        textColor = InkBlack,
                        shadowOffset = 2.dp
                    )

                    Box(modifier = Modifier.size(40.dp))
                }

                Spacer(modifier = Modifier.height(10.dp))

                // شريط التقدم الورقي
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .background(PaperDark, shape = RoundedCornerShape(5.dp))
                        .border(1.5.dp, PaperBorder, shape = RoundedCornerShape(5.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth((playerIndex + 1).toFloat() / totalPlayers.toFloat())
                            .height(10.dp)
                            .background(PaperRustRed, shape = RoundedCornerShape(5.dp))
                    )
                }
            }

            // المحتوى الأوسط
            if (gameState.isPrivacyShieldActive) {
                PrivacyShieldOverlay(
                    playerName = currentPlayer?.name ?: "اللاعب ${playerIndex + 1}",
                    onReadyClicked = onUnlockPrivacyShield
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    NeoBadge(
                        text = "دور اللاعب: ${currentPlayer?.name ?: ""}",
                        backgroundColor = PaperAged,
                        textColor = InkBlack,
                        shadowOffset = 2.dp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    if (currentPlayer != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.92f)
                                .aspectRatio(0.66f)
                        ) {
                            FlipRoleCard(
                                role = currentPlayer.role,
                                isFlipped = gameState.isCardFaceUp,
                                onCardClick = onToggleCardFlip
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // زر / وسام لمس الكشف والإخفاء
                    Box(
                        modifier = Modifier
                            .shadow(4.dp, RoundedCornerShape(10.dp))
                            .background(
                                brush = if (gameState.isCardFaceUp) {
                                    Brush.horizontalGradient(listOf(Color(0xFF2C1624), Color(0xFF1A1F2C)))
                                } else {
                                    Brush.horizontalGradient(listOf(Color(0xFF78350F), Color(0xFFB45309)))
                                },
                                shape = RoundedCornerShape(10.dp)
                            )
                            .border(2.dp, PaperBorder, RoundedCornerShape(10.dp))
                            .clickable { onToggleCardFlip() }
                            .padding(horizontal = 18.dp, vertical = 8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (gameState.isCardFaceUp) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                contentDescription = null,
                                tint = InkWhite,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (gameState.isCardFaceUp) "اضغط لإخفاء البطاقة" else "اضغط لقلب البطاقة 3D وكشف دورك",
                                color = InkWhite,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // زر الانتقال والتمرير بالأسفل
            if (!gameState.isPrivacyShieldActive) {
                NeoButton(
                    text = if (isLastPlayer) "إنهاء التوزيع وبدء الجلسة" else "إخفاء البطاقة وتمرير الهاتف للتالي",
                    onClick = onNextPlayerClicked,
                    backgroundColor = if (isLastPlayer) PaperSageGreen else PaperRustRed,
                    contentColor = InkWhite,
                    icon = if (isLastPlayer) Icons.Default.Check else Icons.Default.Lock,
                    shadowOffset = 4.dp,
                    testTag = "next_player_button"
                )
            } else {
                Spacer(modifier = Modifier.height(1.dp))
            }
        }
    }
}
