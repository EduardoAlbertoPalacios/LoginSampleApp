package com.example.loginsampleapp.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.example.loginsampleapp.ui.component.ShowAlertDialog
import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogModel
import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogType
import com.example.loginsampleapp.utils.LoginData.ERROR_DIALOG_TEST
import com.example.loginsampleapp.utils.LoginData.MESSAGE_BODY_DIALOG_TEST
import com.example.loginsampleapp.utils.LoginData.MESSAGE_TITLE_DIALOG_TEST
import com.example.loginsampleapp.utils.LoginData.SUCCESS_DIALOG_TEST
import com.example.loginsampleapp.utils.LoginData.TITLE_BUTTON_DIALOG_TEST
import org.junit.Rule
import org.junit.Test

class CustomAlertTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun customDialog_DisplayCorrectly() {
        composeTestRule.setContent {
            ShowAlertDialog(
                AlertDialogModel(
                    dialogType = AlertDialogType.SUCCESS,
                    title = MESSAGE_TITLE_DIALOG_TEST,
                    body = MESSAGE_BODY_DIALOG_TEST,
                    textButton = TITLE_BUTTON_DIALOG_TEST,
                    backgroundColorButton = Color.Black,
                    contentColorButton = Color.White,
                    actionButton = {}
                )
            )
        }

        composeTestRule.apply {
            onNodeWithContentDescription(SUCCESS_DIALOG_TEST).assertExists()
            onNodeWithText(MESSAGE_TITLE_DIALOG_TEST).assertExists()
            onNodeWithText(MESSAGE_BODY_DIALOG_TEST).assertExists()
            onNodeWithText(TITLE_BUTTON_DIALOG_TEST).assertExists()
        }
    }

    @Test
    fun customDialog_ShowSuccessDialog() {
        composeTestRule.setContent {
            ShowAlertDialog(
                AlertDialogModel(
                    dialogType = AlertDialogType.SUCCESS,
                    title = "",
                    body = "",
                    textButton = "",
                    backgroundColorButton = Color.Black,
                    contentColorButton = Color.White,
                    actionButton = {}
                )
            )
        }

        composeTestRule.apply {
            onNodeWithContentDescription(SUCCESS_DIALOG_TEST).assertExists()
        }
    }

    @Test
    fun customDialog_ShowErrorDialog() {
        composeTestRule.setContent {
            ShowAlertDialog(
                AlertDialogModel(
                    dialogType = AlertDialogType.ERROR,
                    title = "",
                    body = "",
                    textButton = "",
                    backgroundColorButton = Color.Black,
                    contentColorButton = Color.White,
                    actionButton = {}
                )
            )
        }

        composeTestRule.apply {
            onNodeWithContentDescription(ERROR_DIALOG_TEST).assertExists()
        }
    }
}
