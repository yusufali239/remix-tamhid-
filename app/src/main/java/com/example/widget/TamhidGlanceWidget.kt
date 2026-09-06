package com.example.widget

import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.MainActivity

class TamhidGlanceWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val todayQuote = TamhidWidgetQuotes.getTodayQuote()

        val clickIntent = Intent(context, MainActivity::class.java).apply {
            action = Intent.ACTION_VIEW
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("EXTRA_NAVIGATE_TO", "reader")
            putExtra("EXTRA_CHAPTER_ID", todayQuote.chapterId)
            putExtra("EXTRA_PAGE_NUMBER", todayQuote.pageNumber)
        }

        provideContent {
            val emeraldBg = Color(0xFF1B4D3E)
            val goldAccent = Color(0xFFE5C378)
            val textLight = Color(0xFFF9FAF9)
            val textMuted = Color(0xFFC7D4CD)

            Box(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .cornerRadius(20.dp)
                    .background(ColorProvider(emeraldBg))
                    .clickable(actionStartActivity(clickIntent))
                    .padding(14.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = GlanceModifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Header: Badge / Title
                    Row(
                        modifier = GlanceModifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "AT-TAMHID • KUNLIK HIKMAT",
                            style = TextStyle(
                                color = ColorProvider(goldAccent),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Spacer(modifier = GlanceModifier.height(6.dp))

                    // Arabic Quote
                    Text(
                        text = todayQuote.arabicText,
                        style = TextStyle(
                            color = ColorProvider(textLight),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        maxLines = 2
                    )

                    Spacer(modifier = GlanceModifier.height(4.dp))

                    // Uzbek Translation
                    Text(
                        text = "«${todayQuote.uzbekTranslation}»",
                        style = TextStyle(
                            color = ColorProvider(textMuted),
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center
                        ),
                        maxLines = 2
                    )

                    Spacer(modifier = GlanceModifier.height(6.dp))

                    // Source and Tap Affordance
                    Row(
                        modifier = GlanceModifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${todayQuote.source}  ➔  O'qish",
                            style = TextStyle(
                                color = ColorProvider(goldAccent),
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }
        }
    }
}

class TamhidGlanceWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TamhidGlanceWidget()
}
