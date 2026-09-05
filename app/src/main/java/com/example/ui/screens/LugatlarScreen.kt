package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.DictionaryWord
import com.example.ui.theme.*
import com.example.ui.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LugatlarScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val allWords by viewModel.allDictionaryWords.collectAsState()
    val searchQuery by viewModel.dictionarySearchQuery.collectAsState()

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedLetter by remember { mutableStateOf("Barchasi") }

    val alphabetList = remember {
        listOf("Barchasi", "A", "B", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "X", "Y", "Z")
    }

    val filteredList = remember(allWords, selectedLetter) {
        if (selectedLetter == "Barchasi") {
            allWords
        } else {
            allWords.filter { it.uzbekTarjima.startsWith(selectedLetter, ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = if (selectedTabIndex == 0) "Lug'atlar" else "Terminologiya",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = if (selectedTabIndex == 0) "«At-Tamhid» asarining aqidaviy atamalari" else "Aqida va Kalom terminlari • Wikipedia",
                            style = MaterialTheme.typography.labelSmall,
                            color = TamhidEmerald
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            if (selectedTabIndex == 0) {
                ExtendedFloatingActionButton(
                    onClick = { showAddDialog = true },
                    containerColor = TamhidEmerald,
                    contentColor = Color.White,
                    icon = { Icon(Icons.Default.Add, contentDescription = null) },
                    text = { Text("Yangi so'z qo'shish") }
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Bo'limlar tab tanlagichi
            PrimaryTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = TamhidEmerald
            ) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = {
                        Text(
                            "«At-Tamhid» Lug'ati",
                            fontWeight = if (selectedTabIndex == 0) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = {
                        Text(
                            "Terminologiya (Wikipedia)",
                            fontWeight = if (selectedTabIndex == 1) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                )
            }

            if (selectedTabIndex == 0) {
                // 1-TAB: At-Tamhid Lug'ati
                LugatTabContent(
                    allWords = filteredList,
                    searchQuery = searchQuery,
                    onSearchChange = { viewModel.dictionarySearchQuery.value = it },
                    selectedLetter = selectedLetter,
                    alphabetList = alphabetList,
                    onLetterSelect = { selectedLetter = it },
                    onDeleteWord = { viewModel.deleteDictionaryWord(it) }
                )
            } else {
                // 2-TAB: Terminologiya & Wikipedia
                TerminologiyaTabContent()
            }
        }
    }

    // Yangi so'z qo'shish Dialogi
    if (showAddDialog) {
        AddWordDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { ar, uz, izoh, bet ->
                viewModel.addDictionaryWord(ar, uz, izoh, bet)
                showAddDialog = false
            }
        )
    }
}

@Composable
private fun LugatTabContent(
    allWords: List<DictionaryWord>,
    searchQuery: String,
    onSearchChange: (String) -> Unit,
    selectedLetter: String,
    alphabetList: List<String>,
    onLetterSelect: (String) -> Unit,
    onDeleteWord: (DictionaryWord) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp)),
                    placeholder = {
                        Text(
                            "Lug'atdan qidirish (o'zbekcha yoki arabcha)...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Qidirish",
                            tint = TamhidEmerald
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = TamhidEmerald,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                        focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Alifbo bo'yicha filter
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(alphabetList) { letter ->
                        val isSelected = selectedLetter == letter
                        FilterChip(
                            selected = isSelected,
                            onClick = { onLetterSelect(letter) },
                            label = {
                                Text(
                                    text = letter,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = TamhidEmerald,
                                selectedLabelColor = Color.White,
                                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                labelColor = MaterialTheme.colorScheme.onSurface
                            ),
                            border = null
                        )
                    }
                }
            }
        }

        if (allWords.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Book,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                        modifier = Modifier.size(56.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "So'z topilmadi",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Boshqa so'zni qidirib ko'ring yoki yangi so'z qo'shing",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(allWords, key = { it.id }) { word ->
                    DictionaryWordCard(
                        word = word,
                        onDelete = { onDeleteWord(word) }
                    )
                }
            }
        }
    }
}

