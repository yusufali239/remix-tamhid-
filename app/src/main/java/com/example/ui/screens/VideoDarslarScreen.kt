package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VideoLibrary
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.R
import com.example.data.local.AudioPlayerManager
import com.example.data.model.AudioDarsItem
import com.example.data.model.AudioDarslarRepository
import com.example.ui.theme.AmiriQuranArabicStyle
import com.example.ui.theme.MadrasaEmerald
import com.example.ui.theme.TamhidEmerald
import com.example.ui.theme.TamhidGold
import com.example.ui.theme.TamhidSageContainer

data class VideoDarsItem(
    val id: String,
    val darsRaqami: String,
    val sarlavha: String,
    val tavsif: String,
    val arabchaSarlavha: String,
    val davomiyligi: String,
    val youtubeVideoId: String,
    val playlistId: String = "PLnXQtAauTV73EWkehpEMh9lyxawNL7og8"
)

enum class DarslarFilterType(val label: String) {
    BARCHASI("Barchasi"),
    VIDEO("🎥 Video darslar"),
    AUDIO("🎧 Audio darslar")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoDarslarScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val audioPlayerManager = remember { AudioPlayerManager.getInstance(context) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf(DarslarFilterType.BARCHASI) }

    // Observe active audio playback and saved progress
    val currentPlayingAudio by audioPlayerManager.currentAudio.collectAsState()
    val isAudioPlaying by audioPlayerManager.isPlaying.collectAsState()
    val savedProgressVersion by audioPlayerManager.savedProgressVersion.collectAsState()

    // 15 ta alohida rasmiy YouTube darslari (aniq va individual video ID lari bilan)
    val videoDarslar = remember {
        listOf(
            VideoDarsItem(
                id = "v_1",
                darsRaqami = "1-DARS",
                sarlavha = "Haqiqatlar sobitdir - Sofistlarga rad",
                tavsif = "Ashyoning haqiqati va ilm hosil qilish sabablari",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ١",
                davomiyligi = "1:04:15",
                youtubeVideoId = "_9X6LXRl094"
            ),
            VideoDarsItem(
                id = "v_kirish",
                darsRaqami = "KIRISH",
                sarlavha = "At-Tamhid asari bilan tanishuv ma'ruzasi",
                tavsif = "Abu as-Sano al-Lomishiy hayoti, kitob tuzilishi va Moturidiyya kalomi",
                arabchaSarlavha = "محاضرة تعريفية بكتاب التمهيد للامشي",
                davomiyligi = "22:30",
                youtubeVideoId = "-JtzoihNvzg"
            ),
            VideoDarsItem(
                id = "v_2",
                darsRaqami = "2-DARS",
                sarlavha = "Olamning hadis (yaratilgan) ekani",
                tavsif = "Javhar va a'rozlarning yaratilganligi isboti",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ٢",
                davomiyligi = "1:34:20",
                youtubeVideoId = "Q1rsXEUNlIg"
            ),
            VideoDarsItem(
                id = "v_3",
                darsRaqami = "3-DARS",
                sarlavha = "Olamning Yaratuvchisi borligi",
                tavsif = "Hudus dalili va Yagona Xoliqning zarurati",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ٣",
                davomiyligi = "1:28:45",
                youtubeVideoId = "Ks1rGJac3dY"
            ),
            VideoDarsItem(
                id = "v_4",
                darsRaqami = "4-DARS",
                sarlavha = "Tavhid va Tamonu' dalili",
                tavsif = "Alloh taoloning yakkayu yagonaligi aqliy isboti",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ٤",
                davomiyligi = "1:05:10",
                youtubeVideoId = "5TWfD-AsXm4"
            ),
            VideoDarsItem(
                id = "v_5",
                darsRaqami = "5-DARS",
                sarlavha = "Tanzih: Makon va jismdan poklik",
                tavsif = "Alloh taolo a'roz va jism emasligi bayoni",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ٥",
                davomiyligi = "1:27:35",
                youtubeVideoId = "Wijq8BmDrVQ"
            ),
            VideoDarsItem(
                id = "v_6",
                darsRaqami = "6-DARS",
                sarlavha = "Azaliy va abadiy Zotiy sifatlar",
                tavsif = "Hayot, Ilm, Qudrat, Iroda sifatlari sharhi",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ٦",
                davomiyligi = "1:27:50",
                youtubeVideoId = "HShWmnH-aPo"
            ),
            VideoDarsItem(
                id = "v_7",
                darsRaqami = "7-DARS",
                sarlavha = "Kalomulloh va Takvin sifati",
                tavsif = "Moturidiy mazhabining Takvin masalasidagi o'rni",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ٧",
                davomiyligi = "1:33:15",
                youtubeVideoId = "o-jAIL091Y8"
            ),
            VideoDarsItem(
                id = "v_8",
                darsRaqami = "8-DARS",
                sarlavha = "Oxiratda Ru'yotulloh (Allohni ko'rish)",
                tavsif = "Mo'minlarning jannatda Parvardigorni ko'rishi",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ٨",
                davomiyligi = "1:31:40",
                youtubeVideoId = "P4qEvVcA7TU"
            ),
            VideoDarsItem(
                id = "v_9",
                darsRaqami = "9-DARS",
                sarlavha = "Af'olul ibod: Bandalar fe'li va Kasb",
                tavsif = "Jabriya va Qadariyaga raddiya, ixtiyor haqiqati",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ٩",
                davomiyligi = "1:22:25",
                youtubeVideoId = "F4FgVtz37KI"
            ),
            VideoDarsItem(
                id = "v_10",
                darsRaqami = "10-DARS",
                sarlavha = "Qazo va Qadar, Rizo masalasi",
                tavsif = "Taqdirning sir-asrorlari va mo'minning pozitsiyasi",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ١٠",
                davomiyligi = "1:38:50",
                youtubeVideoId = "tVALgw_URk0"
            ),
            VideoDarsItem(
                id = "v_11",
                darsRaqami = "11-DARS",
                sarlavha = "Qabr azobi va Sam'iyot masalalari",
                tavsif = "Munkar va Nakir, Sirot, Mezon va Shafoat",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ١١",
                davomiyligi = "1:16:30",
                youtubeVideoId = "mmjbuDBLpVQ"
            ),
            VideoDarsItem(
                id = "v_12",
                darsRaqami = "12-DARS",
                sarlavha = "Iymon va Islom mohiyati",
                tavsif = "Tasdiq, iqror va amallarning iymonga aloqasi",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ١٢",
                davomiyligi = "1:37:15",
                youtubeVideoId = "kozOu2WSAYk"
            ),
            VideoDarsItem(
                id = "v_13",
                darsRaqami = "13-DARS",
                sarlavha = "Imomat va Sahobalar ehtiromi",
                tavsif = "Xulafoi roshidin va sahobalarga ehtirom qoidalari",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ١٣",
                davomiyligi = "1:24:40",
                youtubeVideoId = "kRv3emQdsOE"
            ),
            VideoDarsItem(
                id = "v_14",
                darsRaqami = "14-DARS",
                sarlavha = "Kalom ilmi yakuni va Aqida sobitligi",
                tavsif = "At-Tamhid asari xulosasi va to'g'ri e'tiqodda sobitlik",
                arabchaSarlavha = "التمهيد لقواعد التوحيد الدرس ١٥",
                davomiyligi = "1:45:10",
                youtubeVideoId = "zfCSLnmK3sc"
            )
        )
    }

