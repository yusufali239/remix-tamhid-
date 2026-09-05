package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.ui.viewmodel.MessageAuthor
import com.example.ui.viewmodel.UstozMessage
import com.example.ui.viewmodel.UstozUiState
import com.example.ui.viewmodel.UstozViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UstozScreen(
    viewModel: UstozViewModel,
    modifier: Modifier = Modifier,
    onNavigateToReader: (Int, Int) -> Unit = { _, _ -> }
) {
    val messages by viewModel.messages.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    var inputQuery by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()

    val quickSuggestions = listOf(
        "Aqidani o'rganishni nimadan boshlay?",
        "Kitobdan qidir: Ilm sabablari",
        "Alloh taolo jism va makondan pokmi?",
        "Dalil keltir: Kalomulloh azaliyligi",
        "Narsalarning haqiqati sobitmi?"
    )

    fun sendCurrentQuery() {
        val q = inputQuery.trim()
        if (q.isNotBlank() && uiState !is UstozUiState.Loading) {
            viewModel.askQuestion(q)
            inputQuery = ""
            keyboardController?.hide()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "AI Ustoz",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Suhbat rejimi (odatiy) • Kitobdan qidiruv (trigger bilan)",
                            style = MaterialTheme.typography.labelSmall,
                            color = TamhidEmerald
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.clearChat() }) {
                        Icon(
                            imageVector = Icons.Default.DeleteSweep,
                            contentDescription = "Tozalash",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
          containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Error banner if any
            if (uiState is UstozUiState.Error) {
                val errorMsg = (uiState as UstozUiState.Error).xabar
                Surface(
                    color = MaterialTheme.colorScheme.errorContainer,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Xato",
                            tint = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = errorMsg,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            modifier = Modifier.weight(1f)
                        )
                        TextButton(onClick = { viewModel.retryLast() }) {
                            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Qayta urinish")
                        }
                    }
                }
            }

            // Message list with reverseLayout = true for auto-anchor to bottom without crash
            val reversedMessages = remember(messages) { messages.asReversed() }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                LazyColumn(
                    state = listState,
                    reverseLayout = true,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    // If loading, top of reversed list = bottom of chat screen
                    if (uiState is UstozUiState.Loading) {
                        item(key = "loading_bubble") {
                            UstozLoadingIndicator()
                        }
                    }

                    items(
                        items = reversedMessages,
                        key = { it.id }
                    ) { message ->
                        UstozMessageItem(
                            message = message,
                            onSourceClick = { chapterNum, pageNum ->
                                onNavigateToReader(chapterNum, pageNum)
                            }
                        )
                    }
                }
            }

            // Quick suggestion chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(quickSuggestions) { suggestion ->
                    SuggestionChip(
                        onClick = {
                            inputQuery = suggestion
                            sendCurrentQuery()
                        },
                        label = {
                            Text(
                                text = suggestion,
                                style = MaterialTheme.typography.labelSmall,
                                maxLines = 1,
                                softWrap = false
                            )
                        },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = TamhidSageContainer,
                            labelColor = TamhidEmerald
                        ),
                        border = null
                    )
                }
            }

            // Chat input field
            Surface(
                tonalElevation = 2.dp,
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = inputQuery,
                        onValueChange = { inputQuery = it },
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(24.dp)),
                        placeholder = {
                            Text(
                                "Ustozga savol bering yoki «kitobdan qidir» deng...",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                            )
                        },
                        shape = RoundedCornerShape(24.dp),
                        maxLines = 4,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = TamhidEmerald,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                        keyboardActions = KeyboardActions(onSend = { sendCurrentQuery() })
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    val canSend = inputQuery.isNotBlank() && uiState !is UstozUiState.Loading

                    IconButton(
                        onClick = { sendCurrentQuery() },
                        enabled = canSend,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(if (canSend) TamhidEmerald else MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Yuborish",
                            tint = if (canSend) Color.White else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun UstozLoadingIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Surface(
            color = TamhidSageContainer,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 4.dp),
            modifier = Modifier.padding(end = 64.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                    color = TamhidEmerald
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Ustoz fikrlamoqda...",
                    style = MaterialTheme.typography.bodySmall,
                    color = TamhidEmerald
                )
            }
        }
    }
}

@Composable
private fun UstozMessageItem(
    message: UstozMessage,
    onSourceClick: (Int, Int) -> Unit
) {
    val isStudent = message.author == MessageAuthor.STUDENT

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = if (isStudent) Arrangement.End else Arrangement.Start
    ) {
        if (!isStudent) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(TamhidEmerald),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        Surface(
            color = if (isStudent) TamhidEmerald else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (isStudent) 16.dp else 4.dp,
                bottomEnd = if (isStudent) 4.dp else 16.dp
            ),
            modifier = if (isStudent) {
                Modifier.widthIn(min = 40.dp, max = 320.dp)
            } else {
                Modifier
                    .weight(1f, fill = false)
                    .widthIn(max = 540.dp)
            }
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                if (!isStudent && message.isRagSource) {
                    Surface(
                        color = TamhidGold.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Text(
                            text = "KITOBDAN DALIL VA MANBA",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            fontWeight = FontWeight.Bold,
                            color = TamhidGold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Text(
                    text = message.content,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = if (isStudent) Color.White else MaterialTheme.colorScheme.onSurface
                )

                // Manbalar ro'yxati (agar mavjud bo'lsa)
                if (message.sources.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Kitob manbalari:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmerald
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    message.sources.forEach { source ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .clickable { onSourceClick(source.chapterNumber, source.pageNumber) }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Book,
                                    contentDescription = null,
                                    tint = TamhidEmerald,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "${source.chapterNumber}-bob, ${source.sectionNumber}-bo'lim (${source.pageNumber}-bet)",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TamhidEmerald
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
