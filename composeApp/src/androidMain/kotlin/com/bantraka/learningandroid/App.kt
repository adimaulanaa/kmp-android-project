package com.bantraka.learningandroid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.example.composenavigation.core.navigation.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}