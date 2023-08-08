package com.example.loginsampleapp.ui.login

import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogType
import com.example.loginsampleapp.ui.component.progressBar.ProgressBarLogoType

data class LoginScreenState(
    val isLoading: Boolean = false,
    val progressBarLogoType: ProgressBarLogoType = ProgressBarLogoType.SPIRAL,
    val isNotValidEmail: Boolean = false,
    val isNotValidPassword: Boolean = false,
    val dialogType: AlertDialogType = AlertDialogType.NONE,
    val messageDialog: String = ""
)
