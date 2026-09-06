package com.example.widget

import java.util.Calendar

data class WidgetQuote(
    val id: Int,
    val arabicText: String,
    val uzbekTranslation: String,
    val source: String,
    val chapterId: String,
    val pageNumber: Int
)

object TamhidWidgetQuotes {
    val quotes = listOf(
        WidgetQuote(
            id = 1,
            arabicText = "الْعِلْمُ صِفَةٌ يَتَبَيَّنُ بِهَا الْمَذْكُورُ كَمَا هُوَ",
            uzbekTranslation = "Ilm — narsa qanday bo'lsa, xuddi shunday ochib beradigan sifatdir.",
            source = "1-bob • Ilm va uning ta'rifi",
            chapterId = "ch_01",
            pageNumber = 3
        ),
        WidgetQuote(
            id = 2,
            arabicText = "الْعَالَمُ كُلُّهُ حَادِثٌ بِأَسْرِهِ أَعْيَانُهُ وَأَعْرَاضُهُ",
            uzbekTranslation = "Koinotning barchasi — uning javharlari ham, a'rozlari ham keyin paydo bo'lgan (hodis)dir.",
            source = "2-bob • Olamning yaratilganligi",
            chapterId = "ch_02",
            pageNumber = 11
        ),
        WidgetQuote(
            id = 3,
            arabicText = "إِنَّ لِلْعَالَمِ صَانِعًا وَاحِدًا قَدِيمًا لَا شَرِيكَ لَهُ",
            uzbekTranslation = "Koinotning yagona, qadim va tengi bo'lmagan Yaratuvchisi bordir.",
            source = "3-bob • Yaratuvchining yagonaligi",
            chapterId = "ch_03",
            pageNumber = 20
        ),
        WidgetQuote(
            id = 4,
            arabicText = "لَيْسَ كَمِثْلِهِ شَيْءٌ وَهُوَ السَّمِيعُ الْبَصِيرُ",
            uzbekTranslation = "U Zotga o'xshash hech narsa yo'qdir. U Eshituvchi va Ko'rib turuvchidir.",
            source = "4-bob • Salbiy sifatlar",
            chapterId = "ch_04",
            pageNumber = 35
        ),
        WidgetQuote(
            id = 5,
            arabicText = "الْإِيمَانُ هُوَ التَّصْدِيقُ بِالْقَلْبِ وَالْإِقْرَارُ بِاللِّسَانِ",
            uzbekTranslation = "Iymon — qalb bilan tasdiqlash va til bilan iqror bo'lishdir.",
            source = "15-bob • Iymon va Islom mohiyati",
            chapterId = "ch_15",
            pageNumber = 142
        ),
        WidgetQuote(
            id = 6,
            arabicText = "وَكَلَامُ اللَّهِ تَعَالَى قَدِيمٌ غَيْرُ مَخْلُوقٍ",
            uzbekTranslation = "Alloh taoloning Kalomi qadimdir, maxluq (yaratilgan) emasdir.",
            source = "7-bob • Allohning Kalom sifati",
            chapterId = "ch_07",
            pageNumber = 63
        ),
        WidgetQuote(
            id = 7,
            arabicText = "الْقُرْآنُ كَلَامُ اللَّهِ غَيْرُ مَخْلُوقٍ وَهُوَ مَكْتُوبٌ فِي الْمَصَاحِفِ",
            uzbekTranslation = "Qur'on Allohning yaratilmagan kalomidir va u sahifalarda yozilgandir.",
            source = "8-bob • Qur'on va uning qadimiyligi",
            chapterId = "ch_08",
            pageNumber = 75
        )
    )

    fun getTodayQuote(): WidgetQuote {
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        return quotes[dayOfYear % quotes.size]
    }
}
