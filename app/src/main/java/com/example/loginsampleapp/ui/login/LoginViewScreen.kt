package com.example.loginsampleapp.ui.login

import RoundedButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.loginsampleapp.R
import com.example.loginsampleapp.ui.component.CustomProgressBar
import com.example.loginsampleapp.ui.component.RoundedTextField
import com.example.loginsampleapp.ui.component.ShowAlertDialog
import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogModel
import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogType
import com.example.loginsampleapp.ui.component.roundedButton.RoundedButtonModel
import com.example.loginsampleapp.ui.component.roundedTextField.RoundedTextFieldModel
import com.example.loginsampleapp.ui.theme.Dimens
import com.example.loginsampleapp.ui.theme.Dimens.Padding24
import com.example.loginsampleapp.ui.theme.PrimaryColor
import com.example.loginsampleapp.ui.theme.TextSize.FontSize16
import com.example.loginsampleapp.ui.theme.TextSize.FontSize20

@Composable
fun LoginViewScreen(
    screenState: LoginScreenState,
    emailState: String,
    passwordState: String,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    onDismissDialog: () -> Unit,
    executeLogin: () -> Unit
) {
    LoginScreenContent(
        loginScreenState = screenState,
        emailState = emailState,
        passwordState = passwordState,
        emailChanged = updateEmail,
        passwordChanged = updatePassword,
        actionAlert = onDismissDialog,
        executeLogin = executeLogin
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreenContent(
    loginScreenState: LoginScreenState,
    emailState: String,
    passwordState: String,
    emailChanged: (String) -> Unit,
    passwordChanged: (String) -> Unit,
    actionAlert: () -> Unit,
    executeLogin: () -> Unit

) {
    var showPassword by remember { mutableStateOf(false) }

    val (focusRequester) = FocusRequester.createRefs()

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PrimaryColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                Modifier
                    .padding(Dimens.Padding10)
                    .size(Dimens.Size200)
            )

            RoundedTextField(
                RoundedTextFieldModel(
                    value = emailState,
                    onValueChange = { newText -> emailChanged(newText) },
                    modifier = Modifier
                        .padding(start = Dimens.Padding16, end = Dimens.Padding16)
                        .fillMaxWidth(),
                    roundedCorner = Dimens.RoundedCorner24,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = stringResource(R.string.email),
                        )
                    },
                    placeHolderText = stringResource(R.string.email),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = VisualTransformation.None,
                    isError = loginScreenState.isNotValidEmail,
                    focusRequester = focusRequester,
                    focusManager = LocalFocusManager.current,
                    supportingText = {
                        if (loginScreenState.isNotValidEmail) {
                            Text(
                                modifier = Modifier.padding(start = Padding24),
                                text = stringResource(id = R.string.email_error),
                                color = Color.Red,
                            )
                        }
                    },
                )
            )

            Spacer(modifier = Modifier.size(Dimens.Padding34))

            RoundedTextField(
                RoundedTextFieldModel(
                    value = passwordState,
                    onValueChange = { newText -> passwordChanged(newText) },
                    modifier = Modifier
                        .padding(start = Dimens.Padding16, end = Dimens.Padding16)
                        .fillMaxWidth(),
                    roundedCorner = Dimens.RoundedCorner24,
                    trailingIcon = {
                        IconButton(onClick = { showPassword = !showPassword }) {
                            Icon(
                                imageVector = if (showPassword) {
                                    Icons.Outlined.Face
                                } else {
                                    Icons.Outlined.Lock
                                },
                                contentDescription = stringResource(R.string.password)
                            )
                        }
                    },
                    placeHolderText = stringResource(R.string.password),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = if (showPassword) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    isError = loginScreenState.isNotValidPassword,
                    focusRequester = focusRequester,
                    focusManager = LocalFocusManager.current,
                    supportingText = {
                        if (loginScreenState.isNotValidPassword) {
                            Text(
                                modifier = Modifier.padding(start = Padding24),
                                text = stringResource(id = R.string.password_error),
                                color = Color.Red,
                            )
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.size(Dimens.Padding30))

            RoundedButton(
                RoundedButtonModel(
                    onClick = { executeLogin.invoke() },
                    text = stringResource(id = R.string.sign_in),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimens.Padding16),
                    roundedCorner = Dimens.RoundedCorner24,
                    textSize = FontSize20,
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = Color.Black
                )
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(Dimens.Padding16),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.dont_have_an_account),
                    fontSize = FontSize16,
                    textAlign = TextAlign.Start,
                    color = Color.White
                )

                Spacer(modifier = Modifier.width(Dimens.Size6))

                Text(
                    text = stringResource(R.string.create_one),
                    fontSize = FontSize16,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
            if (loginScreenState.dialogType != AlertDialogType.NONE) {
                ShowAlertDialog(
                    AlertDialogModel(
                        dialogType = loginScreenState.dialogType,
                        title = stringResource(
                            id = if (loginScreenState.dialogType == AlertDialogType.SUCCESS)
                                R.string.Welcome
                            else
                                R.string.Error
                        ),
                        body = loginScreenState.messageDialog,
                        textButton = stringResource(id = R.string.accept),
                        actionButton = actionAlert,
                        backgroundColorButton = MaterialTheme.colors.primary,
                        contentColorButton = Color.White
                    )
                )
            }
        }
        if (loginScreenState.isLoading) {
            CustomProgressBar()
        }
    }
}
