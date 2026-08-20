package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Role

// ألوان قالب البطاقة الحجرية والذهبية الأسطورية
val StoneDarkOuter = Color(0xFF11141A)
val StoneGreyOuter = Color(0xFF1F242D)
val StoneHighlight = Color(0xFF38404E)
val StonePlaqueDark = Color(0xFF141820)
val StonePlaqueBorder = Color(0xFF2A323F)

val GoldBorderLight = Color(0xFFFDE68A)
val GoldBorderMain = Color(0xFFD97706)
val GoldBorderDark = Color(0xFF78350F)
val GoldBevelHighlight = Color(0xFFFFFBEB)

val GemCyanLight = Color(0xFF7DD3FC)
val GemCyanMain = Color(0xFF0284C7)
val GemCyanDark = Color(0xFF075985)
val GemCyanGlow = Color(0xFF38BDF8)

val ParchmentLight = Color(0xFFFDF6E2)
val ParchmentMain = Color(0xFFF4E5B8)
val ParchmentDark = Color(0xFFD8BF83)
val ParchmentBorder = Color(0xFF6B4210)

/**
 * مكون البطاقة الأسطورية الكاملة مع الإطار الحجري، الجوهرة المضيئة، لفافة العنوان، ولوح الوصف
 */
@Composable
fun MasterStoneRoleCard(
    role: Role,
    modifier: Modifier = Modifier,
    showTips: Boolean = false,
    onCardClick: (() -> Unit)? = null
) {
    val baseModifier = modifier
        .testTag("master_stone_role_card_${role.id}")
        .shadow(16.dp, shape = RoundedCornerShape(24.dp))
        .clip(RoundedCornerShape(24.dp))
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(StoneGreyOuter, StoneDarkOuter)
            )
        )

    val finalModifier = if (onCardClick != null) {
        baseModifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { onCardClick() }
        )
    } else {
        baseModifier
    }

    Box(modifier = finalModifier) {
        // رسم النقوش الحجرية والشقوق الجانبية
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawStoneTextureDetails(size)
        }

        // محتوى البطاقة الداخلي
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // 1. الجزء العلوي: الإطار الذهبي وبداخله صورة/رسمة الشخصية مع الشارات
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.15f)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 8.dp, bottomEnd = 8.dp))
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                role.category.color.copy(alpha = 0.35f),
                                Color(0xFF0B0E14)
                            )
                        )
                    )
                    .border(
                        width = 3.5.dp,
                        brush = Brush.verticalGradient(
                            colors = listOf(GoldBorderLight, GoldBorderMain, GoldBorderDark)
                        ),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 8.dp, bottomEnd = 8.dp)
                    )
            ) {
                // مشهد وخلفية الشخصية
                RoleCharacterArtworkView(role = role)

                // شارات الزوايا (الفئة والأولوية)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // شارة الفئة (عشيرة الذئاب، أهل القرية، ...)
                    Box(
                        modifier = Modifier
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(role.category.color, role.category.color.copy(alpha = 0.85f))
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(1.5.dp, GoldBorderLight.copy(alpha = 0.8f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = role.category.titleArabic,
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // شارة ليل / نهار
                    if (role.hasNightAction) {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF3B1D54), shape = RoundedCornerShape(8.dp))
                                .border(1.5.dp, Color(0xFFC084FC), RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Nightlight,
                                    contentDescription = null,
                                    tint = Color(0xFFE9D5FF),
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "ليل ${role.nightPriority}",
                                    color = Color(0xFFF3E8FF),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF5C3C00), shape = RoundedCornerShape(8.dp))
                                .border(1.5.dp, GoldBorderLight, RoundedCornerShape(8.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.WbSunny,
                                    contentDescription = null,
                                    tint = GoldBorderLight,
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "نهاري",
                                    color = Color(0xFFFFFBEB),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // 2. لفافة العنوان الأسطورية (Parchment Ribbon)
            ParchmentTitleRibbon(
                titleArabic = role.nameArabic,
                subtitleEnglish = role.nameEnglish,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-8).dp)
            )

            // 3. لوح الوصف الحجري (Stone Tablet Description Box)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.95f)
                    .offset(y = (-4).dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(StonePlaqueDark, Color(0xFF0F1217))
                        )
                    )
                    .border(1.5.dp, StonePlaqueBorder, RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = role.description,
                        color = Color(0xFFE2E8F0),
                        fontSize = 11.5.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )

                    if (role.tipsArabic.isNotBlank() && showTips) {
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "💡 ${role.tipsArabic}",
                            color = GoldBorderLight,
                            fontSize = 9.5.sp,
                            lineHeight = 13.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                            maxLines = 2
                        )
                    }
                }
            }
        }

        // 4. الجوهرة الزرقاء في أعلى وسط الإطار (The Glowing Diamond Gem)
        DiamondGemHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-4).dp)
        )
    }
}

