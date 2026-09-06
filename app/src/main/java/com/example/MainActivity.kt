package com.example

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.AudioPlayerManager
import com.example.ui.components.AudioFullPlayerDialog
import com.example.ui.components.AudioMiniPlayer
import com.example.ui.screens.*
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UstozViewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val ustozViewModel: UstozViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleWidgetIntent(intent)
        com.example.data.worker.StudyReminderWorker.createNotificationChannel(this)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            val appThemeMode by mainViewModel.appThemeMode.collectAsState()
            GlobalAppTheme(appThemeMode = appThemeMode) {
                AdaptiveTamhidApp(
                    mainViewModel = mainViewModel,
                    ustozViewModel = ustozViewModel,
                    widthSizeClass = windowSizeClass.widthSizeClass
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleWidgetIntent(intent)
    }

    private fun handleWidgetIntent(intent: Intent?) {
        if (intent == null) return
        val navTarget = intent.getStringExtra("EXTRA_NAVIGATE_TO")
        if (navTarget == "reader") {
            val chapterId = intent.getStringExtra("EXTRA_CHAPTER_ID") ?: "ch_01"
            val pageNumber = intent.getIntExtra("EXTRA_PAGE_NUMBER", 1)
            mainViewModel.openChapterParagraphInReader(chapterId, pageNumber)
        }
    }
}

data class NavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun AdaptiveTamhidApp(
    mainViewModel: MainViewModel,
    ustozViewModel: UstozViewModel,
    widthSizeClass: WindowWidthSizeClass
) {
    val isOnboardingDone by mainViewModel.isOnboardingDone.collectAsState()
    val currentScreen by mainViewModel.currentScreen.collectAsState()

    val context = LocalContext.current
    val audioPlayerManager = remember { AudioPlayerManager.getInstance(context) }
    val activeAudio by audioPlayerManager.currentAudio.collectAsState()
    val isAudioPlaying by audioPlayerManager.isPlaying.collectAsState()
    val isAudioBuffering by audioPlayerManager.isBuffering.collectAsState()
    val currentAudioPos by audioPlayerManager.currentPositionMs.collectAsState()
    val audioDuration by audioPlayerManager.durationMs.collectAsState()
    val showFullPlayer by audioPlayerManager.showFullPlayer.collectAsState()

    // 1. Mandatory First-Run Onboarding Flow (Name & Age 12-80)
    if (!isOnboardingDone) {
        OnboardingScreen(
            onComplete = { name, age ->
                mainViewModel.completeOnboarding(name, age)
            }
        )
        return
    }

    // Android Hardware Back Navigation Handler
    BackHandler(enabled = currentScreen != "darslik") {
        when (currentScreen) {
            "zaxira_nusxa" -> mainViewModel.navigateTo("profil")
            "reader" -> mainViewModel.navigateTo("darslik")
            "quiz_session" -> mainViewModel.navigateTo("darslik")
            "xatchop_detail" -> mainViewModel.navigateTo("xatchop_list")
            "xatchop_list" -> mainViewModel.navigateTo("profil")
            "video_darslar" -> mainViewModel.navigateTo("darslik")
            "lugatlar" -> mainViewModel.navigateTo("darslik")
            "terminologiya" -> mainViewModel.navigateTo("lugatlar")
            "flashcards" -> mainViewModel.navigateTo("darslik")
            else -> mainViewModel.navigateTo("darslik")
        }
    }

    // Top-Level Navigation items: [Darslik, Video Darslar, Lug'atlar, AI Ustoz, Profil]
    val navItems = remember {
        listOf(
            NavItem("darslik", "Darslik", Icons.Filled.AutoStories, Icons.Outlined.AutoStories),
            NavItem("video_darslar", "Darslar", Icons.Filled.PlayCircle, Icons.Outlined.PlayCircle),
            NavItem("lugatlar", "Lug'at", Icons.AutoMirrored.Filled.MenuBook, Icons.AutoMirrored.Outlined.MenuBook),
            NavItem("ai_ustoz", "AI Ustoz", Icons.Filled.Psychology, Icons.Outlined.Psychology),
            NavItem("profil", "Profil", Icons.Filled.Person, Icons.Outlined.Person)
        )
    }

    val isTopLevelScreen = currentScreen in listOf("darslik", "video_darslar", "lugatlar", "ai_ustoz", "profil")
    val isMediumOrExpanded = widthSizeClass != WindowWidthSizeClass.Compact

    if (isMediumOrExpanded) {
        // Medium (600-840dp) & Expanded (>840dp): NavigationRail on the left
        Row(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
            if (isTopLevelScreen) {
                NavigationRail(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = TamhidEmerald,
                    modifier = Modifier.fillMaxHeight(),
                    header = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
                        ) {
                            Text(
                                text = "التمهيد",
                                style = AmiriQuranArabicStyle.copy(fontSize = 20.sp),
                                fontWeight = FontWeight.Bold,
                                color = TamhidEmerald
                            )
                        }
                    }
                ) {
                    navItems.forEach { item ->
                        val selected = currentScreen == item.route
                        NavigationRailItem(
                            selected = selected,
                            onClick = { mainViewModel.navigateTo(item.route) },
                            icon = {
                                Icon(
                                    imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.label
                                )
                            },
                            label = { Text(item.label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal) },
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = TamhidEmerald,
                                selectedTextColor = TamhidEmerald,
                                indicatorColor = TamhidSageContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }

            // Screen content centered with maxWidth 760dp for Expanded
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(WindowInsets.systemBars.asPaddingValues()),
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .then(
                            if (widthSizeClass == WindowWidthSizeClass.Expanded && currentScreen != "reader") {
                                Modifier.widthIn(max = 760.dp)
                            } else Modifier
                        )
                ) {
                    ScreenContent(
                        currentScreen = currentScreen,
                        mainViewModel = mainViewModel,
                        ustozViewModel = ustozViewModel,
                        isMediumOrExpanded = true
                    )

                    // Persistent Mini Player for Medium/Expanded screens
                    if (activeAudio != null) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 16.dp)
                                .widthIn(max = 560.dp)
                        ) {
                            AudioMiniPlayer(
                                audio = activeAudio!!,
                                isPlaying = isAudioPlaying,
                                isBuffering = isAudioBuffering,
                                currentPos = currentAudioPos,
                                duration = audioDuration,
                                onPlayPause = { audioPlayerManager.togglePlayPause() },
                                onClick = { audioPlayerManager.expandFullPlayer() },
                                onClose = { audioPlayerManager.stopAndClose() }
                            )
                        }
                    }
                }
            }
        }
    } else {
        // Compact (<600dp): Clean Minimalism 2.0 Scaffold with 4-item NavigationBar
        Scaffold(
            bottomBar = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Persistent Mini Player across all tabs
                    if (activeAudio != null) {
                        AudioMiniPlayer(
                            audio = activeAudio!!,
                            isPlaying = isAudioPlaying,
                            isBuffering = isAudioBuffering,
                            currentPos = currentAudioPos,
                            duration = audioDuration,
                            onPlayPause = { audioPlayerManager.togglePlayPause() },
                            onClick = { audioPlayerManager.expandFullPlayer() },
                            onClose = { audioPlayerManager.stopAndClose() }
                        )
                    }

                    if (isTopLevelScreen) {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 0.dp,
                            modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        ) {
                            navItems.forEach { item ->
                                val selected = currentScreen == item.route
                                NavigationBarItem(
                                    selected = selected,
                                    onClick = { mainViewModel.navigateTo(item.route) },
                                    icon = {
                                        Icon(
                                            imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.label
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = item.label,
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = TamhidEmerald,
                                        selectedTextColor = TamhidEmerald,
                                        indicatorColor = TamhidSageContainer,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }
                        }
                    }
                }
            },
            containerColor = MaterialTheme.colorScheme.background
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ScreenContent(
                    currentScreen = currentScreen,
                    mainViewModel = mainViewModel,
                    ustozViewModel = ustozViewModel,
                    isMediumOrExpanded = false
                )
            }
        }
    }

    // Modal Full Audio Player Dialog
    if (showFullPlayer && activeAudio != null) {
        AudioFullPlayerDialog(
            audioPlayerManager = audioPlayerManager,
            onDismiss = { audioPlayerManager.collapseFullPlayer() }
        )
    }
}

