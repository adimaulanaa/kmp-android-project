package com.bantraka.learningandroid
import androidx.compose.runtime.*
import com.bantraka.learningandroid.core.theme.AppTheme
import com.example.composenavigation.core.navigation.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        AppNavHost()
    }
}