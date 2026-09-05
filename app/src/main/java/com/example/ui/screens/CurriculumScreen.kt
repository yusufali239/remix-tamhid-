package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.ChapterCard
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel

@Composable
fun CurriculumScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val chapters = viewModel.allChapters
    val readingProgress by viewModel.allReadingProgress.collectAsState()
    val quizResults by viewModel.allQuizResults.collectAsState()
    val currentStreak by viewModel.currentStreak.collectAsState()

    var activeTab by remember { mutableStateOf("BOBLAR") } // "BOBLAR", "SINOV", "TAHLIL"

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Minimalist Header with 24dp padding
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Darslik",
                    style = MaterialTheme.typography.displayMedium.copy(fontSize = 28.sp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (currentStreak > 0) TamhidGoldContainer else MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "🔥", fontSize = 14.sp)
                        Text(
                            text = "$currentStreak kun",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (currentStreak > 0) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Imom al-Lomishiy • «Kitob at-Tamhid» (26 bob, 259 masala)",
                style = MaterialTheme.typography.bodySmall,
                color = TamhidEmerald
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Sub-navigation tabs embedded in Darslik: [Boblar, Sinov & Imtihon, O'zlashtirish tahlili]
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    TabPill(
                        label = "Boblar (26)",
                        selected = activeTab == "BOBLAR",
                        icon = Icons.Outlined.AutoStories
                    ) { activeTab = "BOBLAR" }
                }
                item {
                    TabPill(
                        label = "Sinov & Test",
                        selected = activeTab == "SINOV",
                        icon = Icons.Outlined.Quiz
                    ) { activeTab = "SINOV" }
                }
                item {
                    TabPill(
                        label = "Tahlil",
                        selected = activeTab == "TAHLIL",
                        icon = Icons.Outlined.Assessment
                    ) { activeTab = "TAHLIL" }
                }
            }
        }

        when (activeTab) {
            "BOBLAR" -> {
                // Chapters List using ChapterCard (No borders, 24dp rounded corners, 24dp padding)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(chapters, key = { it.id }) { chapter ->
                        val prog = readingProgress.find { it.chapterId == chapter.id }?.percent ?: 0

                        ChapterCard(
                            chapterNumber = chapter.chapterNumber,
                            titleUz = chapter.titleUz,
                            titleAr = chapter.titleAr,
                            startPage = chapter.pdfStartPage,
                            endPage = chapter.pdfEndPage,
                            progressPercent = prog,
                            descriptionUz = chapter.descriptionUz,
                            onClick = {
                                viewModel.selectChapter(chapter)
                                val firstLesson = chapter.lessons.firstOrNull()
                                if (firstLesson != null) {
                                    viewModel.selectLesson(firstLesson)
                                }
                            }
                        )
                    }
                }
            }

            "SINOV" -> {
                // Embedded Test / Quiz Session launcher
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(0.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "«At-Tamhid» umumiy imtihoni",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = TamhidEmerald
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(8.dp),
                                        color = TamhidSageContainer
                                    ) {
                                        Text(
                                            text = "20 ta savol",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = TamhidEmeraldDark,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "Kitobning barcha 26 bobi bo'yicha saralangan aqidaviy savollar. Har bir urinishda savollar va javob variantlari (A, B, C, D) tasodifiy aralashtiriladi.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = { viewModel.startGeneralQuiz(20) },
                                    modifier = Modifier.fillMaxWidth().height(48.dp),
                                    shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = TamhidEmerald),
                                    elevation = ButtonDefaults.buttonElevation(0.dp)
                                ) {
                                    Text("Umumiy imtihonni boshlash", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    item {
                        Text(
                            text = "Boblar bo'yicha alohida testlar (26 bob)",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    items(chapters, key = { "quiz_${it.id}" }) { chapter ->
                        val quizCount = chapter.lessons.sumOf { it.quizQuestions.size }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    val lessonId = chapter.lessons.firstOrNull()?.id ?: chapter.id
                                    viewModel.startQuizForLesson(lessonId)
                                },
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(18.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Text(
                                            text = "${chapter.chapterNumber}-bob testi",
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Surface(
                                            shape = RoundedCornerShape(6.dp),
                                            color = TamhidSageContainer
                                        ) {
                                            Text(
                                                text = "$quizCount ta savol",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = TamhidEmeraldDark,
                                                fontWeight = FontWeight.Medium,
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = chapter.titleAr,
                                        style = AmiriQuranArabicStyle.copy(fontSize = 14.sp),
                                        color = TamhidEmerald,
                                        maxLines = 1
                                    )
                                    Text(
                                        text = chapter.titleUz,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(TamhidSageContainer)
                                        .padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = "Sinov",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = TamhidEmeraldDark
                                    )
                                }
                            }
                        }
                    }
                }
            }

            "TAHLIL" -> {
                // Progress Analytics inside Darslik
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(0.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "O'zlashtirish ko'rsatkichlari",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = TamhidEmerald
                                )
                                Spacer(modifier = Modifier.height(14.dp))

                                val readChaptersCount = readingProgress.count { it.percent > 0 }
                                val averagePercent = if (readingProgress.isNotEmpty()) {
                                    readingProgress.map { it.percent }.average().toInt()
                                } else 0

                                Text(
                                    text = "Mutolaa qilingan boblar: $readChaptersCount / 26",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                LinearProgressIndicator(
                                    progress = { readChaptersCount / 26f },
                                    modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                                    color = TamhidEmerald,
                                    trackColor = TamhidSageContainer
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Topshirilgan sinovlar: ${quizResults.size} ta",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TabPill(
    label: String,
    selected: Boolean,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (selected) TamhidEmerald else MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (selected) Color.White else TamhidEmerald,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                softWrap = false
            )
        }
    }
}
