package com.example.data.local

import com.example.data.model.*

object TamhidChapters6To10 {
    fun getChapters(): List<Chapter> {
        return listOf(
            // 6-BOB: Ibtal al-tashbih
            Chapter(
                id = "ch_6",
                chapterNumber = 6,
                titleUz = "Tashbihni (o'xshatishni) botil qilish",
                titleAr = "فصل في إبطال التشبيه",
                descriptionUz = "Alloh taoloni yaratilgan maxluqotlarga o'xshatishni rad etish, mutashobih nasslarning sahih ta'vili va tanzihi.",
                pdfStartPage = 56,
                pdfEndPage = 62,
                lessons = listOf(
                    Lesson(
                        id = "ch_6_l_1",
                        chapterId = "ch_6",
                        lessonNumber = 1,
                        titleUz = "Tashbihning botilligi va Mutashobih oyatlarning ta'vili",
                        titleAr = "إبطال التشبيه وتأويل الآيات المتشابهات",
                        summaryUz = "Alloh taologa a'zo va jism sifatlarini berish botildir. Oyatlardagi 'Yad' (qudrat/ne'mat) va 'Ayn' (hifz/rioya) kabi lafzlar muhkam qoidalarga muvofiq tushuniladi.",
                        pdfStartPage = 56,
                        pdfEndPage = 62,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 41,
                                arabicText = "وحُجّةُ أهلِ الحقّ ما ذكرنا ولا حُجّةَ لهم في الآيات لأنّها متشابهاتٌ وردتْ مُخالِفةً بظواهرها للدليلِ القطعيّ العقليّ الذي ذكرنا ووردت مخالفةً للآيةِ المُحْكَمة وهي قوله تعالى: ﴿لَيْسَ كَمِثْلِهِ شَيْءٌ﴾. فالتمسّك بظواهرها يُؤدّي إلى التعارض والتناقض في حُجج الله تعالى! والله تعالى حكيمٌ لا تتناقضُ حُججُه ودلائلُه.",
                                uzbekTranslation = "Haq ahlining hujjati biz aytib o'tgan dalillardir. Ularning (mushabbihalarning) oyatlarga yopishishlarida hech qanday hujjat yo'q, chunki u oyatlar mutashobihotdir (zohiriy ma'nosi jismni eslatuvchi). Ularning zohiri biz aytgan qat'iy aqliy dalillarga hamda: «Unga o'xshash hech narsa yo'qdir» degan muhkam oyatga ziddir. Agar ularning zohiriga yopishilsa, Allohning hujjatlarida ziddiyat paydo bo'ladi. Holbuki Alloh taolo Hakaymdir, Uning hujjatlari aslo zid bo'lmaydi.",
                                pageNumber = 58
                            ),
                            ParagraphItem(
                                paragraphNumber = 42,
                                arabicText = "فإمّا أن نشتغل بتأويلها كما هو المرويّ عن كثير من كبار مشايخنا أنّهم قالوا: «نؤمن بتنزيلها ولا نشتغل بتأويلها». وإمّا أن يُحمَل كلُّ واحد منهما على بعض ما يحتمله اللفظ... فيجب حملُها على وجهٍ يُوافق الدليلَ القطعيَّ العقليَّ والآيةَ المُحْكَمة.",
                                uzbekTranslation = "Biz yo ularning ta'vili bilan shug'ullanmaymiz (tafvizi ma'no), buyuk mashoyixlarimiz aytganidek: «Biz ularning nozil bo'lganiga iymon keltiramiz va ta'viliga sho'ng'imaymiz (tanzih qilamiz)», yoki u lafzlar arab tili ehtimol qilgan ma'nolariga ko'ra qat'iy aqliy dalilga va muhkam oyatga muvofiq tarzda ta'vil qilinadi.",
                                pageNumber = 58
                            ),
                            ParagraphItem(
                                paragraphNumber = 43,
                                arabicText = "وبيانُ ذلك وهو أنّ اليدَ قد تُذكَر للنعمة والقوّة والسَّلْطَنة... وكذلك العينُ قد تُذكَر للحِفْظ... فيُحمَل كلُّ واحدٍ منهما على ما يُوافق الدليلَ العقليَّ والآيةَ المُحْكَمة.",
                                uzbekTranslation = "Buning bayoni shuki: «Yad» (qo'l) so'zi arab tilida ne'mat, quvvat va hukmronlik ma'nolarida ishlatiladi... «Ayn» (ko'z) so'zi esa himoya va hifz ma'nosida ishlatiladi. Shunday qilib, har bir lafz aqliy dalilga va muhkam oyatga mos ma'noda tushuniladi.",
                                pageNumber = 59
                            ),
                            ParagraphItem(
                                paragraphNumber = 47,
                                arabicText = "ثم إنّ صانعَ العالَم لا يُشبهُ العالَمَ ولا يُشبهُ جزءاً من أجزاء العالَم لقوله تعالى: ﴿لَيْسَ كَمِثْلِهِ شَيْءٌ﴾... فلا تقع المشابهة بينهما لأن المشابهة بين الشيئين لا تقع لكونهما موجودين... وإنما تقع المشابهة بين الشيئين لكونهما متماثلين متجانسين.",
                                uzbekTranslation = "So'ngra olamning Yaratuvchisi olamga ham, uning biror bo'lagiga ham aslo o'xshamaydi, zero Alloh: «Unga o'xshash hech narsa yo'qdir» degan. Ikki narsa shunchaki ikkovi ham mavjud bo'lgani uchungina o'xshash bo'lib qolmaydi, balki bir jinsdan va teng bo'lgandagina o'xshashlik vujudga keladi. Alloh esa maxluqotlar jinsidan mutlaqo pokdir.",
                                pageNumber = 61
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_tashbih",
                                termUz = "Tashbih (O'xshatish)",
                                termAr = "التشبيه",
                                definitionUz = "Alloh taoloning zotini yoki sifatlarini maxluqotlarning a'zolari va sifatlariga o'xshatish zalolati.",
                                sourceExample = "فصل في إبطال التشبيه (6-bob, 41-fasl)",
                                pageRef = 58
                            ),
                            ConceptItem(
                                id = "c_muhkam_mutashobih",
                                termUz = "Muhkam va Mutashobih",
                                termAr = "المحكم والمتشابه",
                                definitionUz = "Muhkam — ma'nosi ochiq-ravshan bo'lgan asosiy oyatlar. Mutashobih — zohiri jismni eslatib, qat'iy tanzih va ta'vil talab qiluvchi oyatlar.",
                                sourceExample = "الآية المحكمة: ليس كمثله شيء (6-bob, 41-fasl)",
                                pageRef = 58
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_shura_11",
                                titleUz = "Mutlaq tanzih asosi",
                                type = ProofType.QURAN,
                                arabicText = "﴿لَيْسَ كَمِثْلِهِ شَيْءٌ وَهُوَ السَّمِيعُ الْبَصِيرُ﴾",
                                uzbekTranslation = "«Unga o'xshash hech bir narsa yo'qdir. U o'ta eshitguvchi, o'ta ko'rguvchidir» (Sho'ro, 11).",
                                sourceRef = "Kitob at-Tamhid, 61-bet, 47-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mushabbiha",
                                topicTitle = "Allohga a'zo nisbat berish",
                                ahlSunnahView = "Alloh a'zo, jism va chegaradan munazzahdir. Mutashobih lafzlar tanzih qilinadi.",
                                opposingSchool = "Mushabbiha va Mujassima (Hishom ibn Hakam, Javoliqiy)",
                                opposingView = "Allohni nurlar parchasi yoki inson shaklidagi jism deb e'tiqod qilish.",
                                refutationUz = "Bunday da'vo ochiq kufr va botildir, chunki har qanday a'zo va jism yaratilgandir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_6_1",
                                lessonId = "ch_6_l_1",
                                chapterId = "ch_6",
                                questionUz = "Ahli Sunna val-Jamoa ulamolari Qur'ondagi 'Yad' va 'Ayn' kabi mutashobih lafzlarni qanday tushunadilar?",
                                options = listOf(
                                    "Haqiqiy inson qo'li va ko'zi deb qabul qiladilar",
                                    "Allohni jism va a'zolardan pok tutgan holda, arab tili qoidalariga ko'ra qudrat, ne'mat va hifz deb tanzih va ta'vil qiladilar",
                                    "Bu oyatlarni Qur'ondan o'chirib tashlaydilar",
                                    "Bularning hech qanday ma'nosi yo'q deb hisoblaydilar"
                                ),
                                correctIndex = 1,
                                explanationUz = "Imom al-Lomishiy 43-faslda: 'Yad' ne'mat va quvvat, 'Ayn' esa hifz (saqlash) ma'nosida ekanini bayon qilgan.",
                                sourcePageRef = 59,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_6_2",
                                lessonId = "ch_6_l_1",
                                chapterId = "ch_6",
                                questionUz = "Mushabbiha va Mujassima toifalarining botil da'vosi nima edi?",
                                options = listOf(
                                    "Allohni taqdirni yaratmagan deb da'vo qilish",
                                    "Qur'onni maxluq deb aytish",
                                    "Allohni a'zo va qismlarga ega, insoniy shakldagi jism deb e'tiqod qilish",
                                    "Payg'ambarlikni inkor qilish"
                                ),
                                correctIndex = 2,
                                explanationUz = "Mushabbiha Allohni maxluqotga o'xshatadi, Mujassima esa Unga jism va a'zolar nisbat beradi. Bu botildir.",
                                sourcePageRef = 60,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_6_3",
                                lessonId = "ch_6_l_1",
                                chapterId = "ch_6",
                                questionUz = "Nega a'zolar (qo'l, ko'z, yuz kabi jismoniy qismlar) Alloh taolo haqida qabul qilib bo'lmaydi?",
                                options = listOf(
                                    "Chunki a'zolar bir-biriga muhtojlik va tarkib topganlikni bildiradi, Alloh esa har qanday ehtiyojdan pokdir",
                                    "Chunki arab tilida bunday so'zlar yo'q",
                                    "Chunki faqat farishtalarda a'zolar bor",
                                    "Chunki a'zolar faqat dunyoda kerak bo'ladi"
                                ),
                                correctIndex = 0,
                                explanationUz = "A'zolar qismlarga bo'linish va ehtiyojni taqozo qiladi. Azaliy va Qodir Zot esa ehtiyoj va nuqsondan mutlaqo munazzahdir.",
                                sourcePageRef = 61,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 7-BOB: Nafy al-makan val-jiha
            Chapter(
                id = "ch_7",
                chapterNumber = 7,
                titleUz = "Makon va jihatni inkor qilish (Tanzih)",
                titleAr = "فصل في نفي المكان والجهة",
                descriptionUz = "Alloh taoloning makonga va 6 ta jihatga muhtoj emasligi, 'Arshga istivo' oyatining ahli sunna ta'vili.",
                pdfStartPage = 62,
                pdfEndPage = 65,
                lessons = listOf(
                    Lesson(
                        id = "ch_7_l_1",
                        chapterId = "ch_7",
                        lessonNumber = 1,
                        titleUz = "Alloh taoloni makon va jihatdan poklash",
                        titleAr = "نفي المكان والجهة والاستواء على العرش",
                        summaryUz = "Alloh taolo makon yaratilishidan oldin ham bor edi, hozir ham makonga muhtoj emas. 'Arshga istivo' egallash (istilo) va qudrat ma'nosidadir.",
                        pdfStartPage = 62,
                        pdfEndPage = 65,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 49,
                                arabicText = "ثم إنّ الصانعَ - جلّ وعلا! - لا يُوصَفُ بالمكان لما مرّ أنّه لا مشابهةَ بينه وبين شيءٍ من أجزاء العالم. فلو كان مُتمكّناً بمكانٍ لوقعت المشابهةُ بينه وبين المكان من حيث المقدار لأن مكان كل متمكنٍ قدر ما يتمكّن فيه... ولأنه لو كان لم يزل في المكان لكان المكانُ قديماً أزلياً، ولو كان خُلقَ المكانُ وتمكّن فيه لتغيّر عن حاله ولحدثت فيه صفة التمكّن بعد أن لم تكن، وقَبولُ الحوادث من أمارات الحَدَث.",
                                uzbekTranslation = "So'ngra Yaratuvchi Zot makon bilan sifatlanmaydi. Chunki U bilan olam bo'laklaridan biror narsa o'rtasida o'xshashlik yo'qdir. Agar U biror makonda o'rnashgan bo'lsa, miqdor jihatidan U bilan makon o'rtasida o'xshashlik paydo bo'lar edi... Agar U azaldan makonda bo'lganida, makon ham azaliy bo'lib qolar edi; agar makon keyin yaratilib, Alloh unga o'rnashgan bo'lsa, U Zot o'zgarishga uchragan va Unda yangi sifat paydo bo'lgan bo'lar edi. O'zgarishni qabul qilish esa yaratilganlik alomatidir.",
                                pageNumber = 62
                            ),
                            ParagraphItem(
                                paragraphNumber = 55,
                                arabicText = "ووجهُ ذلك أنّ الاستواءَ قد يُذكر ويُراد به الاستقرار، وقد يُذكر ويُراد به الاستيلاءُ فيُحمل على الاستيلاء دفعاً للتناقض. وإنّما خصّ العرشَ بالذكر تعظيماً له كما خصّه بالذكر في قوله تعالى: ﴿وَهُوَ رَبُّ الْعَرْشِ الْعَظِيمِ﴾ وإن كان هو ربّ كل شيء.",
                                uzbekTranslation = "Buning bayoni shuki: «Istivo» lafzi arab tilida biror joyda qaror topish ma'nosida ham, butunlay qamrab olib hukmron bo'lish (istilo/g'alaba) ma'nosida ham keladi. Ziddiyatni daf qilish uchun u istilo (hukmronlik) ma'nosiga yo'yiladi. Arshning xoslab zikr qilinishi esa, uning ulug' mahluq bo'lgani uchundir, xuddi: «U ulug' Arshning Robbisi» (Tavba, 129) deyilgandek, holbuki U barcha narsaning Robbisidir.",
                                pageNumber = 64
                            ),
                            ParagraphItem(
                                paragraphNumber = 57,
                                arabicText = "وكذا الفَوْقُ يُذكر ويُراد به العُلُوّ من حيث المكانُ ويُذكر ويُراد به العلوّ من حيث الرتبة، فيُحمل على العلوّ من حيث الرتبة دفعاً للتعارض.",
                                uzbekTranslation = "Shuningdek, «Favq» (ustunlik) so'zi makon jihatidan balandlik ma'nosida ham, martaba va qadr jihatidan oliylik ma'nosida ham keladi. Ziddiyatni ketkazish uchun u martaba va qudrat jihatidan ustunlikka yo'yiladi.",
                                pageNumber = 64
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_istivo",
                                termUz = "Istivo",
                                termAr = "الاستواء",
                                definitionUz = "O'rnashish emas, balki Alloh taoloning Arsh va barcha borliq ustidan mutlaq qudrat va hukmronligi (istilo).",
                                sourceExample = "يُحمل على الاستيلاء دفعاً للتناقض (7-bob, 55-fasl)",
                                pageRef = 64
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_makan_aqli",
                                titleUz = "Allohning makondan xoli ekanligi aqliy dalili",
                                type = ProofType.AQLIY,
                                arabicText = "لو تمكّن فيه لتغيّر عن حاله، وقبول الحوادث من أمارات الحدث",
                                uzbekTranslation = "Makonga o'rnashish o'zgarishni taqozo qiladi, o'zgaruvchi har qanday narsa esa yaratilgandir.",
                                sourceRef = "Kitob at-Tamhid, 62-bet, 49-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_jiha",
                                topicTitle = "Allohga yuqori yoki pastki makon nisbati",
                                ahlSunnahView = "Alloh barcha makon va jihatlardan munazzahdir. Duo qilishda qo'llarni ko'kka ko'tarish, osmon duo qiblasi bo'lgani uchundir.",
                                opposingSchool = "Karromiya va Mujassima",
                                opposingView = "Alloh Arsh ustida o'tiradi yoki yuqori tomonda joylashgan.",
                                refutationUz = "Ka'ba namoz qiblasi bo'lganidek, osmon duo qiblasidir, Alloh Ka'bada ham, osmonda ham jismoniy joylashmagan."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_7_1",
                                lessonId = "ch_7_l_1",
                                chapterId = "ch_7",
                                questionUz = "Duo qilganda qo'llarni osmonga qaratib ko'tarishning asl sababi nima?",
                                options = listOf(
                                    "Chunki Alloh faqat osmonda joylashgan deb hisoblanadi",
                                    "Chunki farishtalar faqat osmonda yashaydi",
                                    "Chunki osmon duoning qiblasidir (xuddi Ka'ba namozning qiblasi bo'lgani kabi)",
                                    "Chunki qo'l charchamasligi uchun"
                                ),
                                correctIndex = 2,
                                explanationUz = "Lomishiy 57-fasl sharhida: 'Osmon duoning qiblasidir, Ka'ba namoz qiblasi bo'lganidek', deb ta'kidlaydi.",
                                sourcePageRef = 65,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_7_2",
                                lessonId = "ch_7_l_1",
                                chapterId = "ch_7",
                                questionUz = "Alloh taoloning 'Arshga istivo' qilishi haqida Ahli Sunna ulamolarining e'tiqodi qanday?",
                                options = listOf(
                                    "Moddiy o'tirish yoki jismoniy joylashish emas, balki g'alaba, qudrat va oliy hukmronlik (istilo) ma'nosidadir",
                                    "Arsh ustida jismoniy o'tirgan deb tushuniladi",
                                    "Arsh Allohdan kattaroq jism deb hisoblanadi",
                                    "Bu oyatni umuman o'qimaslik kerak"
                                ),
                                correctIndex = 0,
                                explanationUz = "Al-Lomishiy 54-faslda: Istivo jismoniy qaror topish emas, balki qahr, sulton va qudrat ma'nosida ekanini ta'kidlaydi.",
                                sourcePageRef = 63,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_7_3",
                                lessonId = "ch_7_l_1",
                                chapterId = "ch_7",
                                questionUz = "Hazrati Ali (r.a.)ning Alloh va makon haqidagi mashhur so'zlari qaysi?",
                                options = listOf(
                                    "«Alloh har bir toshning ichida mavjuddir»",
                                    "«Alloh makon yo'q bo'lganida ham bor edi va U hozir ham avval qanday bo'lsa shundaydir (makondan behojatdir)»",
                                    "«Alloh yettinchi osmonning tepasidadir»",
                                    "«Makon qadimiy, Alloh esa zamonaviydir»"
                                ),
                                correctIndex = 1,
                                explanationUz = "Ushbu mashhur qoida Alloh taolo makon yaratilishidan oldin ham bor bo'lganini va keyin ham makonga muhtoj bo'lmasligini isbotlaydi.",
                                sourcePageRef = 64,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 8-BOB: As-Sifat val-Asma
            Chapter(
                id = "ch_8",
                chapterNumber = 8,
                titleUz = "Sifatlar va ismlarning sobitligi",
                titleAr = "فصل في إثبات الصِّفات والأسماء",
                descriptionUz = "Alloh taoloning azaliy zotiy va fe'liy sifatlari (Hayot, Ilm, Qudrat, Sam', Basar, Iroda, Kalom, Takvin) va Ism-Musammo masalasi.",
                pdfStartPage = 65,
                pdfEndPage = 70,
                lessons = listOf(
                    Lesson(
                        id = "ch_8_l_1",
                        chapterId = "ch_8",
                        lessonNumber = 1,
                        titleUz = "Azaliy sifatlar va Mu'tazilaga raddiya",
                        titleAr = "إثبات صفات الذات وقدمها والرد على نفاة الصفات",
                        summaryUz = "Alloh taolo azaliy sifatlarga egadir. Uning sifatlari Zotining ayni ham emas, Zotidan butunlay ajralgan boshqa narsa ham emasdir.",
                        pdfStartPage = 65,
                        pdfEndPage = 70,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 59,
                                arabicText = "قال أهلُ السنّة والجماعة: «إنّ لصانع العالَم حياةً وعِلماً وقُدرةً وسمعاً وبصراً» لقوله تعالى: ﴿وَلَا يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ﴾ ولقوله تعالى: ﴿أَنزَلَهُ بِعِلْمِهِ﴾ ولقوله تعالى: ﴿إِنَّ اللَّهَ هُوَ الرَّزَّاقُ ذُو الْقُوَّةِ الْمَتِينُ﴾.",
                                uzbekTranslation = "Ahli Sunna val-Jamoa aytdilar: «Albatta olamning Yaratuvchisi Zotiga loyiq Hayot, Ilm, Qudrat, Eshitish va Ko'rish sifatlariga egadir». Zero Alloh taolo: «Uning ilmidan hech narsani qamrab ololmaslar» (Baqara, 255), «Uni O'z ilmi bilan nozil qildi» (Niso, 166) va «Albatta Allohning O'zi cheksiz rizq beruvchi, kuch-quvvat sohibidir» (Zoriyot, 58) deb marhamat qilgan.",
                                pageNumber = 65
                            ),
                            ParagraphItem(
                                paragraphNumber = 62,
                                arabicText = "وحُجّةُ أهل السنّة والجماعة... أن يُقال: إنّ صفاتِ الله - تعالى! - لا هُو ولا غَيرُه، كالواحد من العَشَرة لا يكون غيرَ العشرة ولا عينَ العشرة لاستحالة بقائه بدونها وبقائها بدونه... وإنّه تعالى بذاته وصفاته قديمٌ لأن كل صفة منها بانفرادها قديمة.",
                                uzbekTranslation = "Ahli Sunna val-Jamoaning hujjati shuki: «Alloh taoloning sifatlari Uning ayni ham emas, Undan o'zga (g'ayri) ham emasdir» («Lā huva va lā g'ayruh»). Xuddi o'ntalikdagi «bir» soni o'nning o'zi ham bo'lmagani, undan mutlaqo begona boshqa narsa ham bo'lmagani kabi. Alloh taolo Zoti va barcha sifatlari bilan birga qadimdir (azaliydir), chunki Uning har bir sifati alohida azaliydir.",
                                pageNumber = 67
                            ),
                            ParagraphItem(
                                paragraphNumber = 65,
                                arabicText = "ثمّ الاسمُ والمُسَمّى واحدٌ عند أهل السنّة والجماعة لقوله تعالى: ﴿سَبِّحِ اسْمَ رَبِّكَ الْأَعْلَى﴾ الذي أمرنا بالتسبيح لاسمه، ولو كان الاسمُ غيرَ المسمّى لكان هذا أمراً بالتسبيح لغير الله تعالى!",
                                uzbekTranslation = "So'ngra Ahli Sunna val-Jamoa nazdida Ism bilan Musammo (nomlangan zot) bittadir. Chunki Alloh: «Eng oliy Robbingning ismini poklab yod et!» (A'lo, 1) degan. Agar ism nomlangandan o'zga narsa bo'lganida, bu buyruq Allohdan boshqasini tasbeh qilishga buyruq bo'lib qolar edi.",
                                pageNumber = 68
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_sifat_azaliya",
                                termUz = "La huva va la g'ayruh",
                                termAr = "لا هو ولا غيره",
                                definitionUz = "Moturidiyya aqidasida sifatlarning Zotga nisbati qoidasi: Sifatlar Zotning aynan o'zi ham emas, Zotdan ajralib turadigan alohida vujud ham emasdir.",
                                sourceExample = "إنّ صفاتِ الله تعالى لا هو ولا غيره (8-bob, 62-fasl)",
                                pageRef = 67
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_sifat_nahl",
                                titleUz = "Allohning ilm va qudrat sifatlari",
                                type = ProofType.QURAN,
                                arabicText = "﴿أَنزَلَهُ بِعِلْمِهِ﴾ ، ﴿ذُو الْقُوَّةِ الْمَتِينُ﴾",
                                uzbekTranslation = "«Uni O'z ilmi bilan tushirdi», «U quvvat sohibi, Metindir».",
                                sourceRef = "Kitob at-Tamhid, 65-bet, 59-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mutazila_sifat",
                                topicTitle = "Sifatlarning mavjudligi",
                                ahlSunnahView = "Alloh azaliy sifatlar bilan sifatlangandir (Alimun bi-ilmih, Qodirun bi-qudratih).",
                                opposingSchool = "Mu'tazila va Jahmiya",
                                opposingView = "Allohning alohida sifatlari yo'q, U zoti bilangina biladi, zoti bilangina qodirdir (ta'til).",
                                refutationUz = "Sifatlarni inkor qilish Qur'on oyatlariga ochiq ziddir va Zotni ma'nosiz qoldirishga olib keladi."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_8_1",
                                lessonId = "ch_8_l_1",
                                chapterId = "ch_8",
                                questionUz = "Ahli Sunna val-Jamoa e'tiqodida Allohning sifatlari Uning Zotiga nisbatan qanday e'tirof etiladi?",
                                options = listOf(
                                    "Zotining aynan o'zidir",
                                    "Zotidan butunlay ajralgan yaratilgan narsalardir",
                                    "Faqat insonlarning tasavvuridir",
                                    "«La huva va la g'ayruh» (Uning ayni ham emas, Undan o'zga ham emas)"
                                ),
                                correctIndex = 3,
                                explanationUz = "Imom al-Lomishiy 62-faslda: 'Sifatlar Uning ayni ham emas, g'ayri ham emas' deb bayon qilgan.",
                                sourcePageRef = 67,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_8_2",
                                lessonId = "ch_8_l_1",
                                chapterId = "ch_8",
                                questionUz = "Mu'taziliylarning 'Sifatlarni tan olish ko'p qadimiylarni (taaddud al-qudamo) keltirib chiqaradi' degan shubhasiga qanday javob beriladi?",
                                options = listOf(
                                    "Sifatlar mustaqil ilohlar emas, balki yagona Zot bilan qoyim bo'lgan azaliy sifatlardir",
                                    "Ular bilan bahslashilmaydi",
                                    "Sifatlar yaratilgan deb qabul qilinadi",
                                    "Mu'taziliylarning da'vosi to'g'ri deb tan olinadi"
                                ),
                                correctIndex = 0,
                                explanationUz = "Qadimiy Zotlar ko'paymaydi, chunki sifatlar alohida zot emas, balki yagona Azaliy Zotning komil sifatlaridir.",
                                sourcePageRef = 68,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_8_3",
                                lessonId = "ch_8_l_1",
                                chapterId = "ch_8",
                                questionUz = "Kalom ilmida 'Ism va Musammo' (nom va nomlangan Zot) masalasida Moturidiylar fikri qanday?",
                                options = listOf(
                                    "Ism musammodan butunlay boshqa narsadir",
                                    "Allohning ismlari Unga nisbatan musammoning (Zotning) ayni bo'lib, lafziy atash esa tasmiyadir",
                                    "Allohning ismlari insonlar tomonidan o'ylab topilgan",
                                    "Ismlar faqat Qur'on tilidagina amal qiladi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy bayon qiladi: Zotiy ismlar (Alloh, Rahmon) musammodan ajralmasdir, talaffuz esa tasmiya deb ataladi.",
                                sourcePageRef = 69,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 9-BOB: Azaliyat Kalamullah
            Chapter(
                id = "ch_9",
                chapterNumber = 9,
                titleUz = "Alloh taoloning Kalomining azaliyligi",
                titleAr = "فصل في أزلية كلام الله تعالى",
                descriptionUz = "Kalomullohning azaliy zotiy sifat ekani, harf va tovushlardan xoli nafsiyligi hamda Qur'onning maxluq emasligi.",
                pdfStartPage = 70,
                pdfEndPage = 74,
                lessons = listOf(
                    Lesson(
                        id = "ch_9_l_1",
                        chapterId = "ch_9",
                        lessonNumber = 1,
                        titleUz = "Kalom sifati va Qur'onning azaliyligi",
                        titleAr = "أزلية كلام الله تعالى ونفي خلقه",
                        summaryUz = "Allohning Kalomi Uning Zoti bilan qoyim bo'lgan azaliy sifatdir. U harf va tovushlar jinsidan emas. Qur'on maxluq emasdir.",
                        pdfStartPage = 70,
                        pdfEndPage = 74,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 70,
                                arabicText = "ثم إنّ كلامَ الله - تعالى! - صفةٌ أزليةٌ لله تعالى عند أهل السنة والجماعة. وقالت المعتزلة: «إنّه مُحدَثٌ مَخلوقٌ». والخلافُ بيننا وبينهم في الحقيقة في ماهية الكلام. فعندنا كلامُ الله صفةٌ أزليةٌ قائمةٌ بذات الله تعالى ليس من جنس الحروف والأصوات وأنّه واحدٌ غيرُ متجزّىء.",
                                uzbekTranslation = "So'ngra Alloh taoloning Kalomi Ahli Sunna val-Jamoa nazdida Alloh taoloning azaliy sifatidir. Mu'taziliylar esa: «U yangidan paydo qilingan (muhdas), maxluqdir», dedilar. Biz bilan ular o'rtasidagi ixtilof aslida Kalomning mohiyati haqidadir. Bizning nazdimizda Allohning Kalomi — Alloh taoloning Zoti bilan qoyim bo'lgan, harflar va tovushlar jinsidan bo'lmagan, bo'linmaydigan yagona azaliy sifatdir.",
                                pageNumber = 70
                            ),
                            ParagraphItem(
                                paragraphNumber = 71,
                                arabicText = "والدليلُ على أنّ كلام الله صفةٌ أزلية قائمة بذاته أنّه لو كان حادثاً لكان الحالُ لا يخلو إما أن يكون حادثاً في ذاته أو في محلٍّ آخرَ أو لا في محلّ. والكُلُّ باطلٌ. أمّا الأول فلأنّ ذات الله ليست بمحلٍّ للحوادث. وأمّا الثاني فلأن صيرورة الذات متكلّمة بكلام قام بمحلّ آخر كصيرورة الذات ذهاباً بذهاب قام بمحلّ آخر وهو باطل. وأمّا الثالث فلأنّ قيام العرَض لا في محلّ مُحالٌ.",
                                uzbekTranslation = "Allohning Kalomi Uning Zotida qoyim azaliy sifat ekanining dalili shuki: agar u yaratilgan (hodis) bo'lganida, yo Uning Zotida paydo bo'lgan, yoki boshqa bir o'rinda paydo bo'lgan, yoki biron o'rinsiz paydo bo'lgan bo'lar edi. Bu uchtasi ham botildir: 1) Allohning Zoti hodisalar o'rni bo'lishdan pokdir; 2) Boshqa joyda paydo bo'lgan kalom bilan Allohni 'so'zlovchi' deyish mantiqan botildir; 3) A'rozning biror o'rinsiz mavjud bo'lishi esa aqlan mutlaqo mustahildir.",
                                pageNumber = 71
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_kalam_nafsi",
                                termUz = "Kalomun Nafsiy (Nafsiy Kalom)",
                                termAr = "الكلام النفسي",
                                definitionUz = "Alloh taoloning Zotida qoyim bo'lgan, harf, ovoz, til va zamonga muhtoj bo'lmagan azaliy so'zlash sifati.",
                                sourceExample = "صفة أزلية قائمة بذات الله ليس من جنس الحروف والأصوات (9-bob, 70-fasl)",
                                pageRef = 70
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_kalam_aqli",
                                titleUz = "Kalomning azaliyligiga 3 ta bo'limli aqliy dalil",
                                type = ProofType.AQLIY,
                                arabicText = "لو كان حادثاً لكان إما في ذاته أو في محل آخر أو لا في محل، والكل باطل",
                                uzbekTranslation = "Agar kalom yaratilgan bo'lsa, yo Zotda, yo o'zgada, yo o'rinsiz bo'lishi kerak edi. Barchasi botildir, demak Kalom azaliydir.",
                                sourceRef = "Kitob at-Tamhid, 71-bet, 71-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_xalq_quran",
                                topicTitle = "Qur'onning maxluqligi masalasi",
                                ahlSunnahView = "Qur'on — Allohning azaliy Kalomidir, maxluq emasdir.",
                                opposingSchool = "Mu'tazila",
                                opposingView = "Qur'on yaratilgan, harf va ovozlardan iborat maxluqdir.",
                                refutationUz = "Alloh taoloning so'zlash sifati azaliydir, uni maxluq deyish Allohni sukut va ojizlik bilan sifatlash bo'lib qoladi."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_9_1",
                                lessonId = "ch_9_l_1",
                                chapterId = "ch_9",
                                questionUz = "Ahli Sunna val-Jamoa e'tiqodida Allohning Kalom sifati qanday vasf qilinadi?",
                                options = listOf(
                                    "Keyin yaratilgan harf va tovushlardir",
                                    "Allohning Zotida qoyim, harf va tovushlardan xoli bo'lgan azaliy sifatdir",
                                    "Jabroil alayhissalomning o'z so'zidir",
                                    "Vaqtinchalik yaratilgan a'rozdir"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 70-faslda: 'Kalomulloh — Allohning zotida qoyim bo'lgan, harf va ovoz jinsidan bo'lmagan azaliy sifatdir', deb uqtirgan.",
                                sourcePageRef = 70,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_9_2",
                                lessonId = "ch_9_l_1",
                                chapterId = "ch_9",
                                questionUz = "Mu'tazila toifasining 'Qur'on maxluqdir' degan botil da'vosiga Ahli Sunna qanday raddiya beradi?",
                                options = listOf(
                                    "Allohning Kalomi Uning azaliy sifati bo'lib, Allohning sifati esa maxluq bo'lishi aslo mumkin emas",
                                    "Mu'tazila bilan faqat siyosiy kelishuv tuziladi",
                                    "Qur'on faqat sahifalar deb e'lon qilinadi",
                                    "Bu masalada sukut saqlash buyurilgan"
                                ),
                                correctIndex = 0,
                                explanationUz = "Agar Allohning Kalomi maxluq bo'lsa, Alloh bir vaqtlar so'zlash sifatidan mahrum (soqov) bo'lgan degan botil xulosa kelib chiqadi.",
                                sourcePageRef = 72,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_9_3",
                                lessonId = "ch_9_l_1",
                                chapterId = "ch_9",
                                questionUz = "Kalom nafsi va til bilan o'qiladigan tilovati o'rtasidagi farq nima?",
                                options = listOf(
                                    "Hech qanday farq yo'q",
                                    "Kalom nafsi — Allohning azaliy sifati, tilovat va qog'ozdagi yozuv esa maxluq bandaning harakati va vositalaridir",
                                    "Tilovat azaliy, kalom nafsi esa hodisdir",
                                    "Faqat arab tilida o'qilganigina kalom hisoblanadi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Imom al-Lomishiy ta'kidlaydi: O'qish (qiroat) bandaning yaratilgan fe'li, o'qilayotgan ilohiy ma'no (maqru') esa azaliy Kalomullohdir.",
                                sourcePageRef = 73,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 10-BOB: At-Takvin
            Chapter(
                id = "ch_10",
                chapterNumber = 10,
                titleUz = "Takvin sifati va uning azaliyligi",
                titleAr = "فصل في أن التكوين غير المكوَّن",
                descriptionUz = "Moturidiyya maktabining eng muhim xususiyatlaridan biri: Takvin sifati azaliy ekani va yaratilgan narsa (mukavvan)dan boshqa ekani.",
                pdfStartPage = 74,
                pdfEndPage = 78,
                lessons = listOf(
                    Lesson(
                        id = "ch_10_l_1",
                        chapterId = "ch_10",
                        lessonNumber = 1,
                        titleUz = "Takvin Mukavvandan boshqadir",
                        titleAr = "التكوين غير المكون وأزلية صفة الفعل",
                        summaryUz = "Takvin — Alloh taoloning yo'qdan bor qilish azaliy sifatidir. U yaratilgan narsalarning ayni emas, balki azaliy fe'liy asosidir.",
                        pdfStartPage = 74,
                        pdfEndPage = 78,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 75,
                                arabicText = "قال أهلُ السنة والجماعة: «التكوينُ غيرُ المُكَوَّن. وإنّه صفةٌ أزليّةٌ لله تعالى». وكذلك غيرُه من صفات الله تعالى لأنّها لو حدثت لا يخلو إما أن حدثت في ذات الله تعالى أو في محلٍّ آخرَ أو لا في محلّ. وكلُّ ذلك باطلٌ.",
                                uzbekTranslation = "Ahli Sunna val-Jamoa (Moturidiylar) aytdilar: «Takvin (yaratish/paydo qilish) yaratilgan narsa (mukavvan)ning o'zi emas, balki undan boshqadir. U Alloh taoloning azaliy sifatidir». Shuningdek boshqa fe'liy sifatlar (taraqqiy ettirish, rizq berish, tiriltirish) ham shundaydir.",
                                pageNumber = 74
                            ),
                            ParagraphItem(
                                paragraphNumber = 79,
                                arabicText = "والحاصلُ أنّ جميعَ صِفات الله تعالى أزليّةٌ عند أهل السنّة والجماعة، سواءٌ كانت من صِفات الذات كالحياة والعِلم والقُدرة والسمع والبصر، أو من صِفات الفِعل كالتكوين والخَلْق والإيجاد والإحداث والإماتة والإحياء والرزق ونحوها.",
                                uzbekTranslation = "Xulosa shuki, Ahli Sunna val-Jamoa (Moturidiylar) nazdida Alloh taoloning barcha sifatlari azaliydir: xoh u Zotiy sifatlar bo'lsin (Hayot, Ilm, Qudrat, Sam', Basar), xoh Fe'liy sifatlar bo'lsin (Takvin, Xalq, Iyjod, Ehdos, O'ldirish, Tiriltirish, Rizqlantirish va hokazo).",
                                pageNumber = 75
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_takvin",
                                termUz = "Takvin (Yaratish sifati)",
                                termAr = "التكوين",
                                definitionUz = "Alloh taoloning borliqni yo'qdan bor qilishdagi azaliy fe'liy sifati (Moturidiya mazhabining 8-sifati).",
                                sourceExample = "التكوين غير المكوّن وإنه صفة أزلية لله تعالى (10-bob, 75-fasl)",
                                pageRef = 74
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_takvin_dalil",
                                titleUz = "Fe'liy sifatlarning azaliyligi",
                                type = ProofType.AQLIY,
                                arabicText = "جميع صفات الله أزلية سواء كانت من صفات الذات أو من صفات الفعل",
                                uzbekTranslation = "Alloh taoloning Zotida yangi sifatlar paydo bo'lishi imkonsiz bo'lgani sababli barcha sifatlar azaliydir.",
                                sourceRef = "Kitob at-Tamhid, 75-bet, 79-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_takvin_ashari_mutazila",
                                topicTitle = "Takvin sifati azaliyligi",
                                ahlSunnahView = "Takvin azaliydir va u yaratilgan maxluqdan (mukavvandan) boshqadir.",
                                opposingSchool = "Ash'ariylar (ayrim qarashlari) va Mu'taziliylar",
                                opposingView = "Takvin — mukavvanning o'zi, ya'ni hodisdir.",
                                refutationUz = "Sindirish sindirilgan narsadan boshqa bo'lganidek, Yaratish ham yaratilgan narsadan mutlaqo boshqadir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_10_1",
                                lessonId = "ch_10_l_1",
                                chapterId = "ch_10",
                                questionUz = "Imom Moturidiy va Imom al-Lomishiy nazdida 'Takvin' sifati haqidagi to'g'ri hukm qaysi?",
                                options = listOf(
                                    "Takvin maxluq bilan birga keyin paydo bo'ladi",
                                    "Takvin faqat insonlarning aqliy iborasidir",
                                    "Takvin — mukavvandan boshqadir va u Allohning azaliy sifatidir",
                                    "Allohda takvin degan sifat umuman yo'q"
                                ),
                                correctIndex = 2,
                                explanationUz = "Moturidiya aqidasida Takvin azaliy mustaqil sifat bo'lib, yaratilgan narsa (mukavvan)dan o'zgadir.",
                                sourcePageRef = 74,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_10_2",
                                lessonId = "ch_10_l_1",
                                chapterId = "ch_10",
                                questionUz = "Moturidiylikda 'Takvin' sifatiga qaysi fe'liy sifatlar kiradi?",
                                options = listOf(
                                    "Faqat ko'rish va eshitish",
                                    "Tiriltirish (ihyo), o'ldirish (imota), rizq berish (tarziq), yaratish (xalq)",
                                    "Faqat jannatni yaratish",
                                    "Insonlarning niyatlari"
                                ),
                                correctIndex = 1,
                                explanationUz = "Takvin azaliy sifat bo'lib, uning barcha fe'liy ko'rinishlari (ihyo, imota, tarziq, tasvir) ushbu sifatga taalluqlidir.",
                                sourcePageRef = 75,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_10_3",
                                lessonId = "ch_10_l_1",
                                chapterId = "ch_10",
                                questionUz = "'Sindirish sindirilgan narsadan boshqa bo'lganidek, Yaratish ham yaratilgan narsadan o'zgadir' qiyosi nimani isbotlaydi?",
                                options = listOf(
                                    "Sindirish moddiy hodisa ekanini",
                                    "Idishlarning sinuvchanligini",
                                    "Takvin (yaratish) yaratilgan narsaning o'zi emas, balki Allohning mustaqil azaliy sifati ekanini",
                                    "Olamning qadimiy ekanini"
                                ),
                                correctIndex = 2,
                                explanationUz = "Al-Lomishiy mashhur 'al-kasru g'ayrul-maksur' qoidasi orqali Takvin mukavvandan mutlaqo boshqa azaliy sifat ekanini isbotlaydi.",
                                sourcePageRef = 76,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            )
        )
    }
}
