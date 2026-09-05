package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TamhidEmerald
import com.example.ui.theme.TamhidSageContainer
import com.example.ui.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZaxiraNusxaScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val lastBackupTimestamp by viewModel.lastBackupTime.collectAsState()
    val bookmarks by viewModel.allBookmarks.collectAsState()
    val notes by viewModel.allNotes.collectAsState()
    val flashcards by viewModel.allFlashcardProgress.collectAsState()
    val readingProgress by viewModel.allReadingProgress.collectAsState()

    var showExportDialog by remember { mutableStateOf(false) }
    var exportedJsonString by remember { mutableStateOf("") }
    var showImportDialog by remember { mutableStateOf(false) }
    var importJsonText by remember { mutableStateOf("") }

    val formattedDate = remember(lastBackupTimestamp) {
        if (lastBackupTimestamp != null && lastBackupTimestamp!! > 0L) {
            val sdf = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
            sdf.format(Date(lastBackupTimestamp!!))
        } else {
            null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Zaxira nusxa",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateTo("profil") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Ortga",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Hero Card: Ma'lumotlarni saqlash tushuntirishi
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(TamhidSageContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.CloudSync,
                                contentDescription = null,
                                tint = TamhidEmerald,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Ma'lumotlarni saqlash",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Xavfsiz mahalliy arxivlash",
                                style = MaterialTheme.typography.labelSmall,
                                color = TamhidEmerald
                            )
                        }
                    }

                    Text(
                        text = "Ma'lumotlaringizni yo‘qotib qo‘ymaslik uchun zaxira nusxa yaratishingiz mumkin.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 22.sp
                    )

                    Text(
                        text = "Ilovadagi barcha xatcho‘plaringiz, shaxsiy qaydlar, mutolaa foizlari va SRS xotira kartalari bitta fayl sifatida saqlanadi yoki boshqa qurilmaga ko‘chiriladi.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                }
            }

            // 2. Status Card: So'nggi zaxira nusxasi
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (formattedDate != null) {
                        TamhidSageContainer.copy(alpha = 0.35f)
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                ),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = if (formattedDate != null) Icons.Default.CloudDone else Icons.Default.Info,
                        contentDescription = null,
                        tint = if (formattedDate != null) TamhidEmerald else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "So'nggi zaxira nusxa:",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = formattedDate ?: "Hozircha zaxira nusxa yaratilmagan",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (formattedDate != null) TamhidEmerald else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // 3. Action Buttons: [ Zaxira nusxa yaratish ] & [ Zaxiradan tiklash ]
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Primary button: Zaxira nusxa yaratish
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val json = viewModel.exportDataJson()
                            exportedJsonString = json
                            showExportDialog = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TamhidEmerald,
                        contentColor = Color.White
                    ),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CloudDownload,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Zaxira nusxa yaratish",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Secondary button: Zaxiradan tiklash
                OutlinedButton(
                    onClick = { showImportDialog = true },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = TamhidEmerald
                    ),
                    border = ButtonDefaults.outlinedButtonBorder(true)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CloudUpload,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Zaxiradan tiklash",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // 4. Data Summary: Nimalar saqlanadi
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Zaxiraga kiritiladigan ma'lumotlar:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    DataSummaryItem(
                        icon = Icons.Outlined.Bookmark,
                        title = "Xatcho‘plar",
                        count = "${bookmarks.size} ta"
                    )
                    DataSummaryItem(
                        icon = Icons.Outlined.EditNote,
                        title = "Shaxsiy qaydlar",
                        count = "${notes.size} ta"
                    )
                    DataSummaryItem(
                        icon = Icons.Outlined.School,
                        title = "SRS xotira kartalari",
                        count = "${flashcards.size} ta"
                    )
                    DataSummaryItem(
                        icon = Icons.Outlined.MenuBook,
                        title = "Mutolaa borishi",
                        count = "${readingProgress.size} ta bob"
                    )
                }
            }
        }
    }

    // Export Dialog / Sheet
    if (showExportDialog) {
        AlertDialog(
            onDismissRequest = { showExportDialog = false },
            title = {
                Text(
                    text = "Zaxira nusxa yaratildi",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Barcha ma'lumotlaringiz xavfsiz JSON formatida tayyorlandi. Uni xotiraga nusxalashingiz yoki Telegram/fayl sifatida saqlab qo'yishingiz mumkin:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 120.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(TamhidSageContainer.copy(alpha = 0.4f))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = exportedJsonString.take(220) + "...\n(to'liq ma'lumotlar nusxalanadi)",
                            style = MaterialTheme.typography.labelSmall,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("At-Tamhid Backup", exportedJsonString)
                        clipboard.setPrimaryClip(clip)
                        Toast.makeText(context, "Zaxira matni nusxalandi!", Toast.LENGTH_SHORT).show()
                        showExportDialog = false
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TamhidEmerald)
                ) {
                    Text("Nusxa olish")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        val sendIntent = Intent(Intent.ACTION_SEND).apply {
                            putExtra(Intent.EXTRA_TEXT, exportedJsonString)
                            type = "application/json"
                        }
                        context.startActivity(Intent.createChooser(sendIntent, "Zaxira nusxani ulashish"))
                    }
                ) {
                    Text("Ulashish", color = TamhidEmerald)
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(24.dp)
        )
    }

    // Import Dialog
    if (showImportDialog) {
        AlertDialog(
            onDismissRequest = { showImportDialog = false },
            title = {
                Text(
                    text = "Zaxiradan tiklash",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Avval saqlab olingan zaxira JSON matnini quyidagi maydonga kiriting:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    OutlinedTextField(
                        value = importJsonText,
                        onValueChange = { importJsonText = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        placeholder = { Text("{\"version\": 2, ...}") },
                        maxLines = 6,
                        shape = RoundedCornerShape(14.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (importJsonText.isNotBlank()) {
                            coroutineScope.launch {
                                val success = viewModel.importDataJson(importJsonText)
                                if (success) {
                                    Toast.makeText(context, "Ma'lumotlar muvaffaqiyatli tiklandi!", Toast.LENGTH_LONG).show()
                                    importJsonText = ""
                                    showImportDialog = false
                                } else {
                                    Toast.makeText(context, "Xatolik: JSON formati noto'g'ri", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TamhidEmerald)
                ) {
                    Text("Tiklash")
                }
            },
            dismissButton = {
                TextButton(onClick = { showImportDialog = false }) {
                    Text("Bekor qilish")
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(24.dp)
        )
    }
}

@Composable
private fun DataSummaryItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    count: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = TamhidEmerald,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Text(
            text = count,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
