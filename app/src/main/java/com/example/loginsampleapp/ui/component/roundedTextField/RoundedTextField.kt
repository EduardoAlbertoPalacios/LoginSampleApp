package com.example.loginsampleapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import com.example.loginsampleapp.ui.component.roundedTextField.RoundedTextFieldModel

@Composable
fun RoundedTextField(model: RoundedTextFieldModel) {
    model.apply {
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
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager?.clearFocus() },
                    onNext = { focusRequester?.requestFocus() }
                ),
                visualTransformation = visualTransformation,
            )
            supportingText?.invoke()
        }
    }
}
