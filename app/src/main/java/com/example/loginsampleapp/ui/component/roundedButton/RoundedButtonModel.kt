package com.example.loginsampleapp.ui.component.roundedButton

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

data class RoundedButtonModel(
    val onClick: () -> Unit,
    val text: String,
    val modifier: Modifier = Modifier,
    val roundedCorner: Dp,
    val textSize: TextUnit,
    val backgroundColor: Color,
    val contentColor: Color
)
