package com.bantraka.learningandroid

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bantraka.learningandroid.page.onboarding.OnboardingScreen

@Composable
fun RootApp(
    isOnboardingDone: Boolean,
    onOnboardingFinished: () -> Unit
) {
    val showOnboarding = remember {
        mutableStateOf(!isOnboardingDone)
    }

    if (showOnboarding.value) {
        OnboardingScreen(
            onFinish = {
                onOnboardingFinished()
                showOnboarding.value = false
            }
        )
    } else {
        App()
    }
}
