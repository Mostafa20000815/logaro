package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.GameState
import com.example.data.model.Role
import com.example.data.model.RoleCategory
import com.example.data.model.RolesRegistry
import com.example.ui.components.GoldBorderDark
import com.example.ui.components.GoldBorderLight
import com.example.ui.components.GoldBorderMain
import com.example.ui.components.MasterStoneRoleCard
import com.example.ui.components.NeoBadge
import com.example.ui.components.NeoButton
import com.example.ui.components.NeoCard
import com.example.ui.components.StoneDarkOuter
import com.example.ui.components.StoneGreyOuter
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
import com.example.ui.theme.PaperWarm

enum class RoleFilter(val titleArabic: String, val emoji: String) {
    ALL("الكل", "🎴"),
    WEREWOLVES("الذئاب", "🐺"),
    VILLAGE("القرية", "👨‍🌾"),
    SOLO("مستقلون", "⚡"),
    SELECTED("المضافة فقط", "⭐")
}

@Composable
fun SetupScreen(
    gameState: GameState,
    onPlayerCountChanged: (Int) -> Unit,
    onPlayerNameChanged: (Int, String) -> Unit,
    onRoleCountChanged: (String, Int) -> Unit,
    onApplyPreset: () -> Unit,
    onStartGame: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditingNames by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf(RoleFilter.ALL) }
    var previewRole by remember { mutableStateOf<Role?>(null) }

    val totalSelectedCards = gameState.selectedRoleCounts.values.sum()
    val isDeckCountMatching = totalSelectedCards == gameState.playerCount

    // إحصائيات التشكيلة الحالية
    val werewolfCardsCount = RolesRegistry.ALL_PLAYABLE_ROLES
        .filter { it.category == RoleCategory.WEREWOLF_CLAN }
        .sumOf { gameState.selectedRoleCounts[it.id] ?: 0 }

    val villageCardsCount = RolesRegistry.ALL_PLAYABLE_ROLES
        .filter { it.category == RoleCategory.VILLAGE }
        .sumOf { gameState.selectedRoleCounts[it.id] ?: 0 }

    val soloCardsCount = RolesRegistry.ALL_PLAYABLE_ROLES
        .filter { it.category == RoleCategory.SOLO }
        .sumOf { gameState.selectedRoleCounts[it.id] ?: 0 }

    // تصفية الأدوار بناءً على الفلتر المختار
    val filteredRoles = remember(selectedFilter, gameState.selectedRoleCounts) {
        when (selectedFilter) {
            RoleFilter.ALL -> RolesRegistry.ALL_PLAYABLE_ROLES
            RoleFilter.WEREWOLVES -> RolesRegistry.ALL_PLAYABLE_ROLES.filter { it.category == RoleCategory.WEREWOLF_CLAN }
            RoleFilter.VILLAGE -> RolesRegistry.ALL_PLAYABLE_ROLES.filter { it.category == RoleCategory.VILLAGE }
            RoleFilter.SOLO -> RolesRegistry.ALL_PLAYABLE_ROLES.filter { it.category == RoleCategory.SOLO }
            RoleFilter.SELECTED -> RolesRegistry.ALL_PLAYABLE_ROLES.filter { (gameState.selectedRoleCounts[it.id] ?: 0) > 0 }
        }
    }

    WerewolfBackground(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // شريط العنوان العلوي
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(PaperCard, shape = RoundedCornerShape(8.dp))
                        .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                        .clickable { onBackToHome() }
                        .testTag("back_to_home_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "رجوع",
                        tint = InkBlack
                    )
                }

                NeoBadge(
                    text = "إعداد الجلسة وتوزيع البطاقات",
                    backgroundColor = PaperAged,
                    textColor = InkBlack,
                    shadowOffset = 2.dp
                )

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(if (isEditingNames) PaperPrussianBlue else PaperCard, shape = RoundedCornerShape(8.dp))
                        .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                        .clickable { isEditingNames = !isEditingNames }
                        .testTag("toggle_names_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isEditingNames) Icons.Default.Style else Icons.Default.People,
                        contentDescription = "الأسماء والبطاقات",
                        tint = if (isEditingNames) InkWhite else InkBlack,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // المحتوى القابل للتمرير
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // 1. شريحة تحديد عدد اللاعبين
                item {
                    NeoCard(
                        backgroundColor = PaperCard,
                        shadowOffset = 3.dp,
                        borderWidth = 2.dp
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "عدد اللاعبين:",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Black,
                                    color = InkBlack
                                )
                                NeoBadge(
                                    text = "${gameState.playerCount} لاعبين",
                                    backgroundColor = PaperSand,
                                    textColor = InkBlack,
                                    shadowOffset = 2.dp
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(PaperDark, shape = RoundedCornerShape(8.dp))
                                        .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                                        .clickable {
                                            if (gameState.playerCount > 4) onPlayerCountChanged(gameState.playerCount - 1)
                                        }
                                        .testTag("decrease_players_button"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(imageVector = Icons.Default.Remove, contentDescription = "إنقاص", tint = InkBlack)
                                }

                                Text(
                                    text = "${gameState.playerCount}",
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Black,
                                    color = InkBlack
                                )

                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(PaperDark, shape = RoundedCornerShape(8.dp))
                                        .border(2.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                                        .clickable {
                                            if (gameState.playerCount < 28) onPlayerCountChanged(gameState.playerCount + 1)
                                        }
                                        .testTag("increase_players_button"),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(imageVector = Icons.Default.Add, contentDescription = "زيادة", tint = InkBlack)
                                }
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            Slider(
                                value = gameState.playerCount.toFloat(),
                                onValueChange = { onPlayerCountChanged(it.toInt()) },
                                valueRange = 4f..28f,
                                steps = 23,
                                colors = SliderDefaults.colors(
                                    thumbColor = PaperRustRed,
                                    activeTrackColor = PaperBorder,
                                    inactiveTrackColor = PaperDark
                                )
                            )
                        }
                    }
                }

                // 2. زر التشكيلة المقترحة الذكية
                item {
                    NeoCard(
                        backgroundColor = PaperSand,
                        shadowOffset = 3.dp,
                        borderWidth = 2.dp,
                        modifier = Modifier.clickable { onApplyPreset() }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .background(PaperCard, shape = CircleShape)
                                        .border(2.dp, PaperBorder, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = InkBlack,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "تطبيق التوزيعة المتوازنة المقترحة",
                                        fontWeight = FontWeight.Black,
                                        fontSize = 13.sp,
                                        color = InkBlack
                                    )
                                    Text(
                                        text = "موصى بها لـ ${gameState.playerCount} لاعبين",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 11.sp,
                                        color = InkMuted
                                    )
                                }
                            }
                            NeoBadge(
                                text = "تطبيق",
                                backgroundColor = PaperPrussianBlue,
                                textColor = InkWhite,
                                shadowOffset = 2.dp
                            )
                        }
                    }
                }

                // 3. شريط مطابقة عدد البطاقات والتوازن الاستراتيجي
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        NeoCard(
                            backgroundColor = if (isDeckCountMatching) PaperSageGreen else PaperRustRed,
                            shadowOffset = 3.dp,
                            borderWidth = 2.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (isDeckCountMatching) Icons.Default.Check else Icons.Default.Info,
                                        contentDescription = null,
                                        tint = InkWhite,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (isDeckCountMatching) "البطاقات تطابق عدد اللاعبين تماماً ($totalSelectedCards / ${gameState.playerCount})"
                                        else "اختر ${gameState.playerCount} بطاقات (المحدد حالياً: $totalSelectedCards)",
                                        fontWeight = FontWeight.Black,
                                        fontSize = 12.sp,
                                        color = InkWhite
                                    )
                                }
                            }
                        }

                        // شريط ملخص العشائر
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color(0xFF881337), RoundedCornerShape(6.dp))
                                    .border(1.dp, PaperBorder, RoundedCornerShape(6.dp))
                                    .padding(vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🐺 الذئاب: $werewolfCardsCount",
                                    color = InkWhite,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color(0xFF1E3A8A), RoundedCornerShape(6.dp))
                                    .border(1.dp, PaperBorder, RoundedCornerShape(6.dp))
                                    .padding(vertical = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "👨‍🌾 القرية: $villageCardsCount",
                                    color = InkWhite,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            if (soloCardsCount > 0) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(Color(0xFF78350F), RoundedCornerShape(6.dp))
                                        .border(1.dp, PaperBorder, RoundedCornerShape(6.dp))
                                        .padding(vertical = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "⚡ مستقل: $soloCardsCount",
                                        color = InkWhite,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                // 4. المحتوى: تعديل الأسماء أو قائمة البطاقات المعاد تصميمها
                if (isEditingNames) {
                    item {
                        NeoBadge(
                            text = "تعديل أسماء اللاعبين:",
                            backgroundColor = PaperAged,
                            textColor = InkBlack,
                            icon = Icons.Default.Edit,
                            shadowOffset = 2.dp
                        )
                    }

                    items(gameState.playerNames.size) { index ->
                        NeoCard(
                            backgroundColor = PaperCard,
                            shadowOffset = 2.dp,
                            borderWidth = 2.dp
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                NeoBadge(
                                    text = "${index + 1}",
                                    backgroundColor = PaperSand,
                                    textColor = InkBlack,
                                    shadowOffset = 1.dp
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                OutlinedTextField(
                                    value = gameState.playerNames[index],
                                    onValueChange = { onPlayerNameChanged(index, it) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("player_name_input_$index"),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = PaperBorder,
                                        unfocusedBorderColor = PaperDark,
                                        focusedTextColor = InkBlack,
                                        unfocusedTextColor = InkBlack,
                                        focusedContainerColor = PaperCard,
                                        unfocusedContainerColor = PaperCard
                                    ),
                                    shape = RoundedCornerShape(6.dp)
                                )
                            }
                        }
                    }
                } else {
                    // شريط فلاتر الأدوار السريعة
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            RoleFilter.values().forEach { filter ->
                                val isSelected = selectedFilter == filter
                                Box(
                                    modifier = Modifier
                                        .background(
                                            if (isSelected) PaperPrussianBlue else PaperCard,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .border(
                                            if (isSelected) 2.dp else 1.5.dp,
                                            if (isSelected) PaperBorder else PaperDark,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .clickable { selectedFilter = filter }
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = filter.emoji,
                                            fontSize = 12.sp
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = filter.titleArabic,
                                            color = if (isSelected) InkWhite else InkBlack,
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // بطاقات الأدوار المصممة بأناقة وفخامة
                    items(filteredRoles, key = { it.id }) { role ->
                        val count = gameState.selectedRoleCounts[role.id] ?: 0
                        RoleSetupCardItem(
                            role = role,
                            count = count,
                            onIncrement = {
                                if (count < role.maxCardsInDeck) onRoleCountChanged(role.id, count + 1)
                            },
                            onDecrement = {
                                if (count > 0) onRoleCountChanged(role.id, count - 1)
                            },
                            onCardThumbnailClick = {
                                previewRole = role
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // زر بدء التوزيع
            NeoButton(
                text = "بدء توزيع البطاقات بالتمرير",
                onClick = onStartGame,
                enabled = isDeckCountMatching,
                backgroundColor = PaperRustRed,
                contentColor = InkWhite,
                icon = Icons.Default.PlayArrow,
                shadowOffset = 4.dp,
                testTag = "start_pass_and_play_button"
            )
        }

        // نافذة معاينة البطاقة الحجرية المكبرة
        previewRole?.let { role ->
            Dialog(onDismissRequest = { previewRole = null }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .aspectRatio(0.66f)
                        .shadow(24.dp, RoundedCornerShape(24.dp)),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        MasterStoneRoleCard(
                            role = role,
                            showTips = true,
                            modifier = Modifier.fillMaxSize(),
                            onCardClick = { previewRole = null }
                        )

                        // زر إغلاق المعاينة بالأعلى
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(12.dp)
                                .size(32.dp)
                                .background(Color(0xCC000000), shape = CircleShape)
                                .border(1.5.dp, GoldBorderLight, CircleShape)
                                .clickable { previewRole = null },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "إغلاق",
                                tint = GoldBorderLight,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * تصميم عنصر الدور الأنيق والفخم في شاشة الإعداد مع صورة البطاقة واسمها وعداد بطاقاتها
 */
@Composable
fun RoleSetupCardItem(
    role: Role,
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onCardThumbnailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isSelected = count > 0
    val isMaxReached = count >= role.maxCardsInDeck

    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(if (isSelected) 6.dp else 2.dp, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .background(
                brush = if (isSelected) {
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF222834),
                            Color(0xFF181D26)
                        )
                    )
                } else {
                    Brush.horizontalGradient(
                        colors = listOf(
                            PaperCard,
                            PaperWarm
                        )
                    )
                }
            )
            .border(
                width = if (isSelected) 2.5.dp else 1.5.dp,
                brush = if (isSelected) {
                    Brush.horizontalGradient(
                        listOf(GoldBorderLight, role.category.color, GoldBorderMain)
                    )
                } else {
                    Brush.horizontalGradient(
                        listOf(PaperBorder, PaperDark)
                    )
                },
                shape = RoundedCornerShape(14.dp)
            )
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. صورة / مصغّر البطاقة الأسطورية (قابلة للنقر للمعاينة)
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1E242E),
                                Color(0xFF0F131A)
                            )
                        )
                    )
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(GoldBorderLight, GoldBorderMain, GoldBorderDark)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { onCardThumbnailClick() }
                    .testTag("preview_role_${role.id}"),
                contentAlignment = Alignment.Center
            ) {
                // هالة داخلية
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(role.category.color.copy(alpha = 0.45f), Color.Transparent)
                            )
                        )
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = role.displayEmoji,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Box(
                        modifier = Modifier
                            .background(Color(0xCC000000), RoundedCornerShape(3.dp))
                            .padding(horizontal = 4.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = "تكبير 🔍",
                            color = GoldBorderLight,
                            fontSize = 7.5.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            // 2. معلومات الدور: الاسم، العشيرة، التوقيت، والشرح
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 6.dp)
            ) {
                // شريط الاسم والوسوم
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = role.nameArabic,
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp,
                        color = if (isSelected) InkWhite else InkBlack
                    )

                    // وسم الليل أو النهار
                    if (role.hasNightAction) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF3B1D54), RoundedCornerShape(4.dp))
                                .border(1.dp, Color(0xFFC084FC), RoundedCornerShape(4.dp))
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Nightlight,
                                    contentDescription = null,
                                    tint = Color(0xFFE9D5FF),
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "أولوية ${role.nightPriority}",
                                    color = Color(0xFFF3E8FF),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF5C3C00), RoundedCornerShape(4.dp))
                                .border(1.dp, GoldBorderLight, RoundedCornerShape(4.dp))
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.WbSunny,
                                    contentDescription = null,
                                    tint = GoldBorderLight,
                                    modifier = Modifier.size(10.dp)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Text(
                                    text = "نهاري",
                                    color = Color(0xFFFFFBEB),
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                // تصنيف العشيرة + الحد المسموح
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = role.category.titleArabic,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = role.category.color
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "• الحد الأقصى: ${role.maxCardsInDeck}",
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isSelected) Color(0xFF94A3B8) else InkMuted
                    )
                }

                // وصف مختصر للقدرة
                Text(
                    text = role.description,
                    fontSize = 10.5.sp,
                    lineHeight = 14.sp,
                    maxLines = 2,
                    color = if (isSelected) Color(0xFFCBD5E1) else InkCharcoal
                )
            }

            // 3. عداد البطاقات والتحكم التفاعلي بالحدود
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // زر الإنقاص (-)
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .shadow(2.dp, RoundedCornerShape(8.dp))
                            .background(
                                if (count > 0) PaperRustRed else Color(0xFF475569),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(1.5.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                            .clickable(enabled = count > 0) { onDecrement() }
                            .testTag("dec_role_${role.id}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "إنقاص",
                            tint = if (count > 0) InkWhite else Color(0xFF94A3B8),
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // صندوق عرض العداد والحد
                    Box(
                        modifier = Modifier
                            .width(52.dp)
                            .height(34.dp)
                            .shadow(2.dp, RoundedCornerShape(8.dp))
                            .background(
                                if (isSelected) Color(0xFF0F172A) else PaperSand,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                1.5.dp,
                                if (isMaxReached) GoldBorderMain else PaperBorder,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "$count / ${role.maxCardsInDeck}",
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp,
                                color = if (isSelected) GoldBorderLight else InkBlack
                            )
                        }
                    }

                    // زر الزيادة (+)
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .shadow(2.dp, RoundedCornerShape(8.dp))
                            .background(
                                if (!isMaxReached) PaperSageGreen else Color(0xFF475569),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(1.5.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                            .clickable(enabled = !isMaxReached) { onIncrement() }
                            .testTag("inc_role_${role.id}"),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "زيادة",
                            tint = if (!isMaxReached) InkWhite else Color(0xFF94A3B8),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                // تنبيه الحد الأقصى أو الإضافة
                if (isMaxReached && count > 0) {
                    Spacer(modifier = Modifier.height(3.dp))
                    Box(
                        modifier = Modifier
                            .background(Color(0xFF78350F), RoundedCornerShape(4.dp))
                            .padding(horizontal = 4.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = "الحد مكتمل 🔒",
                            color = GoldBorderLight,
                            fontSize = 8.5.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else if (isSelected) {
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = "مضاف (${count})",
                        color = PaperSageGreen,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
