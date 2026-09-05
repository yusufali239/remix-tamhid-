package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

/**
 * TamhidQuoteBlock Component
 * Strictly conforms to Clean Minimalism 2.0:
 * - Left accent line: 3dp of TamhidEmerald (#2D5A27)
 * - Background: TamhidSageContainer (#D1E8CF)
 * - Rounded corners: 20dp
 * - Inside: Arabic text (Amiri Quran style) + Uzbek academic translation + source reference.
 * - No border, soft flat elevation.
 */
@Composable
fun TamhidQuoteBlock(
    arabicText: String,
    translation: String,
    modifier: Modifier = Modifier,
    pageNumber: Int? = null,
    sourceTitle: String = "Imom al-Lomishiy «At-Tamhid»"
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(TamhidSageContainer)
    ) {
        Row(modifier = Modifier.fillMaxWidth().intrinsicHeight()) {
            // 3dp primary accent line on the left
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .fillMaxHeight()
                    .background(TamhidEmerald)
            )

            // Content column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 18.dp)
            ) {
                // Source badge header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = sourceTitle,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = TamhidEmeraldDark
                    )
                    if (pageNumber != null) {
                        Text(
                            text = "$pageNumber-bet",
                            style = MaterialTheme.typography.labelSmall,
                            color = TamhidEmeraldDark.copy(alpha = 0.8f)
                        )
                    }
                }

                if (arabicText.isNotBlank()) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = arabicText,
                        style = AmiriQuranArabicStyle.copy(fontSize = 20.sp, lineHeight = 32.sp),
                        color = TamhidEmeraldDark,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Right
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = translation,
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
                    color = TamhidTextPrimaryLight
                )
            }
        }
    }
}

// Helper extension for intrinsic height in compose row
private fun Modifier.intrinsicHeight(): Modifier = this.height(IntrinsicSize.Min)
