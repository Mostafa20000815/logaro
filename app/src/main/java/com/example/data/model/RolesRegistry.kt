package com.example.data.model

object RolesRegistry {

    // ==========================================
    // عشيرة الذئاب (7 بطاقات)
    // ==========================================
    val WEREWOLF = Role(
        id = "werewolf",
        nameArabic = "الذئب",
        nameEnglish = "Werewolf",
        category = RoleCategory.WEREWOLF_CLAN,
        description = "يستيقظ ليلاً مع بقية القطيع للتشاور بالإشارة واختيار ضحية واحدة لافتراسها، ويتخفى نهاراً كقروي لإبعاد الشبهات.",
        nightPriority = 10,
        defaultCardCount = 4,
        iconSymbol = "ذ",
        wakeUpEveryNight = true,
        tipsArabic = "تنسق مع رفاقك بصمت تام واصنع تحالفات نهاراً لتوجيه أصوات القرويين بعيداً عنكم."
    )

    val BIG_BAD_WOLF = Role(
        id = "big_bad_wolf",
        nameArabic = "الذئب الشرير الكبير",
        nameEnglish = "Big Bad Wolf",
        category = RoleCategory.WEREWOLF_CLAN,
        description = "يستيقظ مع القطيع لاختيار ضحية الجماعة؛ وما دام لم يمت أي ذئب من فريقه، يستيقظ ثانية بمفرده في نفس الليلة لافتراس ضحية إضافية.",
        nightPriority = 11,
        defaultCardCount = 1,
        iconSymbol = "ذ.ك",
        wakeUpEveryNight = true,
        tipsArabic = "حمايتك لرفاقك الذئاب تضمن بقاء قدرتك على الافتراس المزدوج كل ليلة."
    )

    val FATHER_WOLF = Role(
        id = "father_wolf",
        nameArabic = "الذئب الأب",
        nameEnglish = "Father Wolf",
        category = RoleCategory.WEREWOLF_CLAN,
        description = "عضو أساسي في القطيع؛ يمتلك قدرة (مرة واحدة طوال اللعبة) لعض ضحية الذئاب وتحويلها إلى ذئب بدلاً من قتلها، لتنضم الضحية سراً للقطيع بدءاً من الليلة التالية.",
        nightPriority = 12,
        defaultCardCount = 1,
        iconSymbol = "ذ.أ",
        wakeUpEveryNight = true,
        tipsArabic = "اختر الوقت المثالي لتحويل قروي قوي أو موثوق إلى صف الذئاب لتفاجئ القرية."
    )

    val WHITE_WEREWOLF = Role(
        id = "white_werewolf",
        nameArabic = "الذئب الأبيض",
        nameEnglish = "White Werewolf",
        category = RoleCategory.SOLO,
        description = "ذئب منشق يلعب لحسابه الخاص؛ يستيقظ مع الذئاب للتضليل، لكنه يستيقظ بمفرده كل ليلتين ليغتال أحد الذئاب. هدفه أن يكون الناجي الوحيد في القرية.",
        nightPriority = 13,
        defaultCardCount = 1,
        iconSymbol = "ذ.ب",
        wakeUpEveryNight = true,
        tipsArabic = "أنت الذئب المنفرد، تظاهر بالولاء للقطيع حتى تحين اللحظة المناسبة للقضاء عليهم واحداً تلو الآخر."
    )

    // ==========================================
    // أهل القرية والأدوار الخاصة (35 بطاقة)
    // ==========================================
    val VILLAGER = Role(
        id = "villager",
        nameArabic = "القروي",
        nameEnglish = "Simple Villager",
        category = RoleCategory.VILLAGE,
        description = "لا يمتلك قدرات ليلية؛ سلاحه النقاش، وقوة الملاحظة، وصوته في مجلس القرية نهاراً لكشف الذئاب والتصويت لإعدامهم.",
        nightPriority = 0,
        defaultCardCount = 9,
        iconSymbol = "ق",
        wakeUpEveryNight = false,
        tipsArabic = "راقب لغة الجسد وتناقضات الكلام أثناء النهار، صوتك هو سلاح القرية الوحيد."
    )