/**
 * لفافة العنوان الورقية المطوية على الجانبين
 */
@Composable
private fun ParchmentTitleRibbon(
    titleArabic: String,
    subtitleEnglish: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.height(46.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawParchmentScrollRibbon(size)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.88f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = titleArabic,
                color = Color(0xFF1E1408),
                fontSize = if (titleArabic.length > 14) 13.5.sp else 15.5.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                maxLines = 1
            )
            Text(
                text = subtitleEnglish.uppercase(),
                color = Color(0xFF784C18),
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.8.sp,
                maxLines = 1
            )
        }
    }
}

/**
 * الجوهرة الكريستالية الزرقاء بأعلى البطاقة
 */
@Composable
private fun DiamondGemHeader(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "gem_glow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.7f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "gem_glow_alpha"
    )

    Box(
        modifier = modifier.size(width = 46.dp, height = 34.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val holderPath = Path().apply {
                moveTo(size.width * 0.1f, 0f)
                lineTo(size.width * 0.9f, 0f)
                lineTo(size.width * 0.75f, size.height * 0.6f)
                lineTo(size.width * 0.5f, size.height)
                lineTo(size.width * 0.25f, size.height * 0.6f)
                close()
            }
            drawPath(
                path = holderPath,
                brush = Brush.verticalGradient(
                    colors = listOf(GoldBorderLight, GoldBorderMain, GoldBorderDark)
                )
            )
            drawPath(
                path = holderPath,
                color = GoldBorderDark,
                style = Stroke(width = 2.dp.toPx())
            )

            val gemPath = Path().apply {
                moveTo(size.width * 0.5f, size.height * 0.18f)
                lineTo(size.width * 0.72f, size.height * 0.45f)
                lineTo(size.width * 0.5f, size.height * 0.82f)
                lineTo(size.width * 0.28f, size.height * 0.45f)
                close()
            }

            drawCircle(
                color = GemCyanGlow.copy(alpha = 0.35f * glowAlpha),
                radius = size.width * 0.35f,
                center = Offset(size.width * 0.5f, size.height * 0.48f)
            )

            drawPath(
                path = gemPath,
                brush = Brush.linearGradient(
                    colors = listOf(GemCyanLight, GemCyanMain, GemCyanDark),
                    start = Offset(size.width * 0.3f, size.height * 0.2f),
                    end = Offset(size.width * 0.7f, size.height * 0.8f)
                )
            )

            drawCircle(
                color = Color.White.copy(alpha = glowAlpha),
                radius = 2.dp.toPx(),
                center = Offset(size.width * 0.45f, size.height * 0.38f)
            )
        }
    }
}

/**
 * رسم مشهد وصورة الشخصية داخل الإطار
 */
@Composable
fun RoleCharacterArtworkView(role: Role) {
    RoleCardPhotoView(
        role = role,
        modifier = Modifier.fillMaxSize()
    )
}

/**
 * دالة مساعدة لرسم شقوق وتفاصيل الحجر الواقعية على خلفية البطاقة
 */
private fun DrawScope.drawStoneTextureDetails(size: Size) {
    val stoneLineColor = Color(0x33000000)
    val highlightColor = Color(0x1AFFFFFF)

    drawLine(
        color = stoneLineColor,
        start = Offset(size.width * 0.05f, size.height * 0.15f),
        end = Offset(size.width * 0.08f, size.height * 0.22f),
        strokeWidth = 2.dp.toPx(),
        cap = StrokeCap.Round
    )
    drawLine(
        color = highlightColor,
        start = Offset(size.width * 0.05f + 1f, size.height * 0.15f + 1f),
        end = Offset(size.width * 0.08f + 1f, size.height * 0.22f + 1f),
        strokeWidth = 1.dp.toPx()
    )

    drawLine(
        color = stoneLineColor,
        start = Offset(size.width * 0.94f, size.height * 0.45f),
        end = Offset(size.width * 0.91f, size.height * 0.52f),
        strokeWidth = 2.dp.toPx(),
        cap = StrokeCap.Round
    )
}

/**
 * دالة لرسم لفافة العنوان الورقية مع الالتفافات الجانبية الأسطورية
 */
