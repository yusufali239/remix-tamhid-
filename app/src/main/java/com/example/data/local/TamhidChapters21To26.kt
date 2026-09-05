package com.example.data.local

import com.example.data.model.*

object TamhidChapters21To26 {
    fun getChapters(): List<Chapter> {
        return listOf(
            // 21-BOB: Al-Qazo val-Qadar
            Chapter(
                id = "ch_21",
                chapterNumber = 21,
                titleUz = "Al-Qazo val-Qadar (Taqdir masalalari)",
                titleAr = "فصل في القضاء والقدر",
                descriptionUz = "Qazo va qadarning asl ma'nosi, azaliy o'lchov va hukm hamda bandasining unga rozi bo'lishi.",
                pdfStartPage = 113,
                pdfEndPage = 118,
                lessons = listOf(
                    Lesson(
                        id = "ch_21_l_1",
                        chapterId = "ch_21",
                        lessonNumber = 1,
                        titleUz = "Qazo va Qadarga iymon keltirish",
                        titleAr = "حقيقة القضاء والقدر والرضا بهما",
                        summaryUz = "Qazo — Allohning umumiy azaliy irodasi va hukmi. Qadar — narsalarning o'z vaqtida muayyan miqdor bilan yaratilishi.",
                        pdfStartPage = 113,
                        pdfEndPage = 118,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 166,
                                arabicText = "القضاءُ والقدَرُ حقٌّ عند أهل السنة والجماعة... فالقضاءُ عبارةٌ عن إرادة الله تعالى الأزلية المتعلّقة بالأشياء على ما هي عليه، والقَدَرُ عبارةٌ عن إيجاد الله تعالى الأشياءَ على مقادير مخصوصة وأوقات معيّنة على وفق علمه وإرادته.",
                                uzbekTranslation = "Qazo va qadar Ahli Sunna val-Jamoa nazdida haqdir. Qazo — Alloh taoloning narsalarga ular qanday bo'ladigan bo'lsa, xuddi shunday bog'langan azaliy irodasidir. Qadar esa — Alloh taoloning narsalarni O'z ilmi va irodasiga muvofiq muayyan o'lchovlar va belgilangan vaqtlarda yo'qdan bor qilishidir.",
                                pageNumber = 113
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_qazo_qadar",
                                termUz = "Qazo va Qadar",
                                termAr = "القضاء والقدر",
                                definitionUz = "Alloh taoloning azaliy ilmi, o'lchovi va vaqti kelganda narsalarni yaratishi.",
                                sourceExample = "القضاء والقدر حق عند أهل السنة والجماعة (21-bob, 166-fasl)",
                                pageRef = 113
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_qadar_quran",
                                titleUz = "Qadarga Qur'oniy dalil",
                                type = ProofType.QURAN,
                                arabicText = "﴿إِنَّا كُلَّ شَيْءٍ خَلَقْنَاهُ بِقَدَرٍ﴾",
                                uzbekTranslation = "«Albatta Biz har bir narsani o'lchov (qadar) bilan yaratdik» (Qamar, 49).",
                                sourceRef = "Kitob at-Tamhid, 114-bet, 168-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_qadar_bahsi",
                                topicTitle = "Taqdirga iymon",
                                ahlSunnahView = "Yaxshilik va yomonlikning taqdiri Allohdandir.",
                                opposingSchool = "Qadariya",
                                opposingView = "Taqdir yo'q, hamma narsa yangidan boshlanadi.",
                                refutationUz = "Qadarni inkor qilish Allohning azaliy ilmini inkor qilishga tengdir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_21_1",
                                lessonId = "ch_21_l_1",
                                chapterId = "ch_21",
                                questionUz = "Qazo bilan Qadarning ta'rifi qanday?",
                                options = listOf(
                                    "Ikkalasi ham insonning o'z xohishidir",
                                    "Qazo — azaliy iroda va hukm, Qadar — narsalarning o'lchov bilan o'z vaqtida yaratilishi",
                                    "Faqat o'lim vaqti",
                                    "Faqat boylik miqdori"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 166-faslda: Qazo azaliy iroda, Qadar esa muayyan miqdorda yaratilish deb ta'riflagan.",
                                sourcePageRef = 113,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_21_2",
                                lessonId = "ch_21_l_1",
                                chapterId = "ch_21",
                                questionUz = "Qadariya toifasining adashuvi nimadan iborat edi?",
                                options = listOf(
                                    "Ular taqdirni inkor qilib, amallarni Alloh oldindan bilmaydi, voqealar to'satdan paydo bo'ladi deb da'vo qildilar",
                                    "Ular faqat ibodatlarga e'tibor berdilar",
                                    "Ular Qur'onni yodlashga chaqirdilar",
                                    "Ular zakot berishni taqiqladilar"
                                ),
                                correctIndex = 0,
                                explanationUz = "Qadariya taqdir va azaliy ilmni inkor qilib, majusiy e'tiqodga yaqinlashgani uchun hadisda bu ummatning majusiylari deb atalgan.",
                                sourcePageRef = 114,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_21_3",
                                lessonId = "ch_21_l_1",
                                chapterId = "ch_21",
                                questionUz = "Qur'oni Karimdagi «Inna kulla shay'in xolaqnahu bi-qadar» oyati nimani anglatadi?",
                                options = listOf(
                                    "Har bir narsa o'z-o'zidan paydo bo'lishini",
                                    "Albatta Biz har bir narsani muayyan o'lchov va taqdir bilan yaratdik",
                                    "Dunyo cheksiz va taqdirlanmagan ekanini",
                                    "Inson faqat tasodiflar bilan yashashini"
                                ),
                                correctIndex = 1,
                                explanationUz = "Qamar surasi 49-oyati barcha mavjudotlar azaliy taqdir va o'lchov bilan yaratilganini qat'iy isbotlaydi.",
                                sourcePageRef = 115,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 22-BOB: Al-Aslah
            Chapter(
                id = "ch_22",
                chapterNumber = 22,
                titleUz = "Allohga eng foydalisi (aslah) vojib emasligi",
                titleAr = "فصل في نفي وجوب الأصلح على الله تعالى",
                descriptionUz = "Alloh taoloning hech bir ishida biror narsa vojib bo'lmasligi, bandalarga faqat O'z fazli va adolati bilan muomala qilishi.",
                pdfStartPage = 118,
                pdfEndPage = 121,
                lessons = listOf(
                    Lesson(
                        id = "ch_22_l_1",
                        chapterId = "ch_22",
                        lessonNumber = 1,
                        titleUz = "Allohga hech narsa vojib emasligi",
                        titleAr = "نفي وجوب الأصلح والرد على أهل العدل المزعوم",
                        summaryUz = "Alloh taologa bandalar uchun 'eng yaxshisini' qilish majburiyati yo'q. Agar vojib bo'lganida kofir va kambag'allar bo'lmas edi.",
                        pdfStartPage = 118,
                        pdfEndPage = 121,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 174,
                                arabicText = "لا يجبُ على الله - تعالى! - شيءٌ من رعاية الأصلح للعباد ولا غيره. وقالت المعتزلة: «يجبُ على الله تعالى أن يفعلَ بعباده ما هو الأصلح لهم في دينهم ودنياهم». وقُلنا: لو كان يجبُ على الله ذلك لما خلقَ كافراً فقيراً مريضاً معذّباً في الدنيا والآخرة، لأنّ الأصلحَ له أن يكون مؤمناً غنياً صحيحاً منعّماً في الدارين!",
                                uzbekTranslation = "Alloh taologa bandalar uchun 'eng aslah' (eng manfaatli) narsani qilish yoki boshqa biror amal aslo vojib (majburiy) emasdir. Mu'taziliylar esa: «Allohga O'z bandalari dinida va dunyosida nima eng aslah bo'lsa, o'shani qilish vojibdir», dedilar. Biz aytdik: Agar Allohga bu narsa vojib bo'lganida edi, U dunyoda va oxiratda azoblanadigan kambag'al, xasta kofirni yaratmagan bo'lar edi. Chunki uning uchun eng aslah narsa — ikki dunyoda ham boy, sog'lom, ne'matlangan mo'min bo'lishi edi!",
                                pageNumber = 118
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_aslah",
                                termUz = "Al-Aslah (Eng yaxshi narsa)",
                                termAr = "الأَصْلَح",
                                definitionUz = "Bandalar uchun eng maqbul va foydali holat. Ahli Sunna nazdida Allohga hech bir narsa aqlan vojib bo'lolmaydi.",
                                sourceExample = "لا يجب على الله شيء من رعاية الأصلح (22-bob, 174-fasl)",
                                pageRef = 118
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_aslah_voqelik",
                                titleUz = "Olamdagi turli holatlarning mavjudligi",
                                type = ProofType.AQLIY,
                                arabicText = "لو وجب الأصلح لما وُجد كافر ولا فقير معذب",
                                uzbekTranslation = "Agar aslah vojib bo'lganida, kambag'allik, kofirlik va dardlar mavjud bo'lmas edi.",
                                sourceRef = "Kitob at-Tamhid, 118-bet, 174-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_aslah_munozarasi",
                                topicTitle = "Allohga biror amal vojib bo'ladimi?",
                                ahlSunnahView = "Alloh xohlaganini qiluvchi Mutlaq Hokimdir, Unga hech narsa vojib emas.",
                                opposingSchool = "Mu'tazila",
                                opposingView = "Alloh bandaga eng aslah narsani qilishga majbur.",
                                refutationUz = "Vojib qilish faqat buyruq beruvchi zot tomonidan bo'ladi. Allohning ustidan esa hech qanday buyruq beruvchi yo'qdir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_22_1",
                                lessonId = "ch_22_l_1",
                                chapterId = "ch_22",
                                questionUz = "Alloh taoloning fe'llari haqida Ahli Sunna val-Jamoaning qoidasi qanday?",
                                options = listOf(
                                    "Alloh har bir bandaga faqat boylik berishga majbur",
                                    "Alloh insonlarning aqliy qoidalariga bo'ysunadi",
                                    "Alloh doimo faqat yaxshilik yaratishga majburdir",
                                    "Allohga hech narsa vojib emas, U Zot O'z hikmati va fazli bilan tasarruf qiladi"
                                ),
                                correctIndex = 3,
                                explanationUz = "Lomishiy 174-faslda: Allohga hech narsa vojib emas, U mutlaq Hakimdir deb uqtiradi.",
                                sourcePageRef = 118,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_22_2",
                                lessonId = "ch_22_l_1",
                                chapterId = "ch_22",
                                questionUz = "Mu'taziliylarning «Al-Aslah» (eng yaxshisini qilish Allohga vojib) qoidasini Imom Abu al-Hasan al-Ash'ariy qaysi mashhur bahs bilan botil qilgan?",
                                options = listOf(
                                    "Uch aka-uka (mo'min, kofir va go'dakligida vafot etgan bola) misoli orqali",
                                    "Dengizdagi kemalar misoli orqali",
                                    "Yulduzlar harakati orqali",
                                    "Bozordagi savdo orqali"
                                ),
                                correctIndex = 0,
                                explanationUz = "Go'dak bola: «Meni nega katta qilib jannat darajalariga yetkazmading?» deganda, Mu'tazila javobsiz qolgan va mazhab puchligi fosh bo'lgan.",
                                sourcePageRef = 119,
                                difficulty = DifficultyLevel.MEDIUM
                            )
                        )
                    )
                )
            ),

            // 23-BOB: Azob al-Qabr
            Chapter(
                id = "ch_23",
                chapterNumber = 23,
                titleUz = "Qabr azobi va Munkar-Nakir savol-javobi",
                titleAr = "فصل في إثبات عذاب القبر وسؤال منكر ونكير",
                descriptionUz = "Barzax hayoti, qabrdagi ne'mat va azob hamda Munkar va Nakir farishtalarining so'roq-savoli haqiqat ekani.",
                pdfStartPage = 121,
                pdfEndPage = 125,
                lessons = listOf(
                    Lesson(
                        id = "ch_23_l_1",
                        chapterId = "ch_23",
                        lessonNumber = 1,
                        titleUz = "Qabr azobi va Barzax xabarlari",
                        titleAr = "عذاب القبر وسؤال الملكين بالأدلة القطعية",
                        summaryUz = "Qabr azobi kofir va osiylar uchun haqdir. Mo'minlar esa qabrda ne'matlanadilar. Hadislar mutavotir darajasidadir.",
                        pdfStartPage = 121,
                        pdfEndPage = 125,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 181,
                                arabicText = "عذابُ القبر للكافرين ولبعض فُسّاق المؤمنين، وتنعيمُ أهل الطاعة في القبر، وسؤالُ مُنكَرٍ ونَكيرٍ حقٌّ بالدلائل السمعية المتواترة... لقوله تعالى في آل فرعون: ﴿النَّارُ يُعْرَضُونَ عَلَيْهَا غُدُوًّا وَعَشِيًّا وَيَوْمَ تَقُومُ السَّاعَةُ أَدْخِلُوا آلَ فِرْعَوْنَ أَشَدَّ الْعَذَابِ﴾.",
                                uzbekTranslation = "Kofirlar va ba'zi osiy mo'minlar uchun qabr azobi, toat ahlini qabrda ne'matlantirish hamda Munkar va Nakir farishtalarining savol-javobi mutavotir naqliy dalillar bilan sobit bo'lgan haqiqatdir. Zero Alloh taolo Fir'avn xonadoni haqida: «Ular ertalab va kechqurun olovga ko'ndalang qilinurlar. Qiyomat qoyim bo'lgan kunda esa: 'Fir'avn ahlini eng qattiq azobga kiriting!' (deyilur)» (G'ofir, 46) deb marhamat qilgan.",
                                pageNumber = 121
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_azob_qabr",
                                termUz = "Qabr azobi va savoli",
                                termAr = "عذاب القبر وسؤال منكر ونكير",
                                definitionUz = "O'limdan keyin qiyomatgacha bo'lgan barzax olamidagi jazo, ne'mat va imtihon.",
                                sourceExample = "عذاب القبر وسؤال منكر ونكير حق بالدلائل السمعية (23-bob, 181-fasl)",
                                pageRef = 121
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_ghafir_46",
                                titleUz = "Fir'avn qavmining qabrdagi azobi",
                                type = ProofType.QURAN,
                                arabicText = "﴿النَّارُ يُعْرَضُونَ عَلَيْهَا غُدُوًّا وَعَشِيًّا﴾",
                                uzbekTranslation = "«Ular ertalab va kechqurun olovga ko'ndalang qilinadilar» (G'ofir, 46).",
                                sourceRef = "Kitob at-Tamhid, 121-bet, 181-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_qabr_inkori",
                                topicTitle = "Qabr azobining sobitligi",
                                ahlSunnahView = "Qabr azobi va ne'mati mutavotir hadislar bilan haqdir.",
                                opposingSchool = "Mu'tazila va Jahmiya",
                                opposingView = "Qabr azobi yo'q, o'lik hech narsani sezmaydi.",
                                refutationUz = "Rasululloh s.a.v. har namozda qabr azobidan panoh so'raganlar va qabr hayoti haqdir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_23_1",
                                lessonId = "ch_23_l_1",
                                chapterId = "ch_23",
                                questionUz = "G'ofir surasi 46-oyatidagi ertalab va kechqurun olovga ko'ndalang qilinish qaysi azobga dalildir?",
                                options = listOf(
                                    "Dunyodagi kasalliklarga",
                                    "Qabr (barzax) azobiga",
                                    "Faqat tushlarga",
                                    "Hech qanday azobga emas"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 181-faslda: Bu oyat qiyomatdan oldingi qabr azobining ochiq dalilidir deb keltirgan.",
                                sourcePageRef = 121,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_23_2",
                                lessonId = "ch_23_l_1",
                                chapterId = "ch_23",
                                questionUz = "Qabrda insonni so'roq-savol qiluvchi ikki farishtaning ismi nima?",
                                options = listOf(
                                    "Jabroil va Mikoil",
                                    "Isrofil va Azroil",
                                    "Munkar va Nakir",
                                    "Horut va Morut"
                                ),
                                correctIndex = 2,
                                explanationUz = "Hadisi shariflarda va Lomishiyning 181-faslida sobit bo'lganidek, mayyitni Munkar va Nakir so'roq qiladi.",
                                sourcePageRef = 122,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_23_3",
                                lessonId = "ch_23_l_1",
                                chapterId = "ch_23",
                                questionUz = "Mu'tazila va Jahmiya qabr azobini inkor qilishda qanday xatoga yo'l qo'ydi?",
                                options = listOf(
                                    "Ular mutavotir va sahih hadislarni aqlga to'g'ri kelmaydi deb rad etdilar, holbuki barzax qonuniyati dunyo qonuniyatidan farq qiladi",
                                    "Ular qabr qazishni taqiqladilar",
                                    "Ular faqat kitob yozish bilan mashg'ul bo'ldilar",
                                    "Ular qabr toshlarini o'rganishmadi"
                                ),
                                correctIndex = 0,
                                explanationUz = "Lomishiy bayon qiladi: Uyqudagi odam tushida lazzat yoki dahshat sezgani kabi, mayyitning ruhi ham barzaxda azob yoki rohatni his etadi.",
                                sourcePageRef = 123,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 24-BOB: Va'id fussaq val-Shafoat
            Chapter(
                id = "ch_24",
                chapterNumber = 24,
                titleUz = "Fosiq mo'minlar va Shafoat",
                titleAr = "فصل في وعيد فُسّاق أهل الصلاة والشفاعة",
                descriptionUz = "Gunohi kabira qilgan mo'min kofir bo'lmasligi, do'zaxda abadiy qolmasligi va Payg'ambarimiz s.a.v.ning shafoatlari.",
                pdfStartPage = 125,
                pdfEndPage = 144,
                lessons = listOf(
                    Lesson(
                        id = "ch_24_l_1",
                        chapterId = "ch_24",
                        lessonNumber = 1,
                        titleUz = "Gunohi kabira egalarining hukmi va Shafoat",
                        titleAr = "حكم مرتكب الكبيرة وإثبات شفاعة النبي صلى الله عليه وسلم",
                        summaryUz = "Kabira gunoh qilgan mo'min iymondan chiqmaydi. Alloh xohlasa kechiradi, xohlasa gunohiga yarasha jazolab, oxir-oqibat jannatga kiritadi. Shafoat haqdir.",
                        pdfStartPage = 125,
                        pdfEndPage = 144,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 193,
                                arabicText = "صاحبُ الكبيرةِ من أهل القبلة مؤمنٌ لا يخرج عن الإيمان بارتكاب الكبيرة ولا يخلد في النار عند أهل السنة والجماعة... لقوله تعالى: ﴿إِنَّ اللَّهَ لَا يَغْفِرُ أَن يُشْرَكَ بِهِ وَيَغْفِرُ مَا دُونَ ذَٰلِكَ لِمَن يَشَاءُ﴾.",
                                uzbekTranslation = "Qibla ahlidan bo'lgan gunohi kabira (katta gunoh) egasi mo'mindir, u katta gunoh sodir etish bilan iymondan chiqmaydi va agar do'zaxga tushsa ham unda abadiy qolmaydi. Zero Alloh taolo: «Albatta Alloh O'ziga shirk keltirilishini kechirmaydi, undan boshqa gunohlarni O'zi xohlagan kishi uchun kechiradi» (Niso, 48) deb marhamat qilgan.",
                                pageNumber = 125
                            ),
                            ParagraphItem(
                                paragraphNumber = 205,
                                arabicText = "والشفاعةُ لرسول الله صلى الله عليه وسلم وللأنبياء والأولياء في حقّ أهل الكبائر من المسلمين ثابتةٌ لقوله عليه الصلاة والسلام: «شَفاعَتي لأهلِ الكبائِرِ من أُمّتي».",
                                uzbekTranslation = "Rasululloh sollallohu alayhi vasallam, barcha payg'ambarlar va avliyolarning gunohi kabira qilgan musulmonlar haqidagi shafoatlari haq va sobitdir. Chunki Payg'ambarimiz s.a.v.: «Mening shafoatim ummatimdan bo'lgan katta gunoh egalari uchundir» deb marhamat qilganlar.",
                                pageNumber = 135
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_kabira",
                                termUz = "Gunohi kabira va Murtakib",
                                termAr = "مرتكب الكبيرة",
                                definitionUz = "Katta gunoh qilgan fosiq musulmon. U kofir bo'lmaydi, balki osiy mo'mindir.",
                                sourceExample = "صاحب الكبيرة مؤمن لا يخرج عن الإيمان (24-bob, 193-fasl)",
                                pageRef = 125
                            ),
                            ConceptItem(
                                id = "c_shafoat",
                                termUz = "Shafoat",
                                termAr = "الشَّفَاعَة",
                                definitionUz = "Qiyomat kuni Payg'ambarimiz sollallohu alayhi vasallamning gunohkor mo'minlarni afv ettirishlari.",
                                sourceExample = "شفاعتي لأهل الكبائر من أمتي (24-bob, 205-fasl)",
                                pageRef = 135
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_nisa_48",
                                titleUz = "Shirkdan boshqa gunohlarning kechirilishi",
                                type = ProofType.QURAN,
                                arabicText = "﴿إِنَّ اللَّهَ لَا يَغْفِرُ أَن يُشْرَكَ بِهِ وَيَغْفِرُ مَا دُونَ ذَٰلِكَ لِمَن يَشَاءُ﴾",
                                uzbekTranslation = "«Alloh O'ziga shirk keltirilishini kechirmaydi, undan o'zgasini xohlaganiga kechiradi» (Niso, 48).",
                                sourceRef = "Kitob at-Tamhid, 125-bet, 193-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_xavorij_mutazila_kabira",
                                topicTitle = "Gunohi kabira qilgan odamning holati",
                                ahlSunnahView = "Osiy mo'min bo'lib qoladi, xohlasa mag'firat qilinadi, do'zaxda abadiy qolmaydi.",
                                opposingSchool = "Xavorij va Mu'tazila",
                                opposingView = "Xavorij: Kofir bo'ladi. Mu'tazila: Na mo'min va na kofir, ikki o'rtada (Manzila baynal manzilatayn) bo'lib, do'zaxda abadiy qoladi.",
                                refutationUz = "Iymonning asosi tasdiqdir. Gunoh qilish tasdiqni yo'qotmaydi, balki toatni kamaytiradi."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_24_1",
                                lessonId = "ch_24_l_1",
                                chapterId = "ch_24",
                                questionUz = "Gunohi kabira qilgan musulmon haqida Ahli Sunna val-Jamoa e'tiqodi qanday?",
                                options = listOf(
                                    "U darhol kofirga aylanadi",
                                    "U dindan butunlay chiqadi",
                                    "U gunohkor mo'mindir, do'zaxda abadiy qolmaydi va shafoat unga yetadi",
                                    "U hech qanday hisob-kitobsiz jannatga kiradi"
                                ),
                                correctIndex = 2,
                                explanationUz = "Lomishiy 193-faslda: Kabira egasi mo'mindir, abadiy do'zaxda qolmaydi deb bayon qilgan.",
                                sourcePageRef = 125,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_24_2",
                                lessonId = "ch_24_l_1",
                                chapterId = "ch_24",
                                questionUz = "Rasululloh sollallohu alayhi vasallamning shafoatlari kimlarga nasib etadi?",
                                options = listOf(
                                    "Faqat gunohsiz farishtalarga",
                                    "Ummatdan gunohi kabira (katta gunoh) qilgan tavbasiz vafot etgan ahli qiblaga ham yetadi",
                                    "Faqat boy-badavlat insonlarga",
                                    "Hech kimga nasib etmaydi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Mashhur hadisda: «Shafoatim ummatimdan kabira gunoh qilganlar uchundir» deb marhamat qilingan.",
                                sourcePageRef = 127,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_24_3",
                                lessonId = "ch_24_l_1",
                                chapterId = "ch_24",
                                questionUz = "Xavorijlarning 'Gunoh qilgan odam kofir bo'ladi' degan botil qoidasiga qarshi qanday dalil keltiriladi?",
                                options = listOf(
                                    "Qur'onda qotillik va o'g'rilik qilganlar ham «Ey iymon keltirganlar» deb chaqirilgan va birodar deb nomlangan",
                                    "Xavorijlar bilan faqat jismoniy kurash olib boriladi",
                                    "Gunohlar umuman gunoh emas deb aytiladi",
                                    "Iymon faqat amallardan iborat deyiladi"
                                ),
                                correctIndex = 0,
                                explanationUz = "Hujurot surasida urushayotgan ikki guruh mo'minlar deb atalgan: «Agar mo'minlardan ikki toifa urushib qolsa...»",
                                sourcePageRef = 128,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 25-BOB: Kitob al-Iyman
            Chapter(
                id = "ch_25",
                chapterNumber = 25,
                titleUz = "Iymonning mohiyati va shartlari",
                titleAr = "كتاب الإيمان وماهيته وزيادته ونقصانه",
                descriptionUz = "Iymonning ta'rifi (dil bilan tasdiq, til bilan iqror), Iymon va Islom munosabati, iymonda istisno ('InshaAlloh mo'minman') qilishning hukmi.",
                pdfStartPage = 145,
                pdfEndPage = 163,
                lessons = listOf(
                    Lesson(
                        id = "ch_25_l_1",
                        chapterId = "ch_25",
                        lessonNumber = 1,
                        titleUz = "Iymon ta'rifi, Islom bilan birligi va Istisno",
                        titleAr = "حقيقة الإيمان: التصديق والإقرار، والرد على الاستثناء",
                        summaryUz = "Iymon — dil bilan qat'iy tasdiq va til bilan iqrordir. Iymon va Islom bir narsadir. 'InshaAlloh mo'minman' deb shak bilan aytish joiz emas, balki qat'iy 'Haqiqiy mo'minman' deyiladi.",
                        pdfStartPage = 145,
                        pdfEndPage = 163,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 218,
                                arabicText = "الإيمانُ في اللغة عبارةٌ عن التصديق... وفي الشريعة عبارةٌ عن تصديق القلب بما جاء به الرسولُ من عند الله تعالى والإقرارُ به باللسان. وهذا مذهبُ عامّة الفقهاء والمتكلّمين من أهل السنّة والجماعة.",
                                uzbekTranslation = "Iymon lug'atda tasdiqlashdan iboratdir... Shariatda esa Payg'ambar sollallohu alayhi vasallam Alloh huzuridan keltirgan barcha narsalarni qalb bilan tasdiqlash va til bilan unga iqror bo'lishdir. Bu Ahli Sunna val-Jamoaning barcha faqihlari va mutakallimlarining mazhabidir.",
                                pageNumber = 145
                            ),
                            ParagraphItem(
                                paragraphNumber = 230,
                                arabicText = "ثم الإيمانُ والإسلامُ واحدٌ عند أهل السنّة والجماعة لأنّ الإسلامَ هو الاستسلامُ والخضوعُ لأمر الله تعالى... ولا خضوعَ بدون التصديق ولا تصديقَ بدون الخضوع.",
                                uzbekTranslation = "So'ngra Iymon bilan Islom Ahli Sunna val-Jamoa (Hanafiylar va Moturidiylar) nazdida bitta narsadir. Chunki Islom — Alloh taoloning amriga bo'ysunish va taslim bo'lishdir... Qalbdagi tasdiqsiz taslimiyat bo'lmaydi, taslimiyatsiz esa haqiqiy tasdiq bo'lmaydi.",
                                pageNumber = 153
                            ),
                            ParagraphItem(
                                paragraphNumber = 240,
                                arabicText = "ولا يجوزُ الاستثناءُ في الإيمان بأن يقول الرجل: «أنا مؤمنٌ إن شاء الله!» على سبيل الشكّ لأنّ الإيمانَ تصديقٌ ويقينٌ، والشكُّ يُنافي التصديق واليقين. بل يقول: «أنا مؤمنٌ حقّاً!».",
                                uzbekTranslation = "Iymonda shak tarzida: «Men inshaAlloh mo'minman!» deb istisno qilish joiz emasdir. Chunki iymon — qat'iy tasdiq va yaqiyniy ishonchdir. Shak-shubha esa tasdiq va yaqiynga mutlaqo ziddir. Balki mo'min kishi qat'iyat bilan: «Men haqiqatan mo'minman!» («Ana mu'minun haqqan!») deb aytadi.",
                                pageNumber = 160
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_iyman_tasdiq",
                                termUz = "Tasdiq va Iqror",
                                termAr = "التصديق والإقرار",
                                definitionUz = "Iymonning ikki asosiy rukni: qalb bilan e'tiqod qilish va til bilan kalimai shahodatni e'tirof etish.",
                                sourceExample = "تصديق القلب والإقرار باللسان (25-bob, 218-fasl)",
                                pageRef = 145
                            ),
                            ConceptItem(
                                id = "c_istisno_iyman",
                                termUz = "Iymonda istisno",
                                termAr = "الاستثناء في الإيمان",
                                definitionUz = "'InshaAlloh mo'minman' deyish. Moturidiylar buni iymondagi shak deb hisoblab man etadilar.",
                                sourceExample = "أنا مؤمن حقاً ولا يجوز الاستثناء بالشك (25-bob, 240-fasl)",
                                pageRef = 160
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_hujurat_15",
                                titleUz = "Iymonda shak bo'lmasligi",
                                type = ProofType.QURAN,
                                arabicText = "﴿إِنَّمَا الْمُؤْمِنُونَ الَّذِينَ آمَنُوا بِاللَّهِ وَرَسُولِهِ ثُمَّ لَمْ يَرْتَابُوا﴾",
                                uzbekTranslation = "«Haqiqiy mo'minlar faqat Allohga va Rasuliga iymon keltirib, so'ngra aslo shubhaga tushmagan zotlardir» (Hujurot, 15).",
                                sourceRef = "Kitob at-Tamhid, 160-bet, 240-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_istisno_bahsi",
                                topicTitle = "Iymonda istisno masalasi",
                                ahlSunnahView = "«Men haqiqiy mo'minman» deyiladi, shubha bilan istisno qilinmaydi.",
                                opposingSchool = "Ash'ariylar (ayrim qarashlari) va boshqalar",
                                opposingView = "Xotima e'tibori bilan «InshaAlloh mo'minman» deyish kerak.",
                                refutationUz = "Hozirgi holatda inson o'zining iymonida shubha qilmasligi shart, shubha iymonni botil qiladi."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_25_1",
                                lessonId = "ch_25_l_1",
                                chapterId = "ch_25",
                                questionUz = "Imom Abu Hanifa va Imom al-Lomishiy nazdida 'Iymonda istisno qilish' (InshaAlloh mo'minman deyish) nega man qilinadi?",
                                options = listOf(
                                    "Chunki arab tili qoidasiga to'g'ri kelmaydi",
                                    "Chunki inshaAlloh so'zi yaxshi emas",
                                    "Chunki iymon qat'iy yaqiyndir, unda shak-shubha qilish joiz emas",
                                    "Chunki faqat farishtalar shunday deydi"
                                ),
                                correctIndex = 2,
                                explanationUz = "Lomishiy 240-faslda: Iymon tasdiq va yaqiyn bo'lib, shak unga ziddir deb uqtiradi.",
                                sourcePageRef = 160,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_25_2",
                                lessonId = "ch_25_l_1",
                                chapterId = "ch_25",
                                questionUz = "Ahli Sunna val-Jamoada iymonning asosiy rukni nima?",
                                options = listOf(
                                    "Faqat til bilan gapirish",
                                    "Qalb bilan tasdiqlash (tasdiq bil-qalb) va til bilan iqror bo'lish",
                                    "Faqat arabcha kiyinish",
                                    "Faqat masjidda yashash"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 218-faslda: Iymon shariatda qalb bilan tasdiq va til bilan iqrordir deb ta'riflagan.",
                                sourcePageRef = 145,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_25_3",
                                lessonId = "ch_25_l_1",
                                chapterId = "ch_25",
                                questionUz = "Iymon va Islom o'rtasidagi munosabat Hanafiya-Moturidiyada qanday talqin qilinadi?",
                                options = listOf(
                                    "Ular mutlaqo zid narsalardir",
                                    "Iymon va Islom mohiyatan bir bo'lib, bir-biridan ajralmaydi",
                                    "Islom faqat podshohlarga tegishli",
                                    "Iymon faqat farishtalarda bo'ladi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 230-faslda: Qalbdagi tasdiqsiz taslimiyat (islom) bo'lmaydi, taslimiyatsiz esa iymon bo'lmaydi deb bitta ekanini bayon qilgan.",
                                sourcePageRef = 153,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 26-BOB: Al-Imama
            Chapter(
                id = "ch_26",
                chapterNumber = 26,
                titleUz = "Imomat (Xalifalik) va Sahobalar fazilati",
                titleAr = "فصل في الإمامة وفضائل الصحابة",
                descriptionUz = "Musulmonlar boshiga adolatli imom saylashning vojibligi, Xulafoi Roshidinlar (Abu Bakr, Umar, Usmon, Ali r.a.) tartibi va barcha sahobalarni sevish.",
                pdfStartPage = 163,
                pdfEndPage = 172,
                lessons = listOf(
                    Lesson(
                        id = "ch_26_l_1",
                        chapterId = "ch_26",
                        lessonNumber = 1,
                        titleUz = "Xalifalik tartibi va Sahobalarga muhabbat",
                        titleAr = "ترتيب الخلفاء الراشدين ونفي طعن الروافض",
                        summaryUz = "Imom saylash ummatga vojibdir. Xalifalarning eng afzali Abu Bakr, so'ng Umar, so'ng Usmon, so'ng Alidir (r.a.). Barcha sahobalarni ehtirom qilish vojibdir.",
                        pdfStartPage = 163,
                        pdfEndPage = 172,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 246,
                                arabicText = "نَصْبُ الإمامِ واجبٌ على الأمّة عند أهل السنّة... لإقامة الحُدود وسَدّ الثُّغور وجباية الأموال وقِسمتها وقهر اللصوص وقُطّاع الطُّرُق وإقامة الجُمَع والأعياد وقَطع المُنازعات الواقعة بين العباد.",
                                uzbekTranslation = "Ahli Sunna nazdida musulmonlar boshiga Imom (davlat rahbari/xalifa) tayinlash butun ummatga vojibdir. Chunki haddlarni (qonuniy jazolarni) joriy qilish, chegaralarni himoyalash, o'ljalar va soliqlarni to'g'ri taqsimlash, qaroqchilar va buzg'unchilarni jilovlash, juma va hayit namozlarini ado etish hamda odamlar o'rtasidagi mojarolarni adolat bilan hal qilish faqat Imom orqali amalga oshadi.",
                                pageNumber = 163
                            ),
                            ParagraphItem(
                                paragraphNumber = 250,
                                arabicText = "وأحقُّ الناسِ بالإمامة بعد رسول الله صلى الله عليه وسلم أبو بكر الصدّيق ثم عُمَر بن الخطّاب ثم عُثمان بن عفّان ثم عليّ بن أبي طالب - رضي الله عنهم أجمعين! - وترتيبُهم في الفضل كترتيبهم في الخِلافة.",
                                uzbekTranslation = "Rasululloh sollallohu alayhi vasallamdan keyin xalifalikka insonlarning eng haqlisi Abu Bakr Siddiq, so'ngra Umar ibn Xattob, so'ngra Usmon ibn Affon, so'ngra Ali ibn Abu Tolibdir (Alloh barchalaridan rozi bo'lsin!). Ularning fazilatdagi tartiblari xalifalikdagi ketma-ketliklari kabidir.",
                                pageNumber = 166
                            ),
                            ParagraphItem(
                                paragraphNumber = 259,
                                arabicText = "ويجبُ الكَفُّ عن ذِكْر الصحابة إلا بخيرٍ... لقوله صلى الله عليه وسلم: «اللهَ اللهَ في أصحابي! لا تتّخذوهم غَرَضاً من بعدي، فمن أحبّهم فبحُبّي أحبّهم ومن أبغضَهم فببُغضي أبغضهم».",
                                uzbekTranslation = "Sahobalar o'rtasida bo'lib o'tgan nizolarni gapirishdan tilni tiyish va ularni faqat yaxshilik bilan yod etish vojibdir. Chunki Payg'ambarimiz s.a.v.: «Sahobalarim borasida Allohdan qo'rqinglar, Allohdan qo'rqinglar! Mendan keyin ularni nishon qilib olmanglar! Kim ularni yaxshi ko'rsa, meni yaxshi ko'rgani uchun yaxshi ko'radi, kim ularni yomon ko'rsa, meni yomon ko'rgani uchun yomon ko'radi!» deb qat'iy buyurganlar.",
                                pageNumber = 171
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_imomat",
                                termUz = "Imomat (Xalifalik)",
                                termAr = "الإمامة والخلافة",
                                definitionUz = "Din va dunyo ishlarini boshqarishda Payg'ambar alayhissalomga o'rinbosarlik qilish.",
                                sourceExample = "نصب الإمام واجب على الأمة (26-bob, 246-fasl)",
                                pageRef = 163
                            ),
                            ConceptItem(
                                id = "c_xulafai_roshidin",
                                termUz = "Xulafoi Roshidin",
                                termAr = "الخلفاء الراشدون",
                                definitionUz = "Hidoyat yo'lidagi to'rtta buyuk xalifa: Abu Bakr, Umar, Usmon, Ali (roziyallohu anhum).",
                                sourceExample = "ترتيبهم في الفضل كترتيبهم في الخلافة (26-bob, 250-fasl)",
                                pageRef = 166
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_hadith_sahoba",
                                titleUz = "Sahobalarni ehtirom qilish haqida hadis",
                                type = ProofType.HADITH,
                                arabicText = "اللهَ اللهَ في أصحابي! لا تتخذوهم غرضاً من بعدي",
                                uzbekTranslation = "«Sahobalarim borasida Allohdan qo'rqinglar! Ularni ta'na nishoni qilmanglar».",
                                sourceRef = "Kitob at-Tamhid, 171-bet, 259-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_shia_rofiza_sahoba",
                                topicTitle = "Xalifalar tartibi va sahobalarni haqorat qilish",
                                ahlSunnahView = "Abu Bakr, Umar, Usmon va Ali r.a. haqli xalifalar va barcha sahobalar adolatli e'zozdadir.",
                                opposingSchool = "Rofiziylar va Shialar",
                                opposingView = "Dastlabki xalifalar xalifalikni tortib olgan deb sahobalarni la'natlash.",
                                refutationUz = "Sahobalarni haqorat qilish Qur'on va Hadisga ochiq tajovuz bo'lib, xalifalik sahobalarning shurosi va ijmosi bilan sobit bo'lgan."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_26_1",
                                lessonId = "ch_26_l_1",
                                chapterId = "ch_26",
                                questionUz = "Ahli Sunna val-Jamoa e'tiqodida Xulafoi Roshidinlarning fazilat tartibi qanday?",
                                options = listOf(
                                    "Faqat hazrati Ali afzal",
                                    "Xalifalikdagi tartiblari kabi: Abu Bakr, Umar, Usmon, so'ngra Ali (r.a.)",
                                    "Hech qanday tartib yo'q",
                                    "Keyingi davr podshohlari afzal"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 250-faslda: Ularning fazilatdagi tartiblari xalifalikdagi tartiblari kabidir deb ta'kidlagan.",
                                sourcePageRef = 166,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_26_2",
                                lessonId = "ch_26_l_1",
                                chapterId = "ch_26",
                                questionUz = "Ummat boshiga odil Imom (rahbar) saylash nima uchun vojib hisoblanadi?",
                                options = listOf(
                                    "Haddlar (qonunlar) ijrosi, chegaralar himoyasi, zulmning oldini olish va juma namozlarini ado etish uchun",
                                    "Faqat soliq yig'ish uchun",
                                    "Faqat urush e'lon qilish uchun",
                                    "Bu umuman vojib emas"
                                ),
                                correctIndex = 0,
                                explanationUz = "Lomishiy 246-faslda: Imom tayinlash ummatning nizom va tinchligini ta'minlash uchun farzi kifoyadir deb uqtiradi.",
                                sourcePageRef = 163,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_26_3",
                                lessonId = "ch_26_l_1",
                                chapterId = "ch_26",
                                questionUz = "Rasululloh s.a.v.ning «Sahobalarim borasida Allohdan qo'rqinglar!» hadislaridan kelib chiqadigan shar'iy vazifa nima?",
                                options = listOf(
                                    "Sahobalarni tanqid qilish",
                                    "Barcha sahobalarni yaxshi ko'rish, ehtirom qilish va oralaridagi ixtiloflarni faqat yaxshilikka yo'yib tilni tiyish",
                                    "Ularni untib yuborish",
                                    "Faqat ayrim sahobalarni tan olish"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 259-faslda: Sahobalarga til tekkizishdan saqlanish va ularni faqat xayr bilan eslash vojib ekanini qat'iy uqtiradi.",
                                sourcePageRef = 171,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            )
        )
    }
}
