package com.bantraka.learningandroid.core.theme

import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFF88C9A1)
val BackgroundColor = Color(0xFFF5FBF7)
val OnPrimaryColor = Color(0xFFFFFFFF)
val SurfaceColor = Color.White
val OnBackgroundColor = Color(0xFF1A1A1A)

val LightColorScheme = androidx.compose.material3.lightColorScheme(
    primary = PrimaryColor,
    onPrimary = OnPrimaryColor,
    background = BackgroundColor,
    surface = SurfaceColor,
    onBackground = OnBackgroundColor,
)