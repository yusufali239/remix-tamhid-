package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.NoteAdd
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FormatSize
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
import com.example.data.model.ParagraphItem
import com.example.ui.components.TamhidQuoteBlock
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier,
    isMediumOrExpanded: Boolean = false
) {
    val context = LocalContext.current
    val lesson = viewModel.selectedLesson.collectAsState().value
    val chapter = viewModel.selectedChapter.collectAsState().value
    val bookmarks by viewModel.allBookmarks.collectAsState()
    val notes by viewModel.allNotes.collectAsState()
    val themeMode by viewModel.readerThemeMode.collectAsState()
    val fontScale by viewModel.fontSizeScale.collectAsState()
    val showArabicDefault by viewModel.showArabicDefault.collectAsState()

    var showSettingsSheet by remember { mutableStateOf(false) }
    var showAddNoteDialog by remember { mutableStateOf(false) }
    var noteInputText by remember { mutableStateOf("") }

    if (lesson == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Darslik matni tanlanmagan")
        }
        return
    }

    val (bgCol, surfCol, textCol) = when (themeMode) {
        ReadingThemeMode.LIGHT -> Triple(TamhidBgLight, TamhidSurfaceLight, TamhidTextPrimaryLight)
        ReadingThemeMode.SEPIA -> Triple(TamhidSepiaBg, TamhidSepiaSurface, TamhidSepiaTextPrimary)
        ReadingThemeMode.DARK -> Triple(TamhidDarkBg, TamhidDarkSurface, TamhidDarkTextPrimary)
        ReadingThemeMode.NIGHT -> Triple(TamhidNightBg, TamhidNightSurface, TamhidNightTextPrimary)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = lesson.titleUz,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = textCol,
                            maxLines = 1
                        )
                        Text(
                            text = "${chapter?.chapterNumber ?: 1}-bob • ${lesson.pdfStartPage}–${lesson.pdfEndPage} betlar",
                            style = MaterialTheme.typography.labelSmall,
                            color = TamhidEmerald
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateTo("darslik") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Ortga",
                            tint = textCol
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showAddNoteDialog = true }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.NoteAdd,
                            contentDescription = "Izoh qo'shish",
                            tint = textCol
                        )
                    }
                    IconButton(onClick = { showSettingsSheet = true }) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(TamhidEmerald.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Aa",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = TamhidEmerald
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = surfCol)
            )
        },
        containerColor = bgCol,
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        if (isMediumOrExpanded) {
            // Adaptive 2-pane layout for Medium/Expanded screens
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Left 65%: Main Reading Column
                Box(modifier = Modifier.weight(0.65f).fillMaxHeight()) {
                    ReadingContentList(
                        paragraphs = lesson.paragraphs,
                        chapterId = lesson.chapterId,
                        lessonTitle = lesson.titleUz,
                        fontScale = fontScale,
                        showArabicDefault = showArabicDefault,
                        textCol = textCol,
                        surfCol = surfCol,
                        bookmarks = bookmarks,
                        onToggleBookmark = { p ->
                            viewModel.toggleBookmark(
                                chapterId = lesson.chapterId,
                                sectionId = p.paragraphNumber.toString(),
                                arabicQuote = p.arabicText,
                                translation = p.uzbekTranslation,
                                pageNumber = p.pageNumber
                            )
                        }
                    )
                }

                // Divider line
                VerticalDivider(color = TamhidSageContainer, thickness = 1.dp)

                // Right 35%: Chapter Notes & Bookmarks Supporting Pane
                Column(
                    modifier = Modifier
                        .weight(0.35f)
                        .fillMaxHeight()
                        .background(surfCol)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Bob izohlari va qaydlar",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmerald
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    val chapterNotes = notes.filter { it.chapterId == lesson.chapterId }
                    if (chapterNotes.isEmpty()) {
                        Text(
                            text = "Ushbu bob bo'yicha shaxsiy izohlar hali kiritilmagan. Yuqoridagi qalamcha belgisi orqali yangi izoh qo'shishingiz mumkin.",
                            style = MaterialTheme.typography.bodySmall,
                            color = textCol.copy(alpha = 0.6f)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(chapterNotes) { note ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(containerColor = bgCol),
                                    elevation = CardDefaults.cardElevation(0.dp)
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Text(
                                            text = note.text,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = textCol
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.End
                                        ) {
                                            IconButton(
                                                onClick = { viewModel.deleteNote(note.id) },
                                                modifier = Modifier.size(24.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Close,
                                                    contentDescription = "O'chirish",
                                                    tint = MaterialTheme.colorScheme.error,
                                                    modifier = Modifier.size(16.dp)
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
        } else {
            // Compact 1-column layout
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ReadingContentList(
                    paragraphs = lesson.paragraphs,
                    chapterId = lesson.chapterId,
                    lessonTitle = lesson.titleUz,
                    fontScale = fontScale,
                    showArabicDefault = showArabicDefault,
                    textCol = textCol,
                    surfCol = surfCol,
                    bookmarks = bookmarks,
                    onToggleBookmark = { p ->
                        viewModel.toggleBookmark(
                            chapterId = lesson.chapterId,
                            sectionId = p.paragraphNumber.toString(),
                            arabicQuote = p.arabicText,
                            translation = p.uzbekTranslation,
                            pageNumber = p.pageNumber
                        )
                        Toast.makeText(context, "Xatcho'p holati yangilandi", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        // Reader Customization Sheet
        if (showSettingsSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSettingsSheet = false },
                containerColor = surfCol
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "Mutolaa sozlamalari",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = textCol
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Mutolaa mavzusi",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmerald
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        ReaderThemeRowOption(
                            title = "Yorug‘i",
                            subtitle = "Sariq/oq yorug' fon, to'q matn",
                            swatchBg = TamhidBgLight,
                            swatchBorder = TamhidBorderLight,
                            swatchTextColor = TamhidTextPrimaryLight,
                            selected = themeMode == ReadingThemeMode.LIGHT,
                            textColor = textCol,
                            onSelect = { viewModel.setReaderTheme(ReadingThemeMode.LIGHT) }
                        )

                        ReaderThemeRowOption(
                            title = "Sepiya",
                            subtitle = "Issiq krem fon, yumshoq to'q matn",
                            swatchBg = TamhidSepiaBg,
                            swatchBorder = TamhidSepiaContainer,
                            swatchTextColor = TamhidSepiaTextPrimary,
                            selected = themeMode == ReadingThemeMode.SEPIA,
                            textColor = textCol,
                            onSelect = { viewModel.setReaderTheme(ReadingThemeMode.SEPIA) }
                        )

                        ReaderThemeRowOption(
                            title = "Tungi",
                            subtitle = "To'q kulrang fon, yorug' matn",
                            swatchBg = TamhidDarkBg,
                            swatchBorder = TamhidDarkContainer,
                            swatchTextColor = TamhidDarkTextPrimary,
                            selected = themeMode == ReadingThemeMode.DARK,
                            textColor = textCol,
                            onSelect = { viewModel.setReaderTheme(ReadingThemeMode.DARK) }
                        )

                        ReaderThemeRowOption(
                            title = "Masjid (AMOLED)",
                            subtitle = "Toza qora #000000 fon, batareyani tejash",
                            swatchBg = Color.Black,
                            swatchBorder = Color(0xFF333333),
                            swatchTextColor = TamhidNightTextPrimary,
                            selected = themeMode == ReadingThemeMode.NIGHT,
                            textColor = textCol,
                            onSelect = { viewModel.setReaderTheme(ReadingThemeMode.NIGHT) }
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Matn hajmi: ${(fontScale * 100).toInt()}%",
                        style = MaterialTheme.typography.labelMedium,
                        color = textCol.copy(alpha = 0.7f)
                    )

                    Slider(
                        value = fontScale,
                        onValueChange = { viewModel.setFontSizeScale(it) },
                        valueRange = 0.85f..1.4f,
                        steps = 5,
                        colors = SliderDefaults.colors(
                            thumbColor = TamhidEmerald,
                            activeTrackColor = TamhidEmerald
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Arabcha asl nusxani doim ko'rsatish",
                            style = MaterialTheme.typography.bodyMedium,
                            color = textCol
                        )
                        Switch(
                            checked = showArabicDefault,
                            onCheckedChange = { viewModel.setShowArabicDefault(it) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = TamhidEmerald
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }

        // Add Note Dialog
        if (showAddNoteDialog) {
            AlertDialog(
                onDismissRequest = { showAddNoteDialog = false },
                title = {
                    Text(
                        text = "Yangi ilmiy qayd",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    OutlinedTextField(
                        value = noteInputText,
                        onValueChange = { noteInputText = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Ushbu bob bo'yicha shaxsiy izohingizni yozing...") },
                        maxLines = 6
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (noteInputText.isNotBlank()) {
                                viewModel.addNote(lesson.chapterId, noteInputText)
                                noteInputText = ""
                                showAddNoteDialog = false
                                Toast.makeText(context, "Izoh saqlandi", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text("Saqlash", color = TamhidEmerald)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showAddNoteDialog = false }) {
                        Text("Bekor qilish")
                    }
                },
                containerColor = surfCol,
                shape = RoundedCornerShape(24.dp)
            )
        }
    }
}

@Composable
private fun ReadingContentList(
    paragraphs: List<ParagraphItem>,
    chapterId: String,
    lessonTitle: String,
    fontScale: Float,
    showArabicDefault: Boolean,
    textCol: Color,
    surfCol: Color,
    bookmarks: List<com.example.data.local.Bookmark>,
    onToggleBookmark: (ParagraphItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 48.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(paragraphs, key = { it.paragraphNumber }) { p ->
            val bookmarkId = "${chapterId}_${p.paragraphNumber}"
            val isBookmarked = bookmarks.any { 
                it.id == bookmarkId || (it.chapterId == chapterId && it.sectionId == p.paragraphNumber.toString()) 
            }

            ReaderParagraphBlock(
                paragraph = p,
                fontScale = fontScale,
                showArabicDefault = showArabicDefault,
                textColor = textCol,
                surfaceColor = surfCol,
                isBookmarked = isBookmarked,
                onBookmarkToggle = { onToggleBookmark(p) }
            )
        }
    }
}

@Composable
private fun ReaderParagraphBlock(
    paragraph: ParagraphItem,
    fontScale: Float,
    showArabicDefault: Boolean,
    textColor: Color,
    surfaceColor: Color,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit
) {
    var isArabicExpanded by remember { mutableStateOf(showArabicDefault) }
    var isFootnoteExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // If paragraph contains significant quote from Imam al-Lamishi, render with TamhidQuoteBlock
    val isImamQuote = paragraph.arabicText.contains("قال الشيخ") ||
            paragraph.arabicText.contains("قال الإمام") ||
            paragraph.arabicText.contains("حَقَائِقُ الأَشْيَاءِ") ||
            paragraph.arabicText.contains("أَسْبَابُ العِلْمِ")

    if (isImamQuote) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TamhidQuoteBlock(
                arabicText = paragraph.arabicText,
                translation = paragraph.uzbekTranslation,
                pageNumber = paragraph.pageNumber,
                sourceTitle = "Imom Abu as-Sano al-Lomishiy matni"
            )

            // Page number footer
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${paragraph.pageNumber}-bet bosma nashr",
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.5f)
                )

                IconButton(
                    onClick = onBookmarkToggle,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Xatcho'p",
                        tint = if (isBookmarked) TamhidEmerald else textColor.copy(alpha = 0.4f),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    } else {
        // Standard academic paragraph block conforming to Clean Minimalism 2.0:
        // - Academic translation shown by default
        // - Arabic hidden with smooth AnimatedVisibility toggle via [الأصل] button
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = surfaceColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Header with paragraph number, [الأصل] button, and bookmark icon
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "§ ${paragraph.paragraphNumber}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmerald
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // [الأصل] Button - Toggles Arabic text smoothly
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isArabicExpanded) TamhidSageContainer else surfaceColor)
                                .clickable { isArabicExpanded = !isArabicExpanded }
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "الأصل",
                                style = AmiriQuranArabicStyle.copy(fontSize = 14.sp, lineHeight = 20.sp),
                                fontWeight = FontWeight.Bold,
                                color = TamhidEmerald
                            )
                        }

                        if (!paragraph.footnotes.isNullOrBlank()) {
                            Spacer(modifier = Modifier.width(6.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(if (isFootnoteExpanded) TamhidSageContainer else surfaceColor)
                                    .clickable { isFootnoteExpanded = !isFootnoteExpanded }
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "Izoh",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isFootnoteExpanded) TamhidEmeraldDark else textColor.copy(alpha = 0.6f)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        IconButton(
                            onClick = onBookmarkToggle,
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Outlined.Bookmark else Icons.Outlined.BookmarkBorder,
                                contentDescription = "Xatcho'p",
                                tint = if (isBookmarked) TamhidEmerald else textColor.copy(alpha = 0.4f),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }

                // Smoothly animated Arabic text container
                AnimatedVisibility(
                    visible = isArabicExpanded,
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(TamhidSageContainer.copy(alpha = 0.4f))
                            .padding(14.dp)
                    ) {
                        Text(
                            text = paragraph.arabicText,
                            style = AmiriQuranArabicStyle.copy(
                                fontSize = (22 * fontScale).sp,
                                lineHeight = (32 * fontScale).sp
                            ),
                            color = TamhidEmeraldDark,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Right
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Academic Uzbek translation (shown by default)
                Text(
                    text = paragraph.uzbekTranslation,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = (16 * fontScale).sp,
                        lineHeight = (24 * fontScale).sp
                    ),
                    color = textColor
                )

                if (!paragraph.footnotes.isNullOrBlank()) {
                    AnimatedVisibility(
                        visible = isFootnoteExpanded,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(TamhidSageContainer.copy(alpha = 0.35f))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Ilmiy izoh: ${paragraph.footnotes}",
                                style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                                color = TamhidEmeraldDark
                            )
                        }
                    }
                }

                // Footer: "X-bet bosma nashr"
                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider(color = TamhidSageContainer.copy(alpha = 0.5f), thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${paragraph.pageNumber}-bet bosma nashr",
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.5f)
                )
            }
        }
    }
}

@Composable
private fun ReaderThemeRowOption(
    title: String,
    subtitle: String,
    swatchBg: Color,
    swatchBorder: Color,
    swatchTextColor: Color,
    selected: Boolean,
    textColor: Color,
    onSelect: () -> Unit
) {
    Surface(
        onClick = onSelect,
        shape = RoundedCornerShape(14.dp),
        color = if (selected) TamhidSageContainer.copy(alpha = 0.45f) else Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RadioButton(
                selected = selected,
                onClick = null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = TamhidEmerald,
                    unselectedColor = textColor.copy(alpha = 0.4f)
                )
            )

            // Visual circular color swatch preview
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(swatchBg)
                    .border(1.dp, swatchBorder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "A",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = swatchTextColor
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                    color = textColor
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    color = textColor.copy(alpha = 0.7f)
                )
            }
        }
    }
}
