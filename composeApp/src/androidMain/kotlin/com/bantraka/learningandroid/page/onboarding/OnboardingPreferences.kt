package com.bantraka.learningandroid.page.onboarding

import android.content.Context

class OnboardingPreferences(context: Context) {

    private val prefs = context.getSharedPreferences(
        "onboarding_prefs",
        Context.MODE_PRIVATE
    )

    var isOnboardingDone: Boolean
        get() = prefs.getBoolean(KEY_ONBOARDING_DONE, false)
        set(value) {
            prefs.edit().putBoolean(KEY_ONBOARDING_DONE, value).apply()
        }

    companion object {
        private const val KEY_ONBOARDING_DONE = "onboarding_done"
    }
}
