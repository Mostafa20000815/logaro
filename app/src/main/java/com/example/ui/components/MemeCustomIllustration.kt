package com.example.ui.components

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * مولّد الرسوم البصرية المركبة بدقة عالية لكل ميم وشخصية مطابقة تماماً للمحتوى المطلوب
 */
@Composable
fun MemeCustomIllustration(
    roleId: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        when (roleId) {
            "father_wolf" -> FatherWolfIllustration()
            "big_bad_wolf" -> BigBadWolfIllustration()
            "white_werewolf" -> WhiteWerewolfIllustration()
            "werewolf" -> WerewolfIllustration()
            "hunter" -> HunterIllustration()
            "spy" -> SpyPerryIllustration()
            "pure_villager" -> MinecraftVillagerIllustration()
            "villager" -> AngryVillagersIllustration()
            "village_idiot" -> BuffRichardIllustration()
            "scapegoat" -> CoolSheepIllustration()
            "crow" -> PuzzledCrowIllustration()
            "judge" -> UNJudgeIllustration()
            "rusty_knight" -> PussInBootsIllustration()
            "bear_tamer" -> DancingBearsIllustration()
            "fox" -> ElegantFoxIllustration()
            "elder" -> WiseSheikhIllustration()
            "thief" -> RobberyBobIllustration()
            "lamplighter" -> BurningCJIllustration()
            "wild_child" -> MowgliChildIllustration()
            "fool_innocent" -> InnocentSlowLorisIllustration()
            "twins" -> AfricanTwinsIllustration()
            "three_brothers" -> OggyCockroachesIllustration()
            "blood_brothers" -> ShaggyScoobyIllustration()
            "doctor" -> RealDoctorIllustration()
            "surgeon_father" -> MiracleSurgeonAliIllustration()
            "seer" -> SecurityScannerIllustration()
            "actor" -> ElegantActorIllustration()
            "savior" -> SaviorKnightIllustration()
            "gypsy" -> GypsyFortuneIllustration()
            "mayor" -> VillageMayorIllustration()
            else -> GenericIllustration()
        }
    }
}

// 1. الذئب الأب مع الوردة الحمراء
@Composable
private fun FatherWolfIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF881337), Color(0xFF1E0A10), Color(0xFF070406))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🐺", fontSize = 42.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xEE831843), RoundedCornerShape(6.dp))
                    .border(1.dp, Color(0xFFF43F5E), RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text("🌹 وردة التحويل الحمراء", color = Color(0xFFFFF1F2), fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// 2. الذئب الشرير الكبير - كلنا أشرار
@Composable
private fun BigBadWolfIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0F172A), Color(0xFF450A0A), Color(0xFF030712))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(6.dp)
        ) {
            Text("🐺🐺🐺", fontSize = 28.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xDD000000), RoundedCornerShape(6.dp))
                    .border(1.dp, Color(0xFFEF4444), RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 3.dp)
            ) {
                Text(
                    "كلنا أشرار في رواية أحدهم",
                    color = Color(0xFFFCA5A5),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// 3. الذئب الأبيض - تحت ضوء القمر
@Composable
private fun WhiteWerewolfIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0C192E), Color(0xFF1E293B), Color(0xFF090D16))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🌕", fontSize = 22.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text("🐺❄️", fontSize = 34.sp)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text("صياد البدر المنفرد", color = Color(0xFF93C5FD), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 4. المستذئب على الشاطئ
@Composable
private fun WerewolfIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1E293B), Color(0xFF334155), Color(0xFF0F172A))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🐺🌊", fontSize = 38.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("المستذئب الحزين على البحر", color = Color(0xFFE2E8F0), fontSize = 9.5.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

