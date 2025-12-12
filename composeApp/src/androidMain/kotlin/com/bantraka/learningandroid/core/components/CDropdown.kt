package com.bantraka.learningandroid.core.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

// Model Dropdown
data class DropdownItem(
    val value: String,
    val label: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CBasicDropdown(
    label: String,
    value: String,
    hint: String,
    options: List<String>,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
    val borderColor = if (value.isNotEmpty()) primary.copy(alpha = 0.6f) else outline

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(Modifier.height(6.dp))
        Box {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                placeholder = {
                    Text(
                        hint,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                    )
                },

                trailingIcon = {
                    Icon(
                        imageVector = if (expanded)
                            Icons.Default.KeyboardArrowUp
                        else
                            Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                },

                shape = RoundedCornerShape(12.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    disabledBorderColor = outline,
                    cursorColor = primary,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White.copy(alpha = 0.7f),
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )

            // overlay clickable = klik di seluruh field
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CDropdown(
    label: String,
    value: DropdownItem?,
    hint: String,
    options: List<DropdownItem>,
    onSelected: (DropdownItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
    val borderColor = if (value != null) primary.copy(alpha = 0.6f) else outline

    Column(modifier = modifier.fillMaxWidth()) {

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(Modifier.height(6.dp))

        Box {
            OutlinedTextField(
                value = value?.label ?: "",
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                    )
                },

                trailingIcon = {
                    Icon(
                        imageVector = if (expanded)
                            Icons.Default.KeyboardArrowUp
                        else
                            Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                },

                shape = RoundedCornerShape(12.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    disabledBorderColor = outline,
                    cursorColor = primary,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White.copy(alpha = 0.7f),
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = true }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                options.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item.label,  // ⭐ tampilkan label
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onSelected(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CDropdownSearch(
    label: String,
    value: DropdownItem?,
    hint: String,
    options: List<DropdownItem>,
    onSelected: (DropdownItem) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    // Filtering
    val filteredList = remember(searchQuery, options) {
        if (searchQuery.isBlank()) options
        else options.filter { it.label.contains(searchQuery, ignoreCase = true) }
    }

    val primary = MaterialTheme.colorScheme.primary
    val outline = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
    val borderColor = if (value != null) primary.copy(alpha = 0.6f) else outline

    Column(modifier = modifier.fillMaxWidth()) {

        // Label
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f),
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(Modifier.height(6.dp))

        Box {
            // Main field (non-editable)
            OutlinedTextField(
                value = value?.label ?: "",
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                placeholder = {
                    Text(
                        text = hint,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                        )
                    )
                },

                trailingIcon = {
                    Icon(
                        imageVector = if (expanded)
                            Icons.Default.KeyboardArrowUp
                        else
                            Icons.Default.KeyboardArrowDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                },

                shape = RoundedCornerShape(12.dp),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    cursorColor = primary,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                )
            )

            // Overlay entire area to detect clicks
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable {
                        expanded = true
                        searchQuery = ""
                    }
            )

            if (expanded) {
                Popup(
                    alignment = Alignment.TopStart,
                    onDismissRequest = { expanded = false },
                    properties = PopupProperties(
                        focusable = true,
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                ) {
                    // Card container (dropdown box)
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        tonalElevation = 6.dp,
                        shadowElevation = 6.dp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(IntrinsicSize.Min)
                                .padding(vertical = 8.dp)
                        ) {
                            // SEARCH BOX (always visible)
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp),
                                placeholder = { Text("Search...") },
                                shape = RoundedCornerShape(10.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = primary.copy(alpha = 0.6f),
                                    unfocusedBorderColor = outline,
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                ),
                            )

                            Spacer(Modifier.height(6.dp))

                            // LIST ITEMS (scrollable only)
                            Box(
                                modifier = Modifier
                                    .heightIn(max = 240.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                Column {
                                    filteredList.forEach { item ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text = item.label,
                                                    style = MaterialTheme.typography.bodyMedium
                                                )
                                            },
                                            onClick = {
                                                onSelected(item)
                                                expanded = false
                                            }
                                        )
                                    }

                                    if (filteredList.isEmpty()) {
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    "No results",
                                                    textAlign = TextAlign.Center,
                                                    modifier = Modifier.fillMaxWidth(),
                                                    style = MaterialTheme.typography.bodyMedium.copy(
                                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                                                    )
                                                )
                                            },
                                            onClick = {}
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




