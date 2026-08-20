package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import com.example.ui.theme.PaperWarm

/**
 * بطاقة نيو-بروتاليزم ورقية بريميوم (Premium Paper Neo-Card)
 * تتميز بملمس الورق الأرشيفي الفاخر مع حبر كربوني حاد وظل صلب أنيق
 */
@Composable
fun NeoCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = PaperCard,
    borderColor: Color = PaperBorder,
    borderWidth: Dp = 2.5.dp,
    shadowOffset: Dp = 4.dp,
    shadowColor: Color = PaperBorder,
    shape: Shape = RoundedCornerShape(12.dp),
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        // الظل الصلب الحاد بالحبر الكربوني
        if (shadowOffset > 0.dp) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = shadowOffset, y = shadowOffset)
                    .background(shadowColor, shape = shape)
            )
        }

        // السطح الورقي الأمامي
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor, shape = shape)
                .border(borderWidth, borderColor, shape = shape)
                .clip(shape)
        ) {
            content()
        }
    }
}

/**
 * زر ورقي بريميوم نيو-بروتاليزم مع تأثير انضغاط فيزيائي حقيقي
 */
@Composable
fun NeoButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = PaperRustRed,
    contentColor: Color = InkWhite,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    shadowOffset: Dp = 4.dp,
    borderWidth: Dp = 2.5.dp,
    testTag: String = "neo_button"
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val currentOffset = if (isPressed && enabled) 0.dp else shadowOffset

    Box(
        modifier = modifier
            .testTag(testTag)
            .height(54.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            )
    ) {
        // الظل الصلب
        if (enabled && currentOffset > 0.dp) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = shadowOffset, y = shadowOffset)
                    .background(PaperBorder, shape = RoundedCornerShape(12.dp))
            )
        }

        // واجهة الزر مع إزاحة الحركة
        Box(
            modifier = Modifier
                .fillMaxSize()
                .offset(
                    x = if (isPressed && enabled) shadowOffset else 0.dp,
                    y = if (isPressed && enabled) shadowOffset else 0.dp
                )
                .background(
                    color = if (enabled) backgroundColor else PaperDark,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(borderWidth, PaperBorder, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (enabled) contentColor else InkMuted,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    color = if (enabled) contentColor else InkMuted,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.3.sp
                )
            }
        }
    }
}

/**
 * شارة / ختم ورقي أرشيفي بأسلوب بريميوم (بدون أي إيموجي)
 */
@Composable
fun NeoBadge(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = PaperAged,
    textColor: Color = InkBlack,
    icon: ImageVector? = null,
    borderWidth: Dp = 2.dp,
    shadowOffset: Dp = 3.dp
) {
    Box(modifier = modifier) {
        if (shadowOffset > 0.dp) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = shadowOffset, y = shadowOffset)
                    .background(PaperBorder, shape = RoundedCornerShape(8.dp))
            )
        }
        Box(
            modifier = Modifier
                .background(backgroundColor, shape = RoundedCornerShape(8.dp))
                .border(borderWidth, PaperBorder, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = textColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                }
                Text(
                    text = text,
                    color = textColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}

/**
 * خلفية الورق البريميوم العتيق
 */
@Composable
fun WerewolfBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PaperWarm)
    ) {
        content()
    }
}

/**
 * ترويسة رئيسية ورقية أرشيفية
 */
@Composable
fun MysticHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NeoBadge(
            text = title,
            backgroundColor = PaperAged,
            textColor = InkBlack,
            shadowOffset = 3.dp
        )

        if (subtitle != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = subtitle,
                color = InkCharcoal,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * شاشة خصوصية وسرية سحب البطاقة (Privacy Shield) - بدون أي إيموجي
 */
@Composable
fun PrivacyShieldOverlay(
    playerName: String,
    onReadyClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    NeoCard(
        backgroundColor = PaperCard,
        shadowOffset = 6.dp,
        borderWidth = 3.dp,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag("privacy_shield_card")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NeoBadge(
                text = "جلسة سرية - حظر الرؤية",
                backgroundColor = PaperRustRed,
                textColor = InkWhite,
                icon = Icons.Default.Security,
                shadowOffset = 3.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .size(76.dp)
                    .background(PaperDark, shape = CircleShape)
                    .border(2.5.dp, PaperBorder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = InkBlack,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "تسليم الجهاز إلى:",
                color = InkMuted,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            NeoBadge(
                text = playerName,
                backgroundColor = PaperAged,
                textColor = InkBlack,
                shadowOffset = 3.dp,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PaperDark, shape = RoundedCornerShape(10.dp))
                    .border(1.5.dp, PaperBorder, shape = RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Text(
                    text = "تأكد من عدم وجود أي لاعب آخر ينظر إلى الشاشة قبل الضغط على زر الكشف.",
                    color = InkCharcoal,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            NeoButton(
                text = "أنا $playerName - كشف الهوية",
                onClick = onReadyClicked,
                backgroundColor = PaperRustRed,
                contentColor = InkWhite,
                icon = Icons.Default.Visibility,
                shadowOffset = 4.dp,
                testTag = "reveal_role_button"
            )
        }
    }
}
