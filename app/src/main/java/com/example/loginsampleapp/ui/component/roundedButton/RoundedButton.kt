import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.loginsampleapp.ui.component.roundedButton.RoundedButtonModel

@Composable
fun RoundedButton(model: RoundedButtonModel) {
    model.apply {
        Button(
            onClick = onClick,
            modifier = modifier,
            shape = RoundedCornerShape(roundedCorner),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor
            ),

            ) {
            Text(fontSize = (textSize), text = text)
        }
    }
}
