package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.PaperOchre
import com.example.ui.theme.PaperPrussianBlue
import com.example.ui.theme.PaperRustRed
import com.example.ui.theme.PaperTerracotta
import com.example.ui.theme.PaperVintagePurple

enum class RoleCategory(
    val titleArabic: String,
    val color: Color,
    val descriptionArabic: String
) {
    WEREWOLF_CLAN(
        titleArabic = "عشيرة الذئاب",
        color = PaperRustRed,
        descriptionArabic = "يسعون لافتراس سكان القرية ليلاً والتخفي نهاراً للسيطرة على القرية"
    ),
    VILLAGE(
        titleArabic = "أهل القرية",
        color = PaperPrussianBlue,
        descriptionArabic = "يبحثون عن الذئاب نهاراً عبر النقاش والتصويت لحماية قريتهم"
    ),
    SPECIAL(
        titleArabic = "أدوار خاصة",
        color = PaperVintagePurple,
        descriptionArabic = "شخصيات ذات قدرات تحول وتبديل وقواعد فريدة"
    ),
    SOLO(
        titleArabic = "منشقون ومنفردون",
        color = PaperOchre,
        descriptionArabic = "يلعبون لحسابهم الخاص لتحقيق شروط فوز استثنائية"
    ),
    LEADERSHIP(
        titleArabic = "منصب قيادي",
        color = PaperTerracotta,
        descriptionArabic = "منصب يُنتخب نهاراً لإدارة القرية وترجيح الأصوات"
    )
}
