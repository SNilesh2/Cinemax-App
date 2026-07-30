package com.example.cinemax.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.cinemax.core.designsystem.R

// Montserrat Font Family
val Montserrat = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
)

// Cinemax Typography — matches Figma text styles exactly
@Immutable
data class CinemaxTypography(

    // Heading / SemiBold
    val h1SemiBold: TextStyle,
    val h2SemiBold: TextStyle,
    val h3SemiBold: TextStyle,
    val h4SemiBold: TextStyle,
    val h5SemiBold: TextStyle,
    val h6SemiBold: TextStyle,
    val h7SemiBold: TextStyle,

    // Heading / Medium
    val h1Medium: TextStyle,
    val h2Medium: TextStyle,
    val h3Medium: TextStyle,
    val h4Medium: TextStyle,
    val h5Medium: TextStyle,
    val h6Medium: TextStyle,
    val h7Medium: TextStyle,

    // Heading / Regular
    val h1Regular: TextStyle,
    val h2Regular: TextStyle,
    val h3Regular: TextStyle,
    val h4Regular: TextStyle,
    val h5Regular: TextStyle,
    val h6Regular: TextStyle,
    val h7Regular: TextStyle,

    // Body / Paragraph
    val bodySemiBold: TextStyle,
    val bodyMedium: TextStyle,
    val bodyRegular: TextStyle,
)

// Default values — Figma specs
val DefaultCinemaxTypography = CinemaxTypography(

    // Heading / SemiBold
    h1SemiBold = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
    ),
    h2SemiBold = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    h3SemiBold = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    h4SemiBold = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    h5SemiBold = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),
    h6SemiBold = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
    h7SemiBold = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
    ),

    // Heading / Medium
    h1Medium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
    ),
    h2Medium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
    ),
    h3Medium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
    ),
    h4Medium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    h5Medium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    h6Medium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    h7Medium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
    ),

    // Heading / Regular
    h1Regular = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
    ),
    h2Regular = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    h3Regular = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    ),
    h4Regular = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    h5Regular = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    h6Regular = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    h7Regular = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),

    // Body / Paragraph
    bodySemiBold = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    bodyRegular = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
)

// CompositionLocal — access via CinemaxTheme.typography
val LocalCinemaxTypography = staticCompositionLocalOf { DefaultCinemaxTypography }


// Material3 Typography mapping — for default Material components
val CinemaxMaterial3Typography = Typography(
    // Display → largest headings (mapped to H1-H2 SemiBold)
    displayLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),

    // Headline → section headings (mapped to H3-H4 SemiBold)
    headlineLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
    ),

    // Title → component titles (mapped to H4-H5 Medium)
    titleLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),

    // Body → paragraph text (mapped to H5-H6 Regular)
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),

    // Label → buttons, captions
    labelLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    ),
)