    val PURE_VILLAGER = Role(
        id = "pure_villager",
        nameArabic = "القروي القح",
        nameEnglish = "Pure Villager",
        category = RoleCategory.VILLAGE,
        description = "تُكشف بطاقته علناً لجميع اللاعبين منذ بداية اللعبة؛ براءته مطلقة ومؤكدة، ويُعتبر مرجعاً آمناً للقرية في إدارة النقاش والتصويت.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "ق.ق",
        wakeUpEveryNight = false,
        tipsArabic = "الجميع يعلم براءتك، قُد النقاش واجمع أصوات الشرفاء ضد المشتبه بهم."
    )

    val SEER = Role(
        id = "seer",
        nameArabic = "الكاشف",
        nameEnglish = "Seer",
        category = RoleCategory.VILLAGE,
        description = "يستيقظ كل ليلة ليختار لاعباً واحداً، فيكشف له مدير اللعبة الهوية الحقيقية لذلك اللاعب سراً.",
        nightPriority = 3,
        defaultCardCount = 1,
        iconSymbol = "ك",
        wakeUpEveryNight = true,
        tipsArabic = "استخدم معلوماتك بذكاء دون أن تفضح هويتك للذئاب فيفترسوك مبكراً."
    )

    val SURGEON_FATHER = Role(
        id = "surgeon_father",
        nameArabic = "الدكتور الأب [الجراح]",
        nameEnglish = "Father Doctor / Surgeon",
        category = RoleCategory.VILLAGE,
        description = "الليلة الأولى: يستيقظ ليختار أحد سكان القرية ويحوّله سراً إلى طبيب.\nبدءاً من الليلة الثانية: يستيقظ كطبيب كامل يمتلك حقيبته المستقلة (حقنة دواء لإنقاذ ضحية الذئاب + حقنة داء لتصفية أي لاعب، تُستخدم كل حقنة مرة واحدة طوال اللعبة).",
        nightPriority = 5,
        defaultCardCount = 1,
        iconSymbol = "ط.أ",
        wakeUpEveryNight = true,
        firstNightOnly = false,
        tipsArabic = "في الليلة الأولى اختر حليفاً أميناً ليصبح طبيباً ثانياً يساند القرية."
    )

    val DOCTOR = Role(
        id = "doctor",
        nameArabic = "الطبيب",
        nameEnglish = "Witch / Doctor",
        category = RoleCategory.VILLAGE,
        description = "يستيقظ ليلاً (بعد هجوم الذئاب) ويمتلك حقنتين مستقلتين لا تُستخدم كل واحدة منهما إلا مرة واحدة طوال اللعبة:\n- حقنة الدواء: إنقاذ ضحية هجوم الذئاب في تلك الليلة وإبقاؤها على قيد الحياة.\n- حقنة الداء: تصفية أي لاعب يختاره سراً أثناء الليل.",
        nightPriority = 14,
        defaultCardCount = 1,
        iconSymbol = "ط",
        wakeUpEveryNight = true,
        tipsArabic = "احتفظ بحقنة الشفاء للشخصيات المحورية، واستخدم السم عندما تتأكد من هوية أحد الذئاب."
    )

    val SAVIOR = Role(
        id = "savior",
        nameArabic = "المنقذ",
        nameEnglish = "Savior / Bodyguard",
        category = RoleCategory.VILLAGE,
        description = "يستيقظ بداية كل ليلة لحماية لاعب واحد من هجوم الذئاب (ويمكنه حماية نفسه)، ولا يحق له حماية نفس الشخص ليلتين متتاليتين.",
        nightPriority = 2,
        defaultCardCount = 1,
        iconSymbol = "م",
        wakeUpEveryNight = true,
        tipsArabic = "توقع من سيستهدفه الذئاب الليلة وكن درعاً له."
    )

    val HUNTER = Role(
        id = "hunter",
        nameArabic = "الصياد",
        nameEnglish = "Hunter",
        category = RoleCategory.VILLAGE,
        description = "إذا مات بأي وسيلة (افتراس ليلاً أو إعدام نهاراً)، يطلق رصاصته الأخيرة فوراً ويأخذ معه لاعباً من اختياره إلى الموت.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "ص",
        wakeUpEveryNight = false,
        tipsArabic = "حدد أكثر المشتبه بهم خطورة حتى تأخذه معك إن سقطت."
    )

    val SPY = Role(
        id = "spy",
        nameArabic = "الجاسوس",
        nameEnglish = "Spy / Peeping Tom",
        category = RoleCategory.VILLAGE,
        description = "يحق له فتح عينيه خلسة واستراق النظر أثناء استيقاظ الذئاب لمعرفة هوياتهم، لكن إذا لمحته الذئاب يُقتل في الحال.",
        nightPriority = 9,
        defaultCardCount = 1,
        iconSymbol = "ج",
        wakeUpEveryNight = true,
        tipsArabic = "المخاطرة عالية جداً، كن حذراً للغاية عند استراق النظر."
    )

    val ELDER = Role(
        id = "elder",
        nameArabic = "العجوز",
        nameEnglish = "Elder",
        category = RoleCategory.VILLAGE,
        description = "يتمتع بحصانة تُمكّنه من النجاة من أول هجوم للذئاب (يموت في الهجوم الثاني)؛ وإذا أعدمته القرية خطأً أو قتله دور بشري، يفقد جميع القرويين قدراتهم الخاصة ندماً وحزناً.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "ع",
        wakeUpEveryNight = false,
        tipsArabic = "حذر القرية من التسرع في إعدامك حتى لا تخسر القرية كل قدراتها."
    )

    val FOX = Role(
        id = "fox",
        nameArabic = "الثعلب",
        nameEnglish = "Fox",
        category = RoleCategory.VILLAGE,
        description = "يستيقظ ليلاً ويشير إلى 3 لاعبين متجاورين؛ يخبره المدير إن كان بينهم ذئب واحد على الأقل. إذا كانت المجموعة خالية تماماً من الذئاب، يفقد الثعلب حاسته لبقية اللعبة.",
        nightPriority = 4,
        defaultCardCount = 1,
        iconSymbol = "ث",
        wakeUpEveryNight = true,
        tipsArabic = "اختر الثلاثي المشبوه بدقة حتى لا تفقد حاستك الثمينة."
    )

    val BEAR_TAMER = Role(
        id = "bear_tamer",
        nameArabic = "الدب",
        nameEnglish = "Bear Tamer",
        category = RoleCategory.VILLAGE,
        description = "في بداية كل يوم، يفحص المدير ترتيب اللاعبين الأحياء المجاورين له مباشرة (عن يمينه ويساره)؛ فإذا كان أحدهما أو كلاهما ذئباً، يُطلق المدير إشارة الدب لتنبيه القرية بوجود خطر قريب.",
        nightPriority = 1,
        defaultCardCount = 1,
        iconSymbol = "د",
        wakeUpEveryNight = false,
        tipsArabic = "إذا زمجر الدب صباحاً، فالذئب يجلس ملاصقاً لك مباشرة."
    )

    val JUDGE = Role(
        id = "judge",
        nameArabic = "القاضي",
        nameEnglish = "Judge",
        category = RoleCategory.VILLAGE,
        description = "يمتلك إشارة سرية متفقاً عليها مع المدير؛ يمكنه استخدامها مرة واحدة في اللعبة بعد انتهاء التصويت النهاري لفرض جولة محاكمة وتصويت ثانية فورية لإعدام متهم آخر.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "قض",
        wakeUpEveryNight = false,
        tipsArabic = "استخدم حكمك الثاني عندما تشعر بأن القرية أخطأت في اختيار المتهم أو عندما تملك دليلاً قاطعاً."
    )

    val RUSTY_KNIGHT = Role(
        id = "rusty_knight",
        nameArabic = "الفارس بسيف الصدأ",
        nameEnglish = "Knight with the Rusty Sword",
        category = RoleCategory.VILLAGE,
        description = "إذا افترسته الذئاب ليلاً، يجرح بسيفه الصدئ أول ذئب يجلس عن يساره؛ فيموت ذلك الذئب المصاب تلقائياً في الليلة التالية متأثراً بالصدأ.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "ف",
        wakeUpEveryNight = false,
        tipsArabic = "موتك على يد الذئاب سيكلفهم ثمناً باهظاً في الليلة التالية."
    )

    val CROW = Role(
        id = "crow",
        nameArabic = "الغراب",
        nameEnglish = "Crow",
        category = RoleCategory.VILLAGE,
        description = "يستيقظ نهاية كل ليلة ليلصق رسالة شؤم على باب أحد اللاعبين، فيبدأ ذلك اللاعب جلسة محاكمة النهار التالي ومعه صوتان إضافيان ضده مسبقاً.",
        nightPriority = 15,
        defaultCardCount = 1,
        iconSymbol = "غر",
        wakeUpEveryNight = true,
        tipsArabic = "وجّه شؤم الغراب نحو اللاعب الأكثر إثارة للشكوك."
    )

