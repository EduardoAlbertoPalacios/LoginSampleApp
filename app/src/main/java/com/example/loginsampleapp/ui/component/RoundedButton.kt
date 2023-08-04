import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun RoundedButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    roundedCorner: Dp,
    textSize : TextUnit,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = Color.White
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape =  RoundedCornerShape(roundedCorner),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),

        ) {
        Text(fontSize = (textSize),text= text)
    }
}
