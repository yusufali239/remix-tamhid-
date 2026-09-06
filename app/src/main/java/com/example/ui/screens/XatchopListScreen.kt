package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
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
import com.example.data.local.Bookmark
import com.example.data.model.Chapter
import com.example.ui.theme.AmiriQuranArabicStyle
import com.example.ui.theme.TamhidEmerald
import com.example.ui.theme.TamhidEmeraldDark
import com.example.ui.theme.TamhidSageContainer

// ============================================================================
// Notion-Style XatchopListScreen
// - Grouped by chapter: "Bob X: [Mavzu] (Y ta xatcho'p)"
// - Dynamic Search across translation, arabic quote, and personal notes
// - Swipe to delete / share actions
// - Click opens XatchopDetailScreen
// ============================================================================

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XatchopListScreen(
    bookmarks: List<Bookmark>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onBookmarkClick: (Bookmark) -> Unit,
    onDeleteBookmark: (String) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    chapters: List<Chapter> = emptyList()
) {
    // Group bookmarks by chapter
    val grouped = remember(bookmarks) {
        bookmarks.groupBy { it.chapterId }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Xatcho'plar va Qaydlar",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${bookmarks.size} ta saqlangan qoida",
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Search Input
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChange,
                    placeholder = { Text("Xatcho'p, matn yoki qaydlardan qidirish...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            tint = TamhidEmerald
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotBlank()) {
                            IconButton(onClick = { onSearchQueryChange("") }) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = "Tozalash",
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("xatchop_search_input"),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = TamhidEmerald,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                    )
                )
            }

            if (bookmarks.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(0.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .background(TamhidSageContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.BookmarkBorder,
                                    contentDescription = null,
                                    tint = TamhidEmerald,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = if (searchQuery.isNotBlank()) "Qidiruv bo'yicha hech narsa topilmadi" else "Hozircha xatcho'plar yo'q",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Darslik mutolaasi vaqtida istalgan abzas yonidagi xatcho'p belgisini bosib, uni shaxsiy kolleksiyangizga qo'shishingiz mumkin.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                // Render Grouped Chapters
                grouped.forEach { (chapterId, chapterBookmarks) ->
                    item(key = "header_$chapterId") {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = TamhidSageContainer.copy(alpha = 0.5f),
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Bob: $chapterId",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = TamhidEmeraldDark
                                )
                                Text(
                                    text = "${chapterBookmarks.size} ta xatcho'p",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = TamhidEmerald
                                )
                            }
                        }
                    }

                    items(chapterBookmarks, key = { it.id }) { bookmark ->
                        val chapter = chapters.find { it.id == bookmark.chapterId || it.chapterNumber.toString() == bookmark.chapterId }
                        val topicUz = chapter?.titleUz ?: "${bookmark.chapterId}-bob"
                        val topicAr = chapter?.titleAr ?: ""

                        NotionBookmarkCard(
                            bookmark = bookmark,
                            topicTitleUz = topicUz,
                            topicTitleAr = topicAr,
                            onClick = { onBookmarkClick(bookmark) },
                            onDelete = { onDeleteBookmark(bookmark.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NotionBookmarkCard(
    bookmark: Bookmark,
    topicTitleUz: String,
    topicTitleAr: String,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("bookmark_item_${bookmark.id}"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Header Row: Page pill + Topic titles + Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = TamhidSageContainer
                ) {
                    Text(
                        text = "${bookmark.pageNumber}-bet",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmeraldDark,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DeleteOutline,
                        contentDescription = "O'chirish",
                        tint = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Mavzu nomi arab va o'zbek tillarida (Topic title in Uzbek and Arabic)
            if (topicTitleAr.isNotBlank() || topicTitleUz.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                if (topicTitleAr.isNotBlank()) {
                    Text(
                        text = topicTitleAr,
                        style = AmiriQuranArabicStyle.copy(fontSize = 15.sp, lineHeight = 22.sp),
                        color = TamhidEmerald,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                if (topicTitleUz.isNotBlank()) {
                    Text(
                        text = topicTitleUz,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Arabic Preview
            if (bookmark.arabicQuote.isNotBlank()) {
                Text(
                    text = bookmark.arabicQuote,
                    style = AmiriQuranArabicStyle.copy(fontSize = 17.sp, lineHeight = 26.sp),
                    color = TamhidEmeraldDark,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = androidx.compose.ui.text.style.TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            // Uzbek Translation
            Text(
                text = bookmark.translation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            // Agar foydalanuvchi izohi (komment) bo'lsa - kartochka ichida ko'rish belgisi
            if (bookmark.userNote.isNotBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = TamhidSageContainer.copy(alpha = 0.35f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.EditNote,
                            contentDescription = null,
                            tint = TamhidEmerald,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Izoh mavjud (kartochka ichida ko'rish)",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 11.sp),
                            color = TamhidEmeraldDark
                        )
                    }
                }
            }
        }
    }
}
