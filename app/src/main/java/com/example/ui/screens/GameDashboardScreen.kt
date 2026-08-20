package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GameState
import com.example.data.model.Player
import com.example.ui.components.NeoBadge
import com.example.ui.components.NeoButton
import com.example.ui.components.NeoCard
import com.example.ui.components.WerewolfBackground
import com.example.ui.theme.InkBlack
import com.example.ui.theme.InkCharcoal
import com.example.ui.theme.InkMuted
import com.example.ui.theme.InkWhite
import com.example.ui.theme.PaperAged
import com.example.ui.theme.PaperBorder
import com.example.ui.theme.PaperCard
import com.example.ui.theme.PaperDark
import com.example.ui.theme.PaperOchre
import com.example.ui.theme.PaperPrussianBlue
import com.example.ui.theme.PaperRustRed
import com.example.ui.theme.PaperSageGreen
import com.example.ui.theme.PaperSand
import com.example.ui.theme.PaperVintagePurple

@Composable
fun GameDashboardScreen(
    gameState: GameState,
    onStartNightPhase: () -> Unit,
    onOpenRolesGuide: () -> Unit,
    onTogglePlayerAlive: (Int) -> Unit,
    onSetMayor: (Int) -> Unit,
    onResetGame: () -> Unit,
    modifier: Modifier = Modifier
) {
    val aliveCount = gameState.players.count { it.isAlive }
    val deadCount = gameState.players.size - aliveCount

    WerewolfBackground(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // الشريط العلوي
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
                        .clickable { onResetGame() }
                        .testTag("home_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "الرئيسية",
                        tint = InkBlack
                    )
                }

                NeoBadge(
                    text = "لوحة إدارة وتسيير القرية",
                    backgroundColor = PaperAged,
                    textColor = InkBlack,
                    shadowOffset = 2.dp
                )

                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(PaperCard, shape = RoundedCornerShape(8.dp))
                        .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                        .clickable { onOpenRolesGuide() }
                        .testTag("roles_guide_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = "دليل الأدوار",
                        tint = InkBlack
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // بطاقات الإحصائيات
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PaperStatBox(
                    title = "الأحياء",
                    value = "$aliveCount",
                    color = PaperSageGreen,
                    textColor = InkWhite,
                    modifier = Modifier.weight(1f)
                )
                PaperStatBox(
                    title = "المستبعدون",
                    value = "$deadCount",
                    color = PaperRustRed,
                    textColor = InkWhite,
                    modifier = Modifier.weight(1f)
                )
                PaperStatBox(
                    title = "الليلة",
                    value = "${gameState.nightNumber}",
                    color = PaperSand,
                    textColor = InkBlack,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // زر إطلاق مرحلة الليل للراوي
            NeoButton(
                text = "بدء تسيير طور الليل ${gameState.nightNumber}",
                onClick = onStartNightPhase,
                backgroundColor = PaperVintagePurple,
                contentColor = InkWhite,
                icon = Icons.Default.Nightlight,
                shadowOffset = 4.dp,
                testTag = "start_night_phase_button"
            )

            Spacer(modifier = Modifier.height(14.dp))

            NeoBadge(
                text = "قائمة اللاعبين وحالتهم:",
                backgroundColor = PaperSand,
                textColor = InkBlack,
                shadowOffset = 2.dp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // قائمة اللاعبين
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(gameState.players) { player ->
                    PaperPlayerStatusRow(
                        player = player,
                        onToggleAlive = { onTogglePlayerAlive(player.id) },
                        onSetMayor = { onSetMayor(player.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            NeoButton(
                text = "إنهاء الجلسة والبدء من جديد",
                onClick = onResetGame,
                backgroundColor = PaperCard,
                contentColor = InkBlack,
                icon = Icons.Default.Refresh,
                shadowOffset = 3.dp,
                testTag = "reset_game_button"
            )
        }
    }
}

@Composable
private fun PaperStatBox(
    title: String,
    value: String,
    color: Color,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    NeoCard(
        backgroundColor = color,
        shadowOffset = 3.dp,
        borderWidth = 2.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, color = textColor, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = value, color = textColor, fontSize = 20.sp, fontWeight = FontWeight.Black)
        }
    }
}

@Composable
private fun PaperPlayerStatusRow(
    player: Player,
    onToggleAlive: () -> Unit,
    onSetMayor: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeoCard(
        backgroundColor = if (player.isAlive) PaperCard else PaperDark,
        shadowOffset = 2.dp,
        borderWidth = 2.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // زر تغيير حالة الحياة / الموت
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(if (player.isAlive) PaperSageGreen else PaperRustRed, shape = CircleShape)
                        .border(1.5.dp, PaperBorder, CircleShape)
                        .clickable { onToggleAlive() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (player.isAlive) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        tint = InkWhite,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = player.name,
                            color = InkBlack,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black,
                            textDecoration = if (player.isAlive) TextDecoration.None else TextDecoration.LineThrough
                        )
                        if (player.isMayor) {
                            Spacer(modifier = Modifier.width(6.dp))
                            NeoBadge(
                                text = "عمدة القرية",
                                backgroundColor = PaperOchre,
                                textColor = InkBlack,
                                icon = Icons.Default.Star,
                                shadowOffset = 1.dp
                            )
                        }
                    }
                    Text(
                        text = if (player.isAlive) "على قيد الحياة" else "مستبعد من الجلسة",
                        color = if (player.isAlive) InkCharcoal else InkMuted,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // زر العمدة
            Box(
                modifier = Modifier
                    .background(if (player.isMayor) PaperOchre else PaperSand, shape = RoundedCornerShape(6.dp))
                    .border(1.5.dp, PaperBorder, shape = RoundedCornerShape(6.dp))
                    .clickable { onSetMayor() }
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (player.isMayor) "العمدة" else "تعيين عمدة",
                    color = InkBlack,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
