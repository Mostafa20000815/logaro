package com.example.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Role

// ألوان لوحة العرض
val DarkCardBg = Color(0xFF0D1117)

/**
 * محرك الفن البصري الحقيقي لبطاقات وميمات اللعبة الـ 28 المرفقة
 */
@Composable
fun RoleVectorArtwork(
    role: Role,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ArtworkAnim")
    val pulseGlow by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "GlowPulse"
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 1. المشهد الخلفي الأسطوري
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawAtmosphericScene(role, pulseGlow)
        }

        // 2. المحتوى الفني للبطاقة
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize().padding(6.dp)
        ) {
            // إطار العمل الفني للشخصية / الميم
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF090D14))
                    .border(
                        width = 1.5.dp,
                        brush = Brush.linearGradient(
                            listOf(
                                GoldBorderLight.copy(alpha = 0.9f),
                                GoldBorderMain.copy(alpha = 0.5f),
                                GoldBorderDark.copy(alpha = 0.8f)
                            )
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                // تجسيد الميم / الشخصية الحقيقية برسم هندسي وفني متقن
                RoleMemeVisualCanvas(roleId = role.id)
            }

            Spacer(modifier = Modifier.height(4.dp))

            // شريط الميم والشعار التوضيحي
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xDD0B0F19),
                                Color(0xFF1E140A),
                                Color(0xDD0B0F19)
                            )
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .border(
                        width = 0.8.dp,
                        brush = Brush.horizontalGradient(
                            listOf(Color.Transparent, GoldBorderLight.copy(alpha = 0.8f), Color.Transparent)
                        ),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(horizontal = 4.dp, vertical = 2.5.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = getRoleMemeQuote(role.id),
                    color = GoldBorderLight,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

/**
 * التجسيد البصري والرسومي لكل صورة من الصور الـ 28 المرفقة
 */
@Composable
fun RoleMemeVisualCanvas(
    roleId: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (roleId) {
            "scapegoat" -> ScapegoatMemeArtwork()
            "judge" -> JudgeArtwork()
            "crow" -> CrowMemeArtwork()
            "wild_child" -> WildChildArtwork()
            "hunter" -> HunterGeorgeMemeArtwork()
            "bear_tamer" -> DancingBearsArtwork()
            "surgeon_father", "surgeon" -> DoctorAliVefaArtwork()
            "spy" -> PerryThePlatypusArtwork()
            "fox" -> FoxDetectiveArtwork()
            "fool_innocent" -> CuteLorisArtwork()
            "three_brothers" -> ThreeCockroachesArtwork()
            "village_idiot" -> BuffedRichardArtwork()
            "villager" -> AngryVillagersArtwork()
            "werewolf" -> PensiveWolfArtwork()
            "thief" -> RobberyBobArtwork()
            "pure_villager" -> MinecraftVillagerArtwork()
            "doctor" -> ClassicDoctorArtwork()
            "white_werewolf" -> WhiteWerewolfMoonArtwork()
            "big_bad_wolf" -> BigBadWolfPackArtwork()
            "seer" -> SecurityScannerArtwork()
            "elder" -> ArabElderSheikhArtwork()
            "rusty_knight" -> PussInBootsArtwork()
            "lamplighter" -> CjOnFireArtwork()
            "twins" -> AfricanTwinsArtwork()
            "blood_brothers" -> ScoobyAndShaggyArtwork()
            "actor" -> ActorTyreseArtwork()
            "father_wolf" -> FatherWolfRoseArtwork()
            else -> GenericRoleArt(roleId)
        }
    }
}

// =========================================================================
// 1. كبش الفداء (خروف لابس كاب ونظارات شمسية وميم "يارب محد يعرفني")
// =========================================================================
@Composable
fun ScapegoatMemeArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية دافئة بلون الصوف والسيارة
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF2C3E50), Color(0xFF1A1A1A))
                )
            )

            // جسم الخروف الصوفي الكثيف
            val woolColor = Color(0xFFE8DCC4)
            val woolShadow = Color(0xFFC7B696)
            drawCircle(woolShadow, radius = w * 0.42f, center = Offset(w * 0.5f, h * 0.65f))
            drawCircle(woolColor, radius = w * 0.38f, center = Offset(w * 0.5f, h * 0.63f))

            // تموجات الصوف
            for (i in 0..7) {
                val angle = (i * 45) * (Math.PI / 180).toFloat()
                val cx = w * 0.5f + (w * 0.32f) * kotlin.math.cos(angle)
                val cy = h * 0.62f + (h * 0.28f) * kotlin.math.sin(angle)
                drawCircle(woolColor, radius = w * 0.12f, center = Offset(cx, cy))
            }

            // أنف وفم الخروف الوردي الفاتح
            drawOval(
                color = Color(0xFFD4A381),
                topLeft = Offset(w * 0.38f, h * 0.52f),
                size = Size(w * 0.24f, h * 0.22f)
            )
            // فتحتا الأنف
            drawCircle(Color(0xFF6B4226), radius = 3.dp.toPx(), center = Offset(w * 0.45f, h * 0.66f))
            drawCircle(Color(0xFF6B4226), radius = 3.dp.toPx(), center = Offset(w * 0.55f, h * 0.66f))

            // كاب جينز كحلي (Baseball Cap)
            val capColor = Color(0xFF1E3A5F)
            val capPath = Path().apply {
                moveTo(w * 0.2f, h * 0.32f)
                cubicTo(w * 0.25f, h * 0.12f, w * 0.75f, h * 0.12f, w * 0.8f, h * 0.32f)
                close()
            }
            drawPath(capPath, capColor)
            // حافة الكاب البارزة
            val visorPath = Path().apply {
                moveTo(w * 0.15f, h * 0.32f)
                quadraticBezierTo(w * 0.5f, h * 0.38f, w * 0.88f, h * 0.34f)
                quadraticBezierTo(w * 0.5f, h * 0.30f, w * 0.15f, h * 0.32f)
                close()
            }
            drawPath(visorPath, Color(0xFF152942))
            // زر أعلى الكاب وشعار
            drawCircle(Color(0xFFE2E8F0), radius = 4.dp.toPx(), center = Offset(w * 0.5f, h * 0.17f))

            // نظارات شمسية سوداء أنيقة
            val glassColor = Color(0xFF0F172A)
            drawRoundRect(
                color = glassColor,
                topLeft = Offset(w * 0.24f, h * 0.38f),
                size = Size(w * 0.22f, h * 0.14f),
                cornerRadius = CornerRadius(12.dp.toPx())
            )
            drawRoundRect(
                color = glassColor,
                topLeft = Offset(w * 0.54f, h * 0.38f),
                size = Size(w * 0.22f, h * 0.14f),
                cornerRadius = CornerRadius(12.dp.toPx())
            )
            // جسر النظارة
            drawLine(
                color = glassColor,
                start = Offset(w * 0.44f, h * 0.42f),
                end = Offset(w * 0.56f, h * 0.42f),
                strokeWidth = 3.dp.toPx()
            )
            // لمعة النظارة البيضاء
            drawLine(
                color = Color.White.copy(alpha = 0.6f),
                start = Offset(w * 0.28f, h * 0.40f),
                end = Offset(w * 0.34f, h * 0.48f),
                strokeWidth = 2.dp.toPx()
            )
            drawLine(
                color = Color.White.copy(alpha = 0.6f),
                start = Offset(w * 0.58f, h * 0.40f),
                end = Offset(w * 0.64f, h * 0.48f),
                strokeWidth = 2.dp.toPx()
            )
        }

        // فقاعة الكلام الشهيرة: "يارب محد يعرفني"
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = 6.dp, y = (-8).dp)
                .background(Color.White, RoundedCornerShape(10.dp))
                .border(1.5.dp, Color(0xFF1E293B), RoundedCornerShape(10.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = "يارب محد يعرفني",
                color = Color.Black,
                fontSize = 9.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }

        // إيموجي الضحك الصغير في الزاوية
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-6).dp, y = (-8).dp)
                .size(24.dp)
                .background(Color(0xFFFFD43B), CircleShape)
                .border(1.dp, Color(0xFFCA8A04), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("😂", fontSize = 13.sp)
        }
    }
}

