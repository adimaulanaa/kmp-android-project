package com.bantraka.learningandroid.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//! Use Code
//CButton(
//    text = "Sign in with Google",
//    variant = CButtonVariant.Filled,
//    leadingIcon = painterResource(Res.drawable.arrow_left),
//    onClick = { /* aksi */ }
//)


@Composable
fun LinkButton(
    text: String,
    onClick: () -> Unit
) {
    TextButton(onClick = onClick) {
        Text(
            text,
            style = MaterialTheme.typography.labelLarge.copy(
                color = Color(0xFF246BFD),
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}


enum class CButtonVariant { Filled, Outline }

@Composable
fun CButton(
    text: String,
    modifier: Modifier = Modifier,
    variant: CButtonVariant = CButtonVariant.Filled,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    leadingIcon: Painter? = null,
    cornerRadius: Dp = 14.dp,
    height: Dp = 52.dp,
    onClick: () -> Unit
) {
    // shared interaction source for ripple + button state
    val interactionSource = remember { MutableInteractionSource() }

    // shared content composable (icon + spacer + text or loading)
    @Composable
    fun Content() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.4.dp, // << tambah dikit biar lebih jelas
                    color = when (variant) {
                        CButtonVariant.Filled ->
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)

                        CButtonVariant.Outline ->
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.85f)
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
            } else {
                leadingIcon?.let {
                    androidx.compose.material3.Icon(
                        painter = it,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = when (variant) {
                            CButtonVariant.Filled -> MaterialTheme.colorScheme.onPrimary
                            CButtonVariant.Outline -> MaterialTheme.colorScheme.primary
                        }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }

            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }

    // ripple color that matches variant
    val rippleColor = when (variant) {
        CButtonVariant.Filled -> MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.16f)
        CButtonVariant.Outline -> MaterialTheme.colorScheme.primary.copy(alpha = 0.16f)
    }

    // common modifier with ripple indication
    val baseModifier = modifier
        .fillMaxWidth()
        .height(height)
        .indication(
            interactionSource = interactionSource,
            indication = ripple(bounded = true, color = rippleColor)
        )

    when (variant) {
        CButtonVariant.Filled -> {
            Button(
                onClick = { if (!isLoading) onClick() },
                enabled = enabled && !isLoading,
                modifier = baseModifier,
                shape = RoundedCornerShape(cornerRadius),
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                    disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.7f)
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Content()
            }
        }

        CButtonVariant.Outline -> {
            OutlinedButton(
                onClick = { if (!isLoading) onClick() },
                enabled = enabled && !isLoading,
                modifier = baseModifier,
                shape = RoundedCornerShape(cornerRadius),
                interactionSource = interactionSource,
                border = BorderStroke(
                    width = 1.5.dp,
                    color = if (enabled) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                )
            ) {
                Content()
            }
        }
    }
}