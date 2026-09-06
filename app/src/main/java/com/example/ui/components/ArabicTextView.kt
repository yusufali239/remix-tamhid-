package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.audio.ElevenLabsTtsManager
import com.example.data.audio.TtsPlaybackState
import com.example.ui.theme.*
import java.security.MessageDigest
import java.util.Locale

@Composable
fun ArabicParagraphCard(
    paragraphNumber: Int,
    arabicText: String,
    uzbekTranslation: String,
    pageNumber: Int,
    footnotes: String? = null,
    arabicFontSize: Float = 20f,
    uzbekFontSize: Float = 15f,
    isBookmarked: Boolean = false,
    onBookmarkToggle: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var showFootnotes by remember { mutableStateOf(false) }

    val ttsManager = remember { ElevenLabsTtsManager.getInstance(context) }
    val playbackState by ttsManager.playbackState.collectAsState()

    val textHash = remember(arabicText) {
        val digest = MessageDigest.getInstance("SHA-256")
        val bytes = digest.digest(arabicText.trim().toByteArray())
        bytes.joinToString("") { "%02x".format(it) }.take(16)
    }

    val isThisLoading = playbackState is TtsPlaybackState.Loading && (playbackState as TtsPlaybackState.Loading).textHash == textHash
    val isThisPlaying = playbackState is TtsPlaybackState.Playing && (playbackState as TtsPlaybackState.Playing).textHash == textHash
    val isThisPaused = playbackState is TtsPlaybackState.Paused && (playbackState as TtsPlaybackState.Paused).textHash == textHash
    val isElevenLabsAudio = (playbackState as? TtsPlaybackState.Playing)?.isElevenLabs == true

    // Pulsing audio animation simulation
    val infiniteTransition = rememberInfiniteTransition(label = "audio_pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_alpha"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header: Paragraph #, Page Badge, Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$paragraphNumber",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                text = "Kitob beti: $pageNumber",
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.MenuBook,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp),
                                tint = MadrasaGold
                            )
                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Audio recital mode toggle (ElevenLabs REST API + Local Audio Cache + Fallback)
                    IconButton(
                        onClick = {
                            ttsManager.playOrPause(arabicText) {
                                Toast.makeText(
                                    context,
                                    "ElevenLabs API kaliti kerak. Oflayn ovoz moduli ishlatildi.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        if (isThisLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                strokeWidth = 2.dp,
                                color = MadrasaGold
                            )
                        } else {
                            Icon(
                                imageVector = when {
                                    isThisPlaying -> Icons.Filled.PauseCircle
                                    isThisPaused -> Icons.Filled.PlayCircle
                                    else -> Icons.AutoMirrored.Outlined.VolumeUp
                                },
                                contentDescription = "Qiroat",
                                tint = if (isThisPlaying || isThisPaused) MadrasaGold else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Copy button
                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("At-Tamhid Matni", "$arabicText\n\nTarjimasi:\n$uzbekTranslation")
                            clipboard.setPrimaryClip(clip)
                            Toast.makeText(context, "Arabiy matn va tarjima nusxalandi", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ContentCopy,
                            contentDescription = "Nusxalash",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Bookmark button
                    IconButton(
                        onClick = onBookmarkToggle,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                            contentDescription = "Xatcho'p",
                            tint = if (isBookmarked) MadrasaGold else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Arabic Text Block (RTL, Emerald Frame with Subtle Gradient)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isThisPlaying)
                            MadrasaEmeraldLight.copy(alpha = 0.12f * pulseAlpha)
                        else
                            MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                    )
                    .border(
                        width = 1.dp,
                        brush = Brush.horizontalGradient(
                            listOf(MadrasaGold.copy(alpha = 0.5f), MadrasaEmerald.copy(alpha = 0.3f))
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = arabicText,
                        style = ArabicBodyStyle.copy(
                            fontSize = arabicFontSize.sp,
                            lineHeight = (arabicFontSize * 1.75f).sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right
                    )

                    if (isThisPlaying) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(MadrasaGold)
                            )
                            Text(
                                text = if (isElevenLabsAudio) "ElevenLabs HD qiroati ijro etilmoqda..." else "Matn qiroat qilinmoqda (Oflayn)...",
                                style = MaterialTheme.typography.labelSmall,
                                color = MadrasaGold,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Uzbek Translation
            Text(
                text = uzbekTranslation,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = uzbekFontSize.sp,
                    lineHeight = (uzbekFontSize * 1.55f).sp
                ),
                color = MaterialTheme.colorScheme.onSurface
            )

            // Footnotes (if present)
            if (!footnotes.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = { showFootnotes = !showFootnotes },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = if (showFootnotes) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (showFootnotes) "Izohni yopish" else "Ilmiy izohni ko'rish",
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                AnimatedVisibility(visible = showFootnotes) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = footnotes,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
