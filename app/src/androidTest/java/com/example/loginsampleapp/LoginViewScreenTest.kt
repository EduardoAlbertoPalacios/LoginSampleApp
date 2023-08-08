package com.example.loginsampleapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogType
import com.example.loginsampleapp.ui.component.progressBar.ProgressBarLogoType
import com.example.loginsampleapp.ui.login.LoginScreenState
import com.example.loginsampleapp.ui.login.LoginViewScreen
import com.example.loginsampleapp.utils.LoginData.MESSAGE_ERROR_DIALOG_TEST
import com.example.loginsampleapp.utils.LoginData.MESSAGE_SUCCESS_DIALOG_TEST
import org.junit.Rule
import org.junit.Test

class LoginViewScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loginScreen_DisplayCorrectly() {
        composeTestRule.setContent {
            LoginViewScreen(
                screenState = LoginScreenState(),
                emailState = "",
                passwordState = "",
                updateEmail = {},
                updatePassword = {},
                showPassword = false,
                updateShowPassword = {},
                onDismissDialog = { },
                executeLogin = {}
            )
        }

        composeTestRule.apply {
            onNodeWithContentDescription(activity.getString(R.string.image_logo_login)).assertExists()
            onNodeWithText(activity.getString(R.string.email)).assertExists()
            onNodeWithText(activity.getString(R.string.password)).assertExists()
            onNodeWithText(activity.getString(R.string.sign_in)).assertExists()
            onNodeWithText(activity.getString(R.string.dont_have_an_account)).assertExists()
            onNodeWithText(activity.getString(R.string.create_one)).assertExists()
        }
    }

    @Test
    fun loginScreen_InvalidEmailAndPassword() {
        composeTestRule.setContent {
            LoginViewScreen(
                screenState = LoginScreenState(
                    isNotValidEmail = true,
                    isNotValidPassword = true
                ),
                emailState = "",
                passwordState = "",
                updateEmail = {},
                updatePassword = {},
                showPassword = false,
                updateShowPassword = {},
                onDismissDialog = { },
                executeLogin = {}
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.email_error)).assertIsDisplayed()
            onNodeWithText(activity.getString(R.string.password_error)).assertIsDisplayed()
        }
    }

    @Test
    fun loginScreen_InvalidEmail() {
        composeTestRule.setContent {
            LoginViewScreen(
                screenState = LoginScreenState(isNotValidEmail = true),
                emailState = "",
                passwordState = "",
                updateEmail = {},
                updatePassword = {},
                showPassword = false,
                updateShowPassword = {},
                onDismissDialog = { },
                executeLogin = {}
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.email_error)).assertIsDisplayed()
        }
    }

    @Test
    fun loginScreen_InvalidPassword() {
        composeTestRule.setContent {
            LoginViewScreen(
                screenState = LoginScreenState(isNotValidPassword = true),
                emailState = "",
                passwordState = "",
                updateEmail = {},
                updatePassword = {},
                showPassword = false,
                updateShowPassword = {},
                onDismissDialog = { },
                executeLogin = {}
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.password_error)).assertIsDisplayed()
        }
    }

    @Test
    fun loginScreen_ShowSuccessDialog() {
        composeTestRule.setContent {
            LoginViewScreen(
                screenState = LoginScreenState(
                    dialogType = AlertDialogType.SUCCESS,
                    messageDialog = MESSAGE_SUCCESS_DIALOG_TEST
                ),
                emailState = "",
                passwordState = "",
                updateEmail = {},
                updatePassword = {},
                showPassword = false,
                updateShowPassword = {},
                onDismissDialog = { },
                executeLogin = {}
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.Welcome)).assertExists()
            onNodeWithText(MESSAGE_SUCCESS_DIALOG_TEST).assertExists()
        }
    }

    @Test
    fun loginScreen_ShowErrorDialog() {
        composeTestRule.setContent {
            LoginViewScreen(
                screenState = LoginScreenState(
                    dialogType = AlertDialogType.ERROR,
                    messageDialog = MESSAGE_ERROR_DIALOG_TEST
                ),
                emailState = "",
                passwordState = "",
                updateEmail = {},
                updatePassword = {},
                showPassword = false,
                updateShowPassword = {},
                onDismissDialog = { },
                executeLogin = {}
            )
        }

        composeTestRule.apply {
            onNodeWithText(activity.getString(R.string.Error)).assertExists()
            onNodeWithText(MESSAGE_ERROR_DIALOG_TEST).assertExists()
        }
    }

    @Test
    fun loginScreen_ShowLoading() {
        composeTestRule.setContent {
            LoginViewScreen(
                screenState = LoginScreenState(
                    isLoading = true,
                    progressBarLogoType = ProgressBarLogoType.NONE
                ),
                emailState = "",
                passwordState = "",
                updateEmail = {},
                updatePassword = {},
                showPassword = false,
                updateShowPassword = {},
                onDismissDialog = { },
                executeLogin = {}
            )
        }

        composeTestRule.apply {
            onNodeWithTag(activity.getString(R.string.baubap_loader)).assertIsDisplayed()
        }
    }
}
