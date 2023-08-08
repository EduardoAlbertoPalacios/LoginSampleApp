package com.example.loginsampleapp.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.loginsampleapp.R
import com.example.loginsampleapp.ui.component.RoundedTextField
import com.example.loginsampleapp.ui.component.roundedTextField.RoundedTextFieldModel
import com.example.loginsampleapp.utils.LoginData.EMAIL_TEST
import com.example.loginsampleapp.utils.LoginData.NAME_TEST
import org.junit.Rule
import org.junit.Test

class RoundedTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun roundedTextField_DisplayTextAndIconCorrectly() {
        composeTestRule.setContent {
            var name = "Eduardo Alberto"
            RoundedTextField(
                model = RoundedTextFieldModel(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    roundedCorner = 2.dp,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = stringResource(R.string.email),
                        )
                    },
                    placeHolderText = "",
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    keyboardOptions = KeyboardOptions.Default,
                    visualTransformation = VisualTransformation.None,
                    isError = false,
                )
            )
        }

        composeTestRule.apply {
            onNodeWithText(NAME_TEST).assertExists()
            onNodeWithContentDescription(EMAIL_TEST)
        }
    }
}