// 5. ميم الصياد - إنه فخ يا جورج
@Composable
private fun HunterIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0284C7), Color(0xFF0369A1), Color(0xFF0C4A6E))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp)
        ) {
            Text("👨‍🦱🕶️", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xDD000000), RoundedCornerShape(4.dp))
                    .border(1.dp, Color(0xFF38BDF8), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    "إنه فخ يا جورج لا تتسرع!",
                    color = Color(0xFFBAE6FD),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// 6. الجاسوس - بيري الخلد Perry
@Composable
private fun SpyPerryIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0D9488), Color(0xFF115E59), Color(0xFF134E4A))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🎩", fontSize = 20.sp)
            Text("🦫🕵️‍♂️", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("بيري الخلد • العميل P", color = Color(0xFF5EEAD4), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 7. القروي القح - ماينكرافت Minecraft
@Composable
private fun MinecraftVillagerIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF15803D), Color(0xFF78350F), Color(0xFF451A03))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("👨‍🌾🟩", fontSize = 38.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Minecraft Villager ⛏️", color = Color(0xFF86EFAC), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 8. قرويو Resident Evil الغاضبون
@Composable
private fun AngryVillagersIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF451A03), Color(0xFF292524), Color(0xFF0C0A09))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("👥⛏️🔥", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("ثورة قرويي القرية", color = Color(0xFFFDBA74), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 9. أحمق القرية - ريتشارد واترسون المعضل
@Composable
private fun BuffRichardIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFEA580C), Color(0xFFC2410C), Color(0xFF7C2D12))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🐰💪💥", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("ريتشارد العملاق المعضل", color = Color(0xFFFFEDD5), fontSize = 9.5.sp, fontWeight = FontWeight.Black)
        }
    }
}

// 10. كبش الفداء - يارب محد يعرفني
@Composable
private fun CoolSheepIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1E293B), Color(0xFF0F172A), Color(0xFF020617))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(4.dp)
        ) {
            Text("🧢", fontSize = 18.sp)
            Text("🐑🕶️", fontSize = 32.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xEEFFFFFF), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    "يارب محد يعرفني 😂",
                    color = Color(0xFF0F172A),
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}

// 11. الغراب - مستغرب
@Composable
private fun PuzzledCrowIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF334155), Color(0xFF1E293B), Color(0xFF0F172A))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🦅❓", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFF000000), RoundedCornerShape(4.dp))
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text("مستغرب", color = Color(0xFFFFFFFF), fontSize = 10.sp, fontWeight = FontWeight.Black)
            }
        }
    }
}

// 12. القاضي - محكمة الأمم المتحدة
@Composable
private fun UNJudgeIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1D4ED8), Color(0xFF1E3A8A), Color(0xFF172554))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🌐", fontSize = 18.sp)
            Text("👨🏿‍⚖️🔨", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("محكمة العدل الدولية", color = Color(0xFFDBEAFE), fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 13. الفارس بالسيف الصدئ - القط ذو الحذاء Puss in Boots
@Composable
private fun PussInBootsIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFD97706), Color(0xFF92400E), Color(0xFF451A03))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🐱👒", fontSize = 32.sp)
            Text("🗡️👢", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("القط بالسيف الصدئ", color = Color(0xFFFEF3C7), fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 14. مروض الدب - الدببة الراقصة 3D
@Composable
private fun DancingBearsIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFCA8A04), Color(0xFFA16207), Color(0xFF713F12))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🐻🐻🐻", fontSize = 26.sp)
            Text("💃🎶", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("رقصة الدببة ثلاثية الأبعاد", color = Color(0xFFFEF08A), fontSize = 9.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 15. الثعلب الأنيق بالغليون
@Composable
private fun ElegantFoxIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF9A3412), Color(0xFF7C2D12), Color(0xFF431407))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🦊🎩", fontSize = 32.sp)
            Text("🧥💨", fontSize = 22.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("المحقق الثعلب الأنيق", color = Color(0xFFFFEDD5), fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 16. العجوز - الشيخ بالشماغ والمسبحة
@Composable
private fun WiseSheikhIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF334155), Color(0xFF1E293B), Color(0xFF0F172A))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🧓📿", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("شيخ القرية الوقور", color = Color(0xFFE2E8F0), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 17. اللص - Robbery Bob
@Composable
private fun RobberyBobIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0284C7), Color(0xFF075985), Color(0xFF0C4A6E))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🥷🐕", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("Robbery Bob اللص", color = Color(0xFFBAE6FD), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 18. المشعل - CJ المشتعل بالنيران GTA
@Composable
private fun BurningCJIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFDC2626), Color(0xFFEA580C), Color(0xFF7C2D12))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🔥🏃🏿‍♂️🔥", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("CJ مشتعل بنيران GTA", color = Color(0xFFFEF08A), fontSize = 9.5.sp, fontWeight = FontWeight.Black)
        }
    }
}