@Composable
private fun TerminologiyaTabContent() {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var expandedTermId by remember { mutableStateOf<String?>(null) }

    val terminlar = remember {
        listOf(
            AqidaTermin(
                id = "t1",
                nomi = "Javhar",
                arabcha = "الجوهر",
                qisqaTarif = "O'z-o'zicha qoyim bo'la oladigan, o'zicha mavjud bo'lgan eng kichik moddiy zarra (substansiya).",
                batafsilIzoh = "Kalom ilmida barcha moddiy olam javharlardan tashkil topgan. Javhar o'zicha mavjud bo'ladi, lekin unga doimo a'rozlar (rang, harakat, sukun) ergashadi.",
                wikipediaTitle = "Substansiya"
            ),
            AqidaTermin(
                id = "t2",
                nomi = "Araz",
                arabcha = "العرض",
                qisqaTarif = "O'zicha mustaqil tura olmaydigan, faqat javharda zohir bo'ladigan xususiyat (aksidensiya).",
                batafsilIzoh = "Rangi, issiq-sovuqligi, harakat yoki sukunati araz hisoblanadi. Arazlar doim o'zgaruvchan va paydo bo'luvchi (hadis) bo'lgani uchun, ular joylashgan olam ham hadisdir.",
                wikipediaTitle = "Aksidensiya"
            ),
            AqidaTermin(
                id = "t3",
                nomi = "Jism",
                arabcha = "الجسم",
                qisqaTarif = "Kamida ikki yoki undan ortiq javharning birlashuvidan hosil bo'lgan tuzilma.",
                batafsilIzoh = "Jism uzunlik, kenglik va chuqurlikka ega bo'ladi. Moturidiy aqidasiga ko'ra, Alloh taolo jism emas va jismlarning xususiyatlaridan butunlay pokdir.",
                wikipediaTitle = "Jism"
            ),
            AqidaTermin(
                id = "t4",
                nomi = "Hudus",
                arabcha = "الحدوث",
                qisqaTarif = "Yo'qlikdan keyin vujudga kelish, paydo bo'lish qonuniyati.",
                batafsilIzoh = "Barcha olam va undagi mavjudotlar hadisdir (ya'ni qachondir yo'q edi, so'ng yaratildi). Hadis bo'lgan narsa albatta Qadim bo'lgan Yaratuvchiga muhtojdir.",
                wikipediaTitle = "Kalom"
            ),
            AqidaTermin(
                id = "t5",
                nomi = "Qidam",
                arabcha = "القدم",
                qisqaTarif = "Azaliylik, mavjudligining boshlanishi va avvali yo'qligi.",
                batafsilIzoh = "Faqat Alloh taologina Qadimdir. Uning zoti va barcha azaliy sifatlari qadim bo'lib, olamdan oldin ham mavjud bo'lgan va abadiydir.",
                wikipediaTitle = "Tavhid"
            ),
            AqidaTermin(
                id = "t6",
                nomi = "Tanzih",
                arabcha = "التنزيه",
                qisqaTarif = "Alloh taoloni nuqson, kamchilik, o'xshashlik, jism va makondan mutlaq pok deb bilish.",
                batafsilIzoh = "Ahli sunna val jamoaning asosiy tamoyili: «U Zotga o'xshash hech narsa yo'qdir». Alloh a'zolardan, zamon va makondan munazzahdir.",
                wikipediaTitle = "Tavhid"
            ),
            AqidaTermin(
                id = "t7",
                nomi = "Takvin",
                arabcha = "التكوين",
                qisqaTarif = "Alloh taoloning yaratish, vujudga keltirish azaliy sifati.",
                batafsilIzoh = "Imom Abu Mansur al-Moturidiy mazhabiga ko'ra, Takvin - Allohning mustaqil zotiy-azaliy sifatidir. Maxluqotlar hodis bo'lsa-da, Allohning Takvin sifati azaliydir.",
                wikipediaTitle = "Moturidiylik"
            ),
            AqidaTermin(
                id = "t8",
                nomi = "Moturidiylik",
                arabcha = "الماتريدية",
                qisqaTarif = "Imom Abu Mansur al-Moturidiy asos solgan, Markaziy Osiyo va butun islom olamida keng tarqalgan sunniy aqida maktabi.",
                batafsilIzoh = "Aql va naql muvozanatini saqlagan, Qur'on va sunnat nasslarini chuqur aqliy dalillar bilan himoya qilgan buyuk kalomiy mazhab.",
                wikipediaTitle = "Moturidiylik"
            ),
            AqidaTermin(
                id = "t9",
                nomi = "Ash'ariylik",
                arabcha = "الأشعرية",
                qisqaTarif = "Imom Abul Hasan al-Ash'ariy asos solgan sunniy e'tiqod maktabi.",
                batafsilIzoh = "Moturidiylik bilan birgalikda Ahli sunna val jamoaning ikki buyuk e'tiqodiy qanotidan biridir. Asosiy e'tiqodiy masalalarda mushtarakdirlar.",
                wikipediaTitle = "Ashʼariylik"
            ),
            AqidaTermin(
                id = "t10",
                nomi = "Kasb",
                arabcha = "الكسب",
                qisqaTarif = "Bandaning o'z irodasi bilan biror amalni tanlashi va unga yuzlanishi.",
                batafsilIzoh = "Bandaning xohishi va harakatiga kasb deyiladi. Amalni vujudga keltirish (xalq) esa yolg'iz Alloh taoloning qudratiga tegishlidir. Inson kasbi uchun javobgar bo'ladi.",
                wikipediaTitle = "Kalom"
            ),
            AqidaTermin(
                id = "t11",
                nomi = "Tavhid",
                arabcha = "التوحيد",
                qisqaTarif = "Alloh taoloni zotida, sifatlarida va ibodatida sheriksiz, Yagona deb e'tiqod qilish.",
                batafsilIzoh = "Islom dinining asosi va bosh g'oyasi. Tavhid rububiyyat, uluhiyyat va asmo-sifat yagonaligini o'z ichiga oladi.",
                wikipediaTitle = "Tavhid"
            ),
            AqidaTermin(
                id = "t12",
                nomi = "Mu'taziliylar",
                arabcha = "المعتزلة",
                qisqaTarif = "Kalom tarixida aqlga haddan tashqari tayanib, ko'plab nasslarni ta'vil qilgan oqim.",
                batafsilIzoh = "Vosil ibn Ato tomonidan asos solingan. Ular Allohning azaliy sifatlarini va oxiratda Allohni ko'rishni inkor qilganlar. At-Tamhid kitobida ularga jiddiy raddiyalar berilgan.",
                wikipediaTitle = "Muʼtaziliylar"
            )
        )
    }

    val filteredList = remember(searchQuery, terminlar) {
        if (searchQuery.isBlank()) terminlar
        else terminlar.filter {
            it.nomi.contains(searchQuery, ignoreCase = true) ||
            it.arabcha.contains(searchQuery, ignoreCase = true) ||
            it.qisqaTarif.contains(searchQuery, ignoreCase = true)
        }
    }

    fun openWikipediaBrowser(title: String) {
        try {
            val encoded = URLEncoder.encode(title, "UTF-8")
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://uz.wikipedia.org/wiki/$encoded"))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        } catch (e: Exception) {
            // Safe fallback
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .clip(RoundedCornerShape(16.dp)),
                placeholder = {
                    Text(
                        "Terminlarni qidirish (Javhar, Araz, Qidam...)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                    )
                },
                leadingIcon = {
                    Icon(Icons.Outlined.Search, contentDescription = "Qidirish", tint = TamhidEmerald)
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = TamhidEmerald,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                )
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredList, key = { it.id }) { termin ->
                val isExpanded = expandedTermId == termin.id
                TerminCardItem(
                    termin = termin,
                    isExpanded = isExpanded,
                    onToggleExpand = {
                        expandedTermId = if (isExpanded) null else termin.id
                    },
                    onOpenWikipedia = { openWikipediaBrowser(termin.wikipediaTitle) }
                )
            }
        }
    }
}