    val audioDarslar = AudioDarslarRepository.allAudioDarslar

    // Filtr va qidiruv mantiqi
    val filteredVideoDarslar = remember(searchQuery, videoDarslar) {
        if (searchQuery.isBlank()) videoDarslar
        else videoDarslar.filter {
            it.sarlavha.contains(searchQuery, ignoreCase = true) ||
            it.tavsif.contains(searchQuery, ignoreCase = true) ||
            it.darsRaqami.contains(searchQuery, ignoreCase = true) ||
            it.arabchaSarlavha.contains(searchQuery, ignoreCase = true)
        }
    }

    val filteredAudioDarslar = remember(searchQuery, audioDarslar) {
        if (searchQuery.isBlank()) audioDarslar
        else audioDarslar.filter {
            it.mavzuUz.contains(searchQuery, ignoreCase = true) ||
            it.tavsifUz.contains(searchQuery, ignoreCase = true) ||
            it.audioRaqami.contains(searchQuery, ignoreCase = true) ||
            it.originalArabicTitle.contains(searchQuery, ignoreCase = true) ||
            it.originalFileName.contains(searchQuery, ignoreCase = true)
        }
    }

    fun openYouTube(videoId: String) {
        try {
            val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
            appIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(appIntent)
        } catch (e: Exception) {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
            webIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(webIntent)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Asosiy Sarlavha & Qidiruv qismi
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Text(
                text = "Darslar",
                style = MaterialTheme.typography.displayMedium.copy(fontSize = 26.sp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "At-Tamhid asari bo'yicha video va yangi audio darsliklar to'plami",
                style = MaterialTheme.typography.bodySmall,
                color = TamhidEmerald
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Qidiruv maydoni (ham video, ham audio darslar ichidan qidiradi)
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = {
                    Text(
                        "Video va audio darslardan qidirish...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = "Qidirish",
                        tint = TamhidEmerald
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TamhidEmerald,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Filtr chipslari (Barchasi / Video darslar / Audio darslar)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DarslarFilterType.values().forEach { filterType ->
                    val isSelected = selectedFilter == filterType
                    FilterChip(
                        selected = isSelected,
                        onClick = { selectedFilter = filterType },
                        label = {
                            Text(
                                text = filterType.label,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                maxLines = 1,
                                softWrap = false
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = TamhidEmerald,
                            selectedLabelColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        }

        // Darslar ro'yxati (2 ustunli Grid tartibi)
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 168.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            // 1. VIDEO DARSLAR BO'LIMI
            if (selectedFilter == DarslarFilterType.BARCHASI || selectedFilter == DarslarFilterType.VIDEO) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    SectionHeader(
                        icon = Icons.Outlined.VideoLibrary,
                        title = "🎥 VIDEO DARSLAR",
                        count = filteredVideoDarslar.size,
                        description = "YouTube rasmiy darslar kursi (har bir dars alohida havola bilan)"
                    )
                }

                if (filteredVideoDarslar.isEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        EmptySearchResult(text = "Qidiruv bo'yicha video dars topilmadi")
                    }
                } else {
                    items(filteredVideoDarslar, key = { it.id }) { dars ->
                        VideoDarsCard(
                            dars = dars,
                            onClick = { openYouTube(dars.youtubeVideoId) }
                        )
                    }
                }
            }

            // O'rtadagi ajratuvchi bo'lim (Agar ikkala bo'lim ko'rsatilayotgan bo'lsa)
            if (selectedFilter == DarslarFilterType.BARCHASI && filteredVideoDarslar.isNotEmpty() && filteredAudioDarslar.isNotEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }

            // 2. QO‘SHIMCHA AUDIO DARSLAR BO'LIMI
            if (selectedFilter == DarslarFilterType.BARCHASI || selectedFilter == DarslarFilterType.AUDIO) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    SectionHeader(
                        icon = Icons.Default.Headphones,
                        title = "🎧 QO‘SHIMCHA AUDIO DARSLAR",
                        count = filteredAudioDarslar.size,
                        description = "M4A formatidagi chuqurlashtirilgan o'zbekcha audio darslar"
                    )
                }

                if (filteredAudioDarslar.isEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        EmptySearchResult(text = "Qidiruv bo'yicha audio dars topilmadi")
                    }
                } else {
                    items(filteredAudioDarslar, key = { it.id }) { audio ->
                        val hasSavedProgress = remember(audio.id, savedProgressVersion) {
                            audioPlayerManager.hasSavedProgress(audio.id)
                        }
                        val savedProgressMs = remember(audio.id, savedProgressVersion) {
                            audioPlayerManager.getSavedProgress(audio.id)
                        }
                        val savedDurationMs = remember(audio.id, savedProgressVersion) {
                            audioPlayerManager.getSavedDuration(audio.id)
                        }
                        val isThisAudioPlaying = (currentPlayingAudio?.id == audio.id && isAudioPlaying)

                        AudioDarsCard(
                            audio = audio,
                            isPlaying = isThisAudioPlaying,
                            hasSavedProgress = hasSavedProgress,
                            savedProgressMs = savedProgressMs,
                            savedDurationMs = savedDurationMs,
                            onPlayClick = {
                                audioPlayerManager.playAudio(audio)
                            },
                            onResumeClick = {
                                audioPlayerManager.playAudio(audio, savedProgressMs)
                            },
                            onRestartClick = {
                                audioPlayerManager.playAudio(audio, 0L)
                            }
                        )
                    }
                }
            }

            // Pastki bo'sh joy (Mini player va Navigation bar uchun)
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }
}

@Composable
private fun SectionHeader(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    count: Int,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = TamhidEmerald,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = TamhidSageContainer
            ) {
                Text(
                    text = "$count ta",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = TamhidEmerald,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun EmptySearchResult(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * YouTube Video Dars kartochkasi:
 * Har bir kartochka o'zining individual YouTube Video ID siga ega!
 */
@Composable
private fun VideoDarsCard(
    dars: VideoDarsItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MadrasaEmerald
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // 1. Yuqori qism: 16:9 YouTube Thumbnail
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(Color(0xFF1B1B1B))
            ) {
                AsyncImage(
                    model = "https://img.youtube.com/vi/${dars.youtubeVideoId}/hqdefault.jpg",
                    contentDescription = dars.sarlavha,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Qora gradient parda
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.25f))
                )

                // Ustida "YOUTUBE" yozuvi
                Surface(
                    color = Color.Black.copy(alpha = 0.75f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        text = "YOUTUBE",
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }

                // Davomiyligi (pastki o'ng burchak)
                Surface(
                    color = Color.Black.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Text(
                        text = dars.davomiyligi,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                    )
                }

                // O'rtadagi Play belgisi
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(MadrasaEmerald.copy(alpha = 0.85f))
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Ijro etish",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // 2. Pastki qism: To'q yashil fon (#2D5A27), ichida OQ harflar bilan
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MadrasaEmerald)
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                Text(
                    text = dars.darsRaqami,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.sp),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = dars.sarlavha,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, lineHeight = 15.sp),
                    fontWeight = FontWeight.Medium,
                    color = Color.White.copy(alpha = 0.95f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = dars.tavsif,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, lineHeight = 13.sp),
                    color = Color.White.copy(alpha = 0.75f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

/**
 * Qo'shimcha Audio Dars kartochkasi:
 * - Vizual ravishda "AUDIO" deb belgilangan
 * - 1-AUDIO / Mavzu: ... / Qisqa tavsif / Davomiylik
 * - O'zbekcha sarlavha va arabcha asl nomi
 * - Tinglangan qismini ko'rsatish va "Oxirgi joydan davom ettirish" tugmasi
 */
@Composable
private fun AudioDarsCard(
    audio: AudioDarsItem,
    isPlaying: Boolean,
    hasSavedProgress: Boolean,
    savedProgressMs: Long,
    savedDurationMs: Long,
    onPlayClick: () -> Unit,
    onResumeClick: () -> Unit,
    onRestartClick: () -> Unit
) {
    val totalMs = if (savedDurationMs > 0) savedDurationMs else 1680000L
    val progressFraction = (savedProgressMs.toFloat() / totalMs.toFloat()).coerceIn(0f, 1f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (hasSavedProgress) onResumeClick() else onPlayClick()
            },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // 1. Yuqori qism: Audio Oblojkasi (16:9)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .background(TamhidEmerald)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.audio_lesson_cover_1788549419271),
                    contentDescription = audio.mavzuUz,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Qoramtir gradient
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color(0xAA0B241A))
                            )
                        )
                )

