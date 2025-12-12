package com.bantraka.learningandroid.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bantraka.learningandroid.core.components.AppTopBar
import com.bantraka.learningandroid.core.components.CBasicField
import com.bantraka.learningandroid.core.components.CButton
import com.bantraka.learningandroid.core.components.CPasswordField
import learningandroid.composeapp.generated.resources.Res
import learningandroid.composeapp.generated.resources.arrow_left

@Composable
fun ComponentsPage(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = "Components Page",
                icon = Res.drawable.arrow_left,
                onBack = onBack
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            CButton(
                text = "Go to Floating Btn",
                modifier = Modifier.padding(horizontal = 16.dp),
                onClick = { onBack() }
            )
        },
    ) { paddingValues ->
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Anda di Components Page")
            Spacer(Modifier.height(16.dp))
            CBasicField(
                value = username,
                onValueChange = { username = it },
                label = "Username",
                hint = "Enter your username"
            )
            Spacer(Modifier.height(16.dp))
            CPasswordField(
                value = password,
                onValueChange = { password = it },
                hint = "Enter your Password"
            )
        }
    }
}