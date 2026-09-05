package com.example.data.local

import com.example.BuildConfig
import com.example.data.model.FlashcardItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

// ============================================================================
// At-Tamhid AI Dvigateli: Gemini (REST API) + Groq Fallback
// Barcha operatsiyalar qat'iy ravishda Dispatchers.IO da, xatoliklar ushlanadi.
// Hech qanday crash bo'lmaydi va loglarsiz to'liq sokin ishlaydi.
// ============================================================================

object AiProvider {
    private const val GROQ_ENDPOINT = "https://api.groq.com/openai/v1/chat/completions"
    private const val GROQ_MODEL = "llama-3.1-70b-versatile"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    fun getGeminiApiKey(): String {
        val envKey = System.getenv("GEMINI_API_KEY") ?: ""
        if (envKey.isNotBlank() && !envKey.startsWith("PLACEHOLDER") && !envKey.startsWith("MY_")) return envKey
        val buildKey = try { BuildConfig.GEMINI_API_KEY } catch (_: Throwable) { "" }
        if (buildKey.isNotBlank() && !buildKey.startsWith("PLACEHOLDER") && !buildKey.startsWith("MY_")) return buildKey
        return ""
    }

    fun getGroqApiKey(): String {
        val envKey = System.getenv("GROQ_API_KEY") ?: ""
        if (envKey.isNotBlank() && !envKey.startsWith("PLACEHOLDER") && !envKey.startsWith("MY_")) return envKey
        val buildKey = try { BuildConfig.GROQ_API_KEY } catch (_: Throwable) { "" }
        if (buildKey.isNotBlank() && !buildKey.startsWith("PLACEHOLDER") && !buildKey.startsWith("MY_")) return buildKey
        return ""
    }

    /**
     * Ustoz AI so'rovi:
     * 1. Avval Gemini Direct REST API (gemini-2.5-flash / gemini-3.5-flash)
     * 2. Agar xatolik bo'lsa -> Groq (Llama 3.1 70B)
     * 3. Agar ikkalasi ham ishlamasa yoki kalit kiritilmagan bo'lsa ->
     *    Zaxira o'zbekcha aqidaviy tushuntirish beriladi (hech qachon crash bo'lmaydi).
     */
    suspend fun askUstoz(
        savol: String,
        kontekst: String = "",
        systemPrompt: String = "",
        userName: String = "Tolib",
        userAge: Int = 20
    ): String = withContext(Dispatchers.IO) {
        val finalSystemPrompt = if (systemPrompt.isNotBlank()) {
            systemPrompt
        } else {
            """
You are "AQIDA AI USTOZ", a teacher of Islamic aqida/kalam based on the book "Kitab at-Tamhid li-qawa'id at-tawhid" by Imam Abu al-Ma'ad al-Lamishi (Abu as-Sano al-Lomishiy).
Student name: $userName, age: $userAge.

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
        }

        val promptToSend = if (kontekst.isNotBlank()) {
            "$kontekst\n\n=== SAVOL ===\n$savol"
        } else {
            savol
        }

        // 1. Avval Gemini REST API
        val geminiKey = getGeminiApiKey()
        if (geminiKey.isNotBlank()) {
            try {
                val geminiResult = callGeminiChat(
                    systemPrompt = finalSystemPrompt,
                    userMessage = promptToSend,
                    apiKey = geminiKey
                )
                if (geminiResult.isNotBlank()) {
                    return@withContext geminiResult.trim()
                }
            } catch (_: Throwable) {
                // Groq ga o'tiladi
            }
        }

        // 2. Fallback: Groq API
        val groqKey = getGroqApiKey()
        if (groqKey.isNotBlank()) {
            try {
                val groqResult = callGroqChat(
                    systemPrompt = finalSystemPrompt,
                    userMessage = promptToSend,
                    apiKey = groqKey
                )
                if (groqResult.isNotBlank()) {
                    return@withContext groqResult.trim()
                }
            } catch (_: Throwable) {
                // Offline zaxiraga o'tiladi
            }
        }

        // 3. Fallback: Offline aqidaviy ustoz javobi
        buildFallbackUstozResponse(savol, kontekst, userName, userAge)
    }

    /**
     * Gemini REST API orqali to'g'ridan-to'g'ri chaqiruv
     * Ktor yoki GenerativeModel kutubxonalarisiz, toza OkHttp yordamida ishlaydi.
     */
    suspend fun callGeminiChat(
        systemPrompt: String,
        userMessage: String,
        apiKey: String
    ): String = withContext(Dispatchers.IO) {
        val models = listOf("gemini-2.5-flash", "gemini-3.5-flash")
        for (model in models) {
            try {
                val url = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent?key=$apiKey"
                val requestJson = JSONObject().apply {
                    if (systemPrompt.isNotBlank()) {
                        put("systemInstruction", JSONObject().apply {
                            put("parts", JSONArray().apply {
                                put(JSONObject().apply { put("text", systemPrompt) })
                            })
                        })
                    }
                    put("contents", JSONArray().apply {
                        put(JSONObject().apply {
                            put("role", "user")
                            put("parts", JSONArray().apply {
                                put(JSONObject().apply { put("text", userMessage) })
                            })
                        })
                    })
                    put("generationConfig", JSONObject().apply {
                        put("temperature", 0.3)
                    })
                }

                val requestBody = requestJson.toString().toRequestBody("application/json".toMediaType())
                val request = Request.Builder()
                    .url(url)
                    .addHeader("Content-Type", "application/json")
                    .post(requestBody)
                    .build()

                okHttpClient.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val body = response.body?.string() ?: ""
                        val root = JSONObject(body)
                        val candidates = root.optJSONArray("candidates")
                        if (candidates != null && candidates.length() > 0) {
                            val firstCand = candidates.getJSONObject(0)
                            val content = firstCand.optJSONObject("content")
                            val parts = content?.optJSONArray("parts")
                            if (parts != null && parts.length() > 0) {
                                val sb = StringBuilder()
                                for (p in 0 until parts.length()) {
                                    val partObj = parts.getJSONObject(p)
                                    val text = partObj.optString("text", "")
                                    sb.append(text)
                                }
                                val result = sb.toString().trim()
                                if (result.isNotBlank()) return@withContext result
                            }
                        }
                    }
                }
            } catch (_: Throwable) {
                // Keyingi modelga o'tadi
            }
        }
        ""
    }

    /**
     * Groq OpenAI-formatidagi API chaqiruvi
     */
    suspend fun callGroqChat(
        systemPrompt: String,
        userMessage: String,
        apiKey: String
    ): String = withContext(Dispatchers.IO) {
        val requestJson = JSONObject().apply {
            put("model", GROQ_MODEL)
            val messages = JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "system")
                    put("content", systemPrompt)
                })
                put(JSONObject().apply {
                    put("role", "user")
                    put("content", userMessage)
                })
            }
            put("messages", messages)
            put("temperature", 0.3)
            put("max_tokens", 1024)
        }

        val requestBody = requestJson.toString().toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(GROQ_ENDPOINT)
            .addHeader("Authorization", "Bearer $apiKey")
            .addHeader("Content-Type", "application/json")
            .post(requestBody)
            .build()

        okHttpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                val err = response.body?.string() ?: ""
                throw RuntimeException("Groq HTTP ${response.code}: $err")
            }
            val resStr = response.body?.string() ?: throw RuntimeException("Bo'sh Groq javobi")
            val json = JSONObject(resStr)
            val choices = json.optJSONArray("choices")
            if (choices != null && choices.length() > 0) {
                val message = choices.getJSONObject(0).optJSONObject("message")
                return@withContext message?.optString("content", "") ?: ""
            }
            throw RuntimeException("Groq javob formati noto'g'ri")
        }
    }

    /**
     * Flashcard yaratish (Groq orqali)
     */
    suspend fun generateFlashcardsWithGroq(
        chapterTitle: String,
        lessonTitle: String,
        content: String
    ): List<FlashcardItem> = withContext(Dispatchers.IO) {
        val groqKey = getGroqApiKey()
        if (groqKey.isBlank()) return@withContext emptyList()

        val systemPrompt = """
Sen Moturidiy aqida ustozi va metodistisan. Berilgan dars matni asosida talaba uchun 3 ta savol-javobli flesh-karta yarat.
Faqat o'zbek lotin tilida yoz. Qat'iy format - FAQAT quyidagi JSON massiv:
[
  {
    "term": "Termin (O'zbekcha)",
    "termAr": "Termin (Arabcha)",
    "definition": "Qisqa va aniq ta'rif",
    "quote": "Arabcha keltirilgan nass yoki ibora",
    "page": 45
  }
]
        """.trimIndent()

        val userPrompt = "Mavzu: $chapterTitle - $lessonTitle\nMatn: ${content.take(1500)}"

        try {
            val response = callGroqChat(systemPrompt, userPrompt, groqKey)
            val jsonStart = response.indexOf('[')
            val jsonEnd = response.lastIndexOf(']')
            if (jsonStart != -1 && jsonEnd != -1 && jsonEnd > jsonStart) {
                val jsonArray = JSONArray(response.substring(jsonStart, jsonEnd + 1))
                val cards = mutableListOf<FlashcardItem>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    cards.add(
                        FlashcardItem(
                            id = "ai_card_${System.currentTimeMillis()}_$i",
                            lessonId = chapterTitle,
                            chapterId = chapterTitle,
                            termUz = obj.optString("term", "Aqida atamasi"),
                            termAr = obj.optString("termAr", "العقيدة"),
                            definitionUz = obj.optString("definition", ""),
                            sourceQuote = obj.optString("quote", ""),
                            pageRef = obj.optInt("page", 40)
                        )
                    )
                }
                return@withContext cards
            }
        } catch (_: Exception) {
            // Loglarsiz, jim
        }
        emptyList()
    }

    private fun buildFallbackUstozResponse(
        savol: String,
        kontekst: String,
        userName: String,
        userAge: Int
    ): String {
        val s = savol.trim().lowercase()

        // 1. Simple greetings
        if (s == "salom" || s == "assalomu alaykum" || s == "assalom alaykum" || s.startsWith("assalom")) {
            return "Va alaykum assalom. Yaxshimisiz? Qanday masala ustida suhbatlashamiz?"
        }
        if (s == "rahmat" || s == "tashakkur" || s.startsWith("rahmat")) {
            return "Arzimaydi, foydali bo'lganidan xursandman. Yana qanday savolingiz bor?"
        }
        if (s == "xayr" || s.startsWith("xayr") || s.contains("ko'rishguncha")) {
            return "Omon bo'ling, ilm yo'lidagi izlanishlaringizda bardavom bo'ling."
        }

        // 2. If grounded in book context
        if (kontekst.isNotBlank()) {
            return "Imom al-Lomishiy «At-Tamhid» asarida bu masalani quyidagicha tushuntiradilar:\n\n" +
                    "Ahli sunna val jamoa e'tiqodiga ko'ra, Alloh taolo zotida, azaliy sifatlarida va fe'llarida yagonadir. U Zot a'roz va jismlarga xos barcha xususiyatlardan (makon, zamon, shakl va o'zgarishdan) butunlay munazzahdir.\n\n" +
                    "Bu tamoyil aql va nassning uyg'unligiga asoslanadi."
        }

        // 3. Simple direct theological response
        return "Moturidiy aqidasiga ko'ra, har bir e'tiqodiy masala naql (Qur'on va Sunnat) asosida o'rnatilib, sog'lom aql bilan isbotlanadi. Siz so'ragan masala ham Allohning tavhidi va Uning mukammal sifatlariga bog'liq. Agar mavzuni aniqroq qismini yozsangiz, batafsilroq to'xtalamiz."
    }

    /**
     * Kundalik dars eslatmasi matnini generatsiya qilish (AI orqali + zaxira matnlar)
     */
    suspend fun generateStudyReminder(
        studentName: String,
        currentChapter: Int,
        progressPercent: Int,
        dueCards: Int
    ): String = withContext(Dispatchers.IO) {
        val systemPrompt = """
            You are "AQIDA AI USTOZ", generating a warm, concise daily study notification in Uzbek (Latin script) for student '$studentName'.
            The student is studying 'Kitab at-Tamhid' (Moturidiy aqida).
            Current progress: Chapter $currentChapter of 26 ($progressPercent% completed). Due flashcards: $dueCards.
            REQUIREMENTS:
            - Write exactly 1-2 friendly, motivating sentences.
            - Mention their real progress naturally (e.g. "siz 26 bobdan $currentChapter-bobdasiz", "$progressPercent% mutolaa qildingiz").
            - No quotes, no markdown, no hashtags, no intro preamble. Maximum 140 characters.
        """.trimIndent()

        val userMessage = "Kundalik eslatma xabarini yozing."

        val geminiKey = getGeminiApiKey()
        if (geminiKey.isNotBlank()) {
            try {
                val res = callGeminiChat(systemPrompt, userMessage, geminiKey).trim()
                if (res.isNotBlank() && res.length in 20..220) {
                    return@withContext res.replace("\"", "").replace("\n", " ").trim()
                }
            } catch (_: Throwable) {}
        }

        val groqKey = getGroqApiKey()
        if (groqKey.isNotBlank()) {
            try {
                val res = callGroqChat(systemPrompt, userMessage, groqKey).trim()
                if (res.isNotBlank() && res.length in 20..220) {
                    return@withContext res.replace("\"", "").replace("\n", " ").trim()
                }
            } catch (_: Throwable) {}
        }

        // Offline fallback options referencing real progress
        val fallbacks = listOf(
            "$studentName, bugun «At-Tamhid» mutolaasini davom ettiramizmi? Siz 26 bobdan $currentChapter-bobdasiz ($progressPercent% yakunlangan).",
            "Ilm talab qilishda bardavomlik xayrlidir! $currentChapter-bobdagi yangi masalalar sizni kutmoqda, $studentName.",
            "$studentName, aqida darslarini o'rganish uchun qulay fursat. 26 bobdan $currentChapter-bob sari birgalikda intilamiz!",
            "Kichik bo'lsa-da har kungi mutolaa ulkan natija beradi. Bugungi darsni boshlang, $studentName ($progressPercent% bajarildi).",
            "$studentName, xotirani mustahkamlash vaqti keldi! «At-Tamhid» darslarini bugun ham davom ettiring."
        )
        fallbacks.random()
    }
}
