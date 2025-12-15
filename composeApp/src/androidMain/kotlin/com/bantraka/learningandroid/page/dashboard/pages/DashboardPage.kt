package com.bantraka.learningandroid.page.dashboard.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bantraka.learningandroid.core.components.AppTopBar
import com.bantraka.learningandroid.core.components.CBasicField
import com.bantraka.learningandroid.core.components.CButton
import com.bantraka.learningandroid.page.dashboard.domain.model.DashboardViewModel
import learningandroid.composeapp.generated.resources.Res
import learningandroid.composeapp.generated.resources.arrow_left

@Composable
fun DashboardPage(
    viewModel: DashboardViewModel,
    onBack: () -> Unit
) {
    val users by viewModel.users.collectAsState()

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

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
                text = "Add User",
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = {
                    val ageInt = age.toIntOrNull() ?: 0
                    if (name.isNotBlank() && ageInt > 0) {
                        viewModel.addUser(name, ageInt)
                        name = ""
                        age = ""
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            CBasicField(
                value = name,
                onValueChange = { name = it },
                label = "Nama Anda",
                hint = "Enter your firstName"
            )
            Spacer(Modifier.height(16.dp))
            CBasicField(
                value = age,
                onValueChange = { age = it },
                label = "Umur Anda",
                hint = "Enter your age",
                keyboardType = KeyboardType.Number
            )
            Spacer(Modifier.height(24.dp))
            // List users
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(users) { user ->
                    Text("${user.id}: ${user.name}, ${user.age}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}