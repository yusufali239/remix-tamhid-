package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AmiriQuranArabicStyle
import com.example.ui.theme.TamhidEmerald
import com.example.ui.theme.TamhidEmeraldDark
import com.example.ui.theme.TamhidSageContainer

// ============================================================================
// Clean Minimalism 2.0 Onboarding Screen
// Mandatory First-Launch Setup: Name & Age (12-80) for AI Ustoz personalization
// ============================================================================

@Composable
fun OnboardingScreen(
    onComplete: (name: String, age: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var nameInput by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var ageError by remember { mutableStateOf<String?>(null) }

    val ageInt = ageInput.toIntOrNull()
    val isAgeValid = ageInt != null && ageInt in 12..80
    val isFormValid = nameInput.isNotBlank() && isAgeValid

    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(WindowInsets.systemBars.asPaddingValues()),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 520.dp)
                .fillMaxWidth()
                .padding(24.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Emblem & Arabic Title
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(TamhidSageContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.AutoStories,
                    contentDescription = null,
                    tint = TamhidEmerald,
                    modifier = Modifier.size(42.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "كتاب التمهيد لقواعد التوحيد",
                style = AmiriQuranArabicStyle.copy(fontSize = 24.sp),
                color = TamhidEmerald,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "At-Tamhid Madrasasi",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Imom Abu as-Sano al-Lomishiyning moturidiy aqidasini o'rganish bo'yicha shaxsiy ta'lim dasturi",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Main Card Form
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Text(
                        text = "Talaba ma'lumotlari",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = TamhidEmeraldDark
                    )

                    // Name Field
                    OutlinedTextField(
                        value = nameInput,
                        onValueChange = { nameInput = it },
                        label = { Text("Ismingiz / Talaba ismi") },
                        placeholder = { Text("Masalan: Abdulloh") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = null,
                                tint = TamhidEmerald
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("onboarding_name_input"),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = TamhidEmerald,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                        )
                    )

                    // Age Field
                    Column {
                        OutlinedTextField(
                            value = ageInput,
                            onValueChange = { input ->
                                val filtered = input.filter { it.isDigit() }.take(2)
                                ageInput = filtered
                                val num = filtered.toIntOrNull()
                                ageError = when {
                                    filtered.isBlank() -> null
                                    num == null || num < 12 || num > 80 -> "Yosh 12 dan 80 gacha bo'lishi kerak"
                                    else -> null
                                }
                            },
                            label = { Text("Yoshingiz (12 - 80)") },
                            placeholder = { Text("24") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Cake,
                                    contentDescription = null,
                                    tint = TamhidEmerald
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            isError = ageError != null,
                            shape = RoundedCornerShape(18.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("onboarding_age_input"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = TamhidEmerald,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant
                            )
                        )
                        if (ageError != null) {
                            Text(
                                text = ageError ?: "",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
                            )
                        } else {
                            Text(
                                text = "AI Ustozi dars uslubini yoshingizga moslashtiradi",
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(start = 12.dp, top = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Start Button
                    Button(
                        onClick = {
                            val age = ageInt
                            if (nameInput.isNotBlank() && age != null && age in 12..80) {
                                onComplete(nameInput.trim(), age)
                            }
                        },
                        enabled = isFormValid,
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TamhidEmerald,
                            contentColor = Color.White,
                            disabledContainerColor = TamhidSageContainer.copy(alpha = 0.5f),
                            disabledContentColor = TamhidEmeraldDark.copy(alpha = 0.4f)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .testTag("onboarding_start_button")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Ta'limni boshlash",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