private fun DrawScope.drawParchmentScrollRibbon(size: Size) {
    val width = size.width
    val height = size.height
    val curlWidth = 24.dp.toPx()

    // 1. الظل خلف اللفافة
    drawRoundRect(
        color = Color(0x88000000),
        topLeft = Offset(curlWidth * 0.5f, 4.dp.toPx()),
        size = Size(width - curlWidth, height - 4.dp.toPx()),
        cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
    )

    // 2. الجوانب الملتفة (Curls)
    val leftCurlPath = Path().apply {
        moveTo(0f, height * 0.3f)
        lineTo(curlWidth, 0f)
        lineTo(curlWidth, height * 0.85f)
        lineTo(0f, height * 0.65f)
        close()
    }
    drawPath(
        path = leftCurlPath,
        brush = Brush.horizontalGradient(
            colors = listOf(ParchmentDark, ParchmentMain)
        )
    )
    drawPath(
        path = leftCurlPath,
        color = ParchmentBorder,
        style = Stroke(width = 1.5.dp.toPx())
    )

    val rightCurlPath = Path().apply {
        moveTo(width, height * 0.3f)
        lineTo(width - curlWidth, 0f)
        lineTo(width - curlWidth, height * 0.85f)
        lineTo(width, height * 0.65f)
        close()
    }
    drawPath(
        path = rightCurlPath,
        brush = Brush.horizontalGradient(
            colors = listOf(ParchmentMain, ParchmentDark)
        )
    )
    drawPath(
        path = rightCurlPath,
        color = ParchmentBorder,
        style = Stroke(width = 1.5.dp.toPx())
    )

    // 3. الجزء الرئيسي الأوسط من اللفافة
    val mainBannerPath = Path().apply {
        moveTo(curlWidth * 0.8f, height * 0.08f)
        lineTo(width - curlWidth * 0.8f, height * 0.08f)
        lineTo(width - curlWidth * 0.8f, height * 0.92f)
        lineTo(curlWidth * 0.8f, height * 0.92f)
        close()
    }

    drawPath(
        path = mainBannerPath,
        brush = Brush.verticalGradient(
            colors = listOf(ParchmentLight, ParchmentMain, ParchmentDark)
        )
    )
    drawPath(
        path = mainBannerPath,
        color = ParchmentBorder,
        style = Stroke(width = 2.dp.toPx())
    )
}

/**
 * ظهر البطاقة الأسطورية المقفلة (Card Back View)
 */
@Composable
fun MasterStoneCardBackView(
    modifier: Modifier = Modifier,
    onCardClick: (() -> Unit)? = null
) {
    val baseModifier = modifier
        .testTag("master_stone_card_back")
        .shadow(16.dp, shape = RoundedCornerShape(24.dp))
        .clip(RoundedCornerShape(24.dp))
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(StoneGreyOuter, StoneDarkOuter)
            )
        )

    val finalModifier = if (onCardClick != null) {
        baseModifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = { onCardClick() }
        )
    } else {
        baseModifier
    }

    Box(modifier = finalModifier) {
        // الإطار الذهبي والرموز السحرية
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(Color(0xFF2C1624), Color(0xFF0D0F14))
                    )
                )
                .border(
                    width = 4.dp,
                    brush = Brush.verticalGradient(
                        colors = listOf(GoldBorderLight, GoldBorderMain, GoldBorderDark)
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // الشعار العلوي
                Box(
                    modifier = Modifier
                        .background(Color(0xFF6B1D2F), shape = RoundedCornerShape(8.dp))
                        .border(1.5.dp, GoldBorderLight, RoundedCornerShape(8.dp))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "وثيقة سرية • قرية الذئاب",
                        color = GoldBorderLight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // الختم المركزي الأسطوري
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(Color(0xFF881337), Color(0xFF4C0519), Color(0xFF1E0A12))
                            ),
                            shape = CircleShape
                        )
                        .border(
                            width = 4.dp,
                            brush = Brush.sweepGradient(
                                colors = listOf(GoldBorderLight, GoldBorderMain, GoldBorderDark, GoldBorderLight)
                            ),
                            shape = CircleShape
                        )
                        .shadow(16.dp, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "🐺",
                            fontSize = 44.sp
                        )
                        Text(
                            text = "ذئاب القرية",
                            color = GoldBorderLight,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }

                // زر / تلميح الكشف بالأسفل
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(ParchmentLight, ParchmentMain)
                            ),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .border(2.dp, ParchmentBorder, RoundedCornerShape(10.dp))
                        .padding(vertical = 10.dp, horizontal = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.TouchApp,
                            contentDescription = null,
                            tint = Color(0xFF3F2005),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "المس لقلب البطاقة وكشف الهوية",
                            color = Color(0xFF2A1503),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }
        }

        // الجوهرة الزرقاء في الأعلى
        DiamondGemHeader(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-4).dp)
        )
    }
}