@Composable
private fun TerminCardItem(
    termin: AqidaTermin,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onOpenWikipedia: () -> Unit
) {
    var wikiSnippet by remember { mutableStateOf<String?>(null) }
    var isWikiLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(isExpanded) {
        if (isExpanded && wikiSnippet == null && !isWikiLoading) {
            coroutineScope.launch {
                isWikiLoading = true
                wikiSnippet = fetchWikipediaExtract(termin.wikipediaTitle)
                isWikiLoading = false
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onToggleExpand() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = termin.nomi,
                        style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = termin.arabcha,
                        style = MaterialTheme.typography.bodyMedium,
                        color = TamhidEmerald
                    )
                }

                IconButton(onClick = onToggleExpand) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Batafsil",
                        tint = TamhidEmerald
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = termin.qisqaTarif,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 12.dp)) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Kalomiy tahlil:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmerald
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = termin.batafsilIzoh,
                        style = MaterialTheme.typography.bodySmall.copy(lineHeight = 20.sp),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Wikipedia bo'limi
                    Surface(
                        color = TamhidSageContainer.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Language,
                                    contentDescription = null,
                                    tint = TamhidEmerald,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Wikipedia ma'lumoti:",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = TamhidEmerald
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            if (isWikiLoading) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(vertical = 4.dp)
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(14.dp),
                                        strokeWidth = 2.dp,
                                        color = TamhidEmerald
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Wikipedia dan yuklanmoqda...",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            } else {
                                val textToShow = wikiSnippet?.ifBlank { null }
                                    ?: "${termin.nomi} haqida O'zbekcha Wikipedia qomusida keng qamrovli maqola mavjud."
                                Text(
                                    text = textToShow,
                                    style = MaterialTheme.typography.bodySmall.copy(lineHeight = 18.sp),
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            OutlinedButton(
                                onClick = onOpenWikipedia,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = TamhidEmerald)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Wikipedia da o'qish", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

private suspend fun fetchWikipediaExtract(title: String): String? = withContext(Dispatchers.IO) {
    try {
        val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        val encodedTitle = URLEncoder.encode(title, "UTF-8")
        val url = "https://uz.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro=true&explaintext=true&titles=$encodedTitle"

        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "AtTamhidApp/1.0 (Android educational kalom client)")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return@withContext null
            val body = response.body?.string() ?: return@withContext null
            val json = JSONObject(body)
            val queryObj = json.optJSONObject("query") ?: return@withContext null
            val pages = queryObj.optJSONObject("pages") ?: return@withContext null
            val keys = pages.keys()
            if (keys.hasNext()) {
                val pageKey = keys.next()
                if (pageKey == "-1") return@withContext null
                val pageObj = pages.getJSONObject(pageKey)
                val extract = pageObj.optString("extract", "")
                if (extract.isNotBlank()) {
                    return@withContext extract.take(400) + if (extract.length > 400) "..." else ""
                }
            }
        }
    } catch (e: Exception) {
        // Safe null
    }
    null
}

