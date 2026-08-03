package com.example.cinemaxapp.core.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


// Cinemax Color Scheme
@Immutable
data class CinemaxColorScheme(
    // Primary
    val dark: Color,
    val soft: Color,
    val blueAccent: Color,

    // Secondary
    val green: Color,
    val orange: Color,
    val red: Color,

    // Text
    val textBlack: Color,
    val darkGrey: Color,
    val grey: Color,
    val whiteGrey: Color,
    val white: Color,
    val lineDark: Color,
)

//Default values from Figma
val DefaultCinemaxColorScheme = CinemaxColorScheme(
    dark = Dark,
    soft = Soft,
    blueAccent = BlueAccent,
    green = Green,
    orange = Orange,
    red = Red,
    textBlack = TextBlack,
    darkGrey = DarkGrey,
    grey = Grey,
    whiteGrey = WhiteGrey,
    white = White,
    lineDark = LineDark,
)

val LocalCinemaxColors = staticCompositionLocalOf { DefaultCinemaxColorScheme }


// Material3 Color Scheme — maps Figma colors to Material roles
private val CinemaxMaterial3ColorScheme = darkColorScheme(
    primary = BlueAccent,
    onPrimary = White,
    secondary = Orange,
    onSecondary = White,
    tertiary = Green,
    onTertiary = White,
    background = Dark,
    onBackground = White,
    surface = Soft,
    onSurface = White,
    surfaceVariant = Soft,
    onSurfaceVariant = Grey,
    error = Red,
    onError = White,
    outline = Grey,
    outlineVariant = LineDark,
)


// CinemaxTheme Composable — wraps the entire app
@Composable
fun CinemaxTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalCinemaxColors provides DefaultCinemaxColorScheme,
        LocalCinemaxTypography provides DefaultCinemaxTypography,
    ) {
        MaterialTheme(
            colorScheme = CinemaxMaterial3ColorScheme,
            typography = CinemaxMaterial3Typography,
            content = content,
        )
    }
}


// CinemaxTheme object — access colors & typography anywhere
//   CinemaxTheme.colors.blueAccent
//   CinemaxTheme.typography.h1SemiBold
object CinemaxTheme {

    val colors: CinemaxColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalCinemaxColors.current

    val typography: CinemaxTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCinemaxTypography.current
}
