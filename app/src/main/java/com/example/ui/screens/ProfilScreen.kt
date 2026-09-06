package com.example.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.outlined.Note
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.*
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
import androidx.core.content.ContextCompat
import com.example.data.local.AppThemeMode
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel
import java.util.Locale

@Composable
fun ProfilScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val studentName by viewModel.studentName.collectAsState()
    val userAge by viewModel.userAge.collectAsState()
    val appThemeMode by viewModel.appThemeMode.collectAsState()
    val bookmarks by viewModel.allBookmarks.collectAsState()
    val notes by viewModel.allNotes.collectAsState()
    val flashcardProgress by viewModel.allFlashcardProgress.collectAsState()
    val currentStreak by viewModel.currentStreak.collectAsState()
    val maxStreak by viewModel.maxStreak.collectAsState()

    var showProfileEditDialog by remember { mutableStateOf(false) }
    var newNameText by remember { mutableStateOf(studentName) }
    var newAgeText by remember { mutableStateOf(userAge.toString()) }

    val context = LocalContext.current
    val reminderEnabled by viewModel.reminderEnabled.collectAsState()
    val reminderHour by viewModel.reminderHour.collectAsState()
    val reminderMinute by viewModel.reminderMinute.collectAsState()

    var showPermissionRationaleDialog by remember { mutableStateOf(false) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.setReminderSettings(true, reminderHour, reminderMinute)
        } else {
            viewModel.setReminderSettings(false, reminderHour, reminderMinute)
            showPermissionRationaleDialog = true
        }
    }

    val onToggleReminder: (Boolean) -> Unit = { enabled ->
        if (enabled) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val hasPermission = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
                if (hasPermission) {
                    viewModel.setReminderSettings(true, reminderHour, reminderMinute)
                } else {
                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            } else {
                viewModel.setReminderSettings(true, reminderHour, reminderMinute)
            }
        } else {
            viewModel.setReminderSettings(false, reminderHour, reminderMinute)
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Foydalanuvchi profili kartochkasi
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(TamhidSageContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = TamhidEmerald,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "$studentName, $userAge yosh",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            IconButton(
                                onClick = {
                                    newNameText = studentName
                                    newAgeText = userAge.toString()
                                    showProfileEditDialog = true
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    contentDescription = "Ismni tahrirlash",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }

                        Text(
                            text = "Madrasa tolibi • «At-Tamhid» kursi",
                            style = MaterialTheme.typography.labelSmall,
                            color = TamhidEmerald
                        )
                    }
                }
            }
        }

        // 1b. Streak (Davomiylik) hisoblagichi kartochkasi
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(TamhidGoldContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🔥",
                                fontSize = 24.sp
                            )
                        }

                        Column {
                            Text(
                                text = if (currentStreak > 0) "$currentStreak kunlik streak!" else "Streakni boshlang!",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (currentStreak > 0) "Ketma-ket dars o'qish davomiyligi" else "Bugun dars o'qib, olovni yoqing",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = TamhidSageContainer
                    ) {
                        Text(
                            text = "Rekord: $maxStreak",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = TamhidEmerald
                        )
                    }
                }
            }
        }

        // 2. Ko‘rinish (Global ilova mavzusi: Avtomatik / Yorug‘ / Tungi)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Palette,
                            contentDescription = null,
                            tint = TamhidEmerald,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Ko‘rinish",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        ThemeRadioOption(
                            title = "Avtomatik",
                            subtitle = "Tizim sozlamalari asosida",
                            selected = appThemeMode == AppThemeMode.AUTO,
                            onClick = { viewModel.setAppThemeMode(AppThemeMode.AUTO) }
                        )
                        ThemeRadioOption(
                            title = "Yorug‘",
                            subtitle = "Kunduzgi yorug‘ interfeys",
                            selected = appThemeMode == AppThemeMode.LIGHT,
                            onClick = { viewModel.setAppThemeMode(AppThemeMode.LIGHT) }
                        )
                        ThemeRadioOption(
                            title = "Tungi",
                            subtitle = "Qorong‘i qulay interfeys",
                            selected = appThemeMode == AppThemeMode.DARK,
                            onClick = { viewModel.setAppThemeMode(AppThemeMode.DARK) }
                        )
                    }
                }
            }
        }

        // 3. Ta'limiy yutuqlar (Study Analytics)
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.School,
                            contentDescription = null,
                            tint = TamhidEmerald,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Ta'limiy yutuqlar",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        StatPill(
                            title = "xatcho‘p",
                            value = "${bookmarks.size}",
                            icon = Icons.Outlined.Bookmark,
                            modifier = Modifier.weight(1f)
                        )
                        StatPill(
                            title = "qayd",
                            value = "${notes.size}",
                            icon = Icons.AutoMirrored.Outlined.Note,
                            modifier = Modifier.weight(1f)
                        )
                        StatPill(
                            title = "SRS karta",
                            value = "${flashcardProgress.size}",
                            icon = Icons.Outlined.School,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // 3b. AI-powered Kundalik Dars Eslatmalari
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(TamhidSageContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.NotificationsActive,
                                    contentDescription = null,
                                    tint = TamhidEmerald,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "Kundalik dars eslatmasi",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "AI-Ustoz xabarnomasi",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TamhidEmerald
                                )
                            }
                        }

                        Switch(
                            checked = reminderEnabled,
                            onCheckedChange = onToggleReminder,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = TamhidEmerald
                            )
                        )
                    }

                    Text(
                        text = "AI har kuni sizning mutolaa natijalaringiz (o'qilgan bob va foiz) asosida shaxsiy, iliq eslatma xabari yuboradi.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )

                    if (reminderEnabled) {
                        HorizontalDivider(
                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                            thickness = 0.8.dp
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Eslatma vaqti",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Har kuni belgilangan vaqtda",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            FilledTonalButton(
                                onClick = {
                                    android.app.TimePickerDialog(
                                        context,
                                        { _, h, m ->
                                            viewModel.setReminderSettings(true, h, m)
                                        },
                                        reminderHour,
                                        reminderMinute,
                                        true
                                    ).show()
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = TamhidSageContainer,
                                    contentColor = TamhidEmerald
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Schedule,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = String.format(Locale.getDefault(), "%02d:%02d", reminderHour, reminderMinute),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }

                    OutlinedButton(
                        onClick = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                                ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
                            ) {
                                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            } else {
                                viewModel.triggerTestReminder()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Send,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Eslatmani hozir sinab ko'rish (Test)", style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }

        // 4. Zaxira nusxa kartochkasi (Eksport va Tiklash ekraniga o'tish)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(22.dp))
                    .clickable { viewModel.navigateTo("zaxira_nusxa") },
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(TamhidSageContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CloudSync,
                            contentDescription = null,
                            tint = TamhidEmerald,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Zaxira nusxa",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Ma'lumotlarni saqlash va tiklash",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "O'tish",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        // 5. Mening xatcho'plarim ro'yxati
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mening xatcho'plarim (${bookmarks.size})",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )

                TextButton(onClick = { viewModel.navigateTo("xatchop_list") }) {
                    Text("Barchasi (Notion)", color = TamhidEmerald, fontWeight = FontWeight.Bold)
                }
            }
        }

        if (bookmarks.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Hozircha xatcho'plar mavjud emas. Mutolaa vaqtida xatcho'p belgisini bosib saqlashingiz mumkin.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } else {
            items(bookmarks, key = { it.id }) { b ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.openBookmarkDetail(b) },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${b.pageNumber}-bet • ${b.chapterId}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = TamhidEmerald
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = b.translation.ifBlank { b.arabicQuote },
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 2,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        IconButton(onClick = { viewModel.deleteBookmark(b.id) }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "O'chirish",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }

    // Profile Edit Dialog (Name & Age)
    if (showProfileEditDialog) {
        val ageNum = newAgeText.toIntOrNull()
        val isAgeValid = ageNum != null && ageNum in 12..80
        AlertDialog(
            onDismissRequest = { showProfileEditDialog = false },
            title = {
                Text(
                    text = "Talaba profilini tahrirlash",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    OutlinedTextField(
                        value = newNameText,
                        onValueChange = { newNameText = it },
                        label = { Text("Talaba ismi") },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newAgeText,
                        onValueChange = { newAgeText = it.filter { ch -> ch.isDigit() }.take(2) },
                        label = { Text("Yoshi (12 - 80)") },
                        singleLine = true,
                        isError = !isAgeValid && newAgeText.isNotBlank(),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val age = ageNum
                        if (newNameText.isNotBlank() && age != null && age in 12..80) {
                            viewModel.completeOnboarding(newNameText.trim(), age)
                            showProfileEditDialog = false
                        }
                    }
                ) {
                    Text("Saqlash", color = TamhidEmerald, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showProfileEditDialog = false }) {
                    Text("Bekor qilish")
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(24.dp)
        )
    }

    if (showPermissionRationaleDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionRationaleDialog = false },
            title = {
                Text(
                    text = "Bildirishnomalarga ruxsat zarur",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "Kundalik aqida darslarini unutmasligingiz uchun eslatma yuborishga ruxsat zarur. Qurilma sozlamalaridan ushbu ruxsatni yoqishingiz mumkin.",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(onClick = { showPermissionRationaleDialog = false }) {
                    Text("Tushunarli", color = TamhidEmerald, fontWeight = FontWeight.Bold)
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(24.dp)
        )
    }
}

@Composable
private fun ThemeRadioOption(
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        color = if (selected) TamhidSageContainer.copy(alpha = 0.5f) else Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RadioButton(
                selected = selected,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = TamhidEmerald,
                    unselectedColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun StatPill(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(TamhidSageContainer.copy(alpha = 0.45f))
            .padding(horizontal = 10.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TamhidEmerald,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}
