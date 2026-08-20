package com.example.data.model

data class Role(
    val id: String,
    val nameArabic: String,
    val nameEnglish: String,
    val category: RoleCategory,
    val description: String,
    val nightPriority: Int, // 0 = لا يستيقظ ليلاً، 1..20 = ترتيب الاستيقاظ
    val defaultCardCount: Int = 1,
    val iconSymbol: String,
    val wakeUpEveryNight: Boolean = true,
    val firstNightOnly: Boolean = false,
    val isElected: Boolean = false,
    val tipsArabic: String = "",
    val emoji: String = ""
) {
    val hasNightAction: Boolean
        get() = nightPriority > 0

    val maxCardsInDeck: Int
        get() = when (id) {
            "werewolf" -> 6
            "villager" -> 12
            "twins" -> 2
            "three_brothers" -> 3
            else -> 1
        }

    val displayEmoji: String
        get() = if (emoji.isNotBlank()) emoji else when (id) {
            "werewolf" -> "🐺"
            "big_bad_wolf" -> "👹"
            "father_wolf" -> "🩸"
            "white_werewolf" -> "🐺❄️"
            "wolf_dog" -> "🐕"
            "wild_child" -> "👶"
            "villager" -> "👨‍🌾"
            "pure_villager" -> "🛡️"
            "seer" -> "🔮"
            "doctor_father" -> "💉"
            "witch" -> "🧙‍♀️"
            "hunter" -> "🏹"
            "cupid" -> "💘"
            "little_girl" -> "👧"
            "thief" -> "🎭"
            "bodyguard" -> "🛡️"
            "fool" -> "🃏"
            "elder" -> "🧓"
            "scapegoat" -> "🐐"
            "defender" -> "🛡️"
            "piper" -> "🪈"
            "angel" -> "🪽"
            "knight_rusty_shield" -> "⚔️"
            "bear_tamer" -> "🐻"
            "fox" -> "🦊"
            "crow" -> "🦅"
            "two_sisters" -> "👯‍♀️"
            "three_brothers" -> "👨‍👨‍👦"
            "stuttering_judge" -> "⚖️"
            "devoted_servant" -> "📜"
            "pyromaniac" -> "🔥"
            "coroner" -> "🔍"
            "preacher" -> "📖"
            "alchemist" -> "🧪"
            "cult_leader" -> "🕯️"
            "detective" -> "🕵️‍♂️"
            "gravedigger" -> "⚰️"
            "medium" -> "🕯️"
            "necromancer" -> "💀"
            "shaman" -> "🔮"
            "vampire" -> "🧛‍♂️"
            "zombie" -> "🧟‍♂️"
            "assassin" -> "🗡️"
            "mayor" -> "👑"
            else -> "✨"
        }
}
