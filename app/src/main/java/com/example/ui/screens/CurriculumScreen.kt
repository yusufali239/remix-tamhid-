package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Assessment
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
    var bookSearchQuery by remember { mutableStateOf("") }

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
                val searchResults = remember(bookSearchQuery, chapters) {
                    if (bookSearchQuery.isBlank()) emptyList()
                    else searchAcrossEntireBook(bookSearchQuery, chapters)
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 48.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Full-Book Search Input Bar
                    item {
                        OutlinedTextField(
                            value = bookSearchQuery,
                            onValueChange = { bookSearchQuery = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("full_book_search_input"),
                            placeholder = {
                                Text(
                                    text = "Butun kitob bo'ylab qidirish (matn, sharh, dalillar)...",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Search,
                                    contentDescription = "Qidiruv",
                                    tint = TamhidEmerald
                                )
                            },
                            trailingIcon = {
                                if (bookSearchQuery.isNotBlank()) {
                                    IconButton(onClick = { bookSearchQuery = "" }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Tozalash",
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = TamhidEmerald,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface
                            )
                        )
                    }

                    if (bookSearchQuery.isNotBlank()) {
                        // Search Results Mode
                        item {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = TamhidSageContainer.copy(alpha = 0.4f),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "${searchResults.size} ta natija topildi (26 ta bob bo'yicha)",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = TamhidEmeraldDark
                                    )
                                    Text(
                                        text = "«${bookSearchQuery.trim()}»",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }

                        if (searchResults.isEmpty()) {
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
                                    shape = RoundedCornerShape(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth().padding(32.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "🔍", fontSize = 36.sp)
                                        Spacer(modifier = Modifier.height(12.dp))
                                        Text(
                                            text = "Hech narsa topilmadi",
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Text(
                                            text = "«$bookSearchQuery» bo'yicha 26 ta bob matni, arabcha iboralari va sharhlaridan hech qanday moslik chiqmadi. Boshqa so'z yoki ibora kiritib ko'ring.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                        )
                                    }
                                }
                            }
                        } else {
                            items(searchResults, key = { it.resultKey }) { result ->
                                BookSearchResultCard(
                                    result = result,
                                    onClick = {
                                        viewModel.openChapterParagraphInReader(result.chapterId, result.pageNumber)
                                    }
                                )
                            }
                        }
                    } else {
                        // Regular 26 Chapters List
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

// ============================================================================
// Full-Book Unified Search Models & Logic (Feature 4)
// ============================================================================

data class FullBookSearchResult(
    val resultKey: String,
    val chapterId: String,
    val chapterNumber: Int,
    val chapterTitleUz: String,
    val chapterTitleAr: String,
    val pageNumber: Int,
    val matchedTitle: String,
    val arabicSnippet: String,
    val uzbekSnippet: String,
    val category: String // "Matn / Masala", "Kalit Tushuncha", "Aqidaviy Dalil", "Munozara & Raddiya"
)

private fun normalizeArabicText(text: String): String {
    return text.replace(Regex("[\u064B-\u065F\u0670\u06D6-\u06ED]"), "")
        .replace("أ", "ا")
        .replace("إ", "ا")
        .replace("آ", "ا")
        .replace("ة", "ه")
        .replace("ى", "ي")
        .trim()
}

private fun searchAcrossEntireBook(query: String, chapters: List<Chapter>): List<FullBookSearchResult> {
    val q = query.trim()
    if (q.isBlank()) return emptyList()
    val qLower = q.lowercase()
    val qNormAr = normalizeArabicText(q)

    val results = mutableListOf<FullBookSearchResult>()

    chapters.forEach { chapter ->
        chapter.lessons.forEach { lesson ->
            // 1. Search Paragraphs (Arabic and Uzbek)
            lesson.paragraphs.forEach { p ->
                val matchUz = p.uzbekTranslation.contains(qLower, ignoreCase = true) ||
                        (p.footnotes?.contains(qLower, ignoreCase = true) == true)
                val matchAr = p.arabicText.contains(q, ignoreCase = true) ||
                        (qNormAr.isNotBlank() && normalizeArabicText(p.arabicText).contains(qNormAr, ignoreCase = true))

                if (matchUz || matchAr) {
                    results.add(
                        FullBookSearchResult(
                            resultKey = "para_${chapter.id}_${p.paragraphNumber}",
                            chapterId = chapter.id,
                            chapterNumber = chapter.chapterNumber,
                            chapterTitleUz = chapter.titleUz,
                            chapterTitleAr = chapter.titleAr,
                            pageNumber = p.pageNumber,
                            matchedTitle = "${p.paragraphNumber}-masala",
                            arabicSnippet = p.arabicText,
                            uzbekSnippet = p.uzbekTranslation,
                            category = "Matn / Masala"
                        )
                    )
                }
            }

            // 2. Search Concepts
            lesson.concepts.forEach { c ->
                val match = c.termUz.contains(qLower, true) ||
                        c.termAr.contains(q, true) ||
                        c.definitionUz.contains(qLower, true) ||
                        c.sourceExample.contains(q, true) ||
                        (qNormAr.isNotBlank() && normalizeArabicText(c.sourceExample).contains(qNormAr, true)) ||
                        (qNormAr.isNotBlank() && normalizeArabicText(c.termAr).contains(qNormAr, true))

                if (match) {
                    results.add(
                        FullBookSearchResult(
                            resultKey = "concept_${c.id}",
                            chapterId = chapter.id,
                            chapterNumber = chapter.chapterNumber,
                            chapterTitleUz = chapter.titleUz,
                            chapterTitleAr = chapter.titleAr,
                            pageNumber = c.pageRef,
                            matchedTitle = "${c.termUz} (${c.termAr})",
                            arabicSnippet = c.sourceExample,
                            uzbekSnippet = c.definitionUz,
                            category = "Kalit Tushuncha"
                        )
                    )
                }
            }

            // 3. Search Proofs
            lesson.proofs.forEach { pr ->
                val match = pr.titleUz.contains(qLower, true) ||
                        pr.uzbekTranslation.contains(qLower, true) ||
                        pr.sourceRef.contains(qLower, true) ||
                        pr.arabicText.contains(q, true) ||
                        (qNormAr.isNotBlank() && normalizeArabicText(pr.arabicText).contains(qNormAr, true))

                if (match) {
                    results.add(
                        FullBookSearchResult(
                            resultKey = "proof_${pr.id}",
                            chapterId = chapter.id,
                            chapterNumber = chapter.chapterNumber,
                            chapterTitleUz = chapter.titleUz,
                            chapterTitleAr = chapter.titleAr,
                            pageNumber = lesson.pdfStartPage,
                            matchedTitle = "${pr.titleUz} (${pr.sourceRef})",
                            arabicSnippet = pr.arabicText,
                            uzbekSnippet = pr.uzbekTranslation,
                            category = "Aqidaviy Dalil"
                        )
                    )
                }
            }

            // 4. Search Debates
            lesson.debates.forEach { d ->
                val match = d.topicTitle.contains(qLower, true) ||
                        d.ahlSunnahView.contains(qLower, true) ||
                        d.opposingSchool.contains(qLower, true) ||
                        d.opposingView.contains(qLower, true) ||
                        d.refutationUz.contains(qLower, true)

                if (match) {
                    results.add(
                        FullBookSearchResult(
                            resultKey = "debate_${d.id}",
                            chapterId = chapter.id,
                            chapterNumber = chapter.chapterNumber,
                            chapterTitleUz = chapter.titleUz,
                            chapterTitleAr = chapter.titleAr,
                            pageNumber = lesson.pdfStartPage,
                            matchedTitle = d.topicTitle,
                            arabicSnippet = "",
                            uzbekSnippet = "Ahli Sunna: ${d.ahlSunnahView}\nRaddiya: ${d.refutationUz}",
                            category = "Munozara & Raddiya"
                        )
                    )
                }
            }
        }
    }

    return results
}

@Composable
private fun BookSearchResultCard(
    result: FullBookSearchResult,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("search_result_${result.resultKey}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header Row: Category pill + Chapter & Page pill
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = when (result.category) {
                        "Matn / Masala" -> TamhidEmerald.copy(alpha = 0.12f)
                        "Kalit Tushuncha" -> TamhidSageContainer
                        "Aqidaviy Dalil" -> TamhidGoldContainer.copy(alpha = 0.5f)
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                ) {
                    Text(
                        text = result.category,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmeraldDark,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = "${result.chapterNumber}-bob • ${result.pageNumber}-bet",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Chapter and Matched Title
            Text(
                text = "${result.chapterTitleUz} — ${result.matchedTitle}",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Arabic Snippet (if available)
            if (result.arabicSnippet.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = TamhidSageContainer.copy(alpha = 0.25f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = result.arabicSnippet,
                        style = AmiriQuranArabicStyle.copy(fontSize = 17.sp, lineHeight = 28.sp),
                        color = TamhidEmeraldDark,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            // Uzbek Snippet
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = result.uzbekSnippet,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Footer: Jump to reader affordance
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.MenuBook,
                    contentDescription = null,
                    tint = TamhidEmerald,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = "Kitobda o'qish",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = TamhidEmerald
                )
            }
        }
    }
}

