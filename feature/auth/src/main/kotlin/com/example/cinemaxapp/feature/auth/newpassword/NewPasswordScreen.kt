package com.example.cinemaxapp.feature.auth.newpassword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinemaxapp.core.designsystem.component.CinemaxButton
import com.example.cinemaxapp.core.designsystem.component.CinemaxTextField
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme

@Composable
fun NewPasswordScreen(
    onBackClick: () -> Unit = {},
    onResetClick: (newPassword: String,confirmPassword: String) -> Unit = {_,_ -> },
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isNewPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
    ) {

        Spacer(modifier = Modifier.height(52.dp))

        // Top bar: back button
        Box(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(CinemaxTheme.colors.soft)
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
            text = "Create New Password",
            style = CinemaxTheme.typography.h2SemiBold,
            color = CinemaxTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Enter your new password",
            style = CinemaxTheme.typography.h5Medium,
            color = CinemaxTheme.colors.whiteGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(48.dp))

        // New Password field
        CinemaxTextField(
            value = newPassword,
            onValueChange = {newPassword = it},
            label = "New Password",
            placeholder = "password123",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if(isNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    isNewPasswordVisible = !isNewPasswordVisible
                }) {
                    Icon(
                        imageVector = if(isNewPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Confirm Password field
        CinemaxTextField(
            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            label = "Confirm Password",
            placeholder = "password123",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if(isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    isConfirmPasswordVisible = !isConfirmPasswordVisible
                }) {
                    Icon(
                        imageVector = if(isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Reusing your existing CinemaxButton component
        CinemaxButton(
            text = "Reset",
            onClick = { onResetClick(newPassword,confirmPassword) }
        )

        Spacer(modifier = Modifier.height(335.dp))
    }
}
