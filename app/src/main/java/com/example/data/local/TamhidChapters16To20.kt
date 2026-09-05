package com.example.data.local

import com.example.data.model.*

object TamhidChapters16To20 {
    fun getChapters(): List<Chapter> {
        return listOf(
            // 16-BOB: Khalq af'al al-ibad
            Chapter(
                id = "ch_16",
                chapterNumber = 16,
                titleUz = "Bandalar fe'llarining yaratilishi va Kasb",
                titleAr = "فصل في إثبات خَلْق أفعال العباد",
                descriptionUz = "Bandalarning barcha ixtiyoriy amallari Alloh taoloning yaratishi (xalq) va bandaning kasbidir. Jabriya va Qadariyaga raddiya.",
                pdfStartPage = 97,
                pdfEndPage = 103,
                lessons = listOf(
                    Lesson(
                        id = "ch_16_l_1",
                        chapterId = "ch_16",
                        lessonNumber = 1,
                        titleUz = "Fe'llarning xalq qilinishi va Kasb ta'rifi",
                        titleAr = "خلق الأفعال والكسب والرد على القدرية والجبرية",
                        summaryUz = "Bandalarning amallari Alloh tomonidan yaratiladi, banda esa o'z ixtiyori bilan kasb qiladi. Shu sababli banda savob va jazo oladi.",
                        pdfStartPage = 97,
                        pdfEndPage = 103,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 127,
                                arabicText = "اختلف الناسُ في الأفعال الاختيارية للعباد. قال أهلُ السنّة والجماعة: «هي مخلوقةٌ لله - تعالى! - مكسوبةٌ للعباد». والله تعالى يُسمّى بتخليقها وإيجادها خالقاً والعبدُ يُسمّى بكَسْبها ومباشرتها فاعلاً لها ويصير مطيعاً أو عاصياً.",
                                uzbekTranslation = "Bandalarning ixtiyoriy fe'llari borasida odamlar ixtilof qildilar. Ahli Sunna val-Jamoa aytdilar: «Bandalarning barcha ixtiyoriy amallari Alloh taoloning xalqi (yaratgani) va bandalarning kasbidir (qozonganidir)». Alloh taolo u fe'llarni yo'qdan bor qilgani uchun Xoliq (Yaratuvchi) deb nomlanadi, banda esa uni kasb qilgani va bevosita bajargani uchun uning bajaruvchisi (foil) deb ataladi hamda shu bilan itoatkor yoki gunohkor bo'ladi.",
                                pageNumber = 97
                            ),
                            ParagraphItem(
                                paragraphNumber = 128,
                                arabicText = "وقالت القدَرية وهم المعتزلة: «لا صُنْعَ لله - تعالى! - في أفعال العباد، والعبادُ هم المُوجِدون المخترِعون لها». وقالت الجَبرية ورئيسُهم جَهْم بن صَفْوان: «إنّ الأفعال المضافة إلى العباد كلّها أفعالُ الله - تعالى! - على الحقيقة لا اختيارَ للعبد في ذلك كما في حركات المُرتَعِش والعُروق النابضة».",
                                uzbekTranslation = "Qadariylar (ya'ni Mu'taziliylar): «Bandalarning fe'llarida Allohning hech qanday yaratishi yo'q, bandalarning o'zlari o'z amallarini yo'qdan bor qiluvchi ijodkorlaridir», dedilar. Jabriylar va ularning boshlig'i Jahm ibn Safvon esa: «Bandalarga nisbat berilgan barcha amallar haqiqatda Allohning O'z fe'lidir, bandada hech qanday ixtiyor yo'q, xuddi qaltirayotgan odamning titrashi yoki tomir urishi kabidir», dedilar.",
                                pageNumber = 97
                            ),
                            ParagraphItem(
                                paragraphNumber = 129,
                                arabicText = "وحُجّةُ أهل الحقّ على القدَرية قوله تعالى: ﴿خَالِقُ كُلِّ شَيْءٍ﴾ وقوله تعالى: ﴿وَاللَّهُ خَلَقَكُمْ وَمَا تَعْمَلُونَ﴾ وهذا دليلٌ على أنّ خالقَ أفعال العباد هو الله تعالى.",
                                uzbekTranslation = "Haq ahlining Qadariylarga qarshi hujjati Alloh taoloning: «Alloh barcha narsaning Yaratuvchisidir» (An'om, 102) va «Holbuki sizlarni ham, siz qilayotgan amallarni ham Alloh yaratgandir» (Saffot, 96) degan oyatlaridir. Bu bandalar fe'llarining Xoliqi Alloh ekaniga ochiq dalildir.",
                                pageNumber = 97
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_kasb",
                                termUz = "Kasb (Qozonish)",
                                termAr = "الكَسْب",
                                definitionUz = "Bandaning o'z niyati va juz'iy ixtiyori bilan fe'lga yo'nalishi va Alloh o'sha amaldagi qudratni yaratib berishi natijasida amalga mas'ul bo'lishi.",
                                sourceExample = "مخلوقة لله مكسوبة للعباد (16-bob, 127-fasl)",
                                pageRef = 97
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_saffat_96",
                                titleUz = "Fe'llar yaratilishiga Qur'oniy dalil",
                                type = ProofType.QURAN,
                                arabicText = "﴿وَاللَّهُ خَلَقَكُمْ وَمَا تَعْمَلُونَ﴾",
                                uzbekTranslation = "«Holbuki sizlarni ham, qilayotgan amallaringizni ham Alloh yaratgandir» (Saffot, 96).",
                                sourceRef = "Kitob at-Tamhid, 97-bet, 129-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_qadariya_jabriya",
                                topicTitle = "Bandalarning amallari va ixtiyor",
                                ahlSunnahView = "Fe'llarni Alloh yaratadi, banda ixtiyor bilan kasb qiladi (O'rta yo'l).",
                                opposingSchool = "Qadariya (Mu'tazila) va Jabriya",
                                opposingView = "Qadariya: Banda o'z fe'lining xoliqi. Jabriya: Banda mutlaqo majbur, ixtiyori yo'q.",
                                refutationUz = "Qadariya shirkka (ko'p xoliqlar bor deyishga) olib boradi, Jabriya esa shariat buyruqlari va mukofot-jazoni bekor qiladi."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_16_1",
                                lessonId = "ch_16_l_1",
                                chapterId = "ch_16",
                                questionUz = "Ahli Sunna val-Jamoa nazdida insonning qilayotgan amali kimga tegishli bo'ladi?",
                                options = listOf(
                                    "Barcha jihatdan faqat bandaning o'zi yaratadi",
                                    "Yaratish (xalq) jihatidan Allohga, qozonish (kasb) va mas'uliyat jihatidan bandaga",
                                    "Banda mutlaqo majbur bo'lib, hech qanday ixtiyorga ega emas",
                                    "Farishtalar yaratadi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 127-faslda: 'Amallar Allohning xalqi va bandaning kasbidir' deb ta'riflagan.",
                                sourcePageRef = 97,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_16_2",
                                lessonId = "ch_16_l_1",
                                chapterId = "ch_16",
                                questionUz = "Jabriya mazhabining adashishi qaysi jihatda edi?",
                                options = listOf(
                                    "Ular insonni o'z amallarining yaratuvchisi deb da'vo qildilar",
                                    "Ular insonni shamolda uchayotgan par kabi butunlay majbur deb, uning ixtiyorini inkor qildilar",
                                    "Ular faqat boylikka sig'inishga buyurdilar",
                                    "Ular barcha amallarni bekor qildilar"
                                ),
                                correctIndex = 1,
                                explanationUz = "Jabriylar insonning kasb va ixtiyorini inkor qilib, qilmishlariga mas'ul emas deb adashdilar.",
                                sourcePageRef = 98,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_16_3",
                                lessonId = "ch_16_l_1",
                                chapterId = "ch_16",
                                questionUz = "«Ixlos» surasidagi «Allohu xoliqo kulli shay'» oyati nimani isbotlaydi?",
                                options = listOf(
                                    "Faqat osmon va yer yaratilganini",
                                    "Bandalarning barcha harakatlari va fe'llari ham Alloh tomonidan yaratilganini",
                                    "Inson o'zi xoliq ekanini",
                                    "Farishtalar mustaqil ekanini"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy ta'kidlaydi: Banda ham, uning amali ham 'shay' (narsa) jumlasiga kiradi, demak uning xoliqi ham Allohdir.",
                                sourcePageRef = 100,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 17-BOB: Mutavalladot
            Chapter(
                id = "ch_17",
                chapterNumber = 17,
                titleUz = "Mutavalladot (Hosil bo'luvchi fe'llar)",
                titleAr = "فصل في أن المتولَّدات مخلوقة لله تعالى",
                descriptionUz = "Inson fe'lidan keyin hosil bo'ladigan oqibatlar (otilgan kamon yetkazgan jarohat, shisha sinishi) ham Allohning xalqi ekani.",
                pdfStartPage = 103,
                pdfEndPage = 105,
                lessons = listOf(
                    Lesson(
                        id = "ch_17_l_1",
                        chapterId = "ch_17",
                        lessonNumber = 1,
                        titleUz = "Mutavalladot va Sababiyat qonuni",
                        titleAr = "حقيقة المتولدات وخلقها لله تعالى",
                        summaryUz = "Banda o'q otganda paydo bo'ladigan jarohat va og'riq bandaning emas, balki Alloh taoloning yaratishidir.",
                        pdfStartPage = 103,
                        pdfEndPage = 105,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 141,
                                arabicText = "الأفعالُ والآثارُ التي سمّتها القدريّةُ مُتولَّداتٍ كلُّها مخلوقةٌ لله تعالى عند أهل السنة... لأن العبدَ لا قدرةَ له على الامتناع من المُضيّ في السهم بعد الرَّمْي ومن الألم في الحيوان بعد الضَّرب.",
                                uzbekTranslation = "Qadariylar «mutavalladot» (fe'ldan tug'iluvchi oqibatlar) deb atagan barcha asarlar Ahli Sunna nazdida Alloh taoloning yaratig'idir. Chunki bandaning o'zi kamonni otgandan keyin o'qning uchib ketishini yoki urgandan keyin tirik jonda paydo bo'ladigan og'riqni to'xtatishga aslo qudrati yetmaydi.",
                                pageNumber = 103
                            ),
                            ParagraphItem(
                                paragraphNumber = 144,
                                arabicText = "وقلنا: هذا باطلٌ لأن التخليق لا بُدّ له من القُدرة... وإنما سُمّي الرميُ والضربُ والجرح ونحوها أسباباً لها عُرفاً لإجراء الله تعالى العادةَ بتخليق هذه الأشياء عَقيبَ هذه الأفعال لا لأنها حدثت منها.",
                                uzbekTranslation = "Biz aytdik: Qadariylarning fikri botildir, chunki yaratish uchun mutlaq qudrat lozimdir. Kamon otish, urish va jarohatlash kabi narsalar faqat urfda 'sabab' deb ataladi. Chunki Alloh taolo O'z odatiga ko'ra bu amallardan keyin o'sha oqibatlarni yaratadi, oqibatlar sababning o'zidan mustaqil paydo bo'lgani uchun emas.",
                                pageNumber = 104
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_mutavalladot",
                                termUz = "Mutavalladot",
                                termAr = "المُتَوَلَّدَات",
                                definitionUz = "Dastlabki harakatdan keyin zanjirli tarzda yuz beradigan oqibatlar (masalan, tosh otganda oyna sinishi).",
                                sourceExample = "المتولدات كلها مخلوقة لله تعالى (17-bob, 141-fasl)",
                                pageRef = 103
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_odat_dalil",
                                titleUz = "Odatning joriy qilinishi (Sunnatulloh)",
                                type = ProofType.AQLIY,
                                arabicText = "لإجراء الله تعالى العادة بتخليق هذه الأشياء عقيب هذه الأفعال",
                                uzbekTranslation = "Sabab va oqibat o'rtasidagi bog'liqlik Alloh joriy qilgan ilohiy odatdir, oqibatni ham Alloh yaratadi.",
                                sourceRef = "Kitob at-Tamhid, 104-bet, 144-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mutavalladot_bahsi",
                                topicTitle = "Oqibatlarni kim yaratadi?",
                                ahlSunnahView = "Oqibatlarni ham, sabablarni ham faqat Alloh yaratadi.",
                                opposingSchool = "Mu'tazila (Nazzom, Sumoma ibn Ashras)",
                                opposingView = "Oqibat tabiat qonuniyati bilan o'z-o'zidan yoki banda orqali yuzaga keladi.",
                                refutationUz = "Tabiat hech narsani mustaqil yarata olmaydi, hamma narsa Allohning xalqidir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_17_1",
                                lessonId = "ch_17_l_1",
                                chapterId = "ch_17",
                                questionUz = "Kamon otilgandan keyin yetkazilgan jarohat va og'riqni kim yaratadi?",
                                options = listOf(
                                    "O'qning o'zi mustaqil yaratadi",
                                    "Otuvchi odamning o'zi yaratadi",
                                    "Tabiat kuchi yaratadi",
                                    "Alloh taolo O'z odatiga muvofiq yaratadi"
                                ),
                                correctIndex = 3,
                                explanationUz = "Lomishiy 144-faslda: Barcha oqibatlarni Alloh taolo yaratishini uqtiradi.",
                                sourcePageRef = 104,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_17_2",
                                lessonId = "ch_17_l_1",
                                chapterId = "ch_17",
                                questionUz = "Tavallud (sabab orqali oqibatning o'z-o'zidan tug'ilishi) nazariyasi nima uchun botildir?",
                                options = listOf(
                                    "Chunki jonsiz sabablar o'z-o'zidan biron narsani yaratishga qodir emas, barchasini Qodir Alloh yaratadi",
                                    "Chunki bu tibbiyotga to'g'ri kelmaydi",
                                    "Chunki o'q har doim nishonga tegmaydi",
                                    "Chunki kamon yog'ochdan yasaladi"
                                ),
                                correctIndex = 0,
                                explanationUz = "Sabab va oqibat o'rtasida majburiy aqliy bog'liqlik yo'q, olovni yoquvchi qilib yaratgan ham, Ibrohim (a.s.)ga salqin qilgan ham Allohdir.",
                                sourcePageRef = 104,
                                difficulty = DifficultyLevel.MEDIUM
                            )
                        )
                    )
                )
            ),

            // 18-BOB: Ajal al-maqtul
            Chapter(
                id = "ch_18",
                chapterNumber = 18,
                titleUz = "O'ldirilgan odam o'z ajali bilan vafot etishi",
                titleAr = "فصل في أن المقتول ميّت بأجله",
                descriptionUz = "Har bir inson faqat bitta ajalga ega ekani va o'ldirilgan odam ham o'z ajali bilan vafot etishi.",
                pdfStartPage = 105,
                pdfEndPage = 106,
                lessons = listOf(
                    Lesson(
                        id = "ch_18_l_1",
                        chapterId = "ch_18",
                        lessonNumber = 1,
                        titleUz = "Ajal bittaligi va Qotilning jazosi sababi",
                        titleAr = "وحدة الأجل ومسؤولية القاتل",
                        summaryUz = "O'ldirilgan odam o'z ajali bilan o'ladi. Qotil esa gunoh ishni (taqiqlangan harakatni) kasb qilgani uchun qasos yoki jazolarga tortiladi.",
                        pdfStartPage = 105,
                        pdfEndPage = 106,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 146,
                                arabicText = "المقتولُ ميّتٌ بأجَلِه عند أهل السنة والجماعة لأنّ الأجلَ اسمٌ لمُدّةٍ ضُربت لانقضاء عُمره... ولأنّ مَن قال بأنّ الله تعالى جعلَ له أجلاً آخَرَ مع علمه أنه لا يعيش إلى ذلك الزمان فقد نسَبَ اللهَ تعالى إلى الجهل بعواقب الأمور... وكلاهما كُفرٌ.",
                                uzbekTranslation = "Ahli Sunna val-Jamoa nazdida o'ldirilgan kishi o'z ajali bilan o'lgandir. Chunki ajal — inson umrining tugashi uchun belgilangan muddatning nomidir. Kimki: «Alloh unga boshqa ajal belgilagan edi, qotil uni oldinroq o'ldirdi» desa, Alloh taoloni narsalarning oqibatini bilmaslikda (jahl bilan) ayblagan yoki qotilning fe'li Allohning qadarini yengib ketdi deb e'tiqod qilgan bo'ladi. Bularning har ikkisi ham kufrdir.",
                                pageNumber = 105
                            ),
                            ParagraphItem(
                                paragraphNumber = 147,
                                arabicText = "وقالت المعتزلة: «إنّه غير ميّت بأجله وله أجل آخر لو مات بأجله لما وجب القصاص أو الدية على قاتله». وقُلنا: إنّما وجبَ عليه القصاصُ لأنه اكتسبَ فِعلاً أجرى الله تعالى العادةَ بانزهاق الروح عقيبه... وارتكبَ المنهيَّ عنه فجازَ أن يُؤاخذ به.",
                                uzbekTranslation = "Mu'taziliylar: «U o'z ajali bilan o'lmagan, agar o'z ajali bilan o'lganida qotilga qasos yoki xun vojib bo'lmas edi», dedilar. Biz aytdik: Qotilga qasos vojib bo'lishining sababi — u shariatda harom qilingan yovuz ishni kasb qilgani va jon chiqishiga sabab bo'lgan harakatni sodir etganidir. Man qilingan jinoyatni qilgani uchun u jazoga loyiqdir.",
                                pageNumber = 105
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_ajal",
                                termUz = "Ajal",
                                termAr = "الأجَل",
                                definitionUz = "Alloh taoloning azaliy ilmi va qadari bilan har bir jon egasi uchun belgilangan aniq yashash muddati.",
                                sourceExample = "المقتول ميت بأجله عند أهل السنة (18-bob, 146-fasl)",
                                pageRef = 105
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_ajal_ilm",
                                titleUz = "Allohning azaliy ilmining o'zgarmasligi",
                                type = ProofType.AQLIY,
                                arabicText = "الأجل لا يتقدّم ولا يتأخّر عن علم الله تعالى وقضائه",
                                uzbekTranslation = "Insonning o'lim vaqti Allohning azaliy ilmida aniq bo'lib, u zarracha ilgarilamaydi va kechikmaydi.",
                                sourceRef = "Kitob at-Tamhid, 105-bet, 146-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_ajal_bahsi",
                                topicTitle = "O'ldirilgan odamning ajali",
                                ahlSunnahView = "Insonning bitta ajali bor, o'ldirilgan kimsa ham o'z ajali bilan vafot etgan.",
                                opposingSchool = "Mu'tazila",
                                opposingView = "O'ldirilgan kishining ajali kesilgan, agar o'ldirilmaganda yana yashagan bo'lardi.",
                                refutationUz = "Alloh kishining qotil qo'lida qachon o'lishini azalda bilgan, Allohning ilmi o'zgarmaydi."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_18_1",
                                lessonId = "ch_18_l_1",
                                chapterId = "ch_18",
                                questionUz = "O'ldirilgan odam o'z ajali bilan o'lgan bo'lsa, qotil nega qasos yoki jazoga tortiladi?",
                                options = listOf(
                                    "Chunki qotil ajalni o'zgartirgani uchun",
                                    "Chunki qotil shariat qaytargan harom jinoyatni o'z ixtiyori bilan kasb qilgani uchun",
                                    "Faqat odamlar talabi bilan",
                                    "Hech qanday jazoga tortilmaydi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 147-faslda: Qotil man etilgan harom ishni kasb qilgani uchun jazoga tortiladi deb bayon qilgan.",
                                sourcePageRef = 105,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_18_2",
                                lessonId = "ch_18_l_1",
                                chapterId = "ch_18",
                                questionUz = "Mu'taziliylarning 'O'ldirilgan odamning ikki ajali bor edi' degan da'vosiga qanday raddiya beriladi?",
                                options = listOf(
                                    "Allohning azaliy ilmida insonning umr muddati bitta va qat'iydir; ikki ajal bor deyish Allohga jaholat nisbat berishdir",
                                    "Insonning ajali umuman bo'lmaydi deb javob beriladi",
                                    "Faqat shifokorlar xulosasiga tayaniladi",
                                    "Mu'taziliylar bilan rozi bo'linadi"
                                ),
                                correctIndex = 0,
                                explanationUz = "Lomishiy 146-faslda: Ajal Allohning azaliy ilmi bilan belgilangan yagona muddatdir deb ta'kidlaydi.",
                                sourcePageRef = 105,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_18_3",
                                lessonId = "ch_18_l_1",
                                chapterId = "ch_18",
                                questionUz = "Munofiqun surasining «Valan yu'axxirallohu nafsan iza jaa ajaluha» oyati qaysi haqiqatni bildiradi?",
                                options = listOf(
                                    "Ajal kelganda u biron lahza ham kechiktirilmasligini va ilgarilamasligini",
                                    "Odamlar xohlasa o'lmasligini",
                                    "Qotillik gunoh emasligini",
                                    "Ajal insonning kayfiyatiga bog'liqligini"
                                ),
                                correctIndex = 0,
                                explanationUz = "Ajal vaqti yetib kelganda Alloh hech bir jonni kechiktirmaydi.",
                                sourcePageRef = 106,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 19-BOB: Al-Arzaq
            Chapter(
                id = "ch_19",
                chapterNumber = 19,
                titleUz = "Rizqlar va harom ham rizq ekani",
                titleAr = "فصل في الأرزاق",
                descriptionUz = "Rizqning ta'rifi, inson va hayvonlar yeydigan har bir ozuqa (halol bo'lsin, harom bo'lsin) rizq ekani.",
                pdfStartPage = 106,
                pdfEndPage = 107,
                lessons = listOf(
                    Lesson(
                        id = "ch_19_l_1",
                        chapterId = "ch_19",
                        lessonNumber = 1,
                        titleUz = "Rizq ta'rifi va Haromning rizqligi",
                        titleAr = "الحرام رزق وخلق الأغذية للحيوانات",
                        summaryUz = "Rizq — tirik jon oziqlanadigan barcha ozuqadir. Harom luqma ham rizqdir, lekin uni nohaq yegan kishi gunohkor bo'ladi.",
                        pdfStartPage = 106,
                        pdfEndPage = 107,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 149,
                                arabicText = "الحرامُ رِزْقٌ عند أهل السنّة لأنّ الرزقَ في اللغة يقع على الغذاء ويقع على القُوت المُقدَّر ويقع على المِلْك أيضاً. والغذاءُ والقوت قد يكون حلالاً وقد يكون حراماً. ولأنّا نرى بعضَ الأشخاص لا يأكلون مدّة عُمرهم إلا الحرامَ... فمن المحال أن يقال إنه خرج من الدنيا ولم يأكل رزق الله!",
                                uzbekTranslation = "Harom narsa ham Ahli Sunna nazdida rizqdir. Chunki rizq so'zi arab tilida ozuqaga ham, belgilangan quvvatga ham, mulkka ham aytiladi. Ozuqa esa gohida halol, gohida harom bo'ladi. Zero biz umri bo'yi faqat harom yeb o'tgan kishilarni ko'ramiz... Agar harom rizq bo'lmasa, «bu odam dunyodan Allohning rizqini tatimay o'tdi» deyish kerak bo'ladi, bu esa aqlan mutlaqo noto'g'ridir!",
                                pageNumber = 106
                            ),
                            ParagraphItem(
                                paragraphNumber = 151,
                                arabicText = "فمن حمَلَ الرّزقَ على المِلْكِ لا غيرَ فقد ضيّعَ قوله تعالى: ﴿وَمَا مِن دَابَّةٍ فِي الْأَرْضِ إِلَّا عَلَى اللَّهِ رِزْقُهَا﴾ لأنّ الحيواناتِ والدوابَّ لا مِلْكَ لها.",
                                uzbekTranslation = "Kimki rizqni faqatgina 'shaxsiy mulk' deb tushunsa (Mu'tazila kabi), Alloh taoloning: «Yer yuzida o'rmalagan barcha jonzotning rizqi faqat Allohning zimmasidadir» (Hud, 6) degan oyatini zoye qilgan bo'ladi. Chunki hayvonot va jonzotlarning hech qanday shariatdagi shaxsiy mulki yo'q, lekin ularning hammasi rizqlanadi.",
                                pageNumber = 107
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_rizq",
                                termUz = "Rizq",
                                termAr = "الرِّزْق",
                                definitionUz = "Alloh taolo tirik mavjudotlarga ozuqa va manfaat qilib bergan barcha narsalar (xoh halol, xoh harom yo'l bilan olingan bo'lsin).",
                                sourceExample = "الحرام رزق عند أهل السنة (19-bob, 149-fasl)",
                                pageRef = 106
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_hud_6",
                                titleUz = "Barcha jonzotning rizqi Alloh zimmasida ekani",
                                type = ProofType.QURAN,
                                arabicText = "﴿وَمَا مِن دَابَّةٍ فِي الْأَرْضِ إِلَّا عَلَى اللَّهِ رِزْقُهَا﴾",
                                uzbekTranslation = "«Yerdagi har bir jonzotning rizqi faqat Allohning zimmasidadir» (Hud, 6).",
                                sourceRef = "Kitob at-Tamhid, 107-bet, 151-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mutazila_rizq",
                                topicTitle = "Harom narsa rizq bo'ladimi?",
                                ahlSunnahView = "Harom ham rizqdir, lekin uni harom yo'l bilan yeyish taqiqlangan.",
                                opposingSchool = "Mu'tazila",
                                opposingView = "Harom rizq emas, chunki Alloh harom yeyishga buyurmaydi.",
                                refutationUz = "Rizq ovqatlanishdir, unga egalik qilishning halol yoki haromligi esa shariat hukmidir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_19_1",
                                lessonId = "ch_19_l_1",
                                chapterId = "ch_19",
                                questionUz = "Nega hayvonlarning ozuqlanishi rizq faqat 'mulk' emasligiga dalil bo'ladi?",
                                options = listOf(
                                    "Chunki hayvonlar pul ishlatmaydi",
                                    "Chunki hayvonlar faqat o't yeydi",
                                    "Chunki hayvonlarning mulki yo'q, lekin Alloh ularga ozuqa beradi",
                                    "Chunki hayvonlar hisob-kitob qilinmaydi"
                                ),
                                correctIndex = 2,
                                explanationUz = "Lomishiy 151-faslda: Hayvonlarning mulki bo'lmasa-da, Hud surasi 6-oyatiga ko'ra ularga rizq beriladi deb ta'kidlaydi.",
                                sourcePageRef = 107,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_19_2",
                                lessonId = "ch_19_l_1",
                                chapterId = "ch_19",
                                questionUz = "Harom qilingan taom va ichimliklar (masalan o'g'irlangan mol) haqida Ahli Sunna e'tiqodi qanday?",
                                options = listOf(
                                    "Ular rizq hisoblanmaydi",
                                    "U ham Alloh taolo yaratgan rizqdir, biroq uni nohaq yo'l bilan yegan kishi gunohkor bo'ladi",
                                    "Uni yegan odam kofir bo'ladi",
                                    "Uni faqat farishtalar yeyishi mumkin"
                                ),
                                correctIndex = 1,
                                explanationUz = "Rizq — jism manfaat oladigan narsadir. Uni halol yo'l bilan topish farz, harom yo'l bilan olish esa gunohdir.",
                                sourcePageRef = 107,
                                difficulty = DifficultyLevel.MEDIUM
                            )
                        )
                    )
                )
            ),

            // 20-BOB: Al-Ma'asi bi-Iradatillah
            Chapter(
                id = "ch_20",
                chapterNumber = 20,
                titleUz = "Gunohlar va itoatlar Allohning irodasi bilan bo'lishi",
                titleAr = "فصل في أن المعاصي بإرادة الله تعالى ومشيئته",
                descriptionUz = "Olamdagi barcha narsa (yaxshilik va yomonlik) Allohning irodasi va mashiyati bilan bo'lishi, Iroda va Rizo (rozilik) o'rtasidagi farq.",
                pdfStartPage = 108,
                pdfEndPage = 113,
                lessons = listOf(
                    Lesson(
                        id = "ch_20_l_1",
                        chapterId = "ch_20",
                        lessonNumber = 1,
                        titleUz = "Iroda va Rizo o'rtasidagi nozik farq",
                        titleAr = "المعاصي بإرادة الله ومشيئته لا برضاه ومحبته",
                        summaryUz = "Gunohlar ham Allohning irodasi va taqdiri bilan sodir bo'ladi, lekin Alloh ularga rozi emas va ularni yaxshi ko'rmaydi.",
                        pdfStartPage = 108,
                        pdfEndPage = 113,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 154,
                                arabicText = "المعاصي والطاعات وغيرُها من الموجودات كلُّها بإرادة الله ومشيئته وتخليقه وقضائه وقَدَره عند أهل السنّة. ثم ما كان منها طاعةً فهو بأمر الله ورضاه ومحبّته وهدايته وتوفيقه. وما كان منها معصيةً فهو لا بأمره ولا برضاه ولا بمحبّته، بل بخِذْلانه.",
                                uzbekTranslation = "Ahli Sunna nazdida barcha mavjudotlar — itoatlar ham, gunohlar ham Allohning irodasi, mashiyati, yaratishi, qazosi va qadari bilandir. So'ngra ulardan itoat bo'lganlari — Allohning buyrug'i, roziligi, muhabbati, hidoyati va tavfiqi bilandir. Gunoh bo'lganlari esa — Uning buyrug'isiz, roziligisiz va muhabbatisiz, balki Uning xor qilishi (yordamsiz qoldirishi) bilandir.",
                                pageNumber = 108
                            ),
                            ParagraphItem(
                                paragraphNumber = 158,
                                arabicText = "وأمّا الإجماعُ وهو أنّ الأمّةَ بأجمعهم يقولون: «ما شاءَ اللهُ كانَ وما لم يَشَأْ لم يَكُنْ». والمعتزلةُ يقولون: «لم يشأ الله الكفرَ والمعاصي وقد كان!» وهذا يُخالِفُ إجماعَ الأمّة.",
                                uzbekTranslation = "Ijmo dalili shuki, butun ummat bir ovozdan: «Alloh nimani xohlasa, o'sha bo'ladi, nimani xohlamasa, u aslo bo'lmaydi» («Mā shā'allōhu kān, va mā lam yasha' lam yakun») deydilar. Mu'taziliylar esa: «Alloh kufr va gunohlarni xohlamagan edi, lekin ular bo'lib qoldi!» deb butun ummatning ijmosiga qarshi chiqadilar.",
                                pageNumber = 109
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_iroda_rizo",
                                termUz = "Iroda va Rizo farqi",
                                termAr = "الفرق بين الإرادة والرضا",
                                definitionUz = "Iroda — koinotda bo'ladigan har bir zarrani xohlash va yaratish. Rizo — faqat ezgulik, iymon va toatlardan xushnud bo'lish.",
                                sourceExample = "بإرادته لا برضاه ومحبته (20-bob, 154-fasl)",
                                pageRef = 108
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_ijmo_mashiyat",
                                titleUz = "Mashiyat haqidagi ummat shiori",
                                type = ProofType.IJMO,
                                arabicText = "ما شاء الله كان وما لم يشأ لم يكن",
                                uzbekTranslation = "«Alloh nimani xohlasa o'sha bo'ladi, xohlamagani bo'lmaydi».",
                                sourceRef = "Kitob at-Tamhid, 109-bet, 158-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mutazila_maasi",
                                topicTitle = "Gunohlarning xohishi",
                                ahlSunnahView = "Olamda Allohning irodasidan tashqari hech narsa sodir bo'lmaydi.",
                                opposingSchool = "Mu'tazila",
                                opposingView = "Gunohlarni Alloh xohlamagan, odamlar Allohning xohishiga qarshi majburlab yaratgan.",
                                refutationUz = "Mu'tazilaning gapi Allohning mulkida U xohlamagan narsalar majburan bo'lyapti deyish orqali Allohga ojizlik nisbat beradi."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_20_1",
                                lessonId = "ch_20_l_1",
                                chapterId = "ch_20",
                                questionUz = "Gunoh amallar borasida Ahli Sunna val-Jamoa e'tiqodi qanday?",
                                options = listOf(
                                    "Gunohlar Allohga yoqadi va U buyurgan",
                                    "Gunohlarni Alloh bilmaydi",
                                    "Gunohlar Allohning iroda va mashiyati bilan sodir bo'ladi, ammo Uning amri va roziligi bilan emas",
                                    "Gunohlar Allohning irodasisiz mustaqil bo'ladi"
                                ),
                                correctIndex = 2,
                                explanationUz = "Lomishiy 154-faslda: Gunohlar Allohning mashiyati bilan bo'lsa-da, Uning roziligi va muhabbati bilan emasligini uqtiradi.",
                                sourcePageRef = 108,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_20_2",
                                lessonId = "ch_20_l_1",
                                chapterId = "ch_20",
                                questionUz = "«Ma shaallohu kana va ma lam yasha' lam yakun» aqidaviy qoidasi nimani anglatadi?",
                                options = listOf(
                                    "Inson xohlagan hamma narsa bo'ladi",
                                    "Alloh xohlagan narsa albatta bo'ladi, xohlamagan narsa esa aslo vujudga kelmaydi",
                                    "Faqat farishtalar xohishi amalga oshadi",
                                    "Olam o'z-o'zidan boshqariladi"
                                ),
                                correctIndex = 1,
                                explanationUz = "Bu butun Ahli Sunna ittifoq qilgan asos bo'lib, koinotda zarracha narsa ham Allohning mashiyatisiz sodir bo'lolmaydi.",
                                sourcePageRef = 109,
                                difficulty = DifficultyLevel.MEDIUM
                            )
                        )
                    )
                )
            )
        )
    }
}
