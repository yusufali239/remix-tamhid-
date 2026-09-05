package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Language
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
import com.example.ui.theme.TamhidEmerald
import com.example.ui.theme.TamhidSageContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.net.URLEncoder
import java.util.concurrent.TimeUnit

data class AqidaTermin(
    val id: String,
    val nomi: String,
    val arabcha: String,
    val qisqaTarif: String,
    val batafsilIzoh: String,
    val wikipediaTitle: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerminologiyaScreen(
    modifier: Modifier = Modifier
) {
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Terminologiya",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Aqida va Kalom ilmi asosiy tushunchalari • Wikipedia integratsiyasi",
                            style = MaterialTheme.typography.labelSmall,
                            color = TamhidEmerald
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
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
            // Qidiruv
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

            // Ro'yxat
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredList, key = { it.id }) { termin ->
                    val isExpanded = expandedTermId == termin.id
                    TerminCard(
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
}

@Composable
private fun TerminCard(
    termin: AqidaTermin,
    isExpanded: Boolean,
    onToggleExpand: () -> Unit,
    onOpenWikipedia: () -> Unit
) {
    var wikiSnippet by remember { mutableStateOf<String?>(null) }
    var isWikiLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Wikipedia API orqali qisqa kirish ma'lumotini yuklash
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

/**
 * Uzbek Wikipedia API (action=query&prop=extracts) orqali maqola muqaddimasini olish.
 * Qat'iy Dispatchers.IO da va xatoliklar ushlangan holda. Hech qachon crash bo'lmaydi.
 */
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
        // Safe null on any connection or format error
    }
    null
}
