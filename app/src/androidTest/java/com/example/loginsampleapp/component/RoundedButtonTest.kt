package com.example.loginsampleapp.component

import RoundedButton
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.example.loginsampleapp.R
import com.example.loginsampleapp.ui.component.roundedButton.RoundedButtonModel
import com.example.loginsampleapp.ui.theme.TextSize
import com.example.loginsampleapp.utils.LoginData.ACCEPT_TEST
import org.junit.Rule
import org.junit.Test

class RoundedButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun roundedButton_DisplayTextCorrectly() {
        composeTestRule.setContent {
            RoundedButton(
                model = RoundedButtonModel(
                    onClick = {},
                    text = stringResource(R.string.accept),
                    modifier = Modifier.fillMaxWidth(),
                    roundedCorner = 8.dp,
                    textSize = TextSize.FontSize20,
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                )
            )
        }

        composeTestRule.apply {
            onNodeWithText(ACCEPT_TEST).assertExists()
        }
    }
}
