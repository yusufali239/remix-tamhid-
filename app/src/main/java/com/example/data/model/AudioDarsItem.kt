package com.example.data.model

data class AudioDarsItem(
    val id: String,
    val audioRaqami: String,
    val mavzuUz: String,
    val tavsifUz: String,
    val originalArabicTitle: String,
    val originalFileName: String,
    val driveFileId: String,
    val taxminiyDavomiyligi: String = "28:00",
    val tartibRaqami: Int = 1
) {
    val primaryStreamUrl: String
        get() = "https://drive.usercontent.google.com/download?id=$driveFileId&export=download&authuser=0"

    val fallbackStreamUrl: String
        get() = "https://drive.google.com/uc?export=download&id=$driveFileId"
}

object AudioDarslarRepository {
    val allAudioDarslar: List<AudioDarsItem> = listOf(
        AudioDarsItem(
            id = "audio_1",
            audioRaqami = "1-AUDIO",
            mavzuUz = "Narsalar haqiqatining sobitligi",
            tavsifUz = "Ushbu darsda mavjudotlarning haqiqati borligi, safsataviylar va shubhachilarning qarashlariga munosib ilmiy raddiyalar bayon qilinadi.",
            originalArabicTitle = "ثبوت حقائق الأشياء",
            originalFileName = "ثبوت حقائق الأشياء.m4a",
            driveFileId = "14HoNXLSQKcMvCuKOkIHWjm8nU2XWnBvh",
            taxminiyDavomiyligi = "24:30",
            tartibRaqami = 1
        ),
        AudioDarsItem(
            id = "audio_2",
            audioRaqami = "2-AUDIO",
            mavzuUz = "Ashyolar haqiqatini bilishga olib boruvchi sabablar",
            tavsifUz = "Ushbu darsda narsalarning haqiqati va ularni to‘g‘ri anglashga olib boruvchi ilmiy asoslar: besh sezgi a'zosi, to'g'ri xabar va aql bayon qilinadi.",
            originalArabicTitle = "الأسباب الموصلة للعلم بحقائق الأشياء",
            originalFileName = "الأسباب_الموصلة_للعلم_بحقائق_الأشياء.m4a",
            driveFileId = "1R9Q6kQhy21rGGvlaUx3phPlJ-yg3evDL",
            taxminiyDavomiyligi = "28:15",
            tartibRaqami = 2
        ),
        AudioDarsItem(
            id = "audio_3",
            audioRaqami = "3-AUDIO",
            mavzuUz = "Ilm sabablarini inkor qiluvchilar va ularga raddiya",
            tavsifUz = "Ilm va ma'rifat sabablarini inkor qiluvchi botil firqalar va ularning shubhalariga aqliy va naqliy dalillar bilan berilgan javoblar.",
            originalArabicTitle = "قول منكري أسباب العلم والرد عليهم",
            originalFileName = "قول_منكري_أسباب_العلم_و_الرد_عليهم.m4a",
            driveFileId = "1MizpmrLshZow7HEYc7A3-V07ZUQXDGN9",
            taxminiyDavomiyligi = "26:40",
            tartibRaqami = 3
        ),
        AudioDarsItem(
            id = "audio_4",
            audioRaqami = "4-AUDIO",
            mavzuUz = "Indiya toifasi va ularning subyektivlik shubhalari",
            tavsifUz = "«Haqiqat har kimning fikriga ko'ra o'zgaradi» deguvchi Indiya firqasining qarashlari va ularning mantiqiy xatolari bayoni.",
            originalArabicTitle = "العندية وشبهتهم",
            originalFileName = "العندية و شبهتهم.m4a",
            driveFileId = "1nxyZEkmtPlPWjx4DA5Do1M-I_e0fXdIb",
            taxminiyDavomiyligi = "22:50",
            tartibRaqami = 4
        ),
        AudioDarsItem(
            id = "audio_5",
            audioRaqami = "5-AUDIO",
            mavzuUz = "A'roz va ayonlarning hadis (yaratilgan) ekani isboti",
            tavsifUz = "Moddiy olamdagi jism va a'rozlarning vaqt jihatidan yangi paydo bo'lganligi hamda o'zgaruvchanlik dalillari tushuntiriladi.",
            originalArabicTitle = "إثبات حدوث الأعراض والأعيان",
            originalFileName = "إثبات حدوث الأعراض و الأعيان.m4a",
            driveFileId = "1WA09F8F5icqHrZxRvabR7K10dtpr7KDl",
            taxminiyDavomiyligi = "31:20",
            tartibRaqami = 5
        ),
        AudioDarsItem(
            id = "audio_6",
            audioRaqami = "6-AUDIO",
            mavzuUz = "Dahriylarning aqvollari va ularning shubhalari",
            tavsifUz = "Olamni azaliy deb da'vo qiluvchi dahriylarning (materialistlarning) da'volari va uning botilligini fosh qiluvchi qat'iy dalillar.",
            originalArabicTitle = "أقوال الدهرية وشبههم",
            originalFileName = "أقوال الدهرية و شبههم (1).m4a",
            driveFileId = "1Jjd4kh5EJHsAsBWXYE0kJiBL7IiG98pT",
            taxminiyDavomiyligi = "27:10",
            tartibRaqami = 6
        ),
        AudioDarsItem(
            id = "audio_7",
            audioRaqami = "7-AUDIO",
            mavzuUz = "Olam Yaratuvchisi a'roz, javhar va jism emasligi",
            tavsifUz = "Alloh taolo maxluqot xususiyatlaridan butkul pok ekani: U a'roz ham, javhar ham, moddiy jism ham emasligi aqliy asoslanadi.",
            originalArabicTitle = "صانع العالم ليس بعرض ولا جوهر ولا جسم",
            originalFileName = "صانع_العالم_ليس_بعرض_ولا_جوهر_ولا_جسم_1.m4a",
            driveFileId = "1pI7eHgXmqLGmo2PRK31fEh9fC3x8Md7V",
            taxminiyDavomiyligi = "30:45",
            tartibRaqami = 7
        ),
        AudioDarsItem(
            id = "audio_8",
            audioRaqami = "8-AUDIO",
            mavzuUz = "Alloh taoloning jism emasligi haqida",
            tavsifUz = "Tanzih qoidasiga ko'ra Parvardigorning chegaralanish, bo'laklanish va jism bo'lish kabi barcha nuqsonlardan mutlaqo xoli ekanligi sharhlanadi.",
            originalArabicTitle = "الله ليس بجسم",
            originalFileName = "الله ليس بجسم.m4a",
            driveFileId = "1W_SB4rG5Csw7SA0x30bjNPHq5xhiEXAv",
            taxminiyDavomiyligi = "25:35",
            tartibRaqami = 8
        ),
        AudioDarsItem(
            id = "audio_9",
            audioRaqami = "9-AUDIO",
            mavzuUz = "Alloh taolodan makon va jihatni nafiy qilish",
            tavsifUz = "Alloh taolo makon yaratilishidan oldin ham bor bo'lgani, Unga makon va olti tomon (jihat) xos bo'lmasligi haqidagi moturidiy ta'limot.",
            originalArabicTitle = "نفي المكان والجهة عن الله",
            originalFileName = "تفي المكان و الجهة عن الله.m4a",
            driveFileId = "1WpL-FOnbc35b6BMdnS8EJTTZVvknmjHH",
            taxminiyDavomiyligi = "29:15",
            tartibRaqami = 9
        ),
        AudioDarsItem(
            id = "audio_10",
            audioRaqami = "10-AUDIO",
            mavzuUz = "Alloh bilan maxluqlar orasidagi tashbihni botil qilish",
            tavsifUz = "«Laysa kamislihi shay'» oyatining ma'nosi, Xoliqni maxluqqa o'xshatishning zalolati va to'g'ri e'tiqodiy chegaralar.",
            originalArabicTitle = "إبطال التشبيه بين الله وبين غيره",
            originalFileName = "إبطال التشبيه بين الله و بين غيره.m4a",
            driveFileId = "1Nj58BItVIont8MQLnT682nLpeBUd9Q_t",
            taxminiyDavomiyligi = "24:50",
            tartibRaqami = 10
        ),
        AudioDarsItem(
            id = "audio_11",
            audioRaqami = "11-AUDIO",
            mavzuUz = "Allohning yagonaligini isbotlash",
            tavsifUz = "Ushbu darsda Alloh taoloning yagonaligi va bunga dalil bo‘ladigan asosiy masalalar aqliy va naqliy tartibda tushuntiriladi.",
            originalArabicTitle = "إثبات وحدانية الله",
            originalFileName = "إثبات وحدانية الله.m4a",
            driveFileId = "1L4lFF-r7Ep_0_GJ1TBUikJ4ZUPeHnCIv",
            taxminiyDavomiyligi = "32:45",
            tartibRaqami = 11
        ),
        AudioDarsItem(
            id = "audio_12",
            audioRaqami = "12-AUDIO",
            mavzuUz = "Tamonu' dalili va tavhid isboti",
            tavsifUz = "Kalom ilmida olamda ikki mustaqil xudo bo'lishining mantiqan imkonsizligini ko'rsatuvchi klassik Tamonu' dalili tahlili.",
            originalArabicTitle = "دليل التمانع",
            originalFileName = "دليل التمانع (1).m4a",
            driveFileId = "1xu-4nL4f4G3R4vNOyri0qd_WwtPOUg-x",
            taxminiyDavomiyligi = "28:30",
            tartibRaqami = 12
        ),
        AudioDarsItem(
            id = "audio_13",
            audioRaqami = "13-AUDIO",
            mavzuUz = "Alloh taologa Zotiy va Subutiy sifatlarni isbotlash",
            tavsifUz = "Ahli sunna val jamoa e'tiqodiga ko'ra Alloh taoloning azaliy va abadiy komil sifatlari borligini tasdiqlash dalillari.",
            originalArabicTitle = "إثبات الصفات لله",
            originalFileName = "إثبات الصفات لله.m4a",
            driveFileId = "19LIKOePdou40JimtXE-zwGuPOMoiW1G3",
            taxminiyDavomiyligi = "33:10",
            tartibRaqami = 13
        ),
        AudioDarsItem(
            id = "audio_14",
            audioRaqami = "14-AUDIO",
            mavzuUz = "Sifatlar borasida mo'taziliylarning shubhasi va javob",
            tavsifUz = "Mo'taziliylarning «ko'p qadimiylar vujudga keladi» degan shubhalari va Ahli sunnaning unga bergan ilmiy asosli javoblari.",
            originalArabicTitle = "شبهة المعتزلة في إثبات الصفات والجواب عليها",
            originalFileName = "شبهة_المعتزلة_في_إثبات_الصفات_و_الجواب_عليها.m4a",
            driveFileId = "1CIfR6KsrvMVzkoqqu46cUkVLtIXfFOSf",
            taxminiyDavomiyligi = "27:40",
            tartibRaqami = 14
        ),
        AudioDarsItem(
            id = "audio_15",
            audioRaqami = "15-AUDIO",
            mavzuUz = "Mo'taziliylarning qarashlari va ularga raddiya",
            tavsifUz = "Mo'tazila mazhabining aqidaviy asosi va ularning sunnat tushunchasiga zid bo'lgan fikrlariga mantiqiy tahlil.",
            originalArabicTitle = "قول المعتزلة والرد عليهم",
            originalFileName = "قول المعتزلة و الرد عليهم.m4a",
            driveFileId = "1j8E2MFHxwDSAhV-ShWpy9rBQ9jLtvZp4",
            taxminiyDavomiyligi = "29:05",
            tartibRaqami = 15
        ),
        AudioDarsItem(
            id = "audio_16",
            audioRaqami = "16-AUDIO",
            mavzuUz = "Hikmat Alloh taoloning azaliy sifati ekani",
            tavsifUz = "Alloh taoloning barcha fe'llarida hikmat borligi va Hikmat sifati zotiga loyiq azaliy ekanligi bayoni.",
            originalArabicTitle = "الحكمة صفة أزلية لله",
            originalFileName = "الحكمة صفة أزلية لله.m4a",
            driveFileId = "1v9snAYqPPWkVLQHqxWMaAQ1JWGGnz5fO",
            taxminiyDavomiyligi = "26:15",
            tartibRaqami = 16
        ),
        AudioDarsItem(
            id = "audio_17",
            audioRaqami = "17-AUDIO",
            mavzuUz = "Takvin sifati va uning mukavvandan boshqa ekani",
            tavsifUz = "Imom Moturidiy ta'limotidagi asosiy qoidalardan biri: yaratish (Takvin) sifati yaratilgan narsalardan (mukavvan) o'zgadir.",
            originalArabicTitle = "التكوين غير المكون",
            originalFileName = "التكوين غير المكون م.m4a",
            driveFileId = "1VbbCcWWHVYoTbFiXpICXhFfJXYQoq6BJ",
            taxminiyDavomiyligi = "31:00",
            tartibRaqami = 17
        ),
        AudioDarsItem(
            id = "audio_18",
            audioRaqami = "18-AUDIO",
            mavzuUz = "Alloh taoloning kalomi azaliyligini isbotlashga kirish",
            tavsifUz = "Kalomulloh masalasining kalom ilmidagi o'rni, Zotiy Kalomi Nafsiy va uning azaliyligiga muqaddima.",
            originalArabicTitle = "المدخل في إثبات أزلية كلام الله",
            originalFileName = "المدخل في إثبات أزلية كلام الله.m4a",
            driveFileId = "1y08JIa-ldYFks8gwOf9O4mu4o7tFMn_o",
            taxminiyDavomiyligi = "25:20",
            tartibRaqami = 18
        ),
        AudioDarsItem(
            id = "audio_19",
            audioRaqami = "19-AUDIO",
            mavzuUz = "Alloh kalomining haqiqati va azaliyligi",
            tavsifUz = "Qur'oni Karim Allohning yaratilmagan azaliy kalomi ekani hamda bu boradagi mo'taziliy da'volarga qat'iy javob.",
            originalArabicTitle = "حقيقة كلام الله وأزلية كلام الله",
            originalFileName = "حقيقة كلام الله و أزلية كلام الله.m4a",
            driveFileId = "14OOlb1KG_jM_uE2QrYF7c_Yl3OlMgJL8",
            taxminiyDavomiyligi = "34:15",
            tartibRaqami = 19
        ),
        AudioDarsItem(
            id = "audio_20",
            audioRaqami = "20-AUDIO",
            mavzuUz = "Ism va musammo masalasi",
            tavsifUz = "Alloh taoloning ismlari zotining ayni yoki g'ayri ekanligi borasidagi kalomiy munozara va Ahli sunna xulosasi.",
            originalArabicTitle = "الاسم عين المسمى",
            originalFileName = "الاسم عين المسمى.m4a",
            driveFileId = "1-63qxV_pxTRVsyRXeiS2gg8tv5xRhrON",
            taxminiyDavomiyligi = "23:45",
            tartibRaqami = 20
        ),
        AudioDarsItem(
            id = "audio_21",
            audioRaqami = "21-AUDIO",
            mavzuUz = "Ism va musammo haqida ulamolarning aqvollari",
            tavsifUz = "Hanafiy-moturidiy va boshqa buyuk kalom ulamolarining ism va musammo xususidagi nozik ta'riflari va mulohazalari.",
            originalArabicTitle = "الاسم عين المسمى - أقوال العلماء",
            originalFileName = "الاسم عين المسمى ، أقوال العلماء.m4a",
            driveFileId = "1lnBc_QF6NSTqFvbOAQHl1fwrW2Tfy5La",
            taxminiyDavomiyligi = "28:50",
            tartibRaqami = 21
        ),
        AudioDarsItem(
            id = "audio_22",
            audioRaqami = "22-AUDIO",
            mavzuUz = "Mujassimalar (antropomorfistlar) qarashlari va raddiya",
            tavsifUz = "Alloh taoloni maxluqot shaklida tasavvur qiluvchi mujassima va mushabbihalar adashuvlariga Moturidiyya javobi.",
            originalArabicTitle = "أقوال المجسمة والرد عليهم",
            originalFileName = "أقوال المجسمة و الرد عليهم.m4a",
            driveFileId = "1pzuUu_w6GPZfsvotBbJbeINsZ2_FFBtR",
            taxminiyDavomiyligi = "30:10",
            tartibRaqami = 22
        ),
        AudioDarsItem(
            id = "audio_23",
            audioRaqami = "23-AUDIO",
            mavzuUz = "Karromiylarning so'zlari va ularga raddiya",
            tavsifUz = "Karromiylarning zotda hodisalar vujudga kelishi haqidagi botil qarashlari va Lomishiyning mukammal raddiyasi.",
            originalArabicTitle = "أقوال الكرامية والرد عليهم",
            originalFileName = "أقوال الكرامية و الرد عليهم.m4a",
            driveFileId = "1SN5q6ft6dxRM8KfRbpOHetcj2wXV7QmK",
            taxminiyDavomiyligi = "27:30",
            tartibRaqami = 23
        )
    )

    fun searchAudioDarslar(query: String): List<AudioDarsItem> {
        if (query.isBlank()) return allAudioDarslar
        val trimmed = query.trim()
        return allAudioDarslar.filter { audio ->
            audio.mavzuUz.contains(trimmed, ignoreCase = true) ||
            audio.tavsifUz.contains(trimmed, ignoreCase = true) ||
            audio.audioRaqami.contains(trimmed, ignoreCase = true) ||
            audio.originalArabicTitle.contains(trimmed, ignoreCase = true) ||
            audio.originalFileName.contains(trimmed, ignoreCase = true)
        }
    }
}
