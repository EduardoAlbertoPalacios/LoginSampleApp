package com.example.loginsampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.loginsampleapp.ui.login.LoginScreenState
import com.example.loginsampleapp.ui.login.LoginViewModel
import com.example.loginsampleapp.ui.login.LoginViewScreen
import com.example.loginsampleapp.ui.theme.LoginSampleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSampleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = hiltViewModel<LoginViewModel>()
                    viewModel.apply {
                        LoginViewScreen(
                            screenState = state,
                            emailState = email,
                            passwordState = password,
                            updateEmail = ::updateEmail,
                            updatePassword = ::updatePassword,
                            onDismissDialog = ::onDismissDialog,
                            executeLogin = ::executeLogin
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginSampleAppTheme {
        LoginViewScreen(
            screenState = LoginScreenState(),
            emailState = "",
            passwordState = "",
            updateEmail = {},
            updatePassword = {},
            onDismissDialog = {},
            executeLogin = {}
        )
    }
}