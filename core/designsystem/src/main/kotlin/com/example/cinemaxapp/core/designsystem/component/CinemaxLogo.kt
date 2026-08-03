package com.example.cinemaxapp.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons

@Composable
fun CinemaxLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = CinemaxIcons.Logo),
        contentDescription = "Cinemax Logo",
        modifier = modifier.size(72.dp)
    )
}