    val LAMPLIGHTER = Role(
        id = "lamplighter",
        nameArabic = "المشعل",
        nameEnglish = "Lamplighter / Pyromaniac",
        category = RoleCategory.VILLAGE,
        description = "يستيقظ ليلاً لسكب الزيت وإشعال فوانيس منازل اللاعبين لكشف هوياتهم أو تحييد قدراتهم لتلك الليلة.",
        nightPriority = 8,
        defaultCardCount = 1,
        iconSymbol = "مش",
        wakeUpEveryNight = true,
        tipsArabic = "أنر منازل القرية لتبديد الظلام وإرباك مخططات الأعداء."
    )

    val GYPSY = Role(
        id = "gypsy",
        nameArabic = "الغجري",
        nameEnglish = "Gypsy",
        category = RoleCategory.VILLAGE,
        description = "يستحضر الأرواح ليلاً؛ ويطلب من المدير سحب وتفعيل بطاقة من بطاقات الأرواح التي تفرض أحداثاً وظروفاً بيئية خاصة على القرية طوال اليوم التالي.",
        nightPriority = 16,
        defaultCardCount = 1,
        iconSymbol = "غج",
        wakeUpEveryNight = true,
        tipsArabic = "استدعاء الأرواح يغير مجريات اللعبة ويفرض تحديات جديدة."
    )

    val TWINS = Role(
        id = "twins",
        nameArabic = "التوأم",
        nameEnglish = "Two Sisters / Twins",
        category = RoleCategory.VILLAGE,
        description = "يستيقظان معاً في الليلة الأولى فقط للتعارف، ويشكلان كتلة تصويت موثوقة نهاراً.",
        nightPriority = 6,
        defaultCardCount = 2,
        iconSymbol = "ت",
        wakeUpEveryNight = false,
        firstNightOnly = true,
        tipsArabic = "أنت وتوأمك تمثلان صوتين مؤكدين للبراءة، نسقا التصويت دائماً معاً."
    )

    val THREE_BROTHERS = Role(
        id = "three_brothers",
        nameArabic = "الإخوة ثلاثة",
        nameEnglish = "Three Brothers",
        category = RoleCategory.VILLAGE,
        description = "يستيقظون في الليلة الأولى ليعرف بعضهم بعضاً كأبرياء، ويعملون معاً لتنسيق الاتهامات وتوجيه الأصوات.",
        nightPriority = 7,
        defaultCardCount = 3,
        iconSymbol = "أ3",
        wakeUpEveryNight = false,
        firstNightOnly = true,
        tipsArabic = "ثلاثة أصوات مؤكدة في قرية مظلمة، تماسكوا لحسم نتائج المحاكمات."
    )

    val BLOOD_BROTHERS = Role(
        id = "blood_brothers",
        nameArabic = "الأخوة دم دم",
        nameEnglish = "Blood Brothers",
        category = RoleCategory.VILLAGE,
        description = "ثنائي مرتبط بالدم؛ يستيقظان للتعارف، وإذا مات أحدهما بأي طريقة، يموت الآخر فوراً في نفس اللحظة كمداً وحزناً.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "أ.د",
        wakeUpEveryNight = false,
        firstNightOnly = true,
        tipsArabic = "حياتكما مرتبطة معاً، دافع عن أخيك بالدم بكل قوتك فموته يعني موتك."
    )

    val SCAPEGOAT = Role(
        id = "scapegoat",
        nameArabic = "كبش الفداء",
        nameEnglish = "Scapegoat",
        category = RoleCategory.VILLAGE,
        description = "إذا انتهى تصويت القرية النهاري بالتعادل بين مرشحين، يُعدم كبش الفداء تلقائياً بدلاً عنهما، ويحق له قبل موته تحديد من يُسمح لهم بالتصويت في اليوم التالي.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "فد",
        wakeUpEveryNight = false,
        tipsArabic = "تجنب التعادل في التصويت، وإن أُعدمت فاختر من يصوت غداً لمعاقبة المشبوهين."
    )

