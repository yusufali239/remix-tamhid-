package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = MadrasaEmeraldNight,
    onPrimary = MadrasaBackgroundDark,
    primaryContainer = MadrasaEmeraldDark,
    onPrimaryContainer = MadrasaEmeraldNight,
    secondary = MadrasaGoldNight,
    onSecondary = MadrasaBackgroundDark,
    secondaryContainer = MadrasaSurfaceVariantDark,
    onSecondaryContainer = MadrasaGoldNight,
    tertiary = MadrasaAmber,
    onTertiary = MadrasaTextPrimaryDark,
    background = MadrasaBackgroundDark,
    onBackground = MadrasaTextPrimaryDark,
    surface = MadrasaSurfaceDark,
    onSurface = MadrasaTextPrimaryDark,
    surfaceVariant = MadrasaSurfaceVariantDark,
    onSurfaceVariant = MadrasaTextSecondaryDark,
    outline = MadrasaTextSecondaryDark.copy(alpha = 0.4f)
)

private val LightColorScheme = lightColorScheme(
    primary = MadrasaEmerald,
    onPrimary = Color.White,
    primaryContainer = MinimalContainerLight,
    onPrimaryContainer = MadrasaEmeraldDark,
    secondary = MadrasaEmerald,
    onSecondary = Color.White,
    secondaryContainer = MinimalSurfaceVariantLight,
    onSecondaryContainer = MinimalTextSecondaryLight,
    tertiary = MadrasaGold,
    onTertiary = Color.White,
    background = MinimalBackgroundLight,
    onBackground = MinimalTextPrimaryLight,
    surface = MinimalSurfaceLight,
    onSurface = MinimalTextPrimaryLight,
    surfaceVariant = MinimalSurfaceVariantLight,
    onSurfaceVariant = MinimalTextSecondaryLight,
    outline = MinimalBorderLight,
    outlineVariant = MinimalBorderLight.copy(alpha = 0.6f)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
