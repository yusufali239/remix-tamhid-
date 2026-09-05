package com.example.data.local

import com.example.data.model.*

object TamhidChapters1To5 {
    fun getChapters(): List<Chapter> {
        return listOf(
            // 1-BOB: Haqoiq al-ashya
            Chapter(
                id = "ch_1",
                chapterNumber = 1,
                titleUz = "Narsalar haqiqatining sobitligi",
                titleAr = "فصل في ثبوت حقائق الأشياء",
                descriptionUz = "Olamdagi narsalarning haqiqati borligi, sofistlar (safsataviylar) va shubhachilarning qarashlariga raddiya.",
                pdfStartPage = 39,
                pdfEndPage = 41,
                lessons = listOf(
                    Lesson(
                        id = "ch_1_l_1",
                        chapterId = "ch_1",
                        lessonNumber = 1,
                        titleUz = "Haqiqatning sobitligi va Safsataviylarga raddiya",
                        titleAr = "ثبوت حقائق الأشياء والرد على السوفسطائية",
                        summaryUz = "Ahli haq mazhabi: Narsalarning haqiqati sobitdir. Sofistlar va inkorchilarning shubhalari botildir.",
                        pdfStartPage = 39,
                        pdfEndPage = 41,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 1,
                                arabicText = "الحمد لله الذي نصر أهل الحق بالسيوف القاطعة، وأمدّ أهل السُّنّة بالحُجج الساطعة، والصلاة على رسوله المؤيد بالمعجزات الظاهرة، وعلى آله وأصحابه الموسومين بالأخلاق الطاهرة، والعلوم الوافرة!",
                                uzbekTranslation = "Haq ahliga o'tkir qilichlar bilan nusrat bergan, sunnat ahlini ravshan hujjatlar bilan quvvatlagan Allohga hamd bo'lsin! Ochiq-oydin mo'jizalar bilan qo'llab-quvvatlangan Rasuliga, shuningdek, pokiza axloq va serob ilmlar bilan sifatlangan u zotning ahli bayti va sahobalariga salotu salomlar bo'lsin!",
                                pageNumber = 39
                            ),
                            ParagraphItem(
                                paragraphNumber = 2,
                                arabicText = "أمّا بعد! فإنّ حقائقَ الأشياءِ ثابتةٌ. وهو مذهبُ عامّةِ العُقَلاءِ. وقالت طائفةٌ من السوفسطائيّة: «لا حقيقةَ للأشياء». وشُبهتهم أنّ الأحول يرى الشيءَ شيئين، وغيرُه واحداً، ومن به صفراء غالبةٌ يجد الشيءَ الحُلوَ مُرّاً وغيرُه يجده حُلواً. فظهر بهذا أن لا حقيقة للأشياء لأن حقيقة الشيء لا تتعدّد بينه وبين غيره ولا تختلف.",
                                uzbekTranslation = "Ammo ba'd! Albatta narsalarning haqiqatlari sobitdir (mavjuddir). Bu barcha oqillarning mazhabidir. Safsataviylardan (sofistlardan) bir toifa: «Narsalarning haqiqati yo'q», dedilar. Ularning shubhasi shuki: g'ilay kishi bir narsani ikkita ko'radi, boshqasi esa bitta ko'radi; zardasi (o'ti) oshgan kishi shirin narsani achchiq sezadi, boshqasi esa uni shirin topadi. Ularning da'vosicha, narsaning haqiqati o'zi bilan boshqasi o'rtasida ko'paymaydi va o'zgarmaydi, demak narsalarning haqiqati yo'qdir.",
                                pageNumber = 39,
                                footnotes = "Lomishiy bu yerda Moturidiyya kalomidagi eng birinchi gnoseologik tamoyilni qo'ymoqda."
                            ),
                            ParagraphItem(
                                paragraphNumber = 3,
                                arabicText = "قُلنا لهم: هل لمذهبكم هذا حقيقةٌ وهل لنفيكم حقائقَ الأشياء حقيقةٌ؟ فإن قالوا: «لا!» فقد تركوا مذهبهم وأقرّوا ببُطلان دعواهم! وإن قالوا: «نعم!» فقد أقرّوا بحقيقة مذهبهم وبحقيقة نفيهم حقائقَ الأشياء! ومتى أقرّوا بها فقد أثبتوا حقيقة بعض الأشياء وتبيّن بهذا أنّ نافيَها مُثْبِتُها وأنّ في نفيها ثُبوتَها فتكون ثابتةً ضرورةً. ولا حُجّة لهم في من يرى الشيء شيئين ويجد الحلو مرّاً لأنّ النزاع في الحواسّ السليمة، وحاسّةُ هذين ليست بسليمة.",
                                uzbekTranslation = "Biz ularga aytdik: «Sizning bu mazhabingizning (qarashingizning) haqiqati bormi va narsalar haqiqatini inkor qilishingizning o'zi bir haqiqatmi?» Agar ular «Yo'q» desalar, o'z mazhablarini tark etgan va da'volarining botilligini tan olgan bo'ladilar! Agar ular «Ha» desalar, o'z mazhablarining haqiqatini va inkorlarining haqiqat ekanini tan olgan bo'ladilar. Buni tan olgan zahotiyoq ba'zi narsalarning haqiqatini isbotlagan bo'ladilar. Shunday qilib, haqiqatni inkor qiluvchining o'zi uni isbotlovchiga aylanadi. G'ilay va xasta kishining holati esa hujjat bo'lmaydi, chunki bahs sog'lom his-tuyg'ular haqidadir, bu ikkisining hissi esa nosog'lomdir.",
                                pageNumber = 40
                            ),
                            ParagraphItem(
                                paragraphNumber = 4,
                                arabicText = "وقالت طائفة أخرى منهم: «لا ندري هل للأشياء حقيقة أم لا!» وهم المتشكّكون. وقلنا لهم: هل لقولكم: «لا ندري» حقيقةٌ أم لا؟ فإن قالوا: «لا!» فلا مناظرة معهم. وإن قالوا: «نعم!» فقد أثبتوا حقيقة شيء من الأشياء.",
                                uzbekTranslation = "Ulardan yana bir toifa: «Biz narsalarning haqiqati bormi yoki yo'qmi, bilmaymiz!» dedilar. Ular shubhachilardir (la-adriyya). Biz ularga aytdik: «Sizning 'bilmaymiz' degan so'zingizning haqiqati bormi yoki yo'qmi?» Agar «Yo'q» desalar, ular bilan bahslashilmaydi. Agar «Ha» desalar, narsalardan birining haqiqatini isbotlagan bo'ladilar.",
                                pageNumber = 40
                            ),
                            ParagraphItem(
                                paragraphNumber = 5,
                                arabicText = "وقالت طائفة أخرى منهم: «إنّ حقيقةَ الأشياءِ تابعةٌ لاعتقادات المُعتقدين. فحقيقةُ كلّ شيء عند كلّ مُعتقدٍ ما اعتقدَه». وهذا هو دأبُ أهل الدهر وهم طائفة منهم، وبالله العصمةُ عن كلّ ضلال!",
                                uzbekTranslation = "Ulardan yana bir toifa (indiylar): «Narsalarning haqiqati e'tiqod qiluvchilarning e'tiqodlariga tobedir. Har bir narsaning haqiqati har bir e'tiqod qiluvchi nazdida u nima deb e'tiqod qilsa, o'shadir», dedilar. Bu dahriylarning odatidir. Barcha zalolatdan Alloh saqlasin!",
                                pageNumber = 40
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_haqoiq",
                                termUz = "Haqoiq al-ashya (Narsalar haqiqati)",
                                termAr = "حقائق الأشياء",
                                definitionUz = "Narsaning o'ziga xos borlig'i va mohiyati. Ahli Sunna e'tiqodiga ko'ra tashqi olamdagi barcha narsalar real mavjuddir.",
                                sourceExample = "فإنّ حقائقَ الأشياءِ ثابتةٌ. وهو مذهبُ عامّةِ العُقَلاءِ (1-bob, 2-fasl)",
                                pageRef = 39
                            ),
                            ConceptItem(
                                id = "c_sofsata",
                                termUz = "Safsataviylar (Sofistlar)",
                                termAr = "السوفسطائية",
                                definitionUz = "Tashqi olamning mavjudligini yoki bilim olish imkoniyatini inkor etuvchi mutaassib inkorchilar oqimi.",
                                sourceExample = "وقالت طائفة من السوفسطائية: لا حقيقة للأشياء (1-bob, 2-fasl)",
                                pageRef = 39
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_haq_aqli",
                                titleUz = "Haqiqatni inkor qilishning mantiqiy ziddiyati",
                                type = ProofType.AQLIY,
                                arabicText = "إنّ نافيَها مُثْبِتُها وأنّ في نفيها ثُبوتَها فتكون ثابتةً ضرورةً",
                                uzbekTranslation = "Haqiqatni inkor qiluvchi kishi o'z inkorining haqiqat ekanini da'vo qilishi bilan haqiqatni isbotlab qo'yadi.",
                                sourceRef = "Kitob at-Tamhid, 40-bet, 3-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_safsata",
                                topicTitle = "Olam haqiqatlarining mavjudligi",
                                ahlSunnahView = "Narsalarning haqiqatlari aniq sobit va mavjuddir.",
                                opposingSchool = "Sufastoiyya (Inkorchilar, Shubhachilar, Indiylar)",
                                opposingView = "Haqiqat yo'q yoki bilib bo'lmaydi yoki faqat shaxsiy tasavvurga bog'liq.",
                                refutationUz = "Inkorning o'zi haqiqat ekani tan olinsa, narsalarning haqiqati sobit bo'ladi. Og'riq, zahar va qilichdan qochishlari ularning botiniy e'tiroflaridir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_1_1",
                                lessonId = "ch_1_l_1",
                                chapterId = "ch_1",
                                questionUz = "Ahli Sunna val-Jamoa va umumiy oqillar nazdida narsalarning haqiqatlari haqidagi hukm qanday?",
                                options = listOf(
                                    "Narsalarning hech qanday haqiqati yo'q",
                                    "Narsalarning haqiqatlari sobitdir (mavjuddir)",
                                    "Narsalar faqat inson xayolidagina mavjud",
                                    "Haqiqatni bilish mutlaqo imkonsizdir"
                                ),
                                correctIndex = 1,
                                explanationUz = "Imom al-Lomishiy 2-faslda: «Albatta narsalarning haqiqatlari sobitdir. Bu barcha oqillarning mazhabidir», deb bayon qilgan.",
                                sourcePageRef = 39,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_1_2",
                                lessonId = "ch_1_l_1",
                                chapterId = "ch_1",
                                questionUz = "Sufastoiyya (sofistlar) toifasi nechta asosiy guruhga bo'linadi?",
                                options = listOf(
                                    "Ikki guruh: Dahriylar va Falasifa",
                                    "To'rt guruh: Mu'tazila, Jabriyya, Qadariyya, Jahmiyya",
                                    "Uch guruh: Inodiyya (inkorchilar), Indiypa (subyektivchilar), Lo-adriyya (shubhachilar)",
                                    "Besh guruh: Sanaviya, Majusiy, Zanodiqa, Murji'a, Xavorij"
                                ),
                                correctIndex = 2,
                                explanationUz = "Sofistlar uch toifadir: Inodiyya haqiqatni qasddan inkor qiladi, Indiypa subyektiv deb biladi, Lo-adriyya esa hamma narsada ikkilanadi.",
                                sourcePageRef = 40,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_1_3",
                                lessonId = "ch_1_l_1",
                                chapterId = "ch_1",
                                questionUz = "Imom al-Lomishiy safsatachilarning haqiqatni inkor qilishini amaliy jihatdan qanday rad etadi?",
                                options = listOf(
                                    "Faqat uzoq falsafiy bahs yuritish orqali",
                                    "Ularni e'tiborsiz qoldirish orqali",
                                    "Faqat qasam ichirish orqali",
                                    "Ularga olov yoki og'riq yetkazilsa, azob va og'riqning haqiqat ekanini tan olishga majbur bo'lishlari orqali"
                                ),
                                correctIndex = 3,
                                explanationUz = "Lomishiy aytadi: Agar ularga qattiq jazo yoki olov tekkizilsa, darhol faryod urib og'riq haqiqatini tan oladilar.",
                                sourcePageRef = 40,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 2-BOB: Asbob al-ilm
            Chapter(
                id = "ch_2",
                chapterNumber = 2,
                titleUz = "Ilm hosil bo'lish sabablari",
                titleAr = "فصل في الأسباب التي يقع بها العلم بالحقائق",
                descriptionUz = "Maxluqotlar uchun bilim olishning uchta qonuniy vositasi: sog'lom hislar, to'g'ri aql va sodiq xabar.",
                pdfStartPage = 41,
                pdfEndPage = 45,
                lessons = listOf(
                    Lesson(
                        id = "ch_2_l_1",
                        chapterId = "ch_2",
                        lessonNumber = 1,
                        titleUz = "Ilmning uch sababi: Hislar, Aql va Xabar",
                        titleAr = "أسباب العلم: الحواس السليمة، والعقول المستقيمة، والأخبار الصادقة",
                        summaryUz = "Ilm uch yo'l bilan hosil bo'ladi: 5 ta sog'lom sezgi a'zosi, aqliy istidlol (aql) va sodiq xabarlar (mutavotir va payg'ambar xabari).",
                        pdfStartPage = 41,
                        pdfEndPage = 45,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 6,
                                arabicText = "ثم الأسبابُ التي يقع بها العلمُ للمخلوقين بالحقائق ثلاثة: الحواسُّ السليمة، والعقولُ المستقيمة، والأخبارُ الصادرةُ عن الصدق. أمّا الحواس السليمة فلا شك في كونها طريقاً لوقوع العلم بها لأن وقوع العلم بحاسّة السمع بالمسموعات وبحاسة الشم بالمشمومات وبحاسة البصر بالمبصرات وبحاسة الذوق بالمذوقات وبحاسة اللمس بالملموسات ضروريّ لا وجه لإنكاره.",
                                uzbekTranslation = "So'ngra maxluqlar uchun haqiqatlarga oid ilm hosil bo'lish sabablari uchtadir: 1) Salim (sog'lom) his-tuyg'ular; 2) To'g'ri aqllar; 3) Sodiq xabarlar. Salim hislar ilm hosil bo'lish yo'li ekanida shak-shubha yo'qdir. Eshitish orqali tovushlar, hid bilish orqali hidlar, ko'rish orqali ko'riladigan narsalar, tatib ko'rish orqali ta'mlar, ushlab ko'rish orqali tegiladigan narsalar haqida zaruriy ilm hosil bo'ladi.",
                                pageNumber = 41
                            ),
                            ParagraphItem(
                                paragraphNumber = 7,
                                arabicText = "وأمّا العقلُ فكذلك لأنّ العِلمَ بكَوْن الشيء أعظمَ من جُزئه من غير سابقة حِسٍّ ولا خَبَرٍ ليس يقع إلا بالعقل ولأنّ الإستدلال العقليّ بعد استجماع شرائط النظر ممّا يُوصله إلى العِلم.",
                                uzbekTranslation = "Aql ham shundaydir, chunki bir butun narsaning o'z bo'lagidan kattaroq ekani haqidagi ilm hech qanday avvalgi his yoki xabarsiz faqat aql orqali hosil bo'ladi. Shuningdek, fikrlash shartlari to'la bo'lgan aqliy istidlol (dalillash) ilmgacha olib boradi.",
                                pageNumber = 42
                            ),
                            ParagraphItem(
                                paragraphNumber = 8,
                                arabicText = "وأمّا الخبَرُ الصادقُ فهو طريقُ العِلم أيضاً لأنّ الصدقَ إخبارٌ عن الشيء على ما هو به. والأخبارُ عن الشيء على ما هو به طريقٌ لوُقوع العِلم بالمُخبَر به.",
                                uzbekTranslation = "Sodiq xabar ham ilm yo'lidir, chunki sidq (rostlik) – narsani qanday bo'lsa, xuddi shunday xabar qilishdir. Bu esa xabar berilgan narsa haqida ilm hosil bo'lishiga sababdir.",
                                pageNumber = 42
                            ),
                            ParagraphItem(
                                paragraphNumber = 11,
                                arabicText = "وقالت السُمَنيّة وهم فلاسفة الهند: «لا طريق لمعرفة الأشياء إلا بالحِسّ لأنّ قضايا العقل والخَبَر متناقضة». وقُلنا: هذا فاسد لأنه لا يُمكنه معرفة فساد قول صاحبه بالحسّ.",
                                uzbekTranslation = "Hind faylasuflaridan bo'lgan Sumniya toifasi: «Narsalarni bilishning his-tuyg'udan boshqa yo'li yo'q, chunki aql va xabar qarashlari ziddiyatlidir», dedilar. Biz aytdik: Bu buzuq fikrdir, chunki u o'z raqibi so'zining buzuqligini faqat his qilish orqali bila olmaydi.",
                                pageNumber = 43
                            ),
                            ParagraphItem(
                                paragraphNumber = 14,
                                arabicText = "وقال بعضهم: «لا طريق لمعرفة الأشياء إلا بالإلهام». وقُلنا: هذا فاسدٌ لأنّ لخصمه أن يقول: «إنّي أُلهِمتُ أنّ الإلهام ليس بطريق العلم!»",
                                uzbekTranslation = "Ba'zilar: «Narsalarni bilishning ilhomdan boshqa yo'li yo'q», dedilar. Biz aytdik: Bu fikr botildir, chunki uning raqibi: «Menga ilhomning ilm yo'li emasligi ilhom qilindi!» deyishi mumkin.",
                                pageNumber = 44
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_hawas",
                                termUz = "Al-Havoss as-Salima (5 ta sog'lom sezgi)",
                                termAr = "الحواس السليمة",
                                definitionUz = "Eshitish, ko'rish, hid bilish, tatib ko'rish va paypaslab sezish a'zolari.",
                                sourceExample = "السمع، والبصر، والشم، والذوق، واللمس (2-bob, 6-fasl)",
                                pageRef = 41
                            ),
                            ConceptItem(
                                id = "c_khabar",
                                termUz = "Al-Xabar as-Sodiq (Rost xabar)",
                                termAr = "الخبر الصادق",
                                definitionUz = "Mutavotir xabar (yolg'onga kelishib olishi aqlan imkonsiz jamoat xabari) va mo'jiza bilan quvvatlangan payg'ambarning xabari.",
                                sourceExample = "خبر الرسل المعصومين والخبر المتواتر (2-bob, 10-fasl)",
                                pageRef = 42
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_aql_dalil",
                                titleUz = "Aqlning mustaqil ilm manbai ekani",
                                type = ProofType.AQLIY,
                                arabicText = "العِلمَ بكَوْن الشيء أعظمَ من جُزئه ليس يقع إلا بالعقل",
                                uzbekTranslation = "Butunning o'z bo'lagidan kattaroqligi hissiz va xabarsiz sof aql bilan bilinadi.",
                                sourceRef = "Kitob at-Tamhid, 42-bet, 7-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_sumniya",
                                topicTitle = "Ilm manbalari chegarasi",
                                ahlSunnahView = "Ilm hislar, to'g'ri aql va sodiq xabar orqali olinadi.",
                                opposingSchool = "Sumniya (Hind faylasuflari) va Barohima",
                                opposingView = "Faqat sezgi a'zolarigina bilim beradi, aql va xabarga tayanib bo'lmaydi.",
                                refutationUz = "O'tmish podshohlari va uzoq shaharlar haqidagi bilim faqat xabar orqali olinadi, buni inkor qilish ochiq haqiqatni inkor qilishdir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_2_1",
                                lessonId = "ch_2_l_1",
                                chapterId = "ch_2",
                                questionUz = "Imom al-Lomishiy bayon qilgan ilm hosil bo'lishining 3 ta asosiy sababi qaysilar?",
                                options = listOf(
                                    "Sog'lom hislar, to'g'ri aql, sodiq xabar",
                                    "Faqat shaxsiy ilhom va tushlar",
                                    "Faqat ko'rish va eshitish sezgilari",
                                    "Kitob o'qish va taxmin qilish"
                                ),
                                correctIndex = 0,
                                explanationUz = "Moturidiyya aqidasida maxluqotlar uchun ilm 3 vosita: salim hislar, mustaqim aql va sodiq xabar orqali hosil bo'ladi.",
                                sourcePageRef = 41,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_2_2",
                                lessonId = "ch_2_l_1",
                                chapterId = "ch_2",
                                questionUz = "Sodiq xabar (al-xabar as-sodiq) qaysi ikki qismga bo'linadi?",
                                options = listOf(
                                    "Zaif xabar va mavzu xabar",
                                    "Sirli xabar va ochiq xabar",
                                    "Mutavotir xabar va mo'jiza bilan tasdiqlangan Payg'ambar xabari",
                                    "Yozma xabar va og'zaki rivoyat"
                                ),
                                correctIndex = 2,
                                explanationUz = "Kitob at-Tamhidda sodiq xabar 2 turga ajratiladi: mutavotir xabar va Payg'ambar (a.s.) keltirgan vahiy xabari.",
                                sourcePageRef = 42,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_2_3",
                                lessonId = "ch_2_l_1",
                                chapterId = "ch_2",
                                questionUz = "Kalom ilmida ilhom (ilhom qilinish) nega shariat ahkomlarida umumiy aqidaviy hujjat bo'la olmaydi?",
                                options = listOf(
                                    "Chunki ilhom Qur'onda mutlaqo zikr qilinmagan",
                                    "Chunki har bir kishi o'ziga qarama-qarshi narsa ilhom bo'lganini da'vo qilishi mumkin va u boshqalar uchun majburiy dalil emas",
                                    "Chunki ilhom faqat tushda beriladi",
                                    "Chunki inson qalbi hech qachon pok bo'la olmaydi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Moturidiylikda ilhom shaxsiy qanoat keltirishi mumkin, lekin boshqa odamlar ustidan shar'iy hujjat bo'la olmaydi.",
                                sourcePageRef = 44,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 3-BOB: Hudus al-alam
            Chapter(
                id = "ch_3",
                chapterNumber = 3,
                titleUz = "Olamning yaratilganligi (hudus) va Soni'ning qadimiyligi",
                titleAr = "فصل في إثبات حدوث العالم وثبوت الصانع وقدمه",
                descriptionUz = "Olamning barcha qismlari (a'yon va a'roz) yo'qdan bor qilingani (hudus) va uning Yaratuvchisi qadim ekani isboti.",
                pdfStartPage = 45,
                pdfEndPage = 51,
                lessons = listOf(
                    Lesson(
                        id = "ch_3_l_1",
                        chapterId = "ch_3",
                        lessonNumber = 1,
                        titleUz = "Olamning bo'linishi: A'yon (Javhar, Jism) va A'roz",
                        titleAr = "تقسيم العالم: أعيان (جواهر وأجسام) وأعراض",
                        summaryUz = "Allohdan boshqa barcha borliq olamdir. Olam a'roz (sifatlar) va a'yon (javhar va jismlar)dan iborat bo'lib, barchasi yaratilgandir.",
                        pdfStartPage = 45,
                        pdfEndPage = 48,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 16,
                                arabicText = "اعلم أنّ العالَمَ اسمٌ لما سوى الله - تعالى! - من السموات والأرضين وما فيها. وكلُّ ذلك مُحدَثٌ بإحداث الله - تعالى! ثم هو قسمان عند عامّة المتكلّمين: أعراضٌ وأعيانٌ. والأعيانُ قسمان: جواهرُ وأجسامٌ.",
                                uzbekTranslation = "Bilginki, olam — osmonlar, yerlar va ulardagi barcha narsalar, ya'ni Alloh taolodan boshqa barcha mavjudotlarning nomidir. Ularning barchasi Alloh taoloning paydo qilishi bilan paydo bo'lgandir (muhdasdir). So'ngra u kalom ulamolari nazdida ikki qismdir: A'roz (alomatlar/sifatlar) va A'yon (zotlar). A'yon esa ikki qismdir: Javharlar va Jismlar.",
                                pageNumber = 45
                            ),
                            ParagraphItem(
                                paragraphNumber = 17,
                                arabicText = "فأمّا تفسيرُ العَرَض فهو في اللغة اسمٌ لما لا دوامَ له ولا يطول مُكثُه... وفي عُرف أهل الكلام هو اسمٌ للصفات الثابتة للمُحدَثات كالأكوان والألوان والطعوم والروائح والحرارة والبرودة والحياة والموت والقُدرة والعجز ونحوها.",
                                uzbekTranslation = "A'rozning ma'nosiga kelsak, u lug'atda bardavom bo'lmaydigan va uzoq turmaydigan narsadir... Kalom ilmi istilohida esa u yaratilgan narsalarda sobit bo'ladigan sifatlar: harakat, sukun, ranglar, ta'mlar, hidlar, issiqlik, sovuqlik, hayot, o'lim, qudrat, ojizlik kabilarning nomidir.",
                                pageNumber = 45
                            ),
                            ParagraphItem(
                                paragraphNumber = 18,
                                arabicText = "وأمّا الجوهرُ فهو الجزءُ الذي لا يتجزّأ لا فِعلاً ولا وَهماً. وحدُّه أنّه القائم بالذات القابل للصفات المتضادّات على سبيل البَدَل كالحركة والسكون والبياض والسواد ونحوها.",
                                uzbekTranslation = "Javharga kelsak, u na amalda va na xayolan bo'linmaydigan eng kichik zarradir (juz' la yatajazza / atom). Uning ta'rifi: o'z-o'zicha qoyim bo'lib, o'zaro zid sifatlarni (harakat va sukun, oqlik va qoralik kabi) navbatma-navbat qabul qiluvchi zotdir.",
                                pageNumber = 46
                            ),
                            ParagraphItem(
                                paragraphNumber = 19,
                                arabicText = "وأمّا الجِسمُ فهو المركّب المؤلَّف من جزءين أو ثلاثة أو أكثر. هذا الذي ذكرنا هو مذهب عامّة المتكلّمين.",
                                uzbekTranslation = "Jismga kelsak, u ikki, uch yoki undan ortiq javharlardan (zarrachalardan) tuzilgan birikmadir. Bu biz zikr qilgan taqsimot barcha kalom ulamolarining mazhabidir.",
                                pageNumber = 46
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_alam",
                                termUz = "Olam (Borliq)",
                                termAr = "العالَم",
                                definitionUz = "Alloh taolodan o'zga barcha yaratilgan mavjudotlar majmui.",
                                sourceExample = "العالَم اسم لما سوى الله تعالى (3-bob, 16-fasl)",
                                pageRef = 45
                            ),
                            ConceptItem(
                                id = "c_araz",
                                termUz = "A'roz (Sifatlar/Aksidensiya)",
                                termAr = "العَرَض",
                                definitionUz = "O'z-o'zicha tura olmaydigan, jism yoki javharda zohir bo'ladigan o'tkinchi xususiyatlar (rang, harakat, ta'm).",
                                sourceExample = "اسم لما لا دوام له ولا يطول مكثه (3-bob, 17-fasl)",
                                pageRef = 45
                            ),
                            ConceptItem(
                                id = "c_javhar",
                                termUz = "Javhar (Bo'linmas zarra)",
                                termAr = "الجوهر",
                                definitionUz = "Boshqa bo'laklarga bo'linmaydigan eng mayda mustaqil asosiy moddiy birlik (al-juz' allaziy la yatajazza).",
                                sourceExample = "الجوهر فهو الجزء الذي لا يتجزّأ لا فعلاً ولا وهماً (3-bob, 18-fasl)",
                                pageRef = 46
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_hudus_alam",
                                titleUz = "Olamning yangidan paydo bo'lganligi (Hudus) dalili",
                                type = ProofType.AQLIY,
                                arabicText = "العالم مُحدَثٌ لأنّه ينقسم إلى أغراض وأعيان والأعراض حادثة لا شكّ في حدوثها",
                                uzbekTranslation = "Olam a'roz va a'yondan iborat. A'rozning doim o'zgarib turishi uning yaratilganligini ko'rsatadi. A'rozsiz tura olmaydigan a'yon ham yaratilgandir.",
                                sourceRef = "Kitob at-Tamhid, 48-bet, 22-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_dahriya",
                                topicTitle = "Olamning azaliyligi yoki yaratilganligi",
                                ahlSunnahView = "Olam va uning barcha zarrachalari yo'qdan bor qilingandir (muhdas).",
                                opposingSchool = "Dahriya (Materialistlar / Falasifa)",
                                opposingView = "Olam qadimiy, moddaning boshi va oxiri yo'q.",
                                refutationUz = "Olamdagi harakat va o'zgarishlar uning azaliy emasligining qat'iy isbotidir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_3_1",
                                lessonId = "ch_3_l_1",
                                chapterId = "ch_3",
                                questionUz = "Kalom ilmida bo'linmaydigan eng kichik zarraga nima deyiladi?",
                                options = listOf("Jism", "A'roz", "Olam", "Javhar (al-juz' allaziy la yatajazza)"),
                                correctIndex = 3,
                                explanationUz = "Imom al-Lomishiy 18-faslda: «Javhar — fe'lan va vahman bo'linmaydigan juz'dir», deb ta'riflagan.",
                                sourcePageRef = 46,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_3_2",
                                lessonId = "ch_3_l_1",
                                chapterId = "ch_3",
                                questionUz = "Olamning barcha zarrachalari va a'rozlari yaratilganiga (hudusiga) qanday aqliy dalil keltiriladi?",
                                options = listOf(
                                    "Olam doimo bir xil holatda turganligi",
                                    "Olamdagi barcha narsalar harakat va sukun kabi o'zgaruvchan a'rozlardan xoli emasligi va o'zgaruvchan har bir narsa yaratilganligi",
                                    "Olamning cheksiz fazoda joylashganligi",
                                    "Moddaning hech qachon yo'q bo'lmasligi"
                                ),
                                correctIndex = 1,
                                explanationUz = "A'rozlar (harakat, sukun, rang) yangidan paydo bo'ladi. Ulardan xoli bo'lolmagan javharlar ham yangidan paydo bo'lgandir.",
                                sourcePageRef = 48,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_3_3",
                                lessonId = "ch_3_l_1",
                                chapterId = "ch_3",
                                questionUz = "Dahriyya (materialistlar)ning olam qadimiy degan da'vosiga qanday javob beriladi?",
                                options = listOf(
                                    "Agar o'tmish hodisalari cheksiz bo'lsa, bugungi kunga yetib kelish imkonsiz bo'lar edi (tasalsul botilligi)",
                                    "Ular bilan bahslashish taqiqlanadi",
                                    "Olamning qadim ekanini faqat qalb bilan rad etiladi",
                                    "Faqat jismoniy tajriba o'tkazish orqali"
                                ),
                                correctIndex = 0,
                                explanationUz = "Cheksiz o'tmish halqalarining tugallanishi aqlan mumkin emas. Bugungi kun mavjudligi o'tmishning boshlanishi borligini isbotlaydi.",
                                sourcePageRef = 50,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 4-BOB: Vahdaniyat
            Chapter(
                id = "ch_4",
                chapterNumber = 4,
                titleUz = "Yaratuvchining yakkaligi (Vahdaniyat)",
                titleAr = "فصل في إثبات وحدانية الصانع جل وعلا",
                descriptionUz = "Alloh taoloning yagona ekani, Unga hech qanday sherik yo'qligi va Tamonu' (to'sqinlik) dalili orqali ko'pxudolikning botilligi.",
                pdfStartPage = 51,
                pdfEndPage = 55,
                lessons = listOf(
                    Lesson(
                        id = "ch_4_l_1",
                        chapterId = "ch_4",
                        lessonNumber = 1,
                        titleUz = "Vahdaniyat isboti va Tamonu' dalili",
                        titleAr = "إثبات الوحدانية ودليل التمانع",
                        summaryUz = "Agar olamda ikkita yaratuvchi bo'lsa, ularning irodalari to'qnashib, olam nizomi buzilgan bo'lar edi. Alloh taolo yagonadir.",
                        pdfStartPage = 51,
                        pdfEndPage = 55,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 31,
                                arabicText = "ثمّ إنّ صانعَ العالَم واحدٌ إذ لو كان له صانعان لكان الحالُ لا يخلو إما أن كانا مُتوافِقَين في تخليق الأشياء أو كانا مُتخالفَين. فإن كانا متوافقين فالموافقةُ دليلُ عَجْزِهما أو دليلُ عَجزِ أحدهما... وإن كانا متخالفين بأن أراد أحدهما تخليق شيء في محلّ وأراد الآخر تخليق ضده في ذلك المحلّ... وهذا يُسمّى دليلَ التمانُع، فإنّه مأخوذ من قوله تعالى: ﴿لَوْ كَانَ فِيهِمَا آلِهَةٌ إِلَّا اللَّهُ لَفَسَدَتَا﴾.",
                                uzbekTranslation = "So'ngra olamning Yaratuvchisi bittadir. Chunki agar uning ikkita yaratuvchisi bo'lganida, narsalarni yaratishda yo ittifoq bo'lar edilar, yoki ixtilofda bo'lar edilar. Agar ittifoq bo'lsalar, bu ikkovining yoki birining ojizligiga dalildir... Agar ixtilof qilsalar, masalan, biri bir o'rinda bir narsani yaratishni xohlasa, ikkinchisi uning ziddini yaratishni xohlasa: ikkovining ham xohishi amalga oshishi imkonsizdir (ziddiyat birlashmaydi), ikkovining ham xohishi amalga oshmasligi ojizlikdir, faqat birining xohishi amalga oshsa, ikkinchisi ojiz bo'lib qoladi. Ojiz esa iloh bo'lolmaydi. Bu 'Tamonu' dalili' deb ataladi va u Alloh taoloning: «Agar u ikkisida (osmonlar va yerda) Allohdan o'zga ilohlar bo'lganida, har ikkisi buzilib ketar edi» (Anbiyo, 22) degan oyatidan olingandir.",
                                pageNumber = 51
                            ),
                            ParagraphItem(
                                paragraphNumber = 32,
                                arabicText = "وقالت المجوس: «إنّ للعالم صانعين، أحدهما خيّرٌ خالق الخيرات والمسرّات... وهو يَزْدانُ، والآخر شريرٌ خالق الشرور والهموم والآلام... وهو أهْرَمَنْ».",
                                uzbekTranslation = "Majusiylar: «Olamning ikkita yaratuvchisi bor: biri yaxshiliklar va quvonchlar yaratuvchisi ezgu zot – Yazdon; ikkinchisi yomonliklar, g'amlar va azoblar yaratuvchisi yovuz zot – Ahramandir», dedilar.",
                                pageNumber = 52
                            ),
                            ParagraphItem(
                                paragraphNumber = 34,
                                arabicText = "وقالت الثنوية: «للعالَم أصلان قديمان، أحدهما النور والآخر الظلمة». وهم ثلاث فرق: المانوية، والدَّيْصانية، والمَرْقُيونية.",
                                uzbekTranslation = "Sanaviylar (dualistlar): «Olamning ikkita qadimiy asosi bor: biri Nur va ikkinchisi Zulmatdir», dedilar. Ular 3 firqadir: Moniylar, Daysoniylar va Marqiyoniylar.",
                                pageNumber = 54
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_tamonu",
                                termUz = "Dalilut-Tamonu' (O'zaro to'sqinlik dalili)",
                                termAr = "دليل التمانع",
                                definitionUz = "Ikki iloh faraz qilinganda, ularning irodalari to'qnashib, ojizlik yuzaga kelishini isbotlovchi qat'iy mantiqiy-aqliy dalil.",
                                sourceExample = "لو كان فيهما آلهة إلا الله لفسدتا (4-bob, 31-fasl)",
                                pageRef = 52
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_vahdaniyat_quran",
                                titleUz = "Vahdaniyatga Qur'oniy dalil",
                                type = ProofType.QURAN,
                                arabicText = "﴿لَوْ كَانَ فِيهِمَا آلِهَةٌ إِلَّا اللَّهُ لَفَسَدَتَا﴾",
                                uzbekTranslation = "«Agar u ikkisida (osmonlar va yerda) Allohdan o'zga ilohlar bo'lganida, har ikkisi buzilib ketar edi» (Anbiyo surasi, 22-oyat).",
                                sourceRef = "Kitob at-Tamhid, 52-bet, 31-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_majus_sanaviya",
                                topicTitle = "Yaratuvchining birligi va dualizm raddiyasi",
                                ahlSunnahView = "Yaxshilik va yomonlikning yagona Xoliqi Allohdir, Uning sherigi yo'q.",
                                opposingSchool = "Majusiylar va Sanaviya (Moniylar, Daysoniylar)",
                                opposingView = "Yaxshilikni alohida xudo (Yazdon/Nur), yomonlikni alohida xudo (Ahraman/Zulmat) yaratgan.",
                                refutationUz = "Yomonlik va zararlarning yaratilishida ham cheksiz hikmat bor. Ikki mustaqil xudo bo'lishi olam tuzilishiga zid va aqlan botildir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_4_1",
                                lessonId = "ch_4_l_1",
                                chapterId = "ch_4",
                                questionUz = "Alloh taoloning yakkaligini isbotlovchi 'Tamonu' dalili qaysi oyatga asoslanadi?",
                                options = listOf(
                                    "«Qul huvallohu ahad»",
                                    "«Allohu la ilaha illa huval hayyul qayyum»",
                                    "«Agar u ikkisida Allohdan o'zga ilohlar bo'lganida, har ikkisi buzilib ketar edi» (Anbiyo, 22)",
                                    "«Inna nahnu nazzalnaz-zikr»"
                                ),
                                correctIndex = 2,
                                explanationUz = "Imom al-Lomishiy Tamonu' dalilini Anbiyo surasi 22-oyatidan olinganini ta'kidlaydi.",
                                sourcePageRef = 52,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_4_2",
                                lessonId = "ch_4_l_1",
                                chapterId = "ch_4",
                                questionUz = "Tamonu' (o'zaro to'sqinlik) aqliy dalilining mohiyati nimadan iborat?",
                                options = listOf(
                                    "Agar ikki iloh bo'lsa, biri harakatni, ikkinchisi sukunni xohlasa, ularning ikkisi ham ojiz bo'lib qoladi yoki birining irodasi o'tmay ilohlikdan chiqadi",
                                    "Ikki iloh o'zaro kelishib olamni boshqaradi",
                                    "Biri osmonni, ikkinchisi yerni yaratgan",
                                    "Ular faqat ibodatda sherikdirlar"
                                ),
                                correctIndex = 0,
                                explanationUz = "Ilohlardan birining irodasi amalga oshmasa ojiz bo'ladi, ojiz esa iloh bo'lolmaydi. Ikkisining irodasi bir vaqtda amalga oshishi esa ziddiyatdir.",
                                sourcePageRef = 53,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_4_3",
                                lessonId = "ch_4_l_1",
                                chapterId = "ch_4",
                                questionUz = "Sanaviya va majusiylarning 'Yaxshilikni Nur, yomonlikni Zulmat yaratgan' degan dualizmiga qanday raddiya beriladi?",
                                options = listOf(
                                    "Nur va zulmat ikki xil dindir",
                                    "Ular faqat arab tilida gapirmaydilar",
                                    "Yomonlik degan narsa olamda mavjud emas",
                                    "Zarar va yomonlik deb bilingan narsalarda ham ko'plab foyda va hikmatlar bor, ularning Xoliqi yagona bo'lishi lozim"
                                ),
                                correctIndex = 3,
                                explanationUz = "Al-Lomishiy ta'kidlaydi: Olov yoqishi mumkin, lekin unda pishirish va isinish hikmati bor. Har ikkisining Yaratuvchisi faqat Allohdir.",
                                sourcePageRef = 54,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 5-BOB: Nafy al-jism val-a'roz
            Chapter(
                id = "ch_5",
                chapterNumber = 5,
                titleUz = "Yaratuvchi a'roz, javhar yoki jism emasligi",
                titleAr = "فصل في أن صانع العالم ليس بعرض ولا جوهر ولا جسم",
                descriptionUz = "Alloh taolo yaratilganlarga xos bo'lgan a'roz (sifat), javhar (moddiy zarra) yoki jism (shaklli modda) bo'lishdan mutlaqo pok ekani.",
                pdfStartPage = 55,
                pdfEndPage = 56,
                lessons = listOf(
                    Lesson(
                        id = "ch_5_l_1",
                        chapterId = "ch_5",
                        lessonNumber = 1,
                        titleUz = "Alloh taoloni jism va moddiylikdan tanzih qilish",
                        titleAr = "تنزيه الله تعالى عن الجرمية والجسمية والأعراض",
                        summaryUz = "Alloh a'roz emas, chunki a'roz o'tkinchidir; javhar emas, chunki javhar bo'lakdir; jism emas, chunki jism tarkib topuvchidir.",
                        pdfStartPage = 55,
                        pdfEndPage = 56,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 36,
                                arabicText = "ثم إنّ صانعَ العالَم ليس بعَرَضٍ لما مرّ أنّ العرَض مُحدَثٌ وأنّه مستحيل البقاء... وأنه ليس بجوهر أيضاً لأنّ الجوهر أصل المُركّبات... واستحال أن يكون الصانع - جلّ وعلا! - يتركّب منه المركبات وأن يكون محلاً لحدوث الأعراض المتضادات.",
                                uzbekTranslation = "So'ngra olamning Yaratuvchisi a'roz (o'tkinchi sifat) emasdir, chunki a'roz yaratilgandir va uning o'z-o'zicha qolishi imkonsizdir. Shuningdek U javhar ham emasdir, chunki javhar moddiy birikmalarning aslidir. Yaratuvchi Zotdan narsalar tarkib topishi yoki U Zot o'zaro zid a'rozlar o'rni bo'lishi aqlan mutlaqo mustahildir.",
                                pageNumber = 55
                            ),
                            ParagraphItem(
                                paragraphNumber = 38,
                                arabicText = "وإذا ثبت أنّه - تعالى! - ليس بجوهر فلا يُتصوّر أن يكون جسماً أيضاً لأنّ الجسم اسمٌ للمُتَرَكّب من الأجزاء... ولأنّ الجسم لا يُتصوّر إلا على شكلٍ من الأشكال... وقد قال الله تعالى: ﴿لَيْسَ كَمِثْلِهِ شَيْءٌ﴾.",
                                uzbekTranslation = "Modomiki U Zotning javhar emasligi sobit bo'ldimi, jism bo'lishi ham tasavvur qilinmaydi. Chunki jism bo'laklardan tarkib topgan narsaning nomidir. Qolaversa jism muayyan shakllardan biridagina bo'ladi. Holbuki Alloh taolo: «Unga o'xshash hech narsa yo'qdir» (Sho'ro, 11) deb marhamat qilgan.",
                                pageNumber = 56
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_tanzih_jism",
                                termUz = "Tanzih (Poklash)",
                                termAr = "التنزيه",
                                definitionUz = "Alloh taoloni maxluqotlarga xos bo'lgan jism, a'zo, shakl, miqdor va chegaralardan mutlaq pok deb e'tiqod qilish.",
                                sourceExample = "ليس بعرض ولا جوهر ولا جسم (5-bob, 36-fasl)",
                                pageRef = 55
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_tanzih_shura",
                                titleUz = "O'xshashi yo'qligiga Qur'oniy dalil",
                                type = ProofType.QURAN,
                                arabicText = "﴿لَيْسَ كَمِثْلِهِ شَيْءٌ وَهُوَ السَّمِيعُ الْبَصِيرُ﴾",
                                uzbekTranslation = "«Unga o'xshash hech narsa yo'qdir. U o'ta eshitguvchi, o'ta ko'rguvchidir» (Sho'ro surasi, 11-oyat).",
                                sourceRef = "Kitob at-Tamhid, 56-bet, 38-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mujassima",
                                topicTitle = "Allohga jism nisbat berishning botilligi",
                                ahlSunnahView = "Alloh taolo jism va a'zolardan mutlaqo pokdir.",
                                opposingSchool = "Mujassima va Mushabbiha",
                                opposingView = "Allohni jism yoki odam shaklidagi maxluq deb hisoblash.",
                                refutationUz = "Jism bo'laklardan tashkil topadi, har qanday tarkib topuvchi esa yaratilgandir va Yaratuvchiga muhtojdir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_5_1",
                                lessonId = "ch_5_l_1",
                                chapterId = "ch_5",
                                questionUz = "Alloh taolo nima sababdan jism bo'lishi aqlan va shar'an mumkin emas?",
                                options = listOf(
                                    "Chunki jism ko'rinmas bo'ladi",
                                    "Chunki jism bo'laklardan tarkib topadi va shaklga ega bo'ladi, bular esa maxluqlik alomatidir",
                                    "Chunki jism faqat osmonda bo'ladi",
                                    "Chunki jism harakatlanmaydi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 38-faslda: Jism qismlardan tuzilgan va shaklga ega bo'lgan narsadir, Alloh esa bunday sifatlardan munazzahdir.",
                                sourcePageRef = 56,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_5_2",
                                lessonId = "ch_5_l_1",
                                chapterId = "ch_5",
                                questionUz = "Alloh taolo a'roz (o'tkinchi xususiyat) bo'lishi nega mustahildir?",
                                options = listOf(
                                    "Chunki a'roz juda kattadir",
                                    "Chunki a'roz faqat havoda uchadi",
                                    "Chunki a'roz o'zicha qoyim tura olmaydi va o'tkinchidir, Alloh esa boqiy va Qayyumiydir",
                                    "Chunki a'rozni ko'rib bo'lmaydi"
                                ),
                                correctIndex = 2,
                                explanationUz = "A'roz o'zicha turolmaydigan va vaqtincha bo'lgan xususiyatdir. Alloh esa o'z-o'zicha qoyim bo'lgan Azaliy Zotdir.",
                                sourcePageRef = 55,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_5_3",
                                lessonId = "ch_5_l_1",
                                chapterId = "ch_5",
                                questionUz = "Mujassima toifasining Allohni maxluqotga o'xshatishiga qaysi oyat qat'iy javob hisoblanadi?",
                                options = listOf(
                                    "«Laysa kamislihi shay'un va huvas-samiy'ul basiyr» (Sho'ro, 11)",
                                    "«Innamal a'malu bin-niyyat»",
                                    "«Alhamdu lillahi robbil 'alamiyn»",
                                    "«Yaa ayyuhan-naasuttaqu robbakum»"
                                ),
                                correctIndex = 0,
                                explanationUz = "Sho'ro surasi 11-oyati tanzihning eng qat'iy asosi bo'lib, har qanday moddiy o'xshashlikni batamom yo'qqa chiqaradi.",
                                sourcePageRef = 56,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            )
        )
    }
}
