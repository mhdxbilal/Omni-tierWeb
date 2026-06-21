package com.example.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AmoledBlack = Color(0xFF000000)
val QuantumCyan = Color(0xFF00FFCC)
val PrivacyPurple = Color(0xFF7000FF)
val GlassLayer = Color(0x22FFFFFF)

// Core spring specs for fluid physics
fun <T> awoeSpring() = spring<T>(
    dampingRatio = Spring.DampingRatioNoBouncy,
    stiffness = Spring.StiffnessMediumLow
)

private val AWOEColorScheme = darkColorScheme(
    primary = QuantumCyan,
    secondary = PrivacyPurple,
    background = AmoledBlack,
    surface = AmoledBlack,
    surfaceVariant = GlassLayer,
    onPrimary = AmoledBlack,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = Color.LightGray
)

val AWOETypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        letterSpacing = (-0.5).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        letterSpacing = 0.5.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 1.0.sp
    )
)

@Composable
fun AWOETheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AWOEColorScheme,
        typography = AWOETypography,
        content = content
    )
}
