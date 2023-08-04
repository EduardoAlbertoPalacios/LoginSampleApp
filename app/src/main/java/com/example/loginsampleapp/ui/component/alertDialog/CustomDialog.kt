package com.example.loginsampleapp.ui.component

import RoundedButton
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.example.loginsampleapp.R
import com.example.loginsampleapp.ui.component.alertDialog.AlertDialogType
import com.example.loginsampleapp.ui.theme.Dimens
import com.example.loginsampleapp.ui.theme.Dimens.Elevation5
import com.example.loginsampleapp.ui.theme.Dimens.RoundedCorner12
import com.example.loginsampleapp.ui.theme.TextSize.FontSize16
import com.example.loginsampleapp.ui.theme.TextSize.FontSize30

@Composable
fun ShowAlertDialog(
    dialogType: AlertDialogType,
    title: String,
    body: String,
    action: () -> Unit
) {
    Dialog(
        onDismissRequest = { action() }
    ) {
        CustomDialog(
            dialogType = dialogType,
            title = title,
            body = body,
            buttonText = stringResource(R.string.accept),
            actionButton = { action() },
        )
    }
}

@Composable
fun CustomDialog(
    dialogType: AlertDialogType,
    title: String,
    body: String,
    buttonText: String,
    actionButton: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(RoundedCorner12),
        elevation = Elevation5,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(Modifier.padding(Dimens.Padding16)) {
            Icon(
                painter = painterResource(
                    id = if (dialogType == AlertDialogType.SUCCESS)
                        R.drawable.ic_action_done
                    else
                        R.drawable.ic_action_error

                ),
                contentDescription = stringResource(id = R.string.icon),
                tint = if (dialogType == AlertDialogType.SUCCESS)
                    Color.Green
                else
                    Color.Red
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    fontSize = FontSize30,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.size(Dimens.Padding16))

                Text(
                    text = body,
                    fontSize = FontSize16,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                )

                Spacer(modifier = Modifier.size(Dimens.Padding28))

                RoundedButton(
                    onClick = actionButton,
                    text = buttonText,
                    modifier = Modifier.fillMaxWidth(),
                    roundedCorner = Dimens.RoundedCorner24,
                    textSize = FontSize16,
                )
            }
        }
    }
}
