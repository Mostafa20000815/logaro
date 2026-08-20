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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.RecordVoiceOver
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.GameState
import com.example.data.model.Role
import com.example.data.model.RolesRegistry
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
fun NightModeratorScreen(
    gameState: GameState,
    onNextStep: () -> Unit,
    onPrevStep: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    val nightRoles = RolesRegistry.NIGHT_ROLES_ORDERED
    val currentStepIndex = gameState.currentNightStepIndex
    val currentRole = nightRoles.getOrNull(currentStepIndex)
    val isLastStep = currentStepIndex >= nightRoles.size - 1

    WerewolfBackground(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // شريط العنوان العلوي
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
                        .clickable { onClose() }
                        .testTag("close_night_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "إغلاق",
                        tint = InkBlack
                    )
                }

                NeoBadge(
                    text = "تسيير الليلة ${gameState.nightNumber} (${currentStepIndex + 1} من ${nightRoles.size})",
                    backgroundColor = PaperVintagePurple,
                    textColor = InkWhite,
                    icon = Icons.Default.Nightlight,
                    shadowOffset = 2.dp
                )

                Box(modifier = Modifier.size(40.dp))
            }

            // بطاقة توجيه الراوي
            if (currentRole != null) {
                NeoCard(
                    backgroundColor = PaperCard,
                    shadowOffset = 4.dp,
                    borderWidth = 2.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // معلومات الدور والأولوية
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            NeoBadge(
                                text = "أولوية الاستيقاظ: ${currentRole.nightPriority}",
                                backgroundColor = currentRole.category.color,
                                textColor = InkWhite,
                                shadowOffset = 2.dp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier
                                    .size(84.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(currentRole.category.color)
                                    .border(2.5.dp, PaperBorder, RoundedCornerShape(16.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                com.example.ui.components.RoleCardPhotoView(
                                    role = currentRole,
                                    enableImagePicker = false,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = currentRole.nameArabic,
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Black,
                                    color = InkBlack
                                ),
                                textAlign = TextAlign.Center
                            )
                        }

                        // تعليمات وسيناريو الراوي
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp)
                                .background(PaperAged, shape = RoundedCornerShape(10.dp))
                                .border(2.dp, PaperBorder, shape = RoundedCornerShape(10.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                NeoBadge(
                                    text = "توجيهات وسرد الراوي:",
                                    backgroundColor = PaperSand,
                                    textColor = InkBlack,
                                    icon = Icons.Default.RecordVoiceOver,
                                    shadowOffset = 1.dp
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = getModeratorSpeechForRole(currentRole, gameState.nightNumber),
                                    color = InkBlack,
                                    fontSize = 13.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // توجيهات وقدرة الدور
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(PaperDark, shape = RoundedCornerShape(8.dp))
                                .border(1.5.dp, PaperBorder, shape = RoundedCornerShape(8.dp))
                                .padding(10.dp)
                        ) {
                            Column {
                                Text(
                                    text = "قدرة الدور في هذه المرحلة:",
                                    color = InkBlack,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Black
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = currentRole.description,
                                    color = InkCharcoal,
                                    fontSize = 11.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            // أزرار التنقل
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                NeoButton(
                    text = "السابق",
                    onClick = onPrevStep,
                    enabled = currentStepIndex > 0,
                    backgroundColor = PaperCard,
                    contentColor = InkBlack,
                    icon = Icons.Default.ArrowForward,
                    modifier = Modifier.weight(1f),
                    shadowOffset = 3.dp,
                    testTag = "prev_night_step_button"
                )

                NeoButton(
                    text = if (isLastStep) "شروق الشمس" else "الدور التالي",
                    onClick = onNextStep,
                    backgroundColor = if (isLastStep) PaperSageGreen else PaperRustRed,
                    contentColor = InkWhite,
                    icon = if (isLastStep) Icons.Default.WbSunny else Icons.Default.ArrowBack,
                    modifier = Modifier.weight(1.4f),
                    shadowOffset = 3.dp,
                    testTag = "next_night_step_button"
                )
            }
        }
    }
}

private fun getModeratorSpeechForRole(role: Role, nightNumber: Int): String {
    return when (role.id) {
        "thief" -> "«تنام القرية بالكامل ويغمض الجميع أعينهم... يستيقظ اللص سراً ليتفحص البطاقتين الاحتياطيتين ويختار ما إن كان يريد تبديل دوره»."
        "savior" -> "«يستيقظ المنقذ بهدوء ويشير بإصبعه نحو اللاعب الذي يريد حمايته من هجوم الذئاب لهذه الليلة»."
        "seer" -> "«يستيقظ الكاشف... أشر إلى أي لاعب في القرية لأكشف لك هويته الحقيقية سراً»."
        "fox" -> "«يستيقظ الثعلب... أشر إلى ثلاثة لاعبين متجاورين لأخبرك بالإشارة إن كان بينهم ذئب واحد على الأقل»."
        "surgeon_father" -> if (nightNumber == 1) "«يستيقظ الدكتور الأب... أشر إلى لاعب واحد ليتحول سراً إلى طبيب»." else "«يستيقظ الدكتور الأب لاستخدام حقنة الشفاء أو حقنة السم»."
        "twins" -> "«تستيقظ الأختان / التوأم ليتعارفا بالعينين فقط دون إصدار أي صوت»."
        "three_brothers" -> "«يستيقظ الإخوة الثلاثة للتعارف وتنسيق خططهم»."
        "lamplighter" -> "«يستيقظ المشعل ليحدد المنزل الذي يريد إضاءته أو حمايته هذه الليلة»."
        "spy" -> "«تستيقظ القرية في سباتها، ويحق للجاسوس استراق النظر بحذر شديد»."
        "werewolf" -> "«تستيقظ عشيرة الذئاب! يفتح جميع الذئاب أعينهم ويتشاورون بالإشارة فقط لاختيار ضحية واحدة لافتراسها الليلة»."
        "big_bad_wolf" -> "«يستيقظ الذئب الشرير الكبير بمفرده لاختيار ضحية إضافية إن لم يمت أي ذئب من قطيعه»."
        "father_wolf" -> "«يستيقظ الذئب الأب... هل تريد استخدام قدرتك لعض ضحية الليلة وتحويلها لذئب بدلاً من قتلها؟»."
        "white_werewolf" -> "«يستيقظ الذئب الأبيض بمفرده ليقرر ما إذا كان يريد اغتيال أحد الذئاب هذه الليلة»."
        "doctor" -> "«يستيقظ الطبيب... أعرض عليه ضحية هجوم الذئاب؛ هل تستخدم حقنة الشفاء لإنقاذه؟ وهل تريد استخدام حقنة السم لتصفية لاعب آخر؟»."
        "crow" -> "«يستيقظ الغراب ليلصق شؤمه على باب أحد اللاعبين ليحصل على صوتين ضده غداً»."
        "gypsy" -> "«يستيقظ الغجري لطلب بطاقة الأرواح وتفعيل حدث بيئي في القرية»."
        else -> "«يستيقظ ${role.nameArabic} لتنفيذ قدرته الخاصة لهذه الليلة في صمت تام»."
    }
}
