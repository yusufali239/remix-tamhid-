package com.example.data.local

import com.example.data.model.*

object TamhidChapters11To15 {
    fun getChapters(): List<Chapter> {
        return listOf(
            // 11-BOB: Al-Iroda
            Chapter(
                id = "ch_11",
                chapterNumber = 11,
                titleUz = "Alloh taoloning Iroda sifati",
                titleAr = "فصل في إثبات الإرادة لله تعالى",
                descriptionUz = "Alloh taoloning azaliy Iroda sifati, maxluqotlarni O'z ixtiyori bilan yaratgani va majbur emasligi.",
                pdfStartPage = 78,
                pdfEndPage = 79,
                lessons = listOf(
                    Lesson(
                        id = "ch_11_l_1",
                        chapterId = "ch_11",
                        lessonNumber = 1,
                        titleUz = "Iroda sifati va Majburlikning rad etilishi",
                        titleAr = "إثبات الإرادة لله ونفي الاضطرار والسهو",
                        summaryUz = "Alloh taolo narsalarni O'z xohish-irodasi bilan yaratuvchidir. Karohiyat (yoqtirmaslik), majburlik va unutishdan pokdir.",
                        pdfStartPage = 78,
                        pdfEndPage = 79,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 87,
                                arabicText = "قالت عامّةُ المتكلّمين: «إنّ صانعَ العالَم موصوفٌ بالإرادة، والإرادةُ صفةٌ له حقيقةً لأنّ الإرادةَ معنىً يُنافي الكراهةَ والاضطرارَ والسهوَ والغفلةَ فيكون الموصوفُ بها مختاراً في ما فَعَلَه». فلو لم يكن الله - تعالى! - موصوفاً بها لكان موصوفاً بضدٍّ من أضدادها... وهذا مُحالٌ.",
                                uzbekTranslation = "Barcha kalom ulamolari aytdilar: «Albatta olamning Yaratuvchisi haqiqiy Iroda sifati bilan sifatlangandir. Chunki Iroda — karohiyat (yoqtirmaslik), majburlik, yanglishish va g'aflatga zid bo'lgan ma'nodir. U bilan sifatlangan Zot O'z qilgan ishida mutlaq ixtiyor egasidir». Agar Alloh taolo Iroda bilan sifatlanmaganida, uning ziddiyatlari (majburlik, g'aflat) bilan sifatlangan bo'lar edi, bu esa mutlaqo mustahildir.",
                                pageNumber = 78
                            ),
                            ParagraphItem(
                                paragraphNumber = 89,
                                arabicText = "ثمّ الإرادةُ صفةٌ أزليّةٌ قائمةٌ بذات الله - تعالى! - عند أهل السنّة والجماعة، أي هو مُريدٌ بإرادةٍ قائمةٍ في الأزل.",
                                uzbekTranslation = "So'ngra Iroda — Ahli Sunna val-Jamoa nazdida Alloh taoloning Zoti bilan qoyim bo'lgan azaliy sifatdir, ya'ni U azalda qoyim bo'lgan Irodasi bilan doimo Xohlaguvchidir.",
                                pageNumber = 79
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_iroda",
                                termUz = "Al-Iroda (Xohish sifati)",
                                termAr = "الإرادة",
                                definitionUz = "Mumkin bo'lgan narsalardan birini muayyan vaqtda, muayyan xususiyat bilan vujudga keltirishni tayin qiluvchi azaliy sifat.",
                                sourceExample = "الإرادة صفة أزلية قائمة بذات الله تعالى (11-bob, 89-fasl)",
                                pageRef = 79
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_iroda_aqli",
                                titleUz = "Iroda sifatining zarurligi",
                                type = ProofType.AQLIY,
                                arabicText = "الإرادة تنافي الاضطرار والغفلة، والمنزه عنهما موصوف بالإرادة قطعاً",
                                uzbekTranslation = "Majburlik va bexabarlikdan pok bo'lgan Yaratuvchi faqat erkin Iroda bilangina yaratadi.",
                                sourceRef = "Kitob at-Tamhid, 78-bet, 87-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mutazila_iroda",
                                topicTitle = "Iroda azaliyligi",
                                ahlSunnahView = "Iroda azaliy zotiy sifatdir.",
                                opposingSchool = "Mu'tazila va Najjoriya",
                                opposingView = "Iroda yangi paydo bo'ladigan a'roz yoki Zotning o'zidir.",
                                refutationUz = "Yangi sifatlar paydo bo'lishi Zotning o'zgarishiga olib keladi, bu esa nojoizdir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_11_1",
                                lessonId = "ch_11_l_1",
                                chapterId = "ch_11",
                                questionUz = "Ahli Sunna val-Jamoa e'tiqodida Alloh taoloning Iroda sifati qanday xarakterlanadi?",
                                options = listOf(
                                    "Faqat maxluqlar yaratilgandan keyin paydo bo'lgan o'tkinchi xohishdir",
                                    "Allohning Zotida qoyim bo'lgan azaliy sifatdir",
                                    "Bandalarning xohishiga tobe bo'lgan narsadir",
                                    "Faqat farishtalarga berilgan amrdir"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 89-faslda: Iroda azaliy bo'lib, Alloh Zoti bilan qoyimdir deb ta'kidlagan.",
                                sourcePageRef = 79,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_11_2",
                                lessonId = "ch_11_l_1",
                                chapterId = "ch_11",
                                questionUz = "Iroda va Rizo (rozilik) o'rtasidagi farq haqida Moturidiyya ta'limoti qanday?",
                                options = listOf(
                                    "Iroda va Rizo aynan bir xil tushunchadir",
                                    "Alloh yomonlikni ham, yaxshilikni ham iroda qilmaydi",
                                    "Koinotdagi barcha narsa (yaxshi-yu yomon) Allohning irodasi bilan bo'ladi, ammo Alloh faqat yaxshilik va imondan rozi bo'ladi, kufrdan rozi bo'lmaydi",
                                    "Alloh kofirlarning amallaridan rozi bo'ladi"
                                ),
                                correctIndex = 2,
                                explanationUz = "Kalom qoidasi: Har bir mavjud narsa ilohiy mashi'at va iroda bilan yuz beradi, ammo rozi bo'lish faqat toat va imonga xosdir.",
                                sourcePageRef = 80,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_11_3",
                                lessonId = "ch_11_l_1",
                                chapterId = "ch_11",
                                questionUz = "Mu'tazila toifasining 'Alloh yomonlikni iroda qilmaydi' degan fikrining xatarli oqibati nima?",
                                options = listOf(
                                    "Ular koinotda Allohning irodasidan tashqari va Unga bo'ysunmaydigan narsalar sodir bo'lishini (ojizlikni) taqozo qiladilar",
                                    "Ular farishtalarni inkor qilgan bo'ladilar",
                                    "Ular Qur'onni o'qimaslikka chaqiradilar",
                                    "Ular faqat arab tilini o'rganadilar"
                                ),
                                correctIndex = 0,
                                explanationUz = "Agar Alloh xohlamagan narsa Uning mulkida majburan sodir bo'lsa, bu Zotga ojizlik nisbat berish bo'lib, kufrga olib boradi.",
                                sourcePageRef = 80,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 12-BOB: Ru'yatulloh
            Chapter(
                id = "ch_12",
                chapterNumber = 12,
                titleUz = "Ru'yatulloh (Allohni oxiratda ko'rish) isboti",
                titleAr = "فصل في إثبات رُؤية الله تعالى",
                descriptionUz = "Mo'minlarning jannatda Alloh taoloni ko'rishlari aqlan mumkin va naqlan sobit ekani, 21 nafar sahoba rivoyati va Mu'tazilaga raddiyalar.",
                pdfStartPage = 79,
                pdfEndPage = 86,
                lessons = listOf(
                    Lesson(
                        id = "ch_12_l_1",
                        chapterId = "ch_12",
                        lessonNumber = 1,
                        titleUz = "Ru'yatullohning naqliy va aqliy dalillari",
                        titleAr = "أدلة رؤية الله تعالى في الآخرة والرد على المعتزلة",
                        summaryUz = "Mo'minlar oxiratda Allohni kayfiyatsiz, qarama-qarshiliksiz, masofasiz va jihatsiz ko'radilar. Muso alayhissalom so'rovi va 21 sahoba hadisi buni isbotlaydi.",
                        pdfStartPage = 79,
                        pdfEndPage = 86,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 90,
                                arabicText = "قال أهلُ السنّة والجماعة: «رؤيةُ الله - تعالى! - في دار الآخرة جائزةٌ بالدليل السمعيّ والعقليّ». أمّا السمعيّ فلقوله تعالى إخباراً عن موسى صلوات الله عليه: ﴿رَبِّ أَرِنِي أَنظُرْ إِلَيْكَ﴾ سأل ربَّه الرؤية. وهذا دليلٌ على أنّه عَرَفَ ربَّه جائزَ الرؤية. فمن قال بأنّه غيرُ جائز الرؤية فقد قال بأنّ موسى لم يعرف ربَّه! وهذا ضلالٌ.",
                                uzbekTranslation = "Ahli Sunna val-Jamoa aytdilar: «Alloh taoloni oxirat diyorida ko'rish naqliy (sam'iy) va aqliy dalil bilan joizdir (mumkindir va voqe bo'ladi)». Naqliy dalillardan biri: Muso alayhissalomning: «Ey Robbim, menga O'zingni ko'rsatgin, Senga qarayin!» (A'rof, 143) deb so'rashlaridir. Bu Muso alayhissalom o'z Robbisini ko'rish joiz Zot deb bilganlariga qat'iy dalildir. Kimki Allohni ko'rish mutlaqo mumkin emas desa, Muso o'z Robbisini tanimagan deb da'vo qilgan bo'ladi! Bu esa ochiq zalolatdir.",
                                pageNumber = 79
                            ),
                            ParagraphItem(
                                paragraphNumber = 91,
                                arabicText = "وقال تعالى: ﴿وُجُوهٌ يَوْمَئِذٍ نَّاضِرَةٌ * إِلَىٰ رَبِّهَا نَاظِرَةٌ﴾. وهذا نصٌّ على ثُبوت الرؤية بالأبصار في دار القرار. وذكر الشيخ العالِم الزاهد محمد بن علي الحكيم الترمذي... «اتفق على حديث الرؤية أحدٌ وعشرون رجلاً من أصحاب رسول الله صلى الله عليه وسلم - كلّهم أئمّة الهُدى... روَوْا عن رسول الله ما فيه إثباتُ الرؤية».",
                                uzbekTranslation = "Shuningdek, Alloh taolo: «U kunda yuzlar yashnab turuvchidir. O'z Robbisiga boquvchidir!» (Qiyomat, 22-23) deb marhamat qilgan. Bu qiyomatda ko'zlar bilan ko'rishning qat'iy nassidir. Shayx, zohid olim Muhammad ibn Ali al-Hakim at-Termiziy aytganlaridek: «Rasululloh sollallohu alayhi vasallamning yigirma bir (21) nafar sahobalari — hidoyat peshvolari barchalari Allohni ko'rish isbotlangan hadislarni rivoyat qilganlar».",
                                pageNumber = 80
                            ),
                            ParagraphItem(
                                paragraphNumber = 92,
                                arabicText = "والدليلُ عليه ما رُوي عن النبي صلى الله عليه وسلم أنّه قال: «ستَرَوْنَ رَبَّكم كما ترَوْنَ القَمَرَ ليلةَ البَدْر لا تُضامُونَ في رُؤيته».",
                                uzbekTranslation = "Dalillardan yana biri Payg'ambarimiz sollallohu alayhi vasallamning: «Sizlar to'lin oy kechasida oyni ko'rganingizdek Robbingizni ko'rasizlar, Uni ko'rishda bir-biringizga to'siq bo'lmaysizlar (yoki qiynalmaysizlar)» degan hadislaridir.",
                                pageNumber = 81
                            ),
                            ParagraphItem(
                                paragraphNumber = 104,
                                arabicText = "وشُبهتُهم العقلية باطلةٌ برؤية الله تعالى إيانا، فإنّه يرانا بلا مسافةٍ ولا جهةٍ ومُقابَلةٍ... فكذلك نراه في الجنة بلا كيفيّة.",
                                uzbekTranslation = "Mu'tazilaning aqliy shubhasi (masofa, jihat va ro'paralik bo'lmasa ko'rib bo'lmaydi degan da'vosi) botildir. Chunki Alloh taolo bizni hech qanday masofa, jihat va qarama-qarshiliksiz ko'rib turadi. Xuddi shuningdek, biz ham jannatda U Zotni barcha kayfiyatlardan xoli tarzda ko'ramiz.",
                                pageNumber = 85
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_ruya",
                                termUz = "Ru'yatulloh (Allohni ko'rish)",
                                termAr = "رؤية الله تعالى",
                                definitionUz = "Mo'minlarning oxiratda jannatda Alloh taoloni o'z ko'zlari bilan kayfiyatsiz, masofasiz va makonsiz ko'rishlari.",
                                sourceExample = "وجوه يومئذ ناضرة إلى ربها ناظرة (12-bob, 91-fasl)",
                                pageRef = 80
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_qiyama_22",
                                titleUz = "Qiyomat surasidagi ochiq dalil",
                                type = ProofType.QURAN,
                                arabicText = "﴿وُجُوهٌ يَوْمَئِذٍ نَّاضِرَةٌ * إِلَىٰ رَبِّهَا نَاظِرَةٌ﴾",
                                uzbekTranslation = "«U kunda yuzlar yashnab, o'z Robbisiga boquvchidir» (Qiyomat, 22-23).",
                                sourceRef = "Kitob at-Tamhid, 80-bet, 91-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mutazila_ruya",
                                topicTitle = "Oxiratda Allohni ko'rish",
                                ahlSunnahView = "Mo'minlar Allohni ko'radilar (bila kayfiyya, bila jiha).",
                                opposingSchool = "Mu'tazila, Xavorij va Rofiziylar",
                                opposingView = "Allohni ko'rish imkonsiz, 'boqish' so'zi faqat savob kutish ma'nosidadir.",
                                refutationUz = "Agar nazar kutish ma'nosida bo'lsa 'Ila' harfi bilan kelmas edi. Yuzga nisbat berilgan nazar faqat ko'z bilan ko'rishdir."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_12_1",
                                lessonId = "ch_12_l_1",
                                chapterId = "ch_12",
                                questionUz = "Al-Hakim at-Termiziy keltirgan ma'lumotga ko'ra, nechta sahoba Ru'yatulloh hadislarini rivoyat qilgan?",
                                options = listOf("3 nafar sahoba", "50 nafar sahoba", "21 nafar sahoba", "Faqat 1 sahoba"),
                                correctIndex = 2,
                                explanationUz = "Lomishiy 91-faslda: '21 nafar sahoba — barchalari hidoyat imomlari Ru'yatulloh hadisini rivoyat qilganlar', deb keltirgan.",
                                sourcePageRef = 80,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_12_2",
                                lessonId = "ch_12_l_1",
                                chapterId = "ch_12",
                                questionUz = "Oxiratda mo'minlar Alloh taoloni qanday ko'radilar?",
                                options = listOf(
                                    "Muayyan jismoniy shaklda va oraliq masofa bilan",
                                    "Kayfiyatsiz (bila kayf), jismonsiz, yuzma-yuz qarshiliksiz va biron jihatda bo'lmagan holda",
                                    "Faqat parda ortidan soya shaklida",
                                    "Faqat tush ko'rgandek xayolda"
                                ),
                                correctIndex = 1,
                                explanationUz = "Moturidiya aqidasida Ru'yatulloh haqiqatdir, lekin u jism, masofa, qarshilik va makondan xoli ravishda yuz beradi.",
                                sourcePageRef = 82,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_12_3",
                                lessonId = "ch_12_l_1",
                                chapterId = "ch_12",
                                questionUz = "Qiyomat surasidagi «Vujuhuy-yavmaidzin nozira, ila robbiha nozira» oyatidagi 'nazar' so'zi nega ko'rish ma'nosidadir?",
                                options = listOf(
                                    "Chunki arab tilida 'yuz'ga nisbat berilib 'ila' (ga) vositasi bilan kelgan nazar aynan ko'z bilan ko'rishni bildiradi",
                                    "Chunki bu oyat faqat payg'ambarlarga tegishli",
                                    "Chunki savob kutish deb tarjima qilinishi shart",
                                    "Chunki bu oyat majoziydir"
                                ),
                                correctIndex = 0,
                                explanationUz = "Imom al-Lomishiy 93-faslda til qoidasini keltirib, bu oyat qalb bilan kutish emas, aynan Allohni ko'z bilan ko'rish ekanini isbotlaydi.",
                                sourcePageRef = 83,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 13-BOB: Isbot ar-Risala
            Chapter(
                id = "ch_13",
                chapterNumber = 13,
                titleUz = "Risolat (Payg'ambarlik) isboti",
                titleAr = "فصل في إثبات الرِّسالة",
                descriptionUz = "Payg'ambarlar yuborilishining hikmati, mo'jizalarning mohiyati va Barohimaning shubhalariga raddiya.",
                pdfStartPage = 86,
                pdfEndPage = 90,
                lessons = listOf(
                    Lesson(
                        id = "ch_13_l_1",
                        chapterId = "ch_13",
                        lessonNumber = 1,
                        titleUz = "Risolatning zarurligi va Mo'jiza mohiyati",
                        titleAr = "حكمة إرسال الرسل وحقيقة المعجزة",
                        summaryUz = "Inson aqli barcha zarur va zararli shar'iy hukmlarni mustaqil bila olmaydi, shu bois payg'ambarlar yuborilishi ilohiy hikmatdir. Payg'ambarlik mo'jizalar bilan sobit bo'ladi.",
                        pdfStartPage = 86,
                        pdfEndPage = 90,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 105,
                                arabicText = "إرسالُ الرُّسُل ثابتٌ وإنّه من مُقتَضَيات الحِكمة عند المُحقّقين من أئمّة الهدى وحُكماء البشر... لأنّ الله خلقَ الخَلْقَ مُحتاجين إلى البقاء ليتمكّنوا من إقامة ما كُلّفوا به من الإيمان ومن الانتهاء عمّا نُهوا عنه... فلو لم يشرع الباري شرعاً على لسان نبيٍّ لَتَسارعَ كلٌّ إلى ما يميل إليه طبعُه فيؤدّي إلى التقاتل والتفاني.",
                                uzbekTranslation = "Payg'ambarlar yuborilishi sobit haqiqatdir va u muhaqqiq hidoyat imomlari nazdida ilohiy hikmat taqozosidir. Chunki Alloh taolo insonlarni iymon keltirish va qaytariqlardan tiyilish bilan mukallaf qildi. Agar Alloh taolo payg'ambar tili orqali shariat yubormaganida, har bir inson o'z nafsi xohlagan narsaga chopgan bo'lar va bu odamlar o'rtasida qon to'kilishi va halokatga olib borar edi.",
                                pageNumber = 86
                            ),
                            ParagraphItem(
                                paragraphNumber = 112,
                                arabicText = "وقُلنا: قد ظهر بالدليل الذي لا مردّ له وهو النقلُ المتواتر ثُبوتُ المعجزات الناقضاتِ للعادات على يدي الأنبياء - عليهم السلام! - كقَلْب العصا حيّةً وانفلاق البحر على يدي موسى، وإبراء الأكمَه والأبرص وإحياء الموتى على يدي عيسى، وكذا نبيّنا محمد - صلى الله عليه وسلم! - أعجزَ بنَظْم القرآن وفصاحته جميعَ الفصحاء والبلغاء.",
                                uzbekTranslation = "Biz aytdik: Inkor qilib bo'lmas mutavotir naql orqali payg'ambarlar qo'lida odatdan tashqari mo'jizalar sobit bo'lgani zohirdir: Muso qo'lida asoning ilonga aylanishi va dengizning ikkiga bo'linishi; Iso qo'lida tug'ma ko'r va peslarning tuzalishi, o'liklarning tirilishi; Payg'ambarimiz Muhammad sollallohu alayhi vasallam esa Qur'onning nazmi va fasohati bilan butun fasohat va balog'at ahlini ojiz qoldirdilar.",
                                pageNumber = 89
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_mujiza",
                                termUz = "Mo'jiza (Odatdan tashqari hodisa)",
                                termAr = "المُعجِزة",
                                definitionUz = "Payg'ambarlik da'vosi bilan birga yuz beradigan, boshqalar o'xshashini keltirishdan ojiz qoladigan ilohiy favqulodda hodisa.",
                                sourceExample = "المعجزات الناقضات للعادات على يدي الأنبياء (13-bob, 112-fasl)",
                                pageRef = 89
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_quran_tahaddi",
                                titleUz = "Qur'onning eng buyuk boqiy mo'jizaligi",
                                type = ProofType.QURAN,
                                arabicText = "أعجزَ بنظم القرآن وفصاحته جميع الفصحاء والبلغاء",
                                uzbekTranslation = "Qur'on o'z fasohati bilan butun insoniyatni ojiz qoldirgan eng buyuk mo'jizadir.",
                                sourceRef = "Kitob at-Tamhid, 89-bet, 113-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_barohima_risala",
                                topicTitle = "Payg'ambarlarga ehtiyoj",
                                ahlSunnahView = "Aql yolg'iz o'zi shariat hukmlarini bila olmaydi, payg'ambar yuborilishi zarurdir.",
                                opposingSchool = "Barohima",
                                opposingView = "Aql kifoya qiladi, payg'ambarlarga hojat yo'q.",
                                refutationUz = "Inson aqli dunyoviy zararlarni ham to'liq bilmaydi, qanday qilib oxirat saodati va ibodatlarni bilsin?"
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_13_1",
                                lessonId = "ch_13_l_1",
                                chapterId = "ch_13",
                                questionUz = "Payg'ambarimiz Muhammad sollallohu alayhi vasallamning barcha fasohat ahlini ojiz qoldirgan bosh mo'jizalari qaysi?",
                                options = listOf("Aso mo'jizasi", "Dengiz yorilishi", "O'liklarni tiriltirish", "Qur'oni Karim nazmi va fasohati"),
                                correctIndex = 3,
                                explanationUz = "Lomishiy 113-faslda: Qur'onning fasohati va nazmi barcha balog'at egalarini ojiz qoldirgan deb uqtiradi.",
                                sourcePageRef = 89,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_13_2",
                                lessonId = "ch_13_l_1",
                                chapterId = "ch_13",
                                questionUz = "Barohima toifasining 'Aql kifoya, payg'ambarlarga ehtiyoj yo'q' degan shubhasiga qanday raddiya beriladi?",
                                options = listOf(
                                    "Aql inson uchun qaysi ibodatlar va amallar Allohga ma'qul ekanini mustaqil bila olmaydi, payg'ambar ta'limoti zarurdir",
                                    "Aql dinda mutlaqo qadrsiz deb e'lon qilinadi",
                                    "Faqat tuyg'ularga tayanish tavsiya etiladi",
                                    "Ular bilan hech qanday ilmiy bahs qilinmaydi"
                                ),
                                correctIndex = 0,
                                explanationUz = "Inson aqli hatto qaysi o't-o'lan shifo yoki zahar ekanini tajribasiz bilolmaydi, qanday qilib g'ayb va oxirat ahkomlarini mustaqil bilsin?",
                                sourcePageRef = 88,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_13_3",
                                lessonId = "ch_13_l_1",
                                chapterId = "ch_13",
                                questionUz = "Mo'jizaning (al-mu'jiza) ta'rifi qaysi javobda to'g'ri berilgan?",
                                options = listOf(
                                    "Sehrgarlar bajaradigan hiylali ko'zboyamachilik",
                                    "Payg'ambarlik da'vosiga muvofiq, muxoliflarni ojiz qoldiruvchi, odatdan tashqari ilohiy hodisa",
                                    "Har qanday kutilmagan tabiiy hodisa",
                                    "Kelajakni oldindan aytib berish"
                                ),
                                correctIndex = 1,
                                explanationUz = "Mo'jiza payg'ambarlikni da'vo qilgan zotning qo'lida, da'voni tasdiqlash uchun va boshqalarni lol qoldirish uchun zohir bo'ladi.",
                                sourcePageRef = 89,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 14-BOB: Karomat al-Awliya
            Chapter(
                id = "ch_14",
                chapterNumber = 14,
                titleUz = "Avliyolarning karomatlari",
                titleAr = "فصل في كرامات الأولياء",
                descriptionUz = "Allohning suyukli solih bandalari (valiyullohlar) qo'lida sodir bo'ladigan karomatlarning haq ekani va Qur'oniy dalillari.",
                pdfStartPage = 90,
                pdfEndPage = 92,
                lessons = listOf(
                    Lesson(
                        id = "ch_14_l_1",
                        chapterId = "ch_14",
                        lessonNumber = 1,
                        titleUz = "Karomatlarning haq ekani va Mu'tazilaga raddiya",
                        titleAr = "ثبوت كرامات الأولياء والرد على منكريها",
                        summaryUz = "Karomatlar haqdir (Maryam onamiz mevalari, Osif ibn Barxiya, Umar r.a. va Soriya voqeasi). Karomat valiy ergashgan payg'ambarning mo'jizasini quvvatlaydi.",
                        pdfStartPage = 90,
                        pdfEndPage = 92,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 114,
                                arabicText = "كراماتُ الأولياء ثابتةٌ عند أهل السنّة لأنّ الله - تعالى! - قال في قصّة مريم: ﴿وَهُزِّي إِلَيْكِ بِجِذْعِ النَّخْلَةِ تُسَاقِطْ عَلَيْكِ رُطَبًا جَنِيًّا﴾ وقال تعالى: ﴿كُلَّمَا دَخَلَ عَلَيْهَا زَكَرِيَّا الْمِحْرَابَ وَجَدَ عِندَهَا رِزْقًا﴾. وهذه كراماتٌ لها ثَبَتَتْ بحُجّة لا مردّ لها. وأخبَرَ عن صاحب سليمان، آصف، أنّه أتى بعرش بلقيس من المسافة البعيدة في ساعةٍ لطيفةٍ.",
                                uzbekTranslation = "Avliyolarning karomatlari Ahli Sunna nazdida sobitdir. Chunki Alloh taolo Maryam qissasida: «Xurmo tanasini o'zingga qarab silkit, senga yangi terilgan xurmo to'kilsin» (Maryam, 25) va «Zakariyo har gal uning oldiga mehrobga kirganda, uning huzurida rizq (yozda qishki, qishda yozgi mevalar) topardi» (Oli Imron, 37) degan. Bular Maryamning rad qilib bo'lmas karomatlaridir. Shuningdek Sulaymon alayhissalomning hamrohi Osif uzoq masofadan Bilqisning taxtini ko'z ochib yumguncha keltirgani xabar berilgan.",
                                pageNumber = 90
                            ),
                            ParagraphItem(
                                paragraphNumber = 115,
                                arabicText = "وفي المشاهير عن عُمَرَ - رضي الله عنه! - أنّه رأى جيشَه بنهاوَند وهو على المنبر بالمدينة حتى قال: «يا سارِيَةُ! الجَبَلَ! الجَبَلَ!» وقد سمع ساريةُ ذلك. والحكايات في بيان كرامات الأولياء عن الثقات مستفيضةٌ لا وجه لإنكارها.",
                                uzbekTranslation = "Mashhur rivoyatlarda Umar roziyallohu anhuning Madinada minbarda turib, Nahovanddagi lashkarlarini ko'rib: «Ey Soriya! Tog'ga qara, tog'ga!» deganlari va Soriya bu ovozni eshitib tog'ga joylashgani sobit bo'lgan. Ishonchli roviylardan kelgan karomat xabarlari mustafizdir (juda ko'pdir).",
                                pageNumber = 91
                            ),
                            ParagraphItem(
                                paragraphNumber = 117,
                                arabicText = "وقُلنا: المعجزةُ إنّما تظهر على يد مُدّعي النبوّة... والوليُّ لا يَدّعي النبوّة لأنه لو ادّعاها لكفر من ساعته. فلم يكن ظهور الكرامة للوليّ معجزاً عن معرفة النبيّ، بل ظهور الكرامة للوليّ طريقٌ لمعرفة النبيّ لأنّ ظهور الكرامة للولي دليلٌ على أنّه وليُّ الله... وعلى هذا تكون كرامةُ كلّ وليٍّ في كلّ أمةٍ معجزةً لنبيّه.",
                                uzbekTranslation = "Biz aytdik: Mo'jiza payg'ambarlikni da'vo qiluvchi kishi qo'lida zohir bo'ladi. Valiy esa aslo payg'ambarlikni da'vo qilmaydi, chunki da'vo qilgan zahoti kofir bo'ladi. Valiy qo'lidagi karomat u ergashgan payg'ambarning haqligini isbotlaydi. Shu tariqa, har bir valiyning karomati o'z payg'ambarining mo'jizasi hisoblanadi.",
                                pageNumber = 91
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_karoma",
                                termUz = "Karomat",
                                termAr = "الكرامة",
                                definitionUz = "Payg'ambarlik da'vosi qilmagan solih valiyullohlar qo'lida Allohning fazli bilan zohir bo'ladigan favqulodda holat.",
                                sourceExample = "كرامات الأولياء ثابتة عند أهل السنة (14-bob, 114-fasl)",
                                pageRef = 90
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_karoma_maryam",
                                titleUz = "Maryam onamiz va Osif ibn Barxiya karomati",
                                type = ProofType.QURAN,
                                arabicText = "﴿كُلَّمَا دَخَلَ عَلَيْهَا زَكَرِيَّا الْمِحْرَابَ وَجَدَ عِندَهَا رِزْقًا﴾",
                                uzbekTranslation = "«Zakariyo har gal mehrobga kirganda uning huzurida yangi rizq topar edi» (Oli Imron, 37).",
                                sourceRef = "Kitob at-Tamhid, 90-bet, 114-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_mutazila_karomat",
                                topicTitle = "Avliyolarning karomati",
                                ahlSunnahView = "Karomat haqdir va u payg'ambar mo'jizasini tasdiqlovchi dalildir.",
                                opposingSchool = "Mu'tazila",
                                opposingView = "Karomat yo'q, aks holda mo'jiza bilan karomat aralashib ketadi.",
                                refutationUz = "Payg'ambar da'vo qiladi va mo'jiza ko'rsatadi, valiy esa da'vo qilmaydi va payg'ambarga itoati orqali karomat topadi."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_14_1",
                                lessonId = "ch_14_l_1",
                                chapterId = "ch_14",
                                questionUz = "Valiyullohning karomati bilan Payg'ambarning mo'jizasi o'rtasidagi asosiy farq nima?",
                                options = listOf(
                                    "Karomat faqat tushda bo'ladi, mo'jiza esa o'ngda",
                                    "Payg'ambar payg'ambarlik da'vosi bilan ko'rsatadi, valiy esa da'vo qilmaydi va karomat payg'ambarga tobelik belgisidir",
                                    "Karomat faqat boylik keltiradi",
                                    "Hech qanday farq yo'q"
                                ),
                                correctIndex = 1,
                                explanationUz = "Lomishiy 117-faslda: Valiy da'vo qilmasligini va har bir valiyning karomati o'z payg'ambarining mo'jizasi ekanini aytadi.",
                                sourcePageRef = 91,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_14_2",
                                lessonId = "ch_14_l_1",
                                chapterId = "ch_14",
                                questionUz = "Qur'oni Karimda valiy zotlarning karomati zikr qilingan misollardan biri qaysi?",
                                options = listOf(
                                    "Maryam onamizga qishda yoz mevasi berilishi va Asif ibn Barxiyoning Bilqis taxtini ko'z ochib yumguncha keltirishi",
                                    "Fir'avnning saroyi qulashi",
                                    "Qorunning xazinalari ko'payishi",
                                    "Namrudning olovi"
                                ),
                                correctIndex = 0,
                                explanationUz = "Maryam (a.s.) va Sulaymon (a.s.) huzuridagi kitob ilmini bilgan zot (Asif) payg'ambar emas, balki avliyo edilar.",
                                sourcePageRef = 91,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_14_3",
                                lessonId = "ch_14_l_1",
                                chapterId = "ch_14",
                                questionUz = "Mu'tazila toifasi karomatni nega inkor qilgan?",
                                options = listOf(
                                    "Ular karomatni tan olsak mo'jiza bilan chalkashib ketadi deb o'ylashgan",
                                    "Ular avliyolarni yaxshi ko'rishgan",
                                    "Ular Qur'ondan bu oyatlarni topa olishmagan",
                                    "Ular faqat arab tilini tushunishmagan"
                                ),
                                correctIndex = 0,
                                explanationUz = "Mu'tazila karomat mo'jizani shubhali qilib qo'yadi deb gumon qilgan. Ahli Sunna esa: valiyning da'vosi yo'qligi bu chalkashlikni bartaraf qiladi deb javob bergan.",
                                sourcePageRef = 92,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            ),

            // 15-BOB: Istito'at (Qudrat turlari)
            Chapter(
                id = "ch_15",
                chapterNumber = 15,
                titleUz = "Ta'dil va tajvir: Istito'at (imkoniyat) masalalari",
                titleAr = "فصل في مسائل التعديل والتجوير والاستطاعة",
                descriptionUz = "Inson qudratining ikki turi: a'zolar va asboblar salomatligi (fe'ldan oldin) hamda bevosita fe'l paytidagi quvvat (fe'l bilan birga).",
                pdfStartPage = 92,
                pdfEndPage = 97,
                lessons = listOf(
                    Lesson(
                        id = "ch_15_l_1",
                        chapterId = "ch_15",
                        lessonNumber = 1,
                        titleUz = "Istito'atning ikki turi va uning hukmi",
                        titleAr = "أنواع الاستطاعة: سلامة الأسباب والقدرة المقارنة للفعل",
                        summaryUz = "Istito'at 2 xil: 1) Asbob va a'zolar salomatligi (taklif sharti); 2) Fe'l bilan birga bo'luvchi bevosita qudrat.",
                        pdfStartPage = 92,
                        pdfEndPage = 97,
                        paragraphs = listOf(
                            ParagraphItem(
                                paragraphNumber = 118,
                                arabicText = "الاستطاعةُ نوعان: أحدهما سلامةُ الأسباب والآلات، والثاني القُدرةُ التي يُوجَدُ بها الفِعلُ.",
                                uzbekTranslation = "Istito'at (imkoniyat/qudrat) ikki xildir: 1) Sabablar, vositalar va a'zolarning salomatligi; 2) Fe'l uning vositasida yuzaga chiqadigan bevosita qudrat.",
                                pageNumber = 93
                            ),
                            ParagraphItem(
                                paragraphNumber = 121,
                                arabicText = "الاستطاعةُ الأولى وهي سلامةُ الأسباب وصحّة الآلات سابقةٌ على الفعل ولا نزاع بين أهل السنة والمعتزلة فيه. والاستطاعةُ الثانية مُقارِنةٌ للفعل عند أهل السنّة خلافاً للمعتزلة وبعض الكرّاميّة.",
                                uzbekTranslation = "Birinchi turdagi istito'at — sabablar salomatligi va a'zolarning durustligi fe'ldan oldin bo'ladi (bu taklif shartidir) va bunda Ahli Sunna bilan Mu'tazila o'rtasida nizo yo'q. Ikkinchi turdagi istito'at — fe'lni vujudga keltiruvchi qudrat esa Ahli Sunna nazdida fe'l bilan bir vaqtda (muqorin) bo'ladi.",
                                pageNumber = 94
                            )
                        ),
                        concepts = listOf(
                            ConceptItem(
                                id = "c_istitoot",
                                termUz = "Istito'at (Qudrat/Imkoniyat)",
                                termAr = "الاستطاعة",
                                definitionUz = "Bandaning biror amalni bajarishi uchun zarur bo'lgan a'zolar salomatligi va Alloh bergan bevosita quvvat.",
                                sourceExample = "الاستطاعة نوعان: سلامة الأسباب، والقدرة التي يوجد بها الفعل (15-bob, 118-fasl)",
                                pageRef = 93
                            )
                        ),
                        proofs = listOf(
                            ProofItem(
                                id = "p_istitoot_hud",
                                titleUz = "Qur'onda qudratning nafiy qilinishi",
                                type = ProofType.QURAN,
                                arabicText = "﴿مَا كَانُوا يَسْتَطِيعُونَ السَّمْعَ وَمَا كَانُوا يُبْصِرُونَ﴾",
                                uzbekTranslation = "«Ular eshitishga qodir emas edilar va ko'rmas edilar» (Hud, 20).",
                                sourceRef = "Kitob at-Tamhid, 93-bet, 119-fasl"
                            )
                        ),
                        debates = listOf(
                            DebateItem(
                                id = "d_istitoot_vaqt",
                                topicTitle = "Ikkinchi istito'atning vaqti",
                                ahlSunnahView = "Fe'lni vujudga keltiruvchi ikkinchi qudrat fe'l bilan bir vaqtda (muqorin) yaratiladi.",
                                opposingSchool = "Mu'tazila",
                                opposingView = "Qudrat doimo fe'ldan oldin bo'ladi.",
                                refutationUz = "Qudrat a'rozdir, a'roz esa ikki zamon davom etmaydi, shuning uchun u fe'l bilan birga yaratilishi shart."
                            )
                        ),
                        quizQuestions = listOf(
                            QuizQuestion(
                                id = "q_15_1",
                                lessonId = "ch_15_l_1",
                                chapterId = "ch_15",
                                questionUz = "Ahli Sunna val-Jamoa e'tiqodida fe'lning o'zi yuzaga chiqadigan ikkinchi qudrat qachon bo'ladi?",
                                options = listOf(
                                    "Fe'ldan bir yil oldin",
                                    "Faqat fe'l tugagandan keyin",
                                    "Fe'l bilan aynan bir vaqtda (muqorin)",
                                    "Hech qachon bo'lmaydi"
                                ),
                                correctIndex = 2,
                                explanationUz = "Lomishiy 121-faslda: Ikkinchi istito'at fe'l bilan birga (muqorin) yaratiladi deb ta'kidlaydi.",
                                sourcePageRef = 94,
                                difficulty = DifficultyLevel.BASIC
                            ),
                            QuizQuestion(
                                id = "q_15_2",
                                lessonId = "ch_15_l_1",
                                chapterId = "ch_15",
                                questionUz = "Shar'iy taklif (amr va qaytariq) insonning qaysi qudratiga asoslanadi?",
                                options = listOf(
                                    "Birinchi istito'atga: a'zolar, sog'liq va vositalarning salomatligiga",
                                    "Boylik miqdoriga",
                                    "Faqat orzu-havasga",
                                    "Farishtalarning yordamiga"
                                ),
                                correctIndex = 0,
                                explanationUz = "Salomat asboblar va aql bo'lsa kishi amallarni bajarishga mukallaf bo'ladi. Bu taklif shartidir.",
                                sourcePageRef = 93,
                                difficulty = DifficultyLevel.MEDIUM
                            ),
                            QuizQuestion(
                                id = "q_15_3",
                                lessonId = "ch_15_l_1",
                                chapterId = "ch_15",
                                questionUz = "Nega ikkinchi qudrat fe'ldan oldin bo'lishi mumkin emas?",
                                options = listOf(
                                    "Chunki qudrat a'rozdir, a'roz esa vaqt o'tishi bilan o'zgaradi va fe'l lahzasigacha saqlanmaydi, u aynan fe'l bilan birga yaratilishi lozim",
                                    "Chunki inson doimo uxlaydi",
                                    "Chunki aql bunga rozi bo'lmaydi",
                                    "Chunki shariatda buni so'rash taqiqlangan"
                                ),
                                correctIndex = 0,
                                explanationUz = "Lomishiy ta'kidlaydi: Agar qudrat fe'ldan ilgari tugasa, fe'l qudratsiz (ojizlik bilan) bajarilgan bo'lib qolar edi.",
                                sourcePageRef = 95,
                                difficulty = DifficultyLevel.ADVANCED
                            )
                        )
                    )
                )
            )
        )
    }
}
