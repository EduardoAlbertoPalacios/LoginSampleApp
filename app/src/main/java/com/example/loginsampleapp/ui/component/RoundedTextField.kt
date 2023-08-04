package com.example.loginsampleapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.loginsampleapp.ui.theme.SecondaryColor

@Composable
fun RoundedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    roundedCorner: Dp,
    trailingIcon: @Composable (() -> Unit)? = null,
    placeHolderText: String,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    focusRequester: FocusRequester? = null
) {
    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            isError = isError,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = backgroundColor),
            textStyle = TextStyle(color = textColor),
            shape = RoundedCornerShape(roundedCorner),
            trailingIcon = trailingIcon,
            placeholder = { Text(text = placeHolderText) },
            singleLine = true,
            keyboardOptions  = keyboardOptions,
            keyboardActions = KeyboardActions(
                onNext = { focusRequester?.requestFocus() }
            ),
            visualTransformation = visualTransformation,
        )
        supportingText?.invoke()
    }
}
