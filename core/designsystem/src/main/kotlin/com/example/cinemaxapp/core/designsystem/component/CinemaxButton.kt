package com.example.cinemaxapp.core.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinemaxapp.core.designsystem.theme.CinemaxShapes
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme
import com.example.cinemaxapp.core.designsystem.theme.Dimens

@Composable
fun CinemaxButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = CinemaxTheme.colors.blueAccent,
    contentColor: Color = CinemaxTheme.colors.white,
) {
    Button(
        onClick = onClick,
        shape = CinemaxShapes.extraLarge,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.buttonHeightExtraLarge),
    ) {

        Text(
            text = text,
            style = CinemaxTheme.typography.h4Medium,
        )

    }
}

// Preview
@Preview(name = "Extra Large Button - Primary", showBackground = true, backgroundColor = 0xFF1F1D2B)
@Composable
private fun CinemaxButtonPreview() {
    CinemaxTheme {
        CinemaxButton(
            text = "Sign Up",
            onClick = {},
        )
    }
}