// =========================================================================
// 2. القاضي (قاضي المحكمة الدولية للأمم المتحدة بمطرقة العدل)
// =========================================================================
@Composable
fun JudgeArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية خشبية جدارية للمحكمة
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF5C2C16), Color(0xFF2E1307))
                )
            )

            // شعار الأمم المتحدة الدائري الأبيض في الخلفية
            drawCircle(
                color = Color.White.copy(alpha = 0.25f),
                radius = w * 0.32f,
                center = Offset(w * 0.5f, h * 0.28f),
                style = Stroke(width = 2.dp.toPx())
            )
            drawCircle(
                color = Color.White.copy(alpha = 0.15f),
                radius = w * 0.22f,
                center = Offset(w * 0.5f, h * 0.28f),
                style = Stroke(width = 1.5.dp.toPx())
            )

            // جسم القاضي في الرداء القضائي الأسود
            val robeColor = Color(0xFF111827)
            val robePath = Path().apply {
                moveTo(w * 0.2f, h * 0.95f)
                lineTo(w * 0.32f, h * 0.52f)
                lineTo(w * 0.68f, h * 0.52f)
                lineTo(w * 0.8f, h * 0.95f)
                close()
            }
            drawPath(robePath, robeColor)

            // قميص أبيض وربطة عنق
            drawPath(
                Path().apply {
                    moveTo(w * 0.44f, h * 0.52f)
                    lineTo(w * 0.5f, h * 0.65f)
                    lineTo(w * 0.56f, h * 0.52f)
                    close()
                },
                Color.White
            )
            // ربطة عنق زرقاء
            drawRect(
                color = Color(0xFF1D4ED8),
                topLeft = Offset(w * 0.47f, h * 0.56f),
                size = Size(w * 0.06f, h * 0.12f)
            )

            // رأس القاضي الأسمر الوقور
            val skinTone = Color(0xFF5A3825)
            drawCircle(skinTone, radius = w * 0.14f, center = Offset(w * 0.5f, h * 0.42f))

            // منصة القضاة الخشبية في الأسفل
            drawRect(
                brush = Brush.verticalGradient(listOf(Color(0xFF8B4513), Color(0xFF4A2508))),
                topLeft = Offset(0f, h * 0.78f),
                size = Size(w, h * 0.22f)
            )

            // مطرقة العدالة الخشبية (Gavel)
            val gavelColor = Color(0xFFD97706)
            // مقبض المطرقة
            drawLine(
                color = gavelColor,
                start = Offset(w * 0.18f, h * 0.82f),
                end = Offset(w * 0.28f, h * 0.64f),
                strokeWidth = 3.5.dp.toPx(),
                cap = StrokeCap.Round
            )
            // رأس المطرقة الأسطواني
            drawRoundRect(
                color = Color(0xFFB45309),
                topLeft = Offset(w * 0.22f, h * 0.60f),
                size = Size(w * 0.14f, h * 0.07f),
                cornerRadius = CornerRadius(4.dp.toPx())
            )
        }
    }
}

// =========================================================================
// 3. الغراب (رجل برأس غراب أسود وتيشيرت أسود عليه "مستغرب")
// =========================================================================
@Composable
fun CrowMemeArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية نافذة زجاجية ومكتب حديث
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF334155), Color(0xFF0F172A))
                )
            )

            // جسم بقميص أسود
            drawRect(
                color = Color(0xFF090D14),
                topLeft = Offset(w * 0.15f, h * 0.62f),
                size = Size(w * 0.7f, h * 0.38f)
            )

            // رأس وعنق الغراب الأسود الواقعي
            val crowBlack = Color(0xFF0F172A)
            drawOval(
                color = crowBlack,
                topLeft = Offset(w * 0.28f, h * 0.22f),
                size = Size(w * 0.44f, h * 0.44f)
            )

            // منقار الغراب الأسود اللامع
            val beakPath = Path().apply {
                moveTo(w * 0.55f, h * 0.36f)
                lineTo(w * 0.85f, h * 0.42f)
                lineTo(w * 0.55f, h * 0.46f)
                close()
            }
            drawPath(beakPath, Color(0xFF1E293B))
            // لمعة المنقار
            drawLine(
                color = Color.White.copy(alpha = 0.5f),
                start = Offset(w * 0.56f, h * 0.38f),
                end = Offset(w * 0.80f, h * 0.42f),
                strokeWidth = 2.dp.toPx()
            )

            // عين الغراب اليقظة
            drawCircle(Color(0xFFE2E8F0), radius = 4.dp.toPx(), center = Offset(w * 0.50f, h * 0.38f))
            drawCircle(Color.Black, radius = 2.5.dp.toPx(), center = Offset(w * 0.50f, h * 0.38f))
        }

        // كتابة "مستغرب" بالخط الأبيض العريض على التيشيرت
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-14).dp)
                .background(Color(0xEE090D14), RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(
                text = "مستغرب",
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}

// =========================================================================
// 4. الطفل المتوحش (عدنان من كرتون عدنان ولينا يركض بقوة)
// =========================================================================
@Composable
fun WildChildArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية سماء وبحر زرقاء
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF1E40AF), Color(0xFF0284C7))
                )
            )

            // شعر عدنان الكثيف الشوكي الأسود
            val hairColor = Color(0xFF111827)
            val hairPath = Path().apply {
                moveTo(w * 0.2f, h * 0.45f)
                lineTo(w * 0.15f, h * 0.25f)
                lineTo(w * 0.35f, h * 0.12f)
                lineTo(w * 0.65f, h * 0.10f)
                lineTo(w * 0.85f, h * 0.22f)
                lineTo(w * 0.92f, h * 0.48f)
                lineTo(w * 0.75f, h * 0.48f)
                close()
            }
            drawPath(hairPath, hairColor)

            // ذيل الشعر الخلفي المتطاير
            drawCircle(hairColor, radius = w * 0.18f, center = Offset(w * 0.82f, h * 0.46f))

            // وجه عدنان البرونزي
            val skinColor = Color(0xFFC68656)
            drawCircle(skinColor, radius = w * 0.20f, center = Offset(w * 0.48f, h * 0.40f))

            // عين أنمي معبرة
            drawCircle(Color.White, radius = w * 0.05f, center = Offset(w * 0.52f, h * 0.36f))
            drawCircle(Color(0xFF1F2937), radius = w * 0.035f, center = Offset(w * 0.53f, h * 0.36f))

            // فم مفتوح يدل على الحماس والركض
            drawArc(
                color = Color(0xFFDC2626),
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.42f, h * 0.42f),
                size = Size(w * 0.08f, h * 0.06f)
            )

            // جسم عدنان والذراع أثناء الركض
            drawRect(
                color = skinColor,
                topLeft = Offset(w * 0.34f, h * 0.56f),
                size = Size(w * 0.34f, h * 0.30f)
            )

            // الشورت / الإزار الأصفر الكرتوني الشهير
            drawRect(
                color = Color(0xFFFACC15),
                topLeft = Offset(w * 0.36f, h * 0.84f),
                size = Size(w * 0.34f, h * 0.16f)
            )
        }
    }
}