                // "AUDIO" belgisi (Yuqori chap burchak)
                Surface(
                    color = TamhidEmerald.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.TopStart)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Headphones,
                            contentDescription = null,
                            tint = TamhidGold,
                            modifier = Modifier.size(10.dp)
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "AUDIO",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                            fontWeight = FontWeight.Bold,
                            color = TamhidGold
                        )
                    }
                }

                // Davomiylik (Pastki o'ng burchak)
                Surface(
                    color = Color.Black.copy(alpha = 0.75f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(6.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Text(
                        text = if (savedDurationMs > 0) AudioPlayerManager.formatTime(savedDurationMs) else audio.taxminiyDavomiyligi,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                    )
                }

                // O'rtadagi Play / Pause belgisi
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(if (isPlaying) TamhidGold else TamhidEmerald)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Ijro etish",
                        tint = if (isPlaying) Color.Black else Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // Agar qisman tinglangan bo'lsa, progress chizig'i
            if (hasSavedProgress) {
                LinearProgressIndicator(
                    progress = { progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp),
                    color = TamhidGold,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }

            // 2. Pastki qism: Matnli tavsiflar
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp)
            ) {
                // Audio raqami
                Text(
                    text = audio.audioRaqami,
                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 12.sp),
                    fontWeight = FontWeight.Bold,
                    color = TamhidEmerald
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Mavzu: ...
                Text(
                    text = "Mavzu: ${audio.mavzuUz}",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, lineHeight = 15.sp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Arabcha original nomi
                Text(
                    text = audio.originalArabicTitle,
                    style = AmiriQuranArabicStyle.copy(fontSize = 11.sp, lineHeight = 14.sp),
                    color = TamhidEmerald.copy(alpha = 0.85f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(3.dp))

                // Qisqa tavsif
                Text(
                    text = audio.tavsifUz,
                    style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp, lineHeight = 13.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Tugma holati: "Oxirgi joydan davom ettirish" yoki "Tinglash"
                if (hasSavedProgress) {
                    val progressText = "${AudioPlayerManager.formatTime(savedProgressMs)} / ${AudioPlayerManager.formatTime(totalMs)}"
                    Text(
                        text = progressText,
                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 8.sp),
                        color = TamhidGold,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = onResumeClick,
                            modifier = Modifier
                                .weight(1f)
                                .height(28.dp),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = TamhidEmerald),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "Davom ettirish",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        IconButton(
                            onClick = onRestartClick,
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Replay,
                                contentDescription = "Boshidan",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                } else {
                    Button(
                        onClick = onPlayClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(28.dp),
                        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = TamhidEmerald),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Tinglash",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