    val VILLAGE_IDIOT = Role(
        id = "village_idiot",
        nameArabic = "أحمق القرية",
        nameEnglish = "Village Idiot",
        category = RoleCategory.VILLAGE,
        description = "إذا أجمعت القرية نهاراً على إعدامه بالتصويت، يكشف بطاقته ويعفو عنه الجميع لبساطته؛ ينجو من الموت لكنه يُجرد تماماً من حق التصويت لبقية اللعبة.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "أح",
        wakeUpEveryNight = false,
        tipsArabic = "تنجو من حبل المشنقة لمرة واحدة، لكن صوتك سيسلب منك."
    )

    val THIEF = Role(
        id = "thief",
        nameArabic = "اللص",
        nameEnglish = "Thief",
        category = RoleCategory.SPECIAL,
        description = "تُوضع بطاقتان إضافيتان مقلوبتان في البداية؛ يستيقظ في الليلة الأولى ليطّلع عليهما ويختار تبديل بطاقته بإحداهما إن رغب في ذلك.",
        nightPriority = 1,
        defaultCardCount = 1,
        iconSymbol = "ل",
        wakeUpEveryNight = false,
        firstNightOnly = true,
        tipsArabic = "أنت أول من يستيقظ في الليلة الأولى لتختار مصيرك وهويتك الحقيقية."
    )

    val ACTOR = Role(
        id = "actor",
        nameArabic = "الممثل",
        nameEnglish = "Actor",
        category = RoleCategory.SPECIAL,
        description = "تُوضع له 3 بطاقات أدوار خاصة مقلوبة جانباً؛ يختار كل ليلة تقمص دور إحداها واستخدام قدرتها، حتى تنتهي البطاقات الثلاث فيتحول إلى قروي عادي.",
        nightPriority = 2,
        defaultCardCount = 1,
        iconSymbol = "مم",
        wakeUpEveryNight = true,
        tipsArabic = "تقمص الدور المناسب في اللحظة المناسبة لقلب موازين القوى."
    )

    val WILD_CHILD = Role(
        id = "wild_child",
        nameArabic = "الطفل المتوحش",
        nameEnglish = "Wild Child",
        category = RoleCategory.SPECIAL,
        description = "يختار في الليلة الأولى لاعباً ليكون 'قدوته'؛ طالما ظل القدوة حياً يدافع كقروي مخلص، وإذا مات القدوة يتحول الطفل فوراً إلى ذئب وينضم للقطيع.",
        nightPriority = 4,
        defaultCardCount = 1,
        iconSymbol = "طف",
        wakeUpEveryNight = false,
        firstNightOnly = true,
        tipsArabic = "اختر قدوتك بعناية، فمصيره هو ما يحدد انتمائك المستقبلي للقرية أو للذئاب."
    )

    val FOOL_INNOCENT = Role(
        id = "fool_innocent",
        nameArabic = "البريء",
        nameEnglish = "Tanner / The Fool",
        category = RoleCategory.SOLO,
        description = "هدفه الوحيد أن يدفع القرية للتصويت على إعدامه في أول جلسة محاكمة نهارية؛ إذا نجح في ذلك يفوز بالمباراة بمفرده فوراً، وإن فشل يُكمل اللعبة كقروي عادي.",
        nightPriority = 0,
        defaultCardCount = 1,
        iconSymbol = "بر",
        wakeUpEveryNight = false,
        tipsArabic = "تصرّف بريبة مدروسة لدفع القرية لإعدامك نهاراً واقتناص الفوز الفردي الخاطف."
    )

    // ==========================================
    // المنصب القيادي (بدون بطاقة سحب)
    // ==========================================
    val MAYOR = Role(
        id = "mayor",
        nameArabic = "عمدة القرية",
        nameEnglish = "Village Mayor",
        category = RoleCategory.LEADERSHIP,
        description = "يُنتخب بالتصويت العلني في اليوم الأول بين اللاعبين:\n- يُحسب صوته بصوتين في كل جولات التصويت.\n- يرجح الكفة ويفصل النزاع فوراً عند حدوث تعادل.\n- إذا قُتل أو أُعدم، يوصي بتسليم الشارة لأي لاعب آخر يختاره ليكون العمدة الجديد قبل خروجه.",
        nightPriority = 0,
        defaultCardCount = 0,
        iconSymbol = "عم",
        wakeUpEveryNight = false,
        isElected = true,
        tipsArabic = "منصب الشرف والمسؤولية، صوتك المزدوج قادر على إنقاذ الأبرياء أو حسم القرارات المصيرية."
    )