// =========================================================================
// 5. الصياد (ميم "إنه فخ يا جورج لا تتسرع")
// =========================================================================
@Composable
fun HunterGeorgeMemeArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // بحر وضباب
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF94A3B8), Color(0xFF475569))
                )
            )

            // شعر مجدل دريدلوكس ولحية جورج البنية الكثيفة
            val hairColor = Color(0xFF78350F)
            drawCircle(hairColor, radius = w * 0.28f, center = Offset(w * 0.48f, h * 0.38f))

            // وجه جورج
            drawCircle(Color(0xFFD97706), radius = w * 0.18f, center = Offset(w * 0.48f, h * 0.42f))

            // نظارات شمسية بيضاء/سوداء شهيرة
            drawRoundRect(
                color = Color.White,
                topLeft = Offset(w * 0.34f, h * 0.34f),
                size = Size(w * 0.30f, h * 0.12f),
                cornerRadius = CornerRadius(6.dp.toPx())
            )
            drawRoundRect(
                color = Color.Black,
                topLeft = Offset(w * 0.36f, h * 0.36f),
                size = Size(w * 0.11f, h * 0.08f),
                cornerRadius = CornerRadius(4.dp.toPx())
            )
            drawRoundRect(
                color = Color.Black,
                topLeft = Offset(w * 0.51f, h * 0.36f),
                size = Size(w * 0.11f, h * 0.08f),
                cornerRadius = CornerRadius(4.dp.toPx())
            )

            // قميص تركواز / أزرق سماوي
            drawRect(
                color = Color(0xFF06B6D4),
                topLeft = Offset(w * 0.20f, h * 0.60f),
                size = Size(w * 0.60f, h * 0.40f)
            )

            // قبضة اليد المرفوعة للتحذير
            drawCircle(Color(0xFFD97706), radius = w * 0.10f, center = Offset(w * 0.22f, h * 0.55f))
        }

        // شريط الترجمة الشهير: "إنه فخ يا جورج لا تتسرع"
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-6).dp)
                .background(Color(0xDD000000), RoundedCornerShape(4.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = "إنه فخ يا جورج لا تتسرع",
                color = Color(0xFFFDE047),
                fontSize = 8.5.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}

// =========================================================================
// 6. مروض الدب (الدببة الراقصة 3D)
// =========================================================================
@Composable
fun DancingBearsArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // أرضية ثلاثية الأبعاد خضراء وزرقاء
            drawRect(
                brush = Brush.radialGradient(
                    listOf(Color(0xFF22C55E), Color(0xFF0284C7), Color(0xFF0F172A))
                )
            )

            // 4 دببة ذهبية راقصة
            val bearColor = Color(0xFFD97706)
            val bearDark = Color(0xFFB45309)

            // الدب المركزي
            drawCircle(bearColor, radius = w * 0.14f, center = Offset(w * 0.5f, h * 0.45f))
            drawCircle(bearDark, radius = w * 0.08f, center = Offset(w * 0.5f, h * 0.32f))
            drawCircle(bearDark, radius = w * 0.03f, center = Offset(w * 0.44f, h * 0.28f))
            drawCircle(bearDark, radius = w * 0.03f, center = Offset(w * 0.56f, h * 0.28f))

            // الدب الأيسر
            drawCircle(bearColor, radius = w * 0.12f, center = Offset(w * 0.22f, h * 0.50f))
            drawCircle(bearDark, radius = w * 0.06f, center = Offset(w * 0.22f, h * 0.38f))

            // الدب الأيمن
            drawCircle(bearColor, radius = w * 0.12f, center = Offset(w * 0.78f, h * 0.50f))
            drawCircle(bearDark, radius = w * 0.06f, center = Offset(w * 0.78f, h * 0.38f))
        }
    }
}

// =========================================================================
// 7. الجراح / د. علي وفاء (الطبيب المعجزة بسماعة الطبيب ومصعد المستشفى)
// =========================================================================
@Composable
fun DoctorAliVefaArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية المصعد الفولاذية والخشبية
            drawRect(
                brush = Brush.horizontalGradient(
                    listOf(Color(0xFF475569), Color(0xFF64748B), Color(0xFF78350F))
                )
            )

            // شعر أسود مجعد مميز
            val hairColor = Color(0xFF1E293B)
            drawCircle(hairColor, radius = w * 0.26f, center = Offset(w * 0.48f, h * 0.32f))

            // وجه د. علي وفاء الشاب
            val skinTone = Color(0xFFF5D0B5)
            drawCircle(skinTone, radius = w * 0.18f, center = Offset(w * 0.48f, h * 0.38f))

            // مريول الطبيب الأبيض
            drawRect(
                color = Color.White,
                topLeft = Offset(w * 0.20f, h * 0.56f),
                size = Size(w * 0.60f, h * 0.44f)
            )
            // بلوزة العمليات الزرقاء الداخلية
            drawPath(
                Path().apply {
                    moveTo(w * 0.42f, h * 0.56f)
                    lineTo(w * 0.48f, h * 0.74f)
                    lineTo(w * 0.54f, h * 0.56f)
                    close()
                },
                Color(0xFF38BDF8)
            )

            // سماعة الطبيب (Stethoscope)
            drawArc(
                color = Color(0xFF0F172A),
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(w * 0.32f, h * 0.50f),
                size = Size(w * 0.32f, h * 0.32f),
                style = Stroke(width = 3.dp.toPx())
            )
            // رأس السماعة المعدني
            drawCircle(Color(0xFF94A3B8), radius = 5.dp.toPx(), center = Offset(w * 0.48f, h * 0.82f))
        }
    }
}

