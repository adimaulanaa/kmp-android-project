package com.bantraka.learningandroid.page.dashboard.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bantraka.learningandroid.core.components.AppTopBar
import com.bantraka.learningandroid.core.components.CBasicField
import com.bantraka.learningandroid.core.components.CButton
import com.bantraka.learningandroid.core.components.CLoading
import com.bantraka.learningandroid.page.dashboard.domain.model.DashboardViewModel
import learningandroid.composeapp.generated.resources.Res
import learningandroid.composeapp.generated.resources.arrow_left

@Composable
fun DashboardPage(
    viewModel: DashboardViewModel,
    onBack: () -> Unit
) {
    val state = viewModel.uiState.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) { // 1. Box full screen
        Scaffold(
            topBar = {
                AppTopBar(
                    title = "Dashboard Page",
                    icon = Res.drawable.arrow_left,
                    onBack = onBack
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                CButton(
                    text = if (state.isLoading) "Loading..." else "Tambah User",
                    enabled = !state.isLoading,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { viewModel.addUser() }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                CBasicField(
                    value = state.name,
                    onValueChange = { viewModel.onNameChange(it) },
                    label = "Nama",
                )
                Spacer(Modifier.height(16.dp))
                CBasicField(
                    value = state.age,
                    keyboardType = KeyboardType.Number,
                    onValueChange = { viewModel.onAgeChange(it) },
                    label = "Umur"
                )
                Spacer(Modifier.height(16.dp))

                state.errorMessage?.let { msg ->
                    Text(
                        text = msg,
                        color = Color.Red,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                LazyColumn {
                    items(state.users) { user ->
                        Text("${user.name}, ${user.age} tahun")
                    }
                }
            }
        }

        // 2. Letakkan CLoading di sini, di atas semua
        if (state.isLoading) {
            CLoading(message = "Sedang menyimpan data...")
        }
    }
}
