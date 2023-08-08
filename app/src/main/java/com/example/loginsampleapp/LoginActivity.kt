package com.example.loginsampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.loginsampleapp.ui.login.LoginScreenState
import com.example.loginsampleapp.ui.login.LoginViewModel
import com.example.loginsampleapp.ui.login.LoginViewScreen
import com.example.loginsampleapp.ui.theme.LoginSampleAppTheme
import org.koin.androidx.compose.koinViewModel

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginSampleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel = koinViewModel<LoginViewModel>()
                    viewModel.apply {
                        val state by state.collectAsStateWithLifecycle()
                        LoginViewScreen(
                            screenState = state,
                            emailState = email,
                            passwordState = password,
                            updateEmail = ::updateEmail,
                            updatePassword = ::updatePassword,
                            showPassword = showPassword,
                            updateShowPassword = ::updateShowPassword,
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
            showPassword = false,
            updateShowPassword = {},
            onDismissDialog = {},
            executeLogin = {}
        )
    }
}
