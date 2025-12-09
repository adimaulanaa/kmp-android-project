package com.bantraka.learningandroid.core.theme

// Theme.kt
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = AppTypography(),
        shapes = AppShapes,
        content = content
    )
}