// =========================================================================
// 8. الجاسوس (بيري الخلد العميل السري بقبعته الشهيرة)
// =========================================================================
@Composable
fun PerryThePlatypusArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية الشارع والمبنى من الكرتون
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF7DD3FC), Color(0xFFB91C1C))
                )
            )

            // جسم بيري الفيروزي / التركواز
            val perryTeal = Color(0xFF0D9488)
            drawRoundRect(
                color = perryTeal,
                topLeft = Offset(w * 0.25f, h * 0.32f),
                size = Size(w * 0.50f, h * 0.64f),
                cornerRadius = CornerRadius(24.dp.toPx())
            )

            // قبعة العميل السري البنية (Fedora)
            val hatBrown = Color(0xFF92400E)
            // حافة القبعة
            drawOval(
                color = hatBrown,
                topLeft = Offset(w * 0.15f, h * 0.26f),
                size = Size(w * 0.70f, h * 0.14f)
            )
            // تاج القبعة
            drawRoundRect(
                color = Color(0xFF78350F),
                topLeft = Offset(w * 0.28f, h * 0.14f),
                size = Size(w * 0.44f, h * 0.18f),
                cornerRadius = CornerRadius(8.dp.toPx())
            )
            // شريط القبعة الأسود
            drawRect(
                color = Color.Black,
                topLeft = Offset(w * 0.28f, h * 0.26f),
                size = Size(w * 0.44f, h * 0.04f)
            )

            // منقار بيري البرتقالي العريض
            val billOrange = Color(0xFFF97316)
            drawRoundRect(
                color = billOrange,
                topLeft = Offset(w * 0.28f, h * 0.42f),
                size = Size(w * 0.44f, h * 0.14f),
                cornerRadius = CornerRadius(14.dp.toPx())
            )

            // عينا بيري المستديرتان
            drawCircle(Color.White, radius = w * 0.06f, center = Offset(w * 0.40f, h * 0.38f))
            drawCircle(Color.Black, radius = w * 0.035f, center = Offset(w * 0.42f, h * 0.38f))

            drawCircle(Color.White, radius = w * 0.06f, center = Offset(w * 0.60f, h * 0.38f))
            drawCircle(Color.Black, radius = w * 0.035f, center = Offset(w * 0.58f, h * 0.38f))
        }
    }
}

// =========================================================================
// 9. الثعلب (ثعلب شيرلوك هولمز الأنيق بالمعطف والغليون)
// =========================================================================
@Composable
fun FoxDetectiveArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية غامضة خضراء
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF14532D), Color(0xFF052E16))
                )
            )

            // رأس الثعلب البرتقالي
            val foxOrange = Color(0xFFEA580C)
            val headPath = Path().apply {
                moveTo(w * 0.30f, h * 0.25f)
                lineTo(w * 0.70f, h * 0.25f)
                lineTo(w * 0.85f, h * 0.42f)
                lineTo(w * 0.50f, h * 0.58f)
                lineTo(w * 0.15f, h * 0.42f)
                close()
            }
            drawPath(headPath, foxOrange)

            // فراء أبيض على الخدين
            drawCircle(Color.White, radius = w * 0.10f, center = Offset(w * 0.36f, h * 0.48f))
            drawCircle(Color.White, radius = w * 0.10f, center = Offset(w * 0.64f, h * 0.48f))

            // قبعة المحقق الصوفية
            drawRoundRect(
                color = Color(0xFF78350F),
                topLeft = Offset(w * 0.28f, h * 0.16f),
                size = Size(w * 0.44f, h * 0.16f),
                cornerRadius = CornerRadius(10.dp.toPx())
            )

            // سترة المعطف البني
            drawRect(
                color = Color(0xFF9A3412),
                topLeft = Offset(w * 0.22f, h * 0.58f),
                size = Size(w * 0.56f, h * 0.42f)
            )

            // غليون التدخين الخشبي
            drawLine(
                color = Color(0xFF78350F),
                start = Offset(w * 0.60f, h * 0.52f),
                end = Offset(w * 0.82f, h * 0.58f),
                strokeWidth = 3.dp.toPx()
            )
            drawCircle(Color(0xFF451A03), radius = 5.dp.toPx(), center = Offset(w * 0.82f, h * 0.56f))
        }
    }
}

// =========================================================================
// 10. البريء (كائن اللوريس الصغير اللطيف بعينين دائريتين ضخمتين)
// =========================================================================
@Composable
fun CuteLorisArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية ضوئية لطيفة
            drawRect(
                brush = Brush.radialGradient(
                    listOf(Color(0xFF38BDF8), Color(0xFF0F172A))
                )
            )

            // رأس الكائن الصغير الفروي
            val furColor = Color(0xFFD97706)
            drawCircle(furColor, radius = w * 0.32f, center = Offset(w * 0.5f, h * 0.50f))
            drawCircle(Color(0xFFFEF3C7), radius = w * 0.24f, center = Offset(w * 0.5f, h * 0.52f))

            // عينان كرويتان ضخمتان جداً تشعان براءة
            val eyeRadius = w * 0.14f
            // العين اليسرى
            drawCircle(Color.Black, radius = eyeRadius, center = Offset(w * 0.36f, h * 0.46f))
            drawCircle(Color(0xFF78350F), radius = eyeRadius * 0.8f, center = Offset(w * 0.36f, h * 0.46f))
            drawCircle(Color.Black, radius = eyeRadius * 0.6f, center = Offset(w * 0.36f, h * 0.46f))
            drawCircle(Color.White, radius = 5.dp.toPx(), center = Offset(w * 0.34f, h * 0.42f))

            // العين اليمنى
            drawCircle(Color.Black, radius = eyeRadius, center = Offset(w * 0.64f, h * 0.46f))
            drawCircle(Color(0xFF78350F), radius = eyeRadius * 0.8f, center = Offset(w * 0.64f, h * 0.46f))
            drawCircle(Color.Black, radius = eyeRadius * 0.6f, center = Offset(w * 0.64f, h * 0.46f))
            drawCircle(Color.White, radius = 5.dp.toPx(), center = Offset(w * 0.62f, h * 0.42f))

            // أنف وردي صغير
            drawCircle(Color(0xFFF43F5E), radius = 3.dp.toPx(), center = Offset(w * 0.5f, h * 0.58f))
        }
    }
}

// =========================================================================
// 11. الإخوة الثلاثة (صراصير أوجي الثلاثة جوي، دي دي، ماركي يضحكون)
// =========================================================================
@Composable
fun ThreeCockroachesArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية سماء كرتونية زرقاء
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF60A5FA), Color(0xFF1E3A8A))
                )
            )

            // الصرصور 1 (البرتقالي - جوي)
            drawOval(
                color = Color(0xFFEA580C),
                topLeft = Offset(w * 0.08f, h * 0.42f),
                size = Size(w * 0.28f, h * 0.38f)
            )
            // فم ضاحك عريض
            drawArc(
                color = Color.Black,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.12f, h * 0.54f),
                size = Size(w * 0.20f, h * 0.18f)
            )

            // الصرصور 2 (الأوسط - دي دي)
            drawOval(
                color = Color(0xFF8B5CF6),
                topLeft = Offset(w * 0.36f, h * 0.32f),
                size = Size(w * 0.28f, h * 0.48f)
            )
            drawArc(
                color = Color.Black,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.40f, h * 0.48f),
                size = Size(w * 0.20f, h * 0.22f)
            )

            // الصرصور 3 (الأخضر - ماركي)
            drawOval(
                color = Color(0xFF10B981),
                topLeft = Offset(w * 0.64f, h * 0.24f),
                size = Size(w * 0.28f, h * 0.48f)
            )
            drawArc(
                color = Color.Black,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.68f, h * 0.38f),
                size = Size(w * 0.20f, h * 0.22f)
            )
        }
    }
}

// =========================================================================
// 12. أحمق القرية (ريتشارد واترسون بعضلات مفتولة وانفجار ناري)
// =========================================================================
@Composable
fun BuffedRichardArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية انفجار ناري متوهج
            drawRect(
                brush = Brush.radialGradient(
                    listOf(Color(0xFFFEF08A), Color(0xFFF97316), Color(0xFF7F1D1D))
                )
            )

            // عضلات الأكتاف والصدر الضخمة لريتشارد
            val pinkSkin = Color(0xFFFBCFE8)
            val muscleShade = Color(0xFFF472B6)

            // الصدر المفتول
            drawCircle(pinkSkin, radius = w * 0.22f, center = Offset(w * 0.32f, h * 0.64f))
            drawCircle(pinkSkin, radius = w * 0.22f, center = Offset(w * 0.68f, h * 0.64f))

            // الأكتاف الجبارة
            drawCircle(pinkSkin, radius = w * 0.18f, center = Offset(w * 0.14f, h * 0.50f))
            drawCircle(pinkSkin, radius = w * 0.18f, center = Offset(w * 0.86f, h * 0.50f))

            // رأس الأرنب ريتشارد السمين المميز
            drawCircle(pinkSkin, radius = w * 0.20f, center = Offset(w * 0.50f, h * 0.34f))

            // وجه ريتشارد المبتسم الواثق وسِنّه البارز
            drawRect(
                color = Color.White,
                topLeft = Offset(w * 0.46f, h * 0.38f),
                size = Size(w * 0.08f, h * 0.06f)
            )
            // شارب الأرنب
            drawLine(Color.Black, Offset(w * 0.35f, h * 0.36f), Offset(w * 0.25f, h * 0.36f), strokeWidth = 2.dp.toPx())
            drawLine(Color.Black, Offset(w * 0.65f, h * 0.36f), Offset(w * 0.75f, h * 0.36f), strokeWidth = 2.dp.toPx())
        }
    }
}

// =========================================================================
// 13. القروي (قرويو Resident Evil 4 الغاضبون)
// =========================================================================
@Composable
fun AngryVillagersArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية مظلمة لقرية ريزدنت إيفل
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF374151), Color(0xFF111827))
                )
            )

            // القروي الأوسط الغاضب يحمل الفأس
            drawRect(
                color = Color(0xFF78350F),
                topLeft = Offset(w * 0.35f, h * 0.40f),
                size = Size(w * 0.30f, h * 0.55f)
            )
            drawCircle(Color(0xFFD4A373), radius = w * 0.12f, center = Offset(w * 0.50f, h * 0.30f))

            // لحية القروي الرمادية
            drawCircle(Color(0xFF9CA3AF), radius = w * 0.08f, center = Offset(w * 0.50f, h * 0.36f))

            // فأس مرفوعة في اليد
            drawLine(
                color = Color(0xFF92400E),
                start = Offset(w * 0.38f, h * 0.35f),
                end = Offset(w * 0.24f, h * 0.15f),
                strokeWidth = 3.dp.toPx()
            )
            drawRect(
                color = Color(0xFFCBD5E1),
                topLeft = Offset(w * 0.18f, h * 0.12f),
                size = Size(w * 0.10f, h * 0.08f)
            )

            // شوكة القش للمرأة القروية على اليمين
            drawLine(
                color = Color(0xFF92400E),
                start = Offset(w * 0.72f, h * 0.70f),
                end = Offset(w * 0.82f, h * 0.20f),
                strokeWidth = 2.5.dp.toPx()
            )
        }
    }
}

// =========================================================================
// 14. المستذئب (الذئب المتأمل على الشاطئ)
// =========================================================================
@Composable
fun PensiveWolfArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // عاصفة وبحر هائج
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF1E293B), Color(0xFF0F172A), Color(0xFF334155))
                )
            )

            // جذع الشجرة الداكن
            val treePath = Path().apply {
                moveTo(w * 0.75f, 0f)
                lineTo(w * 0.85f, 0f)
                lineTo(w * 0.95f, h)
                lineTo(w * 0.65f, h)
                close()
            }
            drawPath(treePath, Color(0xFF0B0F17))

            // المستذئب الأبيض يجلس متأملاً
            val wolfWhite = Color(0xFFE2E8F0)
            val wolfShade = Color(0xFF94A3B8)

            // الرأس والأذن
            drawCircle(wolfWhite, radius = w * 0.14f, center = Offset(w * 0.48f, h * 0.26f))
            drawPath(
                Path().apply {
                    moveTo(w * 0.46f, h * 0.26f)
                    lineTo(w * 0.32f, h * 0.30f)
                    lineTo(w * 0.46f, h * 0.34f)
                    close()
                },
                wolfShade
            )

            // الظهر والكتف الفروي
            drawCircle(wolfWhite, radius = w * 0.22f, center = Offset(w * 0.52f, h * 0.50f))

            // الذيل الفروي الكثيف
            drawCircle(wolfWhite, radius = w * 0.18f, center = Offset(w * 0.65f, h * 0.76f))
        }
    }
}

// =========================================================================
// 15. اللص (Robbery Bob يهرب من الكلب)
// =========================================================================
@Composable
fun RobberyBobArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية سماء المدينة الزرقاء
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF38BDF8), Color(0xFF0284C7))
                )
            )

            // اللص بوب في قميص السجن المخطط
            drawCircle(Color(0xFFFBBF24), radius = w * 0.18f, center = Offset(w * 0.65f, h * 0.40f))

            // طاقية اللص السوداء
            drawArc(
                color = Color.Black,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.47f, h * 0.22f),
                size = Size(w * 0.36f, h * 0.24f)
            )

            // قناع العيون الأسود
            drawRoundRect(
                color = Color.Black,
                topLeft = Offset(w * 0.48f, h * 0.36f),
                size = Size(w * 0.34f, h * 0.12f),
                cornerRadius = CornerRadius(8.dp.toPx())
            )
            // عيون مذعورة
            drawCircle(Color.White, radius = 5.dp.toPx(), center = Offset(w * 0.56f, h * 0.42f))
            drawCircle(Color.White, radius = 5.dp.toPx(), center = Offset(w * 0.72f, h * 0.42f))

            // الكلب البني الشرس يلاحقه
            drawCircle(Color(0xFF92400E), radius = w * 0.14f, center = Offset(w * 0.22f, h * 0.60f))
            // عين الكلب الحمراء
            drawCircle(Color.Red, radius = 3.dp.toPx(), center = Offset(w * 0.28f, h * 0.58f))
        }
    }
}

// =========================================================================
// 16. القروي القح (قروي ماينكرافت ثلاثي الأبعاد)
// =========================================================================
@Composable
fun MinecraftVillagerArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية رمادية بسيطة
            drawRect(Color(0xFF1E293B))

            // رأس قروي ماينكرافت المكعب
            val headBrown = Color(0xFFB08968)
            drawRect(
                color = headBrown,
                topLeft = Offset(w * 0.32f, h * 0.15f),
                size = Size(w * 0.36f, h * 0.32f)
            )

            // حواجب وعيون خضراء مكعبة
            drawRect(
                color = Color(0xFF4A3728),
                topLeft = Offset(w * 0.34f, h * 0.25f),
                size = Size(w * 0.32f, h * 0.04f)
            )
            drawRect(
                color = Color(0xFF22C55E),
                topLeft = Offset(w * 0.36f, h * 0.29f),
                size = Size(w * 0.08f, h * 0.04f)
            )
            drawRect(
                color = Color(0xFF22C55E),
                topLeft = Offset(w * 0.56f, h * 0.29f),
                size = Size(w * 0.08f, h * 0.04f)
            )

            // الأنف المكعب البارز الشهير
            drawRect(
                color = Color(0xFF8C6239),
                topLeft = Offset(w * 0.44f, h * 0.28f),
                size = Size(w * 0.12f, h * 0.18f)
            )

            // رداء قروي ماينكرافت البني مع الأذرع المعقودة
            val robeColor = Color(0xFF583A24)
            drawRect(
                color = robeColor,
                topLeft = Offset(w * 0.26f, h * 0.47f),
                size = Size(w * 0.48f, h * 0.48f)
            )
            // طية اليدين المعقودتين
            drawRect(
                color = Color(0xFF422B1B),
                topLeft = Offset(w * 0.28f, h * 0.58f),
                size = Size(w * 0.44f, h * 0.12f)
            )
        }
    }
}

