package com.example.loginsampleapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginsampleapp.utils.isNotValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var state by mutableStateOf(LoginScreenState())

    fun executeLogin() {
        viewModelScope.launch {
            verifyInputs {

            }
        }
    }

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun onDismissDialog() {
        updateState(showAlertDialog = false)
    }

    private fun verifyInputs(executeLogin: () -> Unit) {
        when {
            email.isNotValidEmail() && password.isBlank() ->
                updateState(
                    isNotValidEmail = true,
                    isNotValidPassword = true,
                )

            email.isNotValidEmail() ->
                updateState(
                    isNotValidEmail = true,
                    isNotValidPassword = false,
                )

            password.isBlank() ->
                updateState(
                    isNotValidEmail = false,
                    isNotValidPassword = true,
                )

            else -> {
                updateState(
                    isLoading = true,
                    isNotValidEmail = false,
                    isNotValidPassword = false,
                )
                executeLogin.invoke()
            }
        }
    }

    private fun updateState(
        isLoading: Boolean = false,
        isNotValidEmail: Boolean = false,
        isNotValidPassword: Boolean = false,
        showAlertDialog: Boolean = false,
        success: String? = null,
        error: String? = null
    ) {
        state = state.copy(
            isLoading = isLoading,
            isNotValidEmail = isNotValidEmail,
            isNotValidPassword = isNotValidPassword,
            showAlertDialog = showAlertDialog,
            success = success,
            error = error
        )
    }
}

data class LoginScreenState(
    val isLoading: Boolean = false,
    val isNotValidEmail: Boolean = false,
    val isNotValidPassword: Boolean = false,
    val showAlertDialog: Boolean = false,
    val success: String? = null,
    val error: String? = null,
)