@Composable
private fun ScreenContent(
    currentScreen: String,
    mainViewModel: MainViewModel,
    ustozViewModel: UstozViewModel,
    isMediumOrExpanded: Boolean
) {
    AnimatedContent(
        targetState = currentScreen,
        transitionSpec = {
            fadeIn() togetherWith fadeOut()
        },
        label = "screen_transition"
    ) { screen ->
        when (screen) {
            "darslik" -> CurriculumScreen(viewModel = mainViewModel)
            "video_darslar" -> VideoDarslarScreen()
            "lugatlar" -> LugatlarScreen(viewModel = mainViewModel)
            "terminologiya" -> TerminologiyaScreen()
            "flashcards" -> FlashcardsScreen(viewModel = mainViewModel)
            "ai_ustoz" -> UstozScreen(
                viewModel = ustozViewModel,
                onNavigateToReader = { chapterNum, pageNum ->
                    val ch = mainViewModel.allChapters.find { it.chapterNumber == chapterNum }
                    if (ch != null) {
                        mainViewModel.selectChapter(ch)
                        val lesson = ch.lessons.find { pageNum in it.pdfStartPage..it.pdfEndPage }
                            ?: ch.lessons.firstOrNull()
                        if (lesson != null) {
                            mainViewModel.selectLesson(lesson)
                        }
                    }
                }
            )
            "profil" -> ProfilScreen(viewModel = mainViewModel)
            "zaxira_nusxa" -> ZaxiraNusxaScreen(viewModel = mainViewModel)
            "reader" -> ReaderScreen(viewModel = mainViewModel, isMediumOrExpanded = isMediumOrExpanded)
            "quiz_session" -> QuizSessionScreen(viewModel = mainViewModel)
            "xatchop_list" -> {
                val filteredBookmarks by mainViewModel.filteredBookmarks.collectAsState()
                val searchQuery by mainViewModel.bookmarkSearchQuery.collectAsState()
                XatchopListScreen(
                    bookmarks = filteredBookmarks,
                    searchQuery = searchQuery,
                    onSearchQueryChange = { mainViewModel.bookmarkSearchQuery.value = it },
                    onBookmarkClick = { bookmark -> mainViewModel.openBookmarkDetail(bookmark) },
                    onDeleteBookmark = { id -> mainViewModel.deleteBookmark(id) },
                    onBack = { mainViewModel.navigateTo("profil") },
                    chapters = mainViewModel.allChapters
                )
            }
            "xatchop_detail" -> {
                val selectedBookmark by mainViewModel.selectedBookmarkForDetail.collectAsState()
                XatchopDetailScreen(
                    bookmark = selectedBookmark,
                    onBack = { mainViewModel.navigateTo("xatchop_list") },
                    onSaveNote = { id, note -> mainViewModel.updateBookmarkNote(id, note) },
                    onNavigateToSource = { bookmark -> mainViewModel.openBookmarkInReader(bookmark) },
                    onDeleteBookmark = { id -> mainViewModel.deleteBookmark(id) }
                )
            }
            else -> CurriculumScreen(viewModel = mainViewModel)
        }
    }
}