// =========================================================================
// 17. الطبيب (طبيب بالمعطف والسماعة عاقد ذراعيه)
// =========================================================================
@Composable
fun ClassicDoctorArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية بيضاء طبية نظيفة
            drawRect(Color(0xFFF8FAFC))

            // رأس الطبيب
            drawCircle(Color(0xFF3E2723), radius = w * 0.18f, center = Offset(w * 0.50f, h * 0.28f))

            // معطف أبيض ناصع
            drawRect(
                color = Color.White,
                topLeft = Offset(w * 0.15f, h * 0.46f),
                size = Size(w * 0.70f, h * 0.54f)
            )
            // قميص أزرق كلاسيكي
            drawPath(
                Path().apply {
                    moveTo(w * 0.44f, h * 0.46f)
                    lineTo(w * 0.50f, h * 0.60f)
                    lineTo(w * 0.56f, h * 0.46f)
                    close()
                },
                Color(0xFF60A5FA)
            )

            // سماعة الطبيب الفضية
            drawArc(
                color = Color(0xFF334155),
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(w * 0.34f, h * 0.42f),
                size = Size(w * 0.32f, h * 0.32f),
                style = Stroke(width = 3.dp.toPx())
            )
            drawCircle(Color(0xFF94A3B8), radius = 6.dp.toPx(), center = Offset(w * 0.60f, h * 0.72f))
        }
    }
}

// =========================================================================
// 18. المستذئب الأبيض (يعوي تحت ضوء البدر)
// =========================================================================
@Composable
fun WhiteWerewolfMoonArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // سماء ليلية وبدر ساطع
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF0F172A), Color(0xFF1E1B4B))
                )
            )

            // قمر كامل متوهج في السماء
            drawCircle(Color(0xFFFEF08A), radius = w * 0.22f, center = Offset(w * 0.75f, h * 0.25f))
            drawCircle(Color.White.copy(alpha = 0.4f), radius = w * 0.26f, center = Offset(w * 0.75f, h * 0.25f))

            // جسم المستذئب الأبيض الضخم الجاثم
            val werewolfWhite = Color(0xFFF1F5F9)
            val werewolfShadow = Color(0xFF94A3B8)

            drawCircle(werewolfWhite, radius = w * 0.28f, center = Offset(w * 0.40f, h * 0.55f))

            // مخالب حادة سوداء
            drawLine(Color.Black, Offset(w * 0.55f, h * 0.65f), Offset(w * 0.65f, h * 0.72f), strokeWidth = 3.dp.toPx())
            drawLine(Color.Black, Offset(w * 0.52f, h * 0.68f), Offset(w * 0.62f, h * 0.76f), strokeWidth = 3.dp.toPx())
        }
    }
}

// =========================================================================
// 19. الذئب الكبير الشرير (ميم كلنا أشرار في رواية أحدهم)
// =========================================================================
@Composable
fun BigBadWolfPackArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية غابة مظلمة وضباب أزرق
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF0F172A), Color(0xFF022C22))
                )
            )

            // ذئب الظلام الأكبر بعينين صفراوين
            drawCircle(Color(0xFF1F2937), radius = w * 0.28f, center = Offset(w * 0.50f, h * 0.40f))

            // عينان صفراوان متوهجتان بالشر
            drawCircle(Color(0xFFFACC15), radius = 5.dp.toPx(), center = Offset(w * 0.42f, h * 0.38f))
            drawCircle(Color(0xFFFACC15), radius = 5.dp.toPx(), center = Offset(w * 0.58f, h * 0.38f))

            // أنياب بيضاء بارزة
            drawLine(Color.White, Offset(w * 0.46f, h * 0.48f), Offset(w * 0.46f, h * 0.54f), strokeWidth = 2.dp.toPx())
            drawLine(Color.White, Offset(w * 0.54f, h * 0.48f), Offset(w * 0.54f, h * 0.54f), strokeWidth = 2.dp.toPx())
        }

        // عبارة الميم الشهيرة
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-4).dp)
                .background(Color(0xEE000000), RoundedCornerShape(4.dp))
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Text(
                text = "كلنا أشرار في رواية أحدهم",
                color = Color.White,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

// =========================================================================
// 20. الكاشف (جهاز كاشف المعادن الأمني Garrett Super Scanner)
// =========================================================================
@Composable
fun SecurityScannerArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية تقنية
            drawRect(Color(0xFF0F172A))

            // عصا جهاز الكشف الأمني المائل
            val wandPath = Path().apply {
                moveTo(w * 0.35f, h * 0.10f)
                lineTo(w * 0.85f, h * 0.55f)
                lineTo(w * 0.70f, h * 0.70f)
                lineTo(w * 0.20f, h * 0.25f)
                close()
            }
            drawPath(wandPath, Color(0xFF1E293B))

            // مقبض الجهاز الأسود
            drawLine(
                color = Color.Black,
                start = Offset(w * 0.25f, h * 0.75f),
                end = Offset(w * 0.45f, h * 0.55f),
                strokeWidth = 14.dp.toPx(),
                cap = StrokeCap.Round
            )
        }

        // كتابة GARRETT باللون الأصفر البارز
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .background(Color.Black, RoundedCornerShape(2.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = "GARRETT",
                color = Color(0xFFFACC15),
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp
            )
        }
    }
}

// =========================================================================
// 21. العجوز (الشيخ الوقور بالشماغ الأحمر واللحية البيضاء والسبحة)
// =========================================================================
@Composable
fun ArabElderSheikhArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية المجلس العربي
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF451A03), Color(0xFF1C1917))
                )
            )

            // الشماغ الأحمر المنقوش
            val shemaghColor = Color(0xFFDC2626)
            drawArc(
                color = shemaghColor,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.20f, h * 0.12f),
                size = Size(w * 0.60f, h * 0.40f)
            )

            // العقال الأسود المزدوج
            drawArc(
                color = Color.Black,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(w * 0.28f, h * 0.16f),
                size = Size(w * 0.44f, h * 0.20f),
                style = Stroke(width = 4.dp.toPx())
            )

            // وجه الشيخ الوقور
            drawCircle(Color(0xFFD4A373), radius = w * 0.16f, center = Offset(w * 0.50f, h * 0.36f))

            // اللحية البيضاء الكثيفة الوقورة
            val beardColor = Color(0xFFF8FAFC)
            drawCircle(beardColor, radius = w * 0.18f, center = Offset(w * 0.50f, h * 0.48f))

            // الثوب العربي الداكن
            drawRect(
                color = Color(0xFF1E293B),
                topLeft = Offset(w * 0.15f, h * 0.58f),
                size = Size(w * 0.70f, h * 0.42f)
            )

            // حبات السبحة الذهبية في اليد
            val rosaryColor = Color(0xFFFBBF24)
            for (i in 0..5) {
                drawCircle(rosaryColor, radius = 3.dp.toPx(), center = Offset(w * (0.25f + i * 0.05f), h * 0.82f))
            }
        }
    }
}

// =========================================================================
// 22. الفارس بالسيف الصدئ (القط ذو الحذاء وسيف المبارزة)
// =========================================================================
@Composable
fun PussInBootsArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية بيضاء نقية
            drawRect(Color.White)

            // قبعة الفارس السوداء الكبيرة
            drawOval(
                color = Color.Black,
                topLeft = Offset(w * 0.15f, h * 0.12f),
                size = Size(w * 0.70f, h * 0.22f)
            )
            // ريشة القبعة الصفراء المتطايرة
            drawArc(
                color = Color(0xFFFACC15),
                startAngle = 180f,
                sweepAngle = 90f,
                useCenter = true,
                topLeft = Offset(w * 0.10f, h * 0.04f),
                size = Size(w * 0.40f, h * 0.20f)
            )

            // رأس وجسم القط البرتقالي
            val catOrange = Color(0xFFF97316)
            drawCircle(catOrange, radius = w * 0.18f, center = Offset(w * 0.48f, h * 0.38f))

            // حذاء الفارس الجلدي الأسود ذو الطية
            drawRoundRect(
                color = Color.Black,
                topLeft = Offset(w * 0.30f, h * 0.72f),
                size = Size(w * 0.18f, h * 0.24f),
                cornerRadius = CornerRadius(6.dp.toPx())
            )
            drawRoundRect(
                color = Color.Black,
                topLeft = Offset(w * 0.52f, h * 0.72f),
                size = Size(w * 0.18f, h * 0.24f),
                cornerRadius = CornerRadius(6.dp.toPx())
            )

            // سيف المبارزة الفضي المرفوع
            drawLine(
                color = Color(0xFF94A3B8),
                start = Offset(w * 0.55f, h * 0.45f),
                end = Offset(w * 0.92f, h * 0.42f),
                strokeWidth = 2.5.dp.toPx()
            )
            drawCircle(Color(0xFF64748B), radius = 6.dp.toPx(), center = Offset(w * 0.60f, h * 0.45f))
        }
    }
}

// =========================================================================
// 23. المشعل (CJ من GTA San Andreas مشتعل بالنار)
// =========================================================================
@Composable
fun CjOnFireArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // شارع سان أندرياس الإسفلتي
            drawRect(Color(0xFF78716C))

            // ألسنة اللهب البرتقالية والصفراء المشتعلة حول CJ
            val fireColor = Color(0xFFF97316)
            val innerFire = Color(0xFFFBBF24)
            drawCircle(fireColor.copy(alpha = 0.8f), radius = w * 0.34f, center = Offset(w * 0.50f, h * 0.55f))
            drawCircle(innerFire.copy(alpha = 0.9f), radius = w * 0.24f, center = Offset(w * 0.50f, h * 0.55f))

            // CJ بالقميص الأبيض وبنطال الجينز الأزرق
            drawCircle(Color(0xFF5A3825), radius = w * 0.12f, center = Offset(w * 0.50f, h * 0.35f))
            drawRect(
                color = Color.White,
                topLeft = Offset(w * 0.40f, h * 0.45f),
                size = Size(w * 0.20f, h * 0.22f)
            )
            drawRect(
                color = Color(0xFF1E3A8A),
                topLeft = Offset(w * 0.40f, h * 0.67f),
                size = Size(w * 0.20f, h * 0.24f)
            )

            // رادار GTA في الزاوية اليسرى
            drawCircle(Color.Black, radius = w * 0.12f, center = Offset(w * 0.18f, h * 0.82f))
            drawCircle(Color(0xFF22C55E), radius = 3.dp.toPx(), center = Offset(w * 0.18f, h * 0.82f))
        }
    }
}

// =========================================================================
// 24. التوأم (طفلتان توأم إفريقيتان بملابس صفراء)
// =========================================================================
@Composable
fun AfricanTwinsArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية ترابية دافئة
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF78350F), Color(0xFF451A03))
                )
            )

            // رأس الطفلة 1 (اليسار)
            drawCircle(Color(0xFF3E2723), radius = w * 0.16f, center = Offset(w * 0.32f, h * 0.38f))
            // رأس الطفلة 2 (اليمين)
            drawCircle(Color(0xFF3E2723), radius = w * 0.16f, center = Offset(w * 0.68f, h * 0.38f))

            // الفستان الذهبي الأصفر للطفلتين
            val dressYellow = Color(0xFFFBBF24)
            drawRect(
                color = dressYellow,
                topLeft = Offset(w * 0.18f, h * 0.52f),
                size = Size(w * 0.28f, h * 0.36f)
            )
            drawRect(
                color = dressYellow,
                topLeft = Offset(w * 0.54f, h * 0.52f),
                size = Size(w * 0.28f, h * 0.36f)
            )
        }
    }
}

// =========================================================================
// 25. الإخوة دم دم (شاغي وسكوبي دو مذعوران)
// =========================================================================
@Composable
fun ScoobyAndShaggyArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية مظلمة مخيفة
            drawRect(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF1E1B4B), Color(0xFF0F172A))
                )
            )

            // شاغي بقميصه الأخضر وشعره الأشقر
            drawCircle(Color(0xFFFBBF24), radius = w * 0.18f, center = Offset(w * 0.32f, h * 0.32f))
            drawCircle(Color(0xFFFDE68A), radius = w * 0.14f, center = Offset(w * 0.32f, h * 0.38f))
            // قميص شاغي الأخضر
            drawRect(
                color = Color(0xFF65A30D),
                topLeft = Offset(w * 0.18f, h * 0.58f),
                size = Size(w * 0.32f, h * 0.42f)
            )

            // سكوبي دو البني بطوقه الفيروزي
            drawCircle(Color(0xFF92400E), radius = w * 0.18f, center = Offset(w * 0.72f, h * 0.50f))
            // طوق سكوبي دو
            drawArc(
                color = Color(0xFF06B6D4),
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(w * 0.58f, h * 0.62f),
                size = Size(w * 0.28f, h * 0.16f),
                style = Stroke(width = 4.dp.toPx())
            )
        }
    }
}

