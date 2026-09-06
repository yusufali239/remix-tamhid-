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
import com.example.data.model.Chapter
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
                // Comprehensive Academic Analytics for 26 Chapters
                val progressMap = remember(readingProgress) { readingProgress.associateBy { it.chapterId } }
                val readChaptersCount = chapters.count { (progressMap[it.id]?.percent ?: 0) > 0 }
                val averageReadingPercent = if (chapters.isNotEmpty()) {
                    chapters.map { progressMap[it.id]?.percent ?: 0 }.average().toInt()
                } else 0
                val averageQuizScore = if (quizResults.isNotEmpty()) {
                    quizResults.map { it.scorePercentage }.average().toInt()
                } else 0

                // Build per-chapter stats with best score
                val chapterAnalyticsList = remember(chapters, readingProgress, quizResults) {
                    chapters.map { ch ->
                        val rPercent = progressMap[ch.id]?.percent ?: 0
                        val cQuizzes = quizResults.filter { qr ->
                            qr.quizKey == ch.id || ch.lessons.any { it.id == qr.quizKey } || qr.quizKey.startsWith("${ch.id}_")
                        }
                        val bestScore = cQuizzes.maxOfOrNull { it.scorePercentage }
                        val attemptsCount = cQuizzes.size

                        // Review urgency score:
                        // Low quiz score (< 70%) is highest urgency
                        // Partially read chapter (1..99%) is high urgency
                        // Unread (0%) is moderate
                        // Mastered is 0
                        val reviewPriority = when {
                            bestScore != null && bestScore < 70 -> 100 - bestScore
                            rPercent in 1..99 -> 60 - (rPercent / 2)
                            rPercent == 0 -> 25
                            bestScore != null && bestScore < 85 -> 15
                            else -> 0
                        }

                        ChapterAnalyticsData(
                            chapter = ch,
                            readingPercent = rPercent,
                            bestQuizScore = bestScore,
                            quizAttempts = attemptsCount,
                            reviewPriority = reviewPriority
                        )
                    }
                }

                val weakestChapters = remember(chapterAnalyticsList) {
                    chapterAnalyticsList
                        .sortedByDescending { it.reviewPriority }
                        .take(3)
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 1. Overall Summary Card (Streak, Reading, Quizzes)
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(0.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "Umumiy o'zlashtirish ko'rsatkichlari",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = TamhidEmerald
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    // Streak Box
                                    Surface(
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(16.dp),
                                        color = if (currentStreak > 0) TamhidGoldContainer else MaterialTheme.colorScheme.surfaceVariant
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(12.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "🔥", fontSize = 20.sp)
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = "$currentStreak kun",
                                                style = MaterialTheme.typography.titleSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = if (currentStreak > 0) TamhidEmeraldDark else MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = "Joriy streak",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }

                                    // Reading Progress Box
                                    Surface(
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(16.dp),
                                        color = TamhidSageContainer
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(12.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "📖", fontSize = 20.sp)
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = "$readChaptersCount / 26",
                                                style = MaterialTheme.typography.titleSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = TamhidEmeraldDark
                                            )
                                            Text(
                                                text = "Bob mutolaasi",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }

                                    // Quiz Avg Score Box
                                    Surface(
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(16.dp),
                                        color = TamhidSageContainer
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(12.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(text = "🎯", fontSize = 20.sp)
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = if (quizResults.isNotEmpty()) "$averageQuizScore%" else "—",
                                                style = MaterialTheme.typography.titleSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = TamhidEmeraldDark
                                            )
                                            Text(
                                                text = if (quizResults.isNotEmpty()) "Imtihon bali" else "Sinov yo'q",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(18.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Umumiy mutolaa progressi",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                    Text(
                                        text = "$averageReadingPercent%",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = TamhidEmerald
                                    )
                                }

                                Spacer(modifier = Modifier.height(6.dp))

                                LinearProgressIndicator(
                                    progress = { (averageReadingPercent / 100f).coerceIn(0f, 1f) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    color = TamhidEmerald,
                                    trackColor = TamhidSageContainer
                                )

                                if (quizResults.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Text(
                                        text = "Jami topshirilgan sinovlar: ${quizResults.size} ta (O'rtacha natija: $averageQuizScore%)",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                    }

                    // 2. Highlight Weakest / Recommended Review Chapters
                    if (weakestChapters.isNotEmpty()) {
                        item {
                            Text(
                                text = "Qayta takrorlash tavsiya etiladi",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }

                        items(weakestChapters, key = { "weak_${it.chapter.id}" }) { itemData ->
                            val ch = itemData.chapter
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.selectChapter(ch)
                                        val firstLesson = ch.lessons.firstOrNull()
                                        if (firstLesson != null) {
                                            viewModel.selectLesson(firstLesson)
                                        }
                                    },
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                                elevation = CardDefaults.cardElevation(0.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "${ch.chapterNumber}-bob • ${ch.titleUz}",
                                            style = MaterialTheme.typography.titleSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = ch.titleAr,
                                            style = AmiriQuranArabicStyle.copy(fontSize = 13.sp),
                                            color = TamhidEmerald,
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))

                                        val reasonText = when {
                                            itemData.bestQuizScore != null && itemData.bestQuizScore < 70 ->
                                                "⚠️ Test bali past: ${itemData.bestQuizScore}% (eng yaxshi natija)"
                                            itemData.readingPercent in 1..99 ->
                                                "📖 Mutolaa to'liq tugallanmagan: ${itemData.readingPercent}%"
                                            itemData.bestQuizScore != null && itemData.bestQuizScore < 85 ->
                                                "ℹ️ Test natijasini oshirish mumkin: ${itemData.bestQuizScore}%"
                                            itemData.readingPercent == 0 ->
                                                "⏳ Mutolaa hali boshlanmagan (0%)"
                                            else ->
                                                "Qayta takrorlash uchun"
                                        }

                                        Text(
                                            text = reasonText,
                                            style = MaterialTheme.typography.labelSmall,
                                            color = if (itemData.bestQuizScore != null && itemData.bestQuizScore < 70)
                                                MaterialTheme.colorScheme.error
                                            else
                                                TamhidEmeraldDark,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(TamhidSageContainer)
                                            .padding(horizontal = 12.dp, vertical = 6.dp)
                                    ) {
                                        Text(
                                            text = "O'qish",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = TamhidEmeraldDark
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 3. Per-Chapter Breakdown (All 26 Chapters)
                    item {
                        Text(
                            text = "Boblar bo'yicha ko'rsatkichlar (26 bob)",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    items(chapterAnalyticsList, key = { "breakdown_${it.chapter.id}" }) { itemData ->
                        val ch = itemData.chapter
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.selectChapter(ch)
                                    val firstLesson = ch.lessons.firstOrNull()
                                    if (firstLesson != null) {
                                        viewModel.selectLesson(firstLesson)
                                    }
                                },
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                            elevation = CardDefaults.cardElevation(0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "${ch.chapterNumber}-bob • ${ch.titleUz}",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = ch.titleAr,
                                        style = AmiriQuranArabicStyle.copy(fontSize = 12.sp),
                                        color = TamhidEmerald,
                                        maxLines = 1
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        LinearProgressIndicator(
                                            progress = { (itemData.readingPercent / 100f).coerceIn(0f, 1f) },
                                            modifier = Modifier
                                                .weight(1f)
                                                .height(6.dp)
                                                .clip(RoundedCornerShape(3.dp)),
                                            color = if (itemData.readingPercent >= 100) TamhidEmerald else TamhidEmeraldLight,
                                            trackColor = TamhidSageContainer
                                        )

                                        Text(
                                            text = "${itemData.readingPercent}% mutolaa",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }

                                Column(
                                    horizontalAlignment = Alignment.End,
                                    modifier = Modifier.padding(start = 14.dp)
                                ) {
                                    if (itemData.bestQuizScore != null) {
                                        val isGoodScore = itemData.bestQuizScore >= 70
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = if (isGoodScore) TamhidSageContainer else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.6f)
                                        ) {
                                            Text(
                                                text = "Test: ${itemData.bestQuizScore}%",
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isGoodScore) TamhidEmeraldDark else MaterialTheme.colorScheme.onErrorContainer,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                        if (itemData.quizAttempts > 1) {
                                            Spacer(modifier = Modifier.height(2.dp))
                                            Text(
                                                text = "Eng yaxshi (${itemData.quizAttempts} ta)",
                                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                                            )
                                        }
                                    } else {
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            color = MaterialTheme.colorScheme.surfaceVariant
                                        ) {
                                            Text(
                                                text = "Test yo'q",
                                                style = MaterialTheme.typography.labelSmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                            )
                                        }
                                    }
                                }
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

data class ChapterAnalyticsData(
    val chapter: Chapter,
    val readingPercent: Int,
    val bestQuizScore: Int?,
    val quizAttempts: Int,
    val reviewPriority: Int
)