// 19. الطفل المتوحش - ماوكلي
@Composable
private fun MowgliChildIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0284C7), Color(0xFF0369A1), Color(0xFF075985))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("👦🏽🌿🐾", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("ماوكلي فتى الأدغال", color = Color(0xFFE0F2FE), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 20. البريء - الحيوان الصغير ذو العيون الواسعة
@Composable
private fun InnocentSlowLorisIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF78350F), Color(0xFF451A03), Color(0xFF1C1917))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🥺🐾💖", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("البريء • اعدموني وافوز!", color = Color(0xFFFDE68A), fontSize = 9.5.sp, fontWeight = FontWeight.Black)
        }
    }
}

// 21. التوأم
@Composable
private fun AfricanTwinsIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFB45309), Color(0xFF78350F), Color(0xFF451A03))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("👶🏿👶🏿🤝", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("التوأم المتماثل", color = Color(0xFFFEF3C7), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 22. الإخوة الثلاثة - صراصير أوجي
@Composable
private fun OggyCockroachesIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0284C7), Color(0xFF0369A1), Color(0xFF075985))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🪳🪳🪳😂", fontSize = 30.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("صراصير أوجي الثلاثة", color = Color(0xFFBAE6FD), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 23. الإخوة دم دم - شاغي وسكوبي
@Composable
private fun ShaggyScoobyIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF65A30D), Color(0xFF4D7C0F), Color(0xFF1A2E05))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🐕👱🏻‍♂️😱", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("شاغي & سكوبي دو", color = Color(0xFFECFCCB), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 24. الطبيب
@Composable
private fun RealDoctorIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF059669), Color(0xFF047857), Color(0xFF064E3B))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("👨🏿‍⚕️🩺🧪", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("طبيب القرية المعتمد", color = Color(0xFFA7F3D0), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 25. الجراح - د. علي وفاء
@Composable
private fun MiracleSurgeonAliIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0284C7), Color(0xFF0369A1), Color(0xFF075985))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🏥👨🏻‍⚕️🩺", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("د. علي وفاء • الطبيب المعجزة", color = Color(0xFFE0F2FE), fontSize = 9.5.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 26. الكاشف - جهاز Garrett Scanner
@Composable
private fun SecurityScannerIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1E293B), Color(0xFF0F172A), Color(0xFF020617))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("📟⚡🔍", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(Color(0xFFEAB308), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text("GARRETT SCANNER", color = Color(0xFF000000), fontSize = 8.5.sp, fontWeight = FontWeight.Black)
            }
        }
    }
}

// 27. الممثل
@Composable
private fun ElegantActorIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF4F46E5), Color(0xFF3730A3), Color(0xFF1E1B4B))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🤵🏿‍♂️🎭✨", fontSize = 34.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("الممثل العالمي الأنيق", color = Color(0xFFE0E7FF), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 28. الحارس المنقذ
@Composable
private fun SaviorKnightIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0D9488), Color(0xFF115E59), Color(0xFF134E4A))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🛡️⚔️✨", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("درع حماية الأبرياء", color = Color(0xFF99F6E4), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 29. الغجري
@Composable
private fun GypsyFortuneIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF7E22CE), Color(0xFF581C87), Color(0xFF3B0764))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🔮🃏✨", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("عرّاف استحضار الأرواح", color = Color(0xFFF3E8FF), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// 30. العمدة
@Composable
private fun VillageMayorIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFD97706), Color(0xFFB45309), Color(0xFF78350F))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("👑🏛️📜", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(2.dp))
            Text("عمدة القرية • صوتان", color = Color(0xFFFEF3C7), fontSize = 10.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun GenericIllustration() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E293B)),
        contentAlignment = Alignment.Center
    ) {
        Text("⚔️🛡️", fontSize = 36.sp)
    }
}
