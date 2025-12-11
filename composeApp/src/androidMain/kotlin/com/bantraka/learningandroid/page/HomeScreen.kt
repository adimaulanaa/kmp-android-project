package com.bantraka.learningandroid.page
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bantraka.learningandroid.core.components.*

@Composable
fun HomeScreen(
    onNavigateDetail: () -> Unit,
    onNavigateDashboard: () -> Unit,
    onNavigateProfile: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("HomeScreen")
            Spacer(Modifier.height(16.dp))
            CButton(
                text = "Go to Detail",
                onClick = onNavigateDetail
            )
            Spacer(Modifier.height(16.dp))
            CButton(
                text = "Go to Dash",
                onClick = onNavigateDashboard
            )
            Spacer(Modifier.height(16.dp))
            CButton(
                text = "Go to Profile",
                onClick = onNavigateProfile
            )
        }
    }
}