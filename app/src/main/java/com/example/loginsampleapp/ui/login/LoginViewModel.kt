package com.example.loginsampleapp.ui.login

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baubap.domain.entities.AuthEntity
import com.baubap.domain.usecase.Params
import com.baubap.domain.usecase.UseCase
import com.baubap.shared.common.ProcessResult
import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogType
import com.example.loginsampleapp.ui.login.mapper.mapToSuccessMessageLogin
import com.example.loginsampleapp.utils.isNotValidEmail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: UseCase<Params, ProcessResult<AuthEntity>>) :
    ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var showPassword  by mutableStateOf(false)

    var _state = MutableStateFlow(LoginScreenState())
    var state = _state.asStateFlow()

    fun executeLogin() {
        viewModelScope.launch {
            verifyInputs(
                request = {
                    useCase.execute(Params(email, password)).onStart {
                        updateState(
                            isLoading = true,
                            isNotValidEmail = false,
                            isNotValidPassword = false,
                        )
                    }.collect { result ->
                        when (result) {
                            is ProcessResult.Success -> {
                                updateState(
                                    isLoading = false,
                                    dialogType = AlertDialogType.SUCCESS,
                                    messageDialog = result.data.mapToSuccessMessageLogin()
                                )
                            }
                            is ProcessResult.Error -> {
                                result.exception.message?.let { error ->
                                    updateState(
                                        isLoading = false,
                                        dialogType = AlertDialogType.ERROR,
                                        messageDialog = error
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    }

    fun updateShowPassword(isVisible: Boolean) {
        showPassword = isVisible
    }

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun onDismissDialog() {
        updateState(dialogType = AlertDialogType.NONE)
    }

    private suspend fun verifyInputs(request: suspend () -> Unit) {
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

            else -> request.invoke()
        }
    }

    private fun updateState(
        isLoading: Boolean = false,
        isNotValidEmail: Boolean = false,
        isNotValidPassword: Boolean = false,
        dialogType: AlertDialogType = AlertDialogType.NONE,
        messageDialog: String = "",
    ) = _state.update {
        it.copy(
            isLoading = isLoading,
            isNotValidEmail = isNotValidEmail,
            isNotValidPassword = isNotValidPassword,
            dialogType = dialogType,
            messageDialog = messageDialog,
        )
    }
}