// =========================================================================
// 26. الممثل (الممثل تيريس جيبسون يبتسم ابتسامة عريضة في بدلة أنيقة)
// =========================================================================
@Composable
fun ActorTyreseArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية بيضاء ساطعة لأضواء الشهرة
            drawRect(Color.White)

            // رأس أصلع لامع
            val skinTone = Color(0xFF5A3825)
            drawCircle(skinTone, radius = w * 0.22f, center = Offset(w * 0.50f, h * 0.36f))

            // ابتسامة هوليوودية بيضاء ناصعة عريضة
            drawArc(
                color = Color.White,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(w * 0.36f, h * 0.42f),
                size = Size(w * 0.28f, h * 0.14f)
            )
            // لمعان قرط الأذن الماسي
            drawCircle(Color(0xFF38BDF8), radius = 3.dp.toPx(), center = Offset(w * 0.72f, h * 0.40f))

            // سترة جلدية سوداء وقميص أبيض
            drawRect(
                color = Color(0xFF0F172A),
                topLeft = Offset(w * 0.15f, h * 0.58f),
                size = Size(w * 0.70f, h * 0.42f)
            )
            drawPath(
                Path().apply {
                    moveTo(w * 0.44f, h * 0.58f)
                    lineTo(w * 0.50f, h * 0.75f)
                    lineTo(w * 0.56f, h * 0.58f)
                    close()
                },
                Color.White
            )
            // ربطة عنق سوداء
            drawRect(
                color = Color.Black,
                topLeft = Offset(w * 0.48f, h * 0.62f),
                size = Size(w * 0.04f, h * 0.18f)
            )
        }
    }
}

// =========================================================================
// 27. ذئب أب (ذئب رومانسي يحمل وردة حمراء وفراشات حمراء ونجوم)
// =========================================================================
@Composable
fun FatherWolfRoseArtwork() {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // خلفية رومانسية داكنة مع بريق أحمر
            drawRect(
                brush = Brush.radialGradient(
                    listOf(Color(0xFF991B1B), Color(0xFF0F172A))
                )
            )

            // رأس الذئب الرمادي الرومانسي
            val wolfGrey = Color(0xFF64748B)
            drawCircle(wolfGrey, radius = w * 0.26f, center = Offset(w * 0.48f, h * 0.36f))

            // عينان خضراوان زمرديتان
            drawCircle(Color(0xFF10B981), radius = 4.5.dp.toPx(), center = Offset(w * 0.40f, h * 0.34f))
            drawCircle(Color(0xFF10B981), radius = 4.5.dp.toPx(), center = Offset(w * 0.56f, h * 0.34f))

            // وردة حمراء مخملية ضخمة في الزاوية
            val roseRed = Color(0xFFDC2626)
            drawCircle(roseRed, radius = w * 0.20f, center = Offset(w * 0.68f, h * 0.70f))
            drawCircle(Color(0xFF991B1B), radius = w * 0.14f, center = Offset(w * 0.68f, h * 0.70f))

            // غصن الوردة الأخضر في فم الذئب
            drawLine(
                color = Color(0xFF15803D),
                start = Offset(w * 0.48f, h * 0.48f),
                end = Offset(w * 0.68f, h * 0.70f),
                strokeWidth = 3.dp.toPx()
            )

            // فراشات حمراء متطايرة
            drawCircle(Color(0xFFF43F5E), radius = 4.dp.toPx(), center = Offset(w * 0.72f, h * 0.24f))
            drawCircle(Color(0xFFF43F5E), radius = 3.dp.toPx(), center = Offset(w * 0.22f, h * 0.72f))
        }
    }
}

// =========================================================================
// بطاقة افتراضية لباقي الأدوار
// =========================================================================
@Composable
fun GenericRoleArt(roleId: String) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF0F172A)),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            drawCircle(
                color = GoldBorderMain.copy(alpha = 0.3f),
                radius = w * 0.35f,
                center = Offset(w * 0.5f, h * 0.5f)
            )
        }
        Text(
            text = "بطاقة أسطورية",
            color = GoldBorderLight,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * العبارة الفنية والميم المرتبط بكل شخصية
 */
private fun getRoleMemeQuote(roleId: String): String {
    return when (roleId) {
        "father_wolf" -> "الذئب الأب • وردة التحويل 🌹"
        "big_bad_wolf" -> "كلنا أشرار في رواية أحدهم"
        "white_werewolf" -> "الذئب الأبيض • صياد البدر 🌕"
        "werewolf" -> "المستذئب • استرخاء وافتراس"
        "hunter" -> "إنه فخ يا جورج لا تتسرع! 🎯"
        "spy" -> "بيري الخلد • العميل السري 🕵️"
        "pure_villager" -> "القروي القح • براءة ماينكرافت"
        "villager" -> "أهل القرية • ثورة الحصاد 🌾"
        "village_idiot" -> "ريتشارد • عضلات البراءة 💪"
        "scapegoat" -> "الخروف • يارب محد يعرفني! 🧢"
        "crow" -> "الغراب • مستغرب!"
        "judge" -> "محكمة العدالة • الجولة الثانية ⚖️"
        "rusty_knight" -> "القط بالسيف • سم السيف الصدئ 🗡️"
        "bear_tamer" -> "مروض الدب • رقصات الإنذار 🐻"
        "fox" -> "الثعلب الأنيق • شم المشتبهين 🦊"
        "elder" -> "الشيخ العجوز • بركة القرية 📿"
        "thief" -> "اللص بوب • سرقة الأقدار 💰"
        "lamplighter" -> "المشعل CJ • لهيب كشف المنازل 🔥"
        "wild_child" -> "الطفل البري • عدنان في الغابة"
        "fool_innocent" -> "البريء • اعدموني وافوز! 🥺"
        "twins" -> "التوأم • توحد الأصوات 👭"
        "three_brothers" -> "الإخوة الثلاثة • حلف الصراصير 😂"
        "blood_brothers" -> "شاغي وسكوبي • دم دم 😱"
        "doctor" -> "الطبيب • إنقاذ الأرواح 🩺"
        "surgeon_father", "surgeon" -> "د. علي وفاء • الطبيب المعجزة 🏥"
        "seer" -> "الكاشف • جهاز التفتيش الأمني 📡"
        "actor" -> "الممثل • تيريس جيبسون 🌟"
        "savior" -> "الحارس • درع الأبرياء 🛡️"
        "gypsy" -> "الغجرية • أسرار التاروت 🔮"
        "mayor" -> "العمدة • صوت الترجيح 📜"
        else -> "دور أسطوري غامض"
    }
}

/**
 * رسم مشهد الخلفية الأسطوري بتأثير الضباب والأشعة
 */
private fun DrawScope.drawAtmosphericScene(role: Role, pulseGlow: Float) {
    val width = size.width
    val height = size.height

    // سماء ليلية متدرجة تبعا لفريق الدور
    val topSky = when (role.category) {
        com.example.data.model.RoleCategory.WEREWOLF_CLAN -> Color(0xFF200609)
        com.example.data.model.RoleCategory.SOLO -> Color(0xFF1E0A28)
        com.example.data.model.RoleCategory.VILLAGE -> Color(0xFF071424)
        com.example.data.model.RoleCategory.SPECIAL -> Color(0xFF1C1304)
        com.example.data.model.RoleCategory.LEADERSHIP -> Color(0xFF2D1B06)
    }
    val bottomSky = Color(0xFF04060A)

    drawRect(
        brush = Brush.verticalGradient(
            colors = listOf(topSky, bottomSky)
        )
    )

    // هالة متوهجة نابضة في المركز
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(
                GoldBorderMain.copy(alpha = 0.15f * pulseGlow),
                Color.Transparent
            ),
            radius = width * 0.55f
        ),
        radius = width * 0.55f,
        center = Offset(width * 0.5f, height * 0.45f)
    )
}
