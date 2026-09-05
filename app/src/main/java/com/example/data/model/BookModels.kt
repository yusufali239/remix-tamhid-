package com.example.data.model

enum class DifficultyLevel(val labelUz: String) {
    BASIC("Boshlang'ich"),
    MEDIUM("O'rta"),
    ADVANCED("Murakkab")
}

enum class ProofType(val labelUz: String) {
    QURAN("Qur'oni Karim"),
    HADITH("Hadisi Sharif"),
    AQLIY("Aqliy dalil"),
    IJMO("Ummat ijmosi"),
    SALAF("Sahobalar asari")
}

enum class QuestionType {
    SINGLE_CHOICE,
    TRUE_FALSE,
    MULTI_CHOICE,
    DEFINITION,
    MATCHING
}

enum class ReaderTheme(val labelUz: String) {
    LIGHT("Yorug'"),
    SEPIA("Qadimiy (Sepiya)"),
    DARK("Qorong'u"),
    NIGHT("Tungi")
}

data class ConceptItem(
    val id: String,
    val termUz: String,
    val termAr: String,
    val definitionUz: String,
    val sourceExample: String,
    val pageRef: Int
)

data class ProofItem(
    val id: String,
    val titleUz: String,
    val type: ProofType,
    val arabicText: String,
    val uzbekTranslation: String,
    val sourceRef: String
)

data class DebateItem(
    val id: String,
    val topicTitle: String,
    val ahlSunnahView: String,
    val opposingSchool: String,
    val opposingView: String,
    val refutationUz: String
)

data class ParagraphItem(
    val paragraphNumber: Int,
    val arabicText: String,
    val uzbekTranslation: String,
    val pageNumber: Int,
    val footnotes: String? = null
)

data class Lesson(
    val id: String,
    val chapterId: String,
    val lessonNumber: Int,
    val titleUz: String,
    val titleAr: String,
    val summaryUz: String,
    val pdfStartPage: Int,
    val pdfEndPage: Int,
    val paragraphs: List<ParagraphItem>,
    val concepts: List<ConceptItem>,
    val proofs: List<ProofItem>,
    val debates: List<DebateItem>,
    val quizQuestions: List<QuizQuestion>
)

data class Chapter(
    val id: String,
    val chapterNumber: Int,
    val titleUz: String,
    val titleAr: String,
    val descriptionUz: String,
    val pdfStartPage: Int,
    val pdfEndPage: Int,
    val lessons: List<Lesson>
)

data class QuizQuestion(
    val id: String,
    val lessonId: String,
    val chapterId: String,
    val questionUz: String,
    val arabicSnippet: String? = null,
    val options: List<String>,
    val correctIndex: Int,
    val explanationUz: String,
    val sourcePageRef: Int,
    val questionType: QuestionType = QuestionType.SINGLE_CHOICE,
    val difficulty: DifficultyLevel = DifficultyLevel.BASIC
)

data class FlashcardItem(
    val id: String,
    val lessonId: String,
    val chapterId: String,
    val termUz: String,
    val termAr: String,
    val definitionUz: String,
    val sourceQuote: String,
    val pageRef: Int
)

data class MadrasaCourse(
    val bookTitleAr: String = "كتاب التمهيد لقواعد التوحيد",
    val bookTitleUz: String = "Tavhid qoidalari uchun tamhid (muqaddima)",
    val originalAuthor: String = "Abu as-Sano Mahmud ibn Zayd al-Lomishiy al-Hanafiy al-Moturidiy",
    val projectDirectors: List<String> = listOf("Abdurrahmon ibni Avf", "Abu Ansor Yusuf"),
    val totalChapters: Int = 26,
    val chapters: List<Chapter>
)
