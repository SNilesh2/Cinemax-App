package com.example.cinemaxapp.feature.auth.resetpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cinemaxapp.core.designsystem.component.CinemaxButton
import com.example.cinemaxapp.core.designsystem.component.CinemaxTextField
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme

@Composable
fun ResetPasswordScreen(
    onBackClick: () -> Unit = {},
    onNextClick: (email: String) -> Unit = {},
) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
    ) {

        Spacer(modifier = Modifier.height(52.dp))

        // Top bar: back button + centered title
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable { onBackClick() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(CinemaxIcons.BackArrow),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Reset Password",
            style = CinemaxTheme.typography.h2SemiBold,
            color = CinemaxTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Recover your account password",
            style = CinemaxTheme.typography.h5Medium,
            color = CinemaxTheme.colors.whiteGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Email field
        CinemaxTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email Address",
            placeholder = "Tiffanyjearsey@gmail.com",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Reusing your existing CinemaxButton component
        CinemaxButton(
            text = "Next",
            onClick = { onNextClick(email) },
        )

        Spacer(modifier = Modifier.height(425.dp))
    }
}

