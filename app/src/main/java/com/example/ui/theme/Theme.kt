package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
  darkColorScheme(
    primary = CyberBlue,
    secondary = NeonPurple,
    tertiary = CyberPink,
    background = BlackOLED,
    surface = SurfaceDark,
    surfaceVariant = SurfaceLight,
    onPrimary = BlackOLED,
    onSecondary = BlackOLED,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary
  )

private val LightColorScheme = DarkColorScheme // Force OLED Dark Mode

@Composable
fun OmniTheme(
  content: @Composable () -> Unit,
) {
  MaterialTheme(colorScheme = DarkColorScheme, typography = Typography, content = content)
}
