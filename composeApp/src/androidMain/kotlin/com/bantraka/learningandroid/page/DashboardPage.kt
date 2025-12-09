package com.bantraka.learningandroid.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bantraka.learningandroid.core.components.AppTopBar
import learningandroid.composeapp.generated.resources.Res
import learningandroid.composeapp.generated.resources.arrow_left

@Composable
fun DashboardPage(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Dashboard Page",
                icon = Res.drawable.arrow_left,
                onBack = onBack
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Anda di Dashboard Page")
        }
    }
}