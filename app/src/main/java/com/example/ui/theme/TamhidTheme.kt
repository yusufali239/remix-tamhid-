package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.data.local.AppThemeMode

// ============================================================================
// Clean Minimalism 2.0 Design System Palette
// - Background: #FDFCFB
// - Primary: #2D5A27 (used for 5% of elements: active tabs, progress, quote line)
// - SurfaceVariant / Soft Container: #D1E8CF
// - Text Primary: #1A1C19
// - Text Secondary: #5F6368
// - Surface: #FFFFFF
// ============================================================================

val TamhidEmerald = Color(0xFF2D5A27)
val TamhidEmeraldDark = Color(0xFF1E3F1A)
val TamhidEmeraldLight = Color(0xFF4C8245)
val TamhidSageContainer = Color(0xFFD1E8CF)
val TamhidGold = Color(0xFFB8860B)
val TamhidGoldLight = Color(0xFFD4AF37)

val TamhidBgLight = Color(0xFFFDFCFB)
val TamhidSurfaceLight = Color(0xFFFFFFFF)
val TamhidTextPrimaryLight = Color(0xFF1A1C19)
val TamhidTextSecondaryLight = Color(0xFF5F6368)
val TamhidBorderLight = Color(0xFFE2E4DE)

// Sepia Reading Palette
val TamhidSepiaBg = Color(0xFFF7F3E8)
val TamhidSepiaSurface = Color(0xFFEFE8D6)
val TamhidSepiaTextPrimary = Color(0xFF382F24)
val TamhidSepiaTextSecondary = Color(0xFF6B5F4E)
val TamhidSepiaContainer = Color(0xFFE4DAC2)

// Dark / Night Reading Palette
val TamhidDarkBg = Color(0xFF121411)
val TamhidDarkSurface = Color(0xFF1A1D19)
val TamhidDarkTextPrimary = Color(0xFFE2E3DF)
val TamhidDarkTextSecondary = Color(0xFF8D938B)
val TamhidDarkContainer = Color(0xFF243022)

// Night (Pure Black OLED #000000 AMOLED) Reading Palette
val TamhidNightBg = Color(0xFF000000)
val TamhidNightSurface = Color(0xFF000000)
val TamhidNightTextPrimary = Color(0xFFD0D5CE)
val TamhidNightTextSecondary = Color(0xFF7A8177)
val TamhidNightContainer = Color(0xFF1C271A)

enum class ReadingThemeMode {
    LIGHT, SEPIA, DARK, NIGHT
}

val TamhidLightColorScheme = lightColorScheme(
    primary = TamhidEmerald,
    onPrimary = Color.White,
    primaryContainer = TamhidSageContainer,
    onPrimaryContainer = TamhidEmeraldDark,
    secondary = TamhidEmerald,
    onSecondary = Color.White,
    secondaryContainer = TamhidSageContainer,
    onSecondaryContainer = TamhidEmeraldDark,
    background = TamhidBgLight,
    onBackground = TamhidTextPrimaryLight,
    surface = TamhidSurfaceLight,
    onSurface = TamhidTextPrimaryLight,
    surfaceVariant = TamhidSageContainer,
    onSurfaceVariant = TamhidTextSecondaryLight,
    outline = TamhidBorderLight,
    outlineVariant = TamhidBorderLight.copy(alpha = 0.5f)
)

val TamhidSepiaColorScheme = lightColorScheme(
    primary = TamhidEmerald,
    onPrimary = Color.White,
    primaryContainer = TamhidSepiaContainer,
    onPrimaryContainer = TamhidSepiaTextPrimary,
    secondary = TamhidEmerald,
    onSecondary = Color.White,
    secondaryContainer = TamhidSepiaContainer,
    onSecondaryContainer = TamhidSepiaTextPrimary,
    background = TamhidSepiaBg,
    onBackground = TamhidSepiaTextPrimary,
    surface = TamhidSepiaSurface,
    onSurface = TamhidSepiaTextPrimary,
    surfaceVariant = TamhidSepiaContainer,
    onSurfaceVariant = TamhidSepiaTextSecondary,
    outline = TamhidSepiaTextSecondary.copy(alpha = 0.25f),
    outlineVariant = TamhidSepiaTextSecondary.copy(alpha = 0.15f)
)

val TamhidDarkColorScheme = darkColorScheme(
    primary = TamhidEmeraldLight,
    onPrimary = TamhidDarkBg,
    primaryContainer = TamhidDarkContainer,
    onPrimaryContainer = TamhidDarkTextPrimary,
    secondary = TamhidEmeraldLight,
    onSecondary = TamhidDarkBg,
    secondaryContainer = TamhidDarkContainer,
    onSecondaryContainer = TamhidDarkTextPrimary,
    background = TamhidDarkBg,
    onBackground = TamhidDarkTextPrimary,
    surface = TamhidDarkSurface,
    onSurface = TamhidDarkTextPrimary,
    surfaceVariant = TamhidDarkContainer,
    onSurfaceVariant = TamhidDarkTextSecondary,
    outline = TamhidDarkTextSecondary.copy(alpha = 0.3f),
    outlineVariant = TamhidDarkTextSecondary.copy(alpha = 0.15f)
)

val TamhidNightColorScheme = darkColorScheme(
    primary = TamhidEmeraldLight,
    onPrimary = TamhidNightBg,
    primaryContainer = TamhidNightContainer,
    onPrimaryContainer = TamhidNightTextPrimary,
    secondary = TamhidEmeraldLight,
    onSecondary = TamhidNightBg,
    secondaryContainer = TamhidNightContainer,
    onSecondaryContainer = TamhidNightTextPrimary,
    background = TamhidNightBg,
    onBackground = TamhidNightTextPrimary,
    surface = TamhidNightSurface,
    onSurface = TamhidNightTextPrimary,
    surfaceVariant = TamhidNightContainer,
    onSurfaceVariant = TamhidNightTextSecondary,
    outline = TamhidNightTextSecondary.copy(alpha = 0.25f),
    outlineVariant = TamhidNightTextSecondary.copy(alpha = 0.1f)
)

@Composable
fun GlobalAppTheme(
    appThemeMode: AppThemeMode = AppThemeMode.AUTO,
    content: @Composable () -> Unit
) {
    val systemDark = isSystemInDarkTheme()
    val isDark = when (appThemeMode) {
        AppThemeMode.AUTO -> systemDark
        AppThemeMode.LIGHT -> false
        AppThemeMode.DARK -> true
    }

    val colorScheme = if (isDark) TamhidDarkColorScheme else TamhidLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun TamhidTheme(
    mode: ReadingThemeMode = ReadingThemeMode.LIGHT,
    content: @Composable () -> Unit
) {
    val colorScheme: ColorScheme = when (mode) {
        ReadingThemeMode.LIGHT -> TamhidLightColorScheme
        ReadingThemeMode.SEPIA -> TamhidSepiaColorScheme
        ReadingThemeMode.DARK -> TamhidDarkColorScheme
        ReadingThemeMode.NIGHT -> TamhidNightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