@Composable
private fun DictionaryWordCard(
    word: DictionaryWord,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // O'zbekcha atama
                Text(
                    text = word.uzbekTarjima,
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 17.sp),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Arabcha matn
                Text(
                    text = word.arabic,
                    style = AmiriQuranArabicStyle.copy(fontSize = 20.sp),
                    color = TamhidEmerald
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Izoh
            Text(
                text = word.izoh,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Manba va Qo'shimcha belgilar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (word.manbaBet > 0) {
                    Surface(
                        color = TamhidSageContainer,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "At-Tamhid, ${word.manbaBet}-bet",
                            style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                            color = TamhidEmerald,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }

                if (word.isCustom) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = TamhidGold.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = "Shaxsiy so'z",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                                color = TamhidGold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        IconButton(onClick = onDelete, modifier = Modifier.size(28.dp)) {
                            Icon(
                                imageVector = Icons.Default.DeleteOutline,
                                contentDescription = "O'chirish",
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AddWordDialog(
    onDismiss: () -> Unit,
    onAdd: (arabic: String, uzbek: String, izoh: String, bet: Int) -> Unit
) {
    var arabic by remember { mutableStateOf("") }
    var uzbek by remember { mutableStateOf("") }
    var izoh by remember { mutableStateOf("") }
    var betText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Yangi so'z qo'shish",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedTextField(
                    value = uzbek,
                    onValueChange = { uzbek = it },
                    label = { Text("O'zbekcha atama (masalan: Javhar)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = arabic,
                    onValueChange = { arabic = it },
                    label = { Text("Arabcha yozilishi (masalan: الجوهر)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = izoh,
                    onValueChange = { izoh = it },
                    label = { Text("Ta'rifi va izohi") },
                    maxLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = betText,
                    onValueChange = { betText = it },
                    label = { Text("Kitob sahifasi (ixtiyoriy)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val bet = betText.toIntOrNull() ?: 0
                    if (uzbek.isNotBlank() && arabic.isNotBlank()) {
                        onAdd(arabic, uzbek, izoh, bet)
                    }
                },
                enabled = uzbek.isNotBlank() && arabic.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = TamhidEmerald)
            ) {
                Text("Saqlash")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Bekor qilish")
            }
        }
    )
}

