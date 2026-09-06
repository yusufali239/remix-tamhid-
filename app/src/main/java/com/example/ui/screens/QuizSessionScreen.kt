package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuizQuestion
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizSessionScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val questions by viewModel.activeQuizQuestions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val selectedAnswers by viewModel.selectedAnswers.collectAsState()
    val isCompleted by viewModel.isQuizCompleted.collectAsState()

    if (questions.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Savollar topilmadi")
        }
        return
    }

    if (isCompleted) {
        // QUIZ RESULT SCREEN
        var correctCount = 0
        questions.forEachIndexed { index, q ->
            if (selectedAnswers[index] == q.correctIndex) correctCount++
        }
        val scorePercent = (correctCount * 100) / questions.size

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Imtihon natijasi") },
                    navigationIcon = {
                        IconButton(onClick = { viewModel.navigateTo("darslik") }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Darslik")
                        }
                    }
                )
            },
            contentWindowInsets = WindowInsets(0, 0, 0, 0)
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (scorePercent >= 70) MadrasaEmerald.copy(alpha = 0.15f)
                                        else WarningOrange.copy(alpha = 0.15f)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$scorePercent%",
                                    style = MaterialTheme.typography.headlineMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (scorePercent >= 70) MadrasaEmerald else WarningOrange
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = if (scorePercent >= 80) "A'lo darajada o'zlashtirildi!"
                                else if (scorePercent >= 60) "Qoniqarli natija, mavzuni takrorlang"
                                else "Mavzuni qayta o'qib chiqish tavsiya etiladi",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "$correctCount / ${questions.size} ta savolga to'g'ri javob berildi",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Button(
                                    onClick = { viewModel.startGeneralQuiz() },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = MadrasaEmerald)
                                ) {
                                    Text("Qayta topshirish")
                                }

                                OutlinedButton(
                                    onClick = { viewModel.navigateTo("darslik") },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text("Darslikka qaytish")
                                }
                            }
                        }
                    }
                }

                // Question Correction Details
                item {
                    Text(
                        text = "Savollar tahlili va manbalar:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                items(questions.size) { idx ->
                    val q = questions[idx]
                    val userAns = selectedAnswers[idx]
                    val isCorrect = userAns == q.correctIndex

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${idx + 1}-savol",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Icon(
                                    imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                    contentDescription = null,
                                    tint = if (isCorrect) SuccessGreen else ErrorRed,
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = q.questionUz, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "To'g'ri javob: ${q.options[q.correctIndex]}",
                                style = MaterialTheme.typography.bodySmall,
                                color = SuccessGreen,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Manba sharhi: ${q.explanationUz} (Kitob beti: ${q.sourcePageRef})",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    } else {
        // ACTIVE QUESTION SCREEN
        val currentQuestion = questions[currentIndex]
        val selectedOption = selectedAnswers[currentIndex]

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Savol ${currentIndex + 1} / ${questions.size}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { viewModel.navigateTo("darslik") }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Chiqish")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    LinearProgressIndicator(
                        progress = { (currentIndex + 1).toFloat() / questions.size.toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = MadrasaEmerald
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp)
                        ) {
                            Text(
                                text = currentQuestion.questionUz,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            if (!currentQuestion.arabicSnippet.isNullOrBlank()) {
                                Spacer(modifier = Modifier.height(10.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                                        .padding(10.dp)
                                ) {
                                    Text(
                                        text = currentQuestion.arabicSnippet,
                                        style = ArabicQuoteStyle.copy(fontSize = 17.sp),
                                        color = MaterialTheme.colorScheme.onSurface,
                                        textAlign = TextAlign.Right,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Options list
                    currentQuestion.options.forEachIndexed { optIndex, optionText ->
                        val isSelected = selectedOption == optIndex
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { viewModel.selectQuizAnswer(currentIndex, optIndex) },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected)
                                    MaterialTheme.colorScheme.primaryContainer
                                else
                                    MaterialTheme.colorScheme.surface
                            ),
                            border = if (isSelected)
                                androidx.compose.foundation.BorderStroke(1.5.dp, MadrasaEmerald)
                            else
                                null
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                RadioButton(
                                    selected = isSelected,
                                    onClick = { viewModel.selectQuizAnswer(currentIndex, optIndex) },
                                    colors = RadioButtonDefaults.colors(selectedColor = MadrasaEmerald)
                                )
                                Text(
                                    text = optionText,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }

                // Bottom navigation buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (currentIndex > 0) {
                        OutlinedButton(
                            onClick = { viewModel.previousQuizQuestion() },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Oldingi")
                        }
                    }

                    Button(
                        onClick = { viewModel.nextQuizQuestion() },
                        enabled = selectedOption != null,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MadrasaEmerald)
                    ) {
                        Text(if (currentIndex == questions.size - 1) "Yakunlash" else "Keyingi")
                    }
                }
            }
        }
    }
}
