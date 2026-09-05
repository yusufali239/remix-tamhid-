package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AiProvider
import com.example.data.local.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.util.UUID
import kotlin.math.sqrt

// ============================================================================
// UI State (Hech qachon crash bo'lmaydi, qat'iy sealed class)
// ============================================================================
sealed class UstozUiState {
    object Idle : UstozUiState()
    object Loading : UstozUiState()
    data class Success(val javob: String) : UstozUiState()
    data class Error(val xabar: String) : UstozUiState()
}

data class TamhidSectionDoc(
    val chapterNumber: Int,
    val chapterTitle: String,
    val sectionNumber: Int,
    val sectionTitle: String,
    val pageNumber: Int,
    val arabicText: String,
    val uzbekText: String,
    val naqliDalil: String,
    val aqliDalil: String,
    val keywords: List<String>,
    val embedding: List<Float>
)

data class TamhidSourceRef(
    val chapterNumber: Int,
    val sectionNumber: Int,
    val chapterTitle: String,
    val pageNumber: Int
)

enum class MessageAuthor {
    STUDENT, USTOZ
}

data class UstozMessage(
    val id: String = UUID.randomUUID().toString(),
    val author: MessageAuthor,
    val content: String,
    val isRagSource: Boolean = false,
    val sources: List<TamhidSourceRef> = emptyList(),
    val timestamp: Long = System.currentTimeMillis()
)

class UstozViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<UstozUiState>(UstozUiState.Idle)
    val uiState: StateFlow<UstozUiState> = _uiState.asStateFlow()

    private val _messages = MutableStateFlow<List<UstozMessage>>(emptyList())
    val messages: StateFlow<List<UstozMessage>> = _messages.asStateFlow()

    private val dataStoreManager = DataStoreManager(application)
    private val sectionsDatabase = mutableListOf<TamhidSectionDoc>()
    private var lastQuestion: String = ""

    init {
        loadKnowledgeBase()
        addInitialGreeting()
    }

    private fun addInitialGreeting() {
        val greeting = UstozMessage(
            author = MessageAuthor.USTOZ,
            content = "Assalomu alaykum va rahmatullohi va barokatuh, aziz tolib! Men sizning aqida bo'yicha shaxsiy Ustozingizman.\n\nMavzularni tushunishda qiynalayotgan bo'lsangiz yoki savollaringiz bo'lsa, bemalol so'rang — samimiy suhbatlashamiz.\n\nAgar kitobning aynan qaysi bobi va sahifasida kelgani yoki arabcha asl manbasi kerak bo'lsa: «Kitobdan qidir», «Manba keltir» yoki «Dalil keltir» deb yozing.",
            sources = emptyList()
        )
        _messages.value = listOf(greeting)
    }

    private fun loadKnowledgeBase() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val context = getApplication<Application>()
                val jsonString = context.assets.open("tamhid_embeddings.json").bufferedReader().use { it.readText() }
                val jsonArray = JSONArray(jsonString)

                val docs = mutableListOf<TamhidSectionDoc>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    val keywordsArray = obj.optJSONArray("keywords")
                    val keywords = mutableListOf<String>()
                    if (keywordsArray != null) {
                        for (k in 0 until keywordsArray.length()) {
                            keywords.add(keywordsArray.getString(k).lowercase())
                        }
                    }

                    val embeddingArray = obj.optJSONArray("embedding")
                    val embedding = mutableListOf<Float>()
                    if (embeddingArray != null) {
                        for (e in 0 until embeddingArray.length()) {
                            embedding.add(embeddingArray.getDouble(e).toFloat())
                        }
                    }

                    docs.add(
                        TamhidSectionDoc(
                            chapterNumber = obj.getInt("chapterNumber"),
                            chapterTitle = obj.getString("chapterTitle"),
                            sectionNumber = obj.getInt("sectionNumber"),
                            sectionTitle = obj.getString("sectionTitle"),
                            pageNumber = obj.getInt("pageNumber"),
                            arabicText = obj.getString("arabicText"),
                            uzbekText = obj.getString("uzbekText"),
                            naqliDalil = obj.getString("naqliDalil"),
                            aqliDalil = obj.getString("aqliDalil"),
                            keywords = keywords,
                            embedding = embedding
                        )
                    )
                }
                sectionsDatabase.clear()
                sectionsDatabase.addAll(docs)
            } catch (_: Exception) {
                // Hech qanday log chiqarmaydi, xavfsiz ishlaydi
            }
        }
    }

    private fun vectorSearchTopK(query: String, k: Int = 3): Pair<List<TamhidSectionDoc>, Float> {
        if (sectionsDatabase.isEmpty()) return Pair(emptyList(), 0f)

        val cleanQuery = query.lowercase().trim()
        val queryTokens = cleanQuery.split(Regex("[\\s,;:.?!'\"«»()\\[\\]]+")).filter { it.isNotBlank() }

        val queryVector = FloatArray(16) { 0f }
        val conceptMaps = listOf(
            listOf("haqiqat", "ashyo", "sobit", "safsata", "sofist", "bilish", "inkor") to 0,
            listOf("ilm", "sabab", "havoss", "aql", "xabar", "sodiq", "ilhom", "sezgi") to 1,
            listOf("olam", "hadis", "hudus", "a'roz", "javhar", "jism", "harakat", "sukun", "yaratilgan") to 2,
            listOf("vahdaniyat", "yagona", "tamonu", "tavhid", "sherik", "iloh", "soni'") to 3,
            listOf("tanzih", "jism", "javhar", "a'roz", "munazzah", "pok", "tarkib") to 4,
            listOf("makon", "jihat", "arsh", "istivo", "istilo", "tanzih", "qibla") to 5,
            listOf("sifat", "zot", "la huva", "ilm", "qudrat", "hayot", "iroda") to 6,
            listOf("quron", "kalam", "maxluq", "harf", "tovush", "azaliy", "so'z") to 7,
            listOf("takvin", "mukavvan", "xoliq", "yaratish", "azaliy", "moturidiy") to 8,
            listOf("ruya", "korish", "ko'rish", "jannat", "oxirat", "bila kayfiyya") to 9,
            listOf("kasb", "fe'l", "xalq", "af'al", "jabriya", "qadariya", "ixtiyor", "majbur") to 10,
            listOf("qazo", "qadar", "iroda", "taqdir", "rozi", "kufr", "iymon") to 11,
            listOf("qabr", "azob", "munkar", "nakir", "qiyomat", "alomat") to 12,
            listOf("gunoh", "kabira", "shafoat", "fosiq", "momin", "dozax", "shirk") to 13,
            listOf("iymon", "islom", "tasdiq", "iqror", "istisno", "inshaalloh") to 14,
            listOf("imom", "imomat", "xalifa", "sahoba", "abu bakr", "umar", "usmon", "ali") to 15
        )

        for (token in queryTokens) {
            for ((keywords, index) in conceptMaps) {
                if (keywords.any { it.contains(token) || token.contains(it) }) {
                    queryVector[index] += 1.0f
                }
            }
        }

        var qNorm = 0.0
        for (v in queryVector) { qNorm += v * v }
        qNorm = sqrt(qNorm)
        if (qNorm > 0.0) {
            for (i in queryVector.indices) {
                queryVector[i] = (queryVector[i] / qNorm).toFloat()
            }
        }

        val scoredSections = sectionsDatabase.map { doc ->
            var dot = 0.0f
            val docVec = doc.embedding
            val dim = minOf(queryVector.size, docVec.size)
            for (i in 0 until dim) {
                dot += queryVector[i] * docVec[i]
            }

            var keywordBonus = 0f
            for (token in queryTokens) {
                if (doc.keywords.any { it.contains(token) || token.contains(it) }) {
                    keywordBonus += 0.25f
                }
                if (doc.chapterTitle.lowercase().contains(token) || doc.sectionTitle.lowercase().contains(token)) {
                    keywordBonus += 0.35f
                }
            }

            val finalScore = (dot + keywordBonus).coerceIn(0f, 2f)
            Pair(doc, finalScore)
        }.sortedByDescending { it.second }

        val topScore = scoredSections.firstOrNull()?.second ?: 0f
        val topDocs = scoredSections.take(k).map { it.first }
        return Pair(topDocs, topScore)
    }

    /**
     * Foydalanuvchi savoli:
     * 1. Qat'iy ravishda Dispatchers.IO da bajariladi
     * 2. Hech qachon main thread da tarmoq so'rovi bo'lmaydi
     * 3. Bo'sh API kalit yoki tarmoq uzilishida chiroyli xabar beriladi
     * 4. Crash bo'lish ehtimoli 0% ga tushirilgan
     * 5. Loglarsiz, toza va sokin ishlaydi
     */
    fun askQuestion(query: String) {
        val trimmed = query.trim()
        if (trimmed.isBlank() || _uiState.value is UstozUiState.Loading) return

        lastQuestion = trimmed
        val studentMsg = UstozMessage(
            author = MessageAuthor.STUDENT,
            content = trimmed
        )
        _messages.value = _messages.value + studentMsg
        _uiState.value = UstozUiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userName = try {
                    dataStoreManager.studentNameFlow.first().ifBlank { "Tolib" }
                } catch (_: Exception) {
                    "Tolib"
                }

                val userAge = try {
                    dataStoreManager.userAgeFlow.first()
                } catch (_: Exception) {
                    20
                }

                val lowerQuery = trimmed.lowercase()
                // REJIM A vs REJIM B:
                val isRagMode = lowerQuery.contains("kitobdan") ||
                                lowerQuery.contains("kitobda bormi") ||
                                lowerQuery.contains("manba") ||
                                lowerQuery.contains("dalil") ||
                                lowerQuery.contains("at-tamhidda qidir")

                val isFirstMessage = _messages.value.count { it.author == MessageAuthor.STUDENT } <= 1

                // Oldingi suhbat tarixi (oxirgi 4 ta xabar)
                val historyContextBuilder = StringBuilder()
                val recentHistory = _messages.value.dropLast(1).takeLast(4)
                if (recentHistory.isNotEmpty()) {
                    historyContextBuilder.append("=== OLDINGI SUHBAT TARIXI ===\n")
                    recentHistory.forEach { m ->
                        val role = if (m.author == MessageAuthor.STUDENT) "Talaba ($userName)" else "Ustoz"
                        historyContextBuilder.append("$role: ${m.content}\n")
                    }
                    historyContextBuilder.append("=============================\n\n")
                }

                val baseInstructions = """
You are "AQIDA AI USTOZ", a teacher of Islamic aqida/kalam based on the book "Kitab at-Tamhid li-qawa'id at-tawhid" by Imam Abu al-Ma'ad al-Lamishi (Abu as-Sano Mahmud ibn Zayd al-Lomishiy).
Student name: $userName, age: $userAge. Is this the very first message of session: $isFirstMessage.

LANGUAGE: Always respond in Uzbek (Latin script), regardless of the language of these instructions. Match the student's own language/dialect if they write in something other than Uzbek.

CORE RULE — MATCH RESPONSE LENGTH TO THE USER'S MESSAGE:
- Simple greetings ("assalomu alaykum", "salom", "rahmat", etc.) → a short, natural reply (1-2 sentences). Do NOT repeat a long introduction, formal titles, or phrases like "aziz tolibim" every single time — save that tone for the very first message of a new conversation only.
- Simple/direct questions → a direct answer, no more than 3-5 sentences, unless the topic genuinely requires depth.
- Deep theological questions (e.g. "how does the theory of divine attributes work?") → give a detailed, book-grounded explanation, but keep it readable — break into short paragraphs, not a wall of text.
- Never expand length on your own initiative. Only go long if the student explicitly asks for more detail, examples, or a full explanation.

TONE:
- Write in a natural, human, conversational style. Reserve big welcoming phrases ("Xush kelibsiz!", "juda xursandman", etc.) for the first message of a new session only — don't "reset" the relationship in every reply.
- The teacher-student rapport should feel warm but continuous, like an ongoing conversation, not a re-introduction each time.
- Avoid repeating honorifics, titles, or duas in every message.

CONTENT RULES:
- Answers must be grounded in the book's content (Lamishi's Maturidi aqida), but explain in your own words — don't copy long passages from the book verbatim.
- If a question falls outside the book's scope, say so honestly and try to connect it back to the book's themes where relevant, without forcing it.
- If a question is vague or too broad, ask a clarifying question instead of writing a long lecture.

FORMATTING:
- Don't pad simple answers with section headers or extra blank lines. A normal conversational question deserves a normal paragraph or 2-3 short points.
- Use lists or step-by-step structure only for genuinely complex topics (e.g. types of divine attributes).
                """.trimIndent()

                if (isRagMode) {
                    // B) KITOBDAN QIDIRISH REJIMI (trigger bo'lganda)
                    val (topSections, _) = vectorSearchTopK(trimmed, k = 3)

                    val contextBuilder = StringBuilder()
                    contextBuilder.append(historyContextBuilder)
                    if (topSections.isNotEmpty()) {
                        contextBuilder.append("=== AT-TAMHID KITOBIDAN BO'LIMLAR ===\n\n")
                        topSections.forEachIndexed { idx, doc ->
                            contextBuilder.append("[${idx + 1}] Bob ${doc.chapterNumber}: ${doc.chapterTitle}, Bo'lim ${doc.sectionNumber}: ${doc.sectionTitle}, ${doc.pageNumber}-bet\n")
                            contextBuilder.append("Arabcha matn: ${doc.arabicText}\n")
                            contextBuilder.append("Sharh/Mazmun: ${doc.uzbekText}\n")
                            contextBuilder.append("Naqliy dalil: ${doc.naqliDalil}\n")
                            contextBuilder.append("Aqliy dalil: ${doc.aqliDalil}\n\n")
                        }
                    }

                    val systemPrompt = "$baseInstructions\n\nQo'shimcha eslatma: Talaba kitobdan dalil/manba so'radi. Berilgan bo'limlar asosida manba [Bob X, Bo'lim Y, bet Z] va qisqa arabcha iborasini keltirib, o'z so'zing bilan aniq tushuntir."

                    val response = try {
                        AiProvider.askUstoz(
                            savol = trimmed,
                            kontekst = contextBuilder.toString(),
                            systemPrompt = systemPrompt,
                            userName = userName,
                            userAge = userAge
                        )
                    } catch (_: Throwable) {
                        null
                    }

                    val finalResponse = response?.ifBlank { null }
                        ?: if (topSections.isNotEmpty()) {
                            val p = topSections.first()
                            """
«At-Tamhid» kitobida bu masala quyidagicha yoritilgan:

${p.uzbekText}

Arabcha iqtibos:
«${p.arabicText}»

[Bob ${p.chapterNumber}, Bo'lim ${p.sectionNumber}, ${p.pageNumber}-bet]
                            """.trimIndent()
                        } else {
                            "Bu masala «At-Tamhid» kitobining ushbu bo'limlarida bevosita uchramadi. Aniqroq qaysi jihati haqida so'ramoqchisiz?"
                        }

                    val sources = topSections.map {
                        TamhidSourceRef(it.chapterNumber, it.sectionNumber, it.chapterTitle, it.pageNumber)
                    }

                    val ustozMsg = UstozMessage(
                        author = MessageAuthor.USTOZ,
                        content = finalResponse,
                        isRagSource = true,
                        sources = sources
                    )
                    _messages.value = _messages.value + ustozMsg
                    _uiState.value = UstozUiState.Success(finalResponse)

                } else {
                    // A) SUHBAT REJIMI (DEFAULT - Insoniy, samimiy ustoz kabi)
                    val systemPrompt = baseInstructions

                    val response = try {
                        AiProvider.askUstoz(
                            savol = trimmed,
                            kontekst = historyContextBuilder.toString(),
                            systemPrompt = systemPrompt,
                            userName = userName,
                            userAge = userAge
                        )
                    } catch (_: Throwable) {
                        null
                    }

                    val s = trimmed.lowercase()
                    val fallbackDirect = when {
                        s == "salom" || s == "assalomu alaykum" || s.startsWith("assalom") ->
                            "Va alaykum assalom. Yaxshimisiz? Qanday masala ustida suhbatlashamiz?"
                        s == "rahmat" || s == "tashakkur" ->
                            "Arzimaydi, ilm yo'lida bardavom bo'ling. Yana savollaringiz bormi?"
                        else ->
                            "Moturidiy aqidasida har bir e'tiqodiy masala naql va sog'lom aql uyg'unligida tushuntiriladi. Savolingizning qaysi jihatiga ko'proq qiziqyapsiz?"
                    }

                    val finalResponse = response?.ifBlank { null } ?: fallbackDirect

                    val ustozMsg = UstozMessage(
                        author = MessageAuthor.USTOZ,
                        content = finalResponse,
                        isRagSource = false,
                        sources = emptyList()
                    )
                    _messages.value = _messages.value + ustozMsg
                    _uiState.value = UstozUiState.Success(finalResponse)
                }

            } catch (t: Throwable) {
                val errMsg = t.localizedMessage ?: "Internet aloqasida xatolik yuz berdi"
                _uiState.value = UstozUiState.Error(errMsg)
                val failMsg = UstozMessage(
                    author = MessageAuthor.USTOZ,
                    content = "Kechirasiz, aloqa uzilishi sababli javob bera olmadim. Iltimos, qaytadan so'rab ko'ring.",
                    isRagSource = false,
                    sources = emptyList()
                )
                _messages.value = _messages.value + failMsg
            }
        }
    }

    fun retryLast() {
        if (lastQuestion.isNotBlank()) {
            askQuestion(lastQuestion)
        }
    }

    fun clearChat() {
        _messages.value = emptyList()
        _uiState.value = UstozUiState.Idle
        addInitialGreeting()
    }
}
