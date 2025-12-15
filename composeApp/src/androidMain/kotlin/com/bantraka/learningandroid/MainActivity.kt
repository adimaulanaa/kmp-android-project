package com.bantraka.learningandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.bantraka.learningandroid.core.di.AppModule
import com.bantraka.learningandroid.page.onboarding.OnboardingPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private var isLoading = true
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            isLoading
        }

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Inisialisasi AppModule dengan Context
        AppModule.init(this)

        lifecycleScope.launch {
            delay(2000) // ⏱ 2 detik
            isLoading = false
        }

        setContent {
            val onboardingPrefs = OnboardingPreferences(this)

            RootApp(
                isOnboardingDone = onboardingPrefs.isOnboardingDone,
                onOnboardingFinished = {
                    onboardingPrefs.isOnboardingDone = true
                }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}