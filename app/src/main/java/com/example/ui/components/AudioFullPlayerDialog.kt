package com.example.ui.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.R
import com.example.data.local.AudioPlayerManager
import com.example.data.model.AudioDarsItem
import com.example.ui.theme.AmiriQuranArabicStyle
import com.example.ui.theme.TamhidEmerald
import com.example.ui.theme.TamhidGold
import com.example.ui.theme.TamhidSageContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioFullPlayerDialog(
    audioPlayerManager: AudioPlayerManager,
    onDismiss: () -> Unit
) {
    val currentAudio by audioPlayerManager.currentAudio.collectAsState()
    val isPlaying by audioPlayerManager.isPlaying.collectAsState()
    val currentPos by audioPlayerManager.currentPositionMs.collectAsState()
    val duration by audioPlayerManager.durationMs.collectAsState()
    val playbackSpeed by audioPlayerManager.playbackSpeed.collectAsState()
    val isBuffering by audioPlayerManager.isBuffering.collectAsState()
    val errorMessage by audioPlayerManager.errorMessage.collectAsState()

    val audio = currentAudio ?: return

    // User seeking interaction state
    var isDraggingSlider by remember { mutableStateOf(false) }
    var draggingSliderValue by remember { mutableFloatStateOf(0f) }

    val safeDuration = duration.coerceAtLeast(1L)
    val sliderProgress = if (isDraggingSlider) {
        draggingSliderValue
    } else {
        (currentPos.toFloat() / safeDuration.toFloat()).coerceIn(0f, 1f)
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Action Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "Kichraytirish",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "QO‘SHIMCHA AUDIO DARSLIK",
                            style = MaterialTheme.typography.labelSmall,
                            letterSpacing = 1.sp,
                            fontWeight = FontWeight.Bold,
                            color = TamhidEmerald
                        )
                        Text(
                            text = "At-Tamhid li-qavoidit-tavhid",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    val context = LocalContext.current
                    IconButton(
                        onClick = {
                            val shareText = "«At-Tamhid» ilovasida audio dars tinglamoqdaman:\n\n${audio.audioRaqami}: ${audio.mavzuUz}\n${audio.originalArabicTitle}\n\n«At-Tamhid» Moturidiya kalom ilmi ilovasi"
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(sendIntent, "Audio darsni ulashish"))
                        },
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = "Ulashish",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Album Artwork Container
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .shadow(elevation = 12.dp, shape = RoundedCornerShape(24.dp))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(TamhidEmerald, Color(0xFF0F382A))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Try to load custom artwork
                    Image(
                        painter = painterResource(id = R.drawable.audio_lesson_cover_1788549419271),
                        contentDescription = "Audio Dars Muqovasi",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    // Subtle dark gradient overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color(0xAA0B241A))
                                )
                            )
                    )

                    // Audio lesson badge pill on artwork
                    Surface(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xEE0B241A)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Headphones,
                                contentDescription = null,
                                tint = TamhidGold,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = audio.audioRaqami,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = TamhidGold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Lesson Title & Details
                Text(
                    text = audio.mavzuUz,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Original Arabic Title
                Text(
                    text = audio.originalArabicTitle,
                    style = AmiriQuranArabicStyle.copy(fontSize = 18.sp),
                    textAlign = TextAlign.Center,
                    color = TamhidEmerald,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Uzbek Description
                Text(
                    text = audio.tavsifUz,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                )

                // Error message if any
                if (errorMessage != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        color = MaterialTheme.colorScheme.errorContainer,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = errorMessage ?: "",
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Seek Slider & Timestamps
                Column(modifier = Modifier.fillMaxWidth()) {
                    Slider(
                        value = sliderProgress,
                        onValueChange = {
                            isDraggingSlider = true
                            draggingSliderValue = it
                        },
                        onValueChangeFinished = {
                            isDraggingSlider = false
                            val targetMs = (draggingSliderValue * safeDuration).toLong()
                            audioPlayerManager.seekTo(targetMs)
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = TamhidGold,
                            activeTrackColor = TamhidEmerald,
                            inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val displayPos = if (isDraggingSlider) {
                            (draggingSliderValue * safeDuration).toLong()
                        } else {
                            currentPos
                        }
                        Text(
                            text = AudioPlayerManager.formatTime(displayPos),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = AudioPlayerManager.formatTime(duration),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Playback Speed Selector (0.5x, 0.75x, 1x, 1.25x, 1.5x, 1.75x, 2x)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val speeds = listOf(0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 1.75f, 2.0f)
                    speeds.forEach { speed ->
                        val isSelected = (playbackSpeed == speed)
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (isSelected) TamhidEmerald else MaterialTheme.colorScheme.surfaceVariant
                                )
                                .clickable { audioPlayerManager.setSpeed(speed) }
                                .padding(horizontal = 8.dp, vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (speed == 1.0f) "1x" else "${speed}x",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Playback Controls Row: [Previous] [-10s] [PLAY/PAUSE] [+10s] [Next]
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Previous Lesson
                    IconButton(
                        onClick = { audioPlayerManager.playPrevious() },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SkipPrevious,
                            contentDescription = "Oldingi dars",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    // -10 seconds seek
                    IconButton(
                        onClick = { audioPlayerManager.seekRelative(-10000L) },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Replay10,
                            contentDescription = "10 soniya orqaga",
                            tint = TamhidEmerald,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    // Large Play / Pause button
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .shadow(8.dp, CircleShape)
                            .clip(CircleShape)
                            .background(TamhidEmerald)
                            .clickable { audioPlayerManager.togglePlayPause() },
                        contentAlignment = Alignment.Center
                    ) {
                        if (isBuffering) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(36.dp),
                                color = Color.White,
                                strokeWidth = 3.dp
                            )
                        } else {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Pauza" else "Tinglash",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }

                    // +10 seconds seek
                    IconButton(
                        onClick = { audioPlayerManager.seekRelative(10000L) },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Forward10,
                            contentDescription = "10 soniya oldinga",
                            tint = TamhidEmerald,
                            modifier = Modifier.size(26.dp)
                        )
                    }

                    // Next Lesson
                    IconButton(
                        onClick = { audioPlayerManager.playNext() },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.SkipNext,
                            contentDescription = "Keyingi dars",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
