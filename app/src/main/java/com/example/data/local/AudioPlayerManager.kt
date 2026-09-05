package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.PlaybackParams
import android.os.Build
import android.util.Log
import com.example.data.model.AudioDarsItem
import com.example.data.model.AudioDarslarRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AudioPlayerManager(private val context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("tamhid_audio_progress", Context.MODE_PRIVATE)

    private var mediaPlayer: MediaPlayer? = null
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var progressJob: Job? = null

    private val _currentAudio = MutableStateFlow<AudioDarsItem?>(null)
    val currentAudio: StateFlow<AudioDarsItem?> = _currentAudio.asStateFlow()

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying.asStateFlow()

    private val _currentPositionMs = MutableStateFlow(0L)
    val currentPositionMs: StateFlow<Long> = _currentPositionMs.asStateFlow()

    private val _durationMs = MutableStateFlow(0L)
    val durationMs: StateFlow<Long> = _durationMs.asStateFlow()

    private val _playbackSpeed = MutableStateFlow(1.0f)
    val playbackSpeed: StateFlow<Float> = _playbackSpeed.asStateFlow()

    private val _isBuffering = MutableStateFlow(false)
    val isBuffering: StateFlow<Boolean> = _isBuffering.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _showFullPlayer = MutableStateFlow(false)
    val showFullPlayer: StateFlow<Boolean> = _showFullPlayer.asStateFlow()

    private val _savedProgressVersion = MutableStateFlow(0L)
    val savedProgressVersion: StateFlow<Long> = _savedProgressVersion.asStateFlow()

    init {
        // Load last played audio if available
        val lastId = prefs.getString("last_played_audio_id", null)
        if (lastId != null) {
            val item = AudioDarslarRepository.allAudioDarslar.find { it.id == lastId }
            if (item != null) {
                _currentAudio.value = item
                _currentPositionMs.value = getSavedProgress(item.id)
                _durationMs.value = getSavedDuration(item.id)
            }
        }
    }

    fun playAudio(audio: AudioDarsItem, startFromPositionMs: Long? = null) {
        val isSameAudio = _currentAudio.value?.id == audio.id

        if (isSameAudio && mediaPlayer != null) {
            if (startFromPositionMs != null) {
                seekTo(startFromPositionMs)
            }
            resume()
            _showFullPlayer.value = true
            return
        }

        _currentAudio.value = audio
        _errorMessage.value = null
        _isBuffering.value = true
        _isPlaying.value = false
        _showFullPlayer.value = true

        val targetPosition = startFromPositionMs ?: getSavedProgress(audio.id)

        saveLastPlayedAudioId(audio.id)
        startPlaybackWithUrl(audio, audio.primaryStreamUrl, targetPosition, isFallback = false)
    }

    private fun startPlaybackWithUrl(
        audio: AudioDarsItem,
        streamUrl: String,
        targetPositionMs: Long,
        isFallback: Boolean
    ) {
        releaseMediaPlayer()

        try {
            val player = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(streamUrl)
            }
            mediaPlayer = player

            player.setOnPreparedListener { mp ->
                _isBuffering.value = false
                val totalDuration = mp.duration.toLong()
                _durationMs.value = if (totalDuration > 0) totalDuration else 1800000L
                saveDuration(audio.id, _durationMs.value)

                if (targetPositionMs > 0 && targetPositionMs < _durationMs.value) {
                    mp.seekTo(targetPositionMs.toInt())
                    _currentPositionMs.value = targetPositionMs
                } else {
                    _currentPositionMs.value = 0L
                }

                applyPlaybackSpeed(_playbackSpeed.value)
                mp.start()
                _isPlaying.value = true
                startProgressTracker()
            }

            player.setOnCompletionListener {
                _isPlaying.value = false
                _currentPositionMs.value = _durationMs.value
                saveProgress(audio.id, 0L) // Reset on completion
                stopProgressTracker()
            }

            player.setOnErrorListener { _, what, extra ->
                Log.e("AudioPlayerManager", "MediaPlayer error: what=$what, extra=$extra")
                _isBuffering.value = false
                _isPlaying.value = false
                stopProgressTracker()

                if (!isFallback) {
                    Log.d("AudioPlayerManager", "Retrying with fallback stream URL...")
                    startPlaybackWithUrl(audio, audio.fallbackStreamUrl, targetPositionMs, isFallback = true)
                } else {
                    _errorMessage.value = "Audio yuklashda xatolik yuz berdi. Internet aloqasini tekshiring."
                }
                true
            }

            player.setOnBufferingUpdateListener { _, percent ->
                // Can track buffer if needed
            }

            player.prepareAsync()
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Exception initializing MediaPlayer", e)
            _isBuffering.value = false
            _isPlaying.value = false
            if (!isFallback) {
                startPlaybackWithUrl(audio, audio.fallbackStreamUrl, targetPositionMs, isFallback = true)
            } else {
                _errorMessage.value = "Audio o'ynatib bo'lmadi: ${e.localizedMessage ?: "Noma'lum xatolik"}"
            }
        }
    }

    fun togglePlayPause() {
        if (_isPlaying.value) {
            pause()
        } else {
            resume()
        }
    }

    fun pause() {
        try {
            mediaPlayer?.let { player ->
                if (player.isPlaying) {
                    player.pause()
                    _isPlaying.value = false
                    val pos = player.currentPosition.toLong()
                    _currentPositionMs.value = pos
                    _currentAudio.value?.let { saveProgress(it.id, pos) }
                }
            }
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Error in pause()", e)
        }
        stopProgressTracker()
    }

    fun resume() {
        val player = mediaPlayer
        val audio = _currentAudio.value
        if (player != null && audio != null) {
            try {
                applyPlaybackSpeed(_playbackSpeed.value)
                player.start()
                _isPlaying.value = true
                startProgressTracker()
            } catch (e: Exception) {
                Log.e("AudioPlayerManager", "Error in resume(), restarting playback", e)
                playAudio(audio, _currentPositionMs.value)
            }
        } else if (audio != null) {
            playAudio(audio, _currentPositionMs.value)
        }
    }

    fun seekTo(positionMs: Long) {
        val target = positionMs.coerceIn(0L, _durationMs.value.coerceAtLeast(1L))
        _currentPositionMs.value = target
        try {
            mediaPlayer?.seekTo(target.toInt())
            _currentAudio.value?.let { saveProgress(it.id, target) }
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Error in seekTo()", e)
        }
    }

    fun seekRelative(offsetMs: Long) {
        val cur = _currentPositionMs.value
        val target = (cur + offsetMs).coerceIn(0L, _durationMs.value.coerceAtLeast(1L))
        seekTo(target)
    }

    fun setSpeed(speed: Float) {
        _playbackSpeed.value = speed
        applyPlaybackSpeed(speed)
    }

    private fun applyPlaybackSpeed(speed: Float) {
        try {
            mediaPlayer?.let { player ->
                val params = player.playbackParams ?: PlaybackParams()
                params.speed = speed
                player.playbackParams = params
            }
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Error setting playback speed", e)
        }
    }

    fun playNext() {
        val current = _currentAudio.value ?: return
        val list = AudioDarslarRepository.allAudioDarslar
        val index = list.indexOfFirst { it.id == current.id }
        if (index != -1 && index + 1 < list.size) {
            playAudio(list[index + 1])
        } else if (list.isNotEmpty()) {
            playAudio(list.first())
        }
    }

    fun playPrevious() {
        val current = _currentAudio.value ?: return
        val list = AudioDarslarRepository.allAudioDarslar
        val index = list.indexOfFirst { it.id == current.id }
        if (index > 0) {
            playAudio(list[index - 1])
        } else if (list.isNotEmpty()) {
            playAudio(list.last())
        }
    }

    fun expandFullPlayer() {
        _showFullPlayer.value = true
    }

    fun collapseFullPlayer() {
        _showFullPlayer.value = false
    }

    fun stopAndClose() {
        pause()
        releaseMediaPlayer()
        _currentAudio.value = null
        _isPlaying.value = false
        _showFullPlayer.value = false
    }

    private fun startProgressTracker() {
        stopProgressTracker()
        progressJob = coroutineScope.launch {
            var saveCounter = 0
            while (isActive) {
                try {
                    mediaPlayer?.let { player ->
                        if (player.isPlaying) {
                            val pos = player.currentPosition.toLong()
                            _currentPositionMs.value = pos
                            saveCounter++
                            if (saveCounter % 6 == 0) { // Save every ~3 seconds
                                _currentAudio.value?.let { saveProgress(it.id, pos) }
                            }
                        }
                    }
                } catch (e: Exception) {
                    // ignore transient state
                }
                delay(500)
            }
        }
    }

    private fun stopProgressTracker() {
        progressJob?.cancel()
        progressJob = null
    }

    private fun releaseMediaPlayer() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        } catch (e: Exception) {
            // ignore
        }
        mediaPlayer = null
    }

    // Persistence helper methods
    fun hasSavedProgress(audioId: String): Boolean {
        val saved = prefs.getLong("progress_$audioId", 0L)
        return saved > 10000L // More than 10 seconds in
    }

    fun getSavedProgress(audioId: String): Long {
        return prefs.getLong("progress_$audioId", 0L)
    }

    fun getSavedDuration(audioId: String): Long {
        return prefs.getLong("duration_$audioId", 0L)
    }

    fun saveProgress(audioId: String, positionMs: Long) {
        prefs.edit().putLong("progress_$audioId", positionMs).apply()
        _savedProgressVersion.value = System.currentTimeMillis()
    }

    fun saveDuration(audioId: String, durationMs: Long) {
        if (durationMs > 0) {
            prefs.edit().putLong("duration_$audioId", durationMs).apply()
        }
    }

    private fun saveLastPlayedAudioId(audioId: String) {
        prefs.edit().putString("last_played_audio_id", audioId).apply()
    }

    companion object {
        private var instance: AudioPlayerManager? = null

        fun getInstance(context: Context): AudioPlayerManager {
            return instance ?: synchronized(this) {
                instance ?: AudioPlayerManager(context.applicationContext).also { instance = it }
            }
        }

        fun formatTime(ms: Long): String {
            if (ms <= 0) return "00:00"
            val totalSec = ms / 1000
            val hours = totalSec / 3600
            val minutes = (totalSec % 3600) / 60
            val seconds = totalSec % 60
            return if (hours > 0) {
                String.format("%d:%02d:%02d", hours, minutes, seconds)
            } else {
                String.format("%02d:%02d", minutes, seconds)
            }
        }
    }
}
