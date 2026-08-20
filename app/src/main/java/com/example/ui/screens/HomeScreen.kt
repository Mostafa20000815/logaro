package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.example.ui.theme.PaperSand
import com.example.ui.theme.PaperVintagePurple

@Composable
fun HomeScreen(
    onStartNewGame: () -> Unit,
    onOpenRolesGuide: () -> Unit,
    onOpenModeratorGuide: () -> Unit,
    modifier: Modifier = Modifier
) {
    WerewolfBackground(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // قسم الشعار والبانر الرئيسي
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                NeoBadge(
                    text = "لعبة المستذئبون الجماعية",
                    backgroundColor = PaperSand,
                    textColor = InkBlack,
                    shadowOffset = 3.dp
                )

                Spacer(modifier = Modifier.height(16.dp))

                NeoCard(
                    backgroundColor = PaperCard,
                    shadowOffset = 6.dp,
                    borderWidth = 3.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // ختم المونوغرام الكربوني
                        Box(
                            modifier = Modifier
                                .size(88.dp)
                                .background(PaperRustRed, shape = CircleShape)
                                .border(2.5.dp, PaperBorder, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "ذ",
                                color = InkWhite,
                                fontSize = 44.sp,
                                fontWeight = FontWeight.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "لوغارو",
                            style = MaterialTheme.typography.displaySmall.copy(
                                fontWeight = FontWeight.Black,
                                color = InkBlack,
                                letterSpacing = 1.sp
                            )
                        )

                        Text(
                            text = "LOUP-GAROU / WEREWOLF",
                            color = InkMuted,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Box(
                            modifier = Modifier
                                .background(PaperDark, shape = RoundedCornerShape(8.dp))
                                .border(1.5.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "نظام التمرير واللعب لجهاز واحد",
                                color = InkBlack,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // ميزات اللعبة
            NeoCard(
                backgroundColor = PaperCard,
                shadowOffset = 4.dp,
                borderWidth = 2.5.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PaperFeatureRow(
                        icon = Icons.Default.Style,
                        badgeColor = PaperPrussianBlue,
                        title = "بطاقات ثلاثية الأبعاد وسرية",
                        subtitle = "حجب كامل للرؤية لضمان سرية الأدوار"
                    )
                    PaperFeatureRow(
                        icon = Icons.Default.MenuBook,
                        badgeColor = PaperSageGreen(),
                        title = "28 دوراً وتشكيلات مدروسة",
                        subtitle = "عشيرة الذئاب، أهل القرية، والأدوار الخاصة"
                    )
                    PaperFeatureRow(
                        icon = Icons.Default.Nightlight,
                        badgeColor = PaperVintagePurple,
                        title = "إدارة وسيناريو طور الليل",
                        subtitle = "خطوات وأوامر الراوي مرتبة بالأولوية"
                    )
                }
            }

            Spacer(modifier = Modifier.height(22.dp))

            // أزرار العمليات
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                NeoButton(
                    text = "بدء لعبة جديدة",
                    onClick = onStartNewGame,
                    backgroundColor = PaperRustRed,
                    contentColor = InkWhite,
                    icon = Icons.Default.PlayArrow,
                    shadowOffset = 4.dp,
                    testTag = "start_game_button"
                )

                NeoButton(
                    text = "دليل وموسوعة الأدوار",
                    onClick = onOpenRolesGuide,
                    backgroundColor = PaperPrussianBlue,
                    contentColor = InkWhite,
                    icon = Icons.Default.Book,
                    shadowOffset = 4.dp,
                    testTag = "open_roles_guide_button"
                )

                NeoButton(
                    text = "تسيير أطوار الليل للراوي",
                    onClick = onOpenModeratorGuide,
                    backgroundColor = PaperAged,
                    contentColor = InkBlack,
                    icon = Icons.Default.Nightlight,
                    shadowOffset = 4.dp,
                    testTag = "open_moderator_guide_button"
                )
            }
        }
    }
}

@Composable
private fun PaperSageGreen(): Color = Color(0xFF436B4E)

@Composable
private fun PaperFeatureRow(
    icon: ImageVector,
    badgeColor: Color,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(badgeColor, shape = RoundedCornerShape(8.dp))
                .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = InkWhite,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                text = title,
                color = InkBlack,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = subtitle,
                color = InkMuted,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