    // قائمة جميع الأدوار القابلة للتوزيع بالبطاقات
    val ALL_PLAYABLE_ROLES: List<Role> = listOf(
        WEREWOLF,
        BIG_BAD_WOLF,
        FATHER_WOLF,
        WHITE_WEREWOLF,
        VILLAGER,
        PURE_VILLAGER,
        SEER,
        SURGEON_FATHER,
        DOCTOR,
        SAVIOR,
        HUNTER,
        SPY,
        ELDER,
        FOX,
        BEAR_TAMER,
        JUDGE,
        RUSTY_KNIGHT,
        CROW,
        LAMPLIGHTER,
        GYPSY,
        TWINS,
        THREE_BROTHERS,
        BLOOD_BROTHERS,
        SCAPEGOAT,
        VILLAGE_IDIOT,
        THIEF,
        ACTOR,
        WILD_CHILD,
        FOOL_INNOCENT
    )

    // القائمة الكاملة المتضمنة المناصب القيادية
    val ALL_ROLES_INCLUDING_LEADERSHIP: List<Role> = ALL_PLAYABLE_ROLES + listOf(MAYOR)

    fun getRoleById(id: String): Role {
        return ALL_ROLES_INCLUDING_LEADERSHIP.firstOrNull { it.id == id } ?: VILLAGER
    }

    /**
     * ترتيب أدوار الليل تصاعدياً حسب الأولوية
     */
    val NIGHT_ROLES_ORDERED: List<Role> = ALL_PLAYABLE_ROLES
        .filter { it.nightPriority > 0 }
        .sortedBy { it.nightPriority }

    /**
     * إنشاء تشكيلة بطاقات متوازنة مقترحة بناءً على عدد اللاعبين
     */
    fun getRecommendedRoleCounts(playerCount: Int): Map<String, Int> {
        val map = mutableMapOf<String, Int>()
        val count = playerCount.coerceIn(4, 35)

        when {
            count <= 5 -> {
                map[WEREWOLF.id] = 1
                map[SEER.id] = 1
                map[DOCTOR.id] = 1
                map[VILLAGER.id] = count - 3
            }
            count in 6..7 -> {
                map[WEREWOLF.id] = 2
                map[SEER.id] = 1
                map[DOCTOR.id] = 1
                map[HUNTER.id] = 1
                map[VILLAGER.id] = count - 5
            }
            count in 8..9 -> {
                map[WEREWOLF.id] = 2
                map[SEER.id] = 1
                map[DOCTOR.id] = 1
                map[SAVIOR.id] = 1
                map[HUNTER.id] = 1
                map[VILLAGE_IDIOT.id] = 1
                map[VILLAGER.id] = count - 7
            }
            count in 10..12 -> {
                map[WEREWOLF.id] = 2
                map[BIG_BAD_WOLF.id] = 1
                map[SEER.id] = 1
                map[DOCTOR.id] = 1
                map[SAVIOR.id] = 1
                map[HUNTER.id] = 1
                map[FOX.id] = 1
                map[CROW.id] = 1
                map[VILLAGER.id] = count - 9
            }
            count in 13..16 -> {
                map[WEREWOLF.id] = 3
                map[FATHER_WOLF.id] = 1
                map[WHITE_WEREWOLF.id] = 1
                map[SEER.id] = 1
                map[SURGEON_FATHER.id] = 1
                map[SAVIOR.id] = 1
                map[HUNTER.id] = 1
                map[BEAR_TAMER.id] = 1
                map[ELDER.id] = 1
                map[TWINS.id] = 2
                map[VILLAGER.id] = (count - 13).coerceAtLeast(0)
            }
            else -> {
                map[WEREWOLF.id] = 4
                map[BIG_BAD_WOLF.id] = 1
                map[FATHER_WOLF.id] = 1
                map[WHITE_WEREWOLF.id] = 1
                map[SEER.id] = 1
                map[SURGEON_FATHER.id] = 1
                map[DOCTOR.id] = 1
                map[SAVIOR.id] = 1
                map[HUNTER.id] = 1
                map[BEAR_TAMER.id] = 1
                map[FOX.id] = 1
                map[ELDER.id] = 1
                map[CROW.id] = 1
                map[JUDGE.id] = 1
                map[THREE_BROTHERS.id] = 3
                map[TWINS.id] = 2
                map[WILD_CHILD.id] = 1
                map[VILLAGER.id] = (count - 22).coerceAtLeast(0)
            }
        }
        return map
    }
}
