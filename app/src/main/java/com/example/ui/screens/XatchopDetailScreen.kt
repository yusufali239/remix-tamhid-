package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.Bookmark
import com.example.ui.theme.AmiriQuranArabicStyle
import com.example.ui.theme.TamhidEmerald
import com.example.ui.theme.TamhidEmeraldDark
import com.example.ui.theme.TamhidSageContainer
import java.util.Locale

// ============================================================================
// Notion-Style XatchopDetailScreen (Full Bookmark View)
// - Full Arabic original in Amiri Quran 24sp
// - Translation
// - Interactive Editable Qayd (User Note)
// - TTS Audio voice readout
// - Copy Arabic, Share, Jump to Source in Book
// ============================================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XatchopDetailScreen(
    bookmark: Bookmark?,
    onBack: () -> Unit,
    onSaveNote: (bookmarkId: String, newNote: String) -> Unit,
    onNavigateToSource: (Bookmark) -> Unit,
    onDeleteBookmark: (bookmarkId: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    if (bookmark == null) {
        Box(
            modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text("Xatcho'p topilmadi", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    var userNoteText by remember(bookmark.id) { mutableStateOf(bookmark.userNote) }
    var isNoteSaved by remember { mutableStateOf(false) }
    var showDeleteConfirm by remember { mutableStateOf(false) }

    // TTS Setup
    var tts: TextToSpeech? by remember { mutableStateOf(null) }
    var isTtsReady by remember { mutableStateOf(false) }
    var isSpeaking by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        var ttsInstance: TextToSpeech? = null
        ttsInstance = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val res = ttsInstance?.setLanguage(Locale.forLanguageTag("ar"))
                if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                    ttsInstance?.language = Locale.getDefault()
                }
                isTtsReady = true
            }
        }
        tts = ttsInstance
        onDispose {
            ttsInstance?.stop()
            ttsInstance?.shutdown()
        }
    }

    val displayArabic = bookmark.arabicFullText.ifBlank { bookmark.arabicQuote }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Xatcho'p tafsiloti",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${bookmark.pageNumber}-bet • ${bookmark.chapterId}",
                            style = MaterialTheme.typography.labelSmall,
                            color = TamhidEmerald
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Ortga"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showDeleteConfirm = true }) {
                        Icon(
                            imageVector = Icons.Outlined.DeleteOutline,
                            contentDescription = "Xatcho'pni o'chirish",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
         containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Full Arabic Original Card (Amiri 24sp)
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
                            text = "المتن العربي (Asl matn)",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = TamhidEmerald
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // TTS Audio Button
                            IconButton(
                                onClick = {
                                    if (isSpeaking) {
                                        tts?.stop()
                                        isSpeaking = false
                                    } else {
                                        val textToSpeak = displayArabic.ifBlank { bookmark.translation }
                                        tts?.speak(textToSpeak, TextToSpeech.QUEUE_FLUSH, null, "xatchop_tts")
                                        isSpeaking = true
                                    }
                                },
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(TamhidSageContainer),
                                enabled = isTtsReady
                            ) {
                                Icon(
                                    imageVector = if (isSpeaking) Icons.Outlined.Stop else Icons.AutoMirrored.Outlined.VolumeUp,
                                    contentDescription = "Ovozli o'qish",
                                    tint = TamhidEmeraldDark,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            // Copy Arabic Button
                            IconButton(
                                onClick = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    val clip = ClipData.newPlainText("At-Tamhid Arabic", displayArabic)
                                    clipboard.setPrimaryClip(clip)
                                    Toast.makeText(context, "Arabcha matn nusxalandi!", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(CircleShape)
                                    .background(TamhidSageContainer)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.ContentCopy,
                                    contentDescription = "Nusxa olish",
                                    tint = TamhidEmeraldDark,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Arabic Text Box with generous styling
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(TamhidSageContainer.copy(alpha = 0.25f))
                            .padding(18.dp)
                    ) {
                        Text(
                            text = displayArabic,
                            style = AmiriQuranArabicStyle.copy(
                                fontSize = 24.sp,
                                lineHeight = 42.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.End,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            // 2. Academic Translation Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "O'zbekcha tarjima va izoh",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmerald
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = bookmark.translation,
                        style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 24.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // 3. Notion-Style Interactive User Note (Qayd)
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
                            text = "Mening qaydim (Qayd)",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = TamhidEmerald
                        )

                        if (isNoteSaved) {
                            Text(
                                text = "Saqlandi ✓",
                                style = MaterialTheme.typography.labelSmall,
                                color = TamhidEmerald
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = userNoteText,
                        onValueChange = {
                            userNoteText = it
                            isNoteSaved = false
                        },
                        placeholder = {
                            Text("Ushbu masala bo'yicha shaxsiy xulosa va tushuntirishlaringizni yozing...")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp)
                            .testTag("xatchop_user_note_input"),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = TamhidEmerald,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            onSaveNote(bookmark.id, userNoteText.trim())
                            isNoteSaved = true
                            Toast.makeText(context, "Qayd saqlandi", Toast.LENGTH_SHORT).show()
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = TamhidEmerald),
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Qaydni saqlash", style = MaterialTheme.typography.labelMedium)
                    }
                }
            }

            // 4. Primary Actions (Jump to Book Source + Share)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Navigate to Source in Book
                Button(
                    onClick = { onNavigateToSource(bookmark) },
                    modifier = Modifier.weight(1f).height(50.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TamhidEmerald,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.MenuBook,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Kitobda ochish",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Share Button
                OutlinedButton(
                    onClick = {
                        val shareText = """
«Kitob at-Tamhid li-qavoid at-tavhid»
Imom Abu as-Sano Mahmud ibn Zayd al-Lomishiy

$displayArabic

Tarjima:
${bookmark.translation}

Manba: ${bookmark.chapterId}, ${bookmark.pageNumber}-bet
                        """.trimIndent()

                        val sendIntent = Intent(Intent.ACTION_SEND).apply {
                            putExtra(Intent.EXTRA_TEXT, shareText)
                            type = "text/plain"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Xatcho'pni ulashish"))
                    },
                    modifier = Modifier.weight(1f).height(50.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TamhidEmerald)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Ulashish",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    // Delete Confirmation Dialog
    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = { Text("Xatcho'pni o'chirish") },
            text = { Text("Ushbu xatcho'p va unga biriktirilgan qaydni ro'yxatdan o'chirmoqchimisiz?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirm = false
                        onDeleteBookmark(bookmark.id)
                        onBack()
                    }
                ) {
                    Text("O'chirish", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirm = false }) {
                    Text("Bekor qilish")
                }
            },
            shape = RoundedCornerShape(24.dp)
        )
    }
}
