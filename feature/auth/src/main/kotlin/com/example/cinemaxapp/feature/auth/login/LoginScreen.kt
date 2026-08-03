package com.example.cinemaxapp.feature.auth.login

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinemaxapp.core.designsystem.component.CinemaxButton
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme

@Composable
fun LoginScreen(
    userName: String = "Tiffany",
    onBackClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onLoginClick: (email: String, password: String) -> Unit = { _, _ -> },
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

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

            Text(
                text = "Login",
                style = CinemaxTheme.typography.h4SemiBold,
                color = CinemaxTheme.colors.white,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Hi, $userName",
            style = CinemaxTheme.typography.h2SemiBold,
            color = CinemaxTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Welcome back! Please enter\nyour details.",
            style = CinemaxTheme.typography.h6Medium,
            color = CinemaxTheme.colors.whiteGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Email field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text("Email Address")
            },
            placeholder = {
                Text(
                    text = "Tiffanyjearsey@gmail.com"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text("Password")
            },
            placeholder = {
                Text(
                    text = "password123"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility",
                    )
                }
            },
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot password
        Text(
            text = "Forgot Password?",
            style = CinemaxTheme.typography.h6Medium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onForgotPasswordClick() },
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Reusing your existing CinemaxButton component
        CinemaxButton(
            text = "Login",
            onClick = { onLoginClick(email, password) },
        )

        Spacer(modifier = Modifier.height(292.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF13131F)
@Composable
private fun LoginScreenPreview() {
    CinemaxTheme {
        LoginScreen()
    }
}