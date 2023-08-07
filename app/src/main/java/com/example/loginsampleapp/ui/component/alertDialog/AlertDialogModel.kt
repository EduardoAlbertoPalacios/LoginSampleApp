package com.example.loginsampleapp.ui.component.alertDialog

import androidx.compose.ui.graphics.Color

data class AlertDialogModel(
    val dialogType: AlertDialogType,
    val title: String,
    val body: String,
    val textButton: String,
    val backgroundColorButton: Color,
    val contentColorButton: Color,
    val actionButton: () -> Unit
)
