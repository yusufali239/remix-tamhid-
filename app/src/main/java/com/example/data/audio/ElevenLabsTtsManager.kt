package com.example.data.audio

import android.content.Context
import android.media.MediaPlayer
import android.speech.tts.TextToSpeech
import android.util.Log
import com.example.BuildConfig
import com.example.data.api.ElevenLabsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.security.MessageDigest
import java.util.Locale

sealed class TtsPlaybackState {
    object Idle : TtsPlaybackState()
    data class Loading(val textHash: String) : TtsPlaybackState()
    data class Playing(val textHash: String, val isElevenLabs: Boolean) : TtsPlaybackState()
    data class Paused(val textHash: String) : TtsPlaybackState()
    data class Error(val message: String) : TtsPlaybackState()
    object ApiKeyMissing : TtsPlaybackState()
}

class ElevenLabsTtsManager private constructor(private val context: Context) {

    private val service = ElevenLabsService.create()
    private var mediaPlayer: MediaPlayer? = null
    private var offlineTts: TextToSpeech? = null
    private var isOfflineTtsReady = false

    private val _playbackState = MutableStateFlow<TtsPlaybackState>(TtsPlaybackState.Idle)
    val playbackState: StateFlow<TtsPlaybackState> = _playbackState.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Main)

    // Recommended ElevenLabs Voice for Arabic / Multilingual recitation
    // Default: "onwK4e9ZLuTAKqWW03F9" (Daniel) or "21m00Tcm4TlvDq8ikWAM" (Rachel)
    private val voiceId = "onwK4e9ZLuTAKqWW03F9"

    init {
        initOfflineTts()
    }

    private fun initOfflineTts() {
        offlineTts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val res = offlineTts?.setLanguage(Locale.forLanguageTag("ar"))
                if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                    offlineTts?.language = Locale.getDefault()
                }
                isOfflineTtsReady = true
            }
        }
    }

    private fun computeTextHash(text: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = digest.digest(text.trim().toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }.take(16)
    }

    private fun getCacheFile(textHash: String): File {
        val cacheDir = File(context.cacheDir, "elevenlabs_tts").apply { mkdirs() }
        return File(cacheDir, "tts_$textHash.mp3")
    }

    fun isAudioCached(text: String): Boolean {
        val hash = computeTextHash(text)
        val file = getCacheFile(hash)
        return file.exists() && file.length() > 0
    }

    fun playOrPause(
        text: String,
        onApiKeyNeeded: () -> Unit = {}
    ) {
        val hash = computeTextHash(text)
        val currentState = _playbackState.value

        if (currentState is TtsPlaybackState.Playing && currentState.textHash == hash) {
            pause()
            return
        }

        if (currentState is TtsPlaybackState.Paused && currentState.textHash == hash) {
            resume()
            return
        }

        playArabicRecital(text, onApiKeyNeeded)
    }

    fun playArabicRecital(
        text: String,
        onApiKeyNeeded: () -> Unit = {}
    ) {
        val hash = computeTextHash(text)
        stopCurrentPlayback()

        val cacheFile = getCacheFile(hash)
        if (cacheFile.exists() && cacheFile.length() > 0) {
            // 1. Play from local file cache
            playLocalMp3File(cacheFile, hash)
            return
        }

        // 2. Fetch from ElevenLabs REST API
        val apiKey = BuildConfig.ELEVENLABS_API_KEY.trim()
        if (apiKey.isBlank() || apiKey == "PLACEHOLDER_ELEVENLABS_API_KEY") {
            // API key is missing: notify listener, set ApiKeyMissing state, and fallback to offline TTS
            _playbackState.value = TtsPlaybackState.ApiKeyMissing
            onApiKeyNeeded()
            fallbackToOfflineTts(text, hash)
            return
        }

        _playbackState.value = TtsPlaybackState.Loading(hash)

        scope.launch {
            try {
                val jsonPayload = JSONObject().apply {
                    put("text", text)
                    put("model_id", "eleven_multilingual_v2")
                    put("voice_settings", JSONObject().apply {
                        put("stability", 0.5)
                        put("similarity_boost", 0.75)
                    })
                }

                val requestBody = jsonPayload.toString().toRequestBody("application/json".toMediaType())

                val response = withContext(Dispatchers.IO) {
                    service.textToSpeech(
                        voiceId = voiceId,
                        apiKey = apiKey,
                        body = requestBody
                    )
                }

                if (response.isSuccessful && response.body() != null) {
                    val responseBody = response.body()!!
                    withContext(Dispatchers.IO) {
                        FileOutputStream(cacheFile).use { fos ->
                            responseBody.byteStream().use { input ->
                                input.copyTo(fos)
                            }
                        }
                    }
                    playLocalMp3File(cacheFile, hash)
                } else {
                    Log.e("ElevenLabsTTS", "HTTP Error: ${response.code()} ${response.message()}")
                    _playbackState.value = TtsPlaybackState.Error("ElevenLabs xatoligi: ${response.code()}")
                    fallbackToOfflineTts(text, hash)
                }
            } catch (e: Exception) {
                Log.e("ElevenLabsTTS", "Exception during TTS request", e)
                _playbackState.value = TtsPlaybackState.Error("Ulanishda xatolik: ${e.localizedMessage}")
                fallbackToOfflineTts(text, hash)
            }
        }
    }

    private fun playLocalMp3File(file: File, hash: String) {
        try {
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(file.absolutePath)
                prepare()
                setOnCompletionListener {
                    _playbackState.value = TtsPlaybackState.Idle
                }
                setOnErrorListener { _, _, _ ->
                    _playbackState.value = TtsPlaybackState.Error("Audio ijrosida xatolik")
                    false
                }
                start()
            }
            _playbackState.value = TtsPlaybackState.Playing(hash, isElevenLabs = true)
        } catch (e: Exception) {
            _playbackState.value = TtsPlaybackState.Error("Faylni o'qib bo'lmadi")
        }
    }

    private fun fallbackToOfflineTts(text: String, hash: String) {
        if (isOfflineTtsReady && offlineTts != null) {
            offlineTts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, hash)
            _playbackState.value = TtsPlaybackState.Playing(hash, isElevenLabs = false)
        } else {
            _playbackState.value = TtsPlaybackState.Error("Oflayn ovoz moduli mavjud emas")
        }
    }

    fun pause() {
        val current = _playbackState.value
        if (current is TtsPlaybackState.Playing) {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
            }
            offlineTts?.stop()
            _playbackState.value = TtsPlaybackState.Paused(current.textHash)
        }
    }

    fun resume() {
        val current = _playbackState.value
        if (current is TtsPlaybackState.Paused) {
            mediaPlayer?.start()
            _playbackState.value = TtsPlaybackState.Playing(current.textHash, isElevenLabs = true)
        }
    }

    fun stop() {
        stopCurrentPlayback()
        _playbackState.value = TtsPlaybackState.Idle
    }

    private fun stopCurrentPlayback() {
        mediaPlayer?.let {
            if (it.isPlaying) it.stop()
            it.release()
        }
        mediaPlayer = null
        offlineTts?.stop()
    }

    fun release() {
        stopCurrentPlayback()
        offlineTts?.shutdown()
        offlineTts = null
    }

    companion object {
        @Volatile
        private var instance: ElevenLabsTtsManager? = null

        fun getInstance(context: Context): ElevenLabsTtsManager {
            return instance ?: synchronized(this) {
                instance ?: ElevenLabsTtsManager(context.applicationContext).also { instance = it }
            }
        }
    }
}
