package com.example.cinemaxapp.feature.auth.signup

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
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onTermsClick: () -> Unit = {},
    onSignUpClick: (name: String, email: String, password: String) -> Unit = { _, _, _ -> },
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isTermsAccepted by remember { mutableStateOf(false) }

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

            Text(
                text = "Sign Up",
                style = CinemaxTheme.typography.h4SemiBold,
                color = CinemaxTheme.colors.white,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Let's get started",
            style = CinemaxTheme.typography.h2SemiBold,
            color = CinemaxTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "The latest movies and series\nare here",
            style = CinemaxTheme.typography.h6Medium,
            color = CinemaxTheme.colors.whiteGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Full Name field
        CinemaxTextField(
            value = fullName,
            onValueChange = {fullName = it},
            label = "Full Name",
            placeholder = "Tiffany",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email field
        CinemaxTextField(
            value = email,
            onValueChange = {email = it},
            label = "Email Address",
            placeholder = "Tiffanyjearsey@gmail.com",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Password field
        CinemaxTextField(
            value = password,
            onValueChange = {password = it},
            label = "Password",
            placeholder = "password123",
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        imageVector = if(isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Terms & Privacy Policy checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isTermsAccepted,
                onCheckedChange = { isTermsAccepted = it },
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))

            val annotatedText = buildAnnotatedString {
                withStyle(SpanStyle(color = CinemaxTheme.colors.whiteGrey)) {
                    append("I agree to the ")
                }
                withStyle(SpanStyle(color = CinemaxTheme.colors.blueAccent)) {
                    append("Terms and Services")
                }
                withStyle(SpanStyle(color = CinemaxTheme.colors.whiteGrey)) {
                    append(" and ")
                }
                withStyle(SpanStyle(color = CinemaxTheme.colors.blueAccent)) {
                    append("Privacy Policy")
                }
            }

            // Simple approach: whole text is clickable to open Terms;
            // swap for ClickableText with per-range offsets if you need the
            // two links to open different screens.
            Text(
                text = annotatedText,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.clickable { onTermsClick() },
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Reusing your existing CinemaxButton component
        CinemaxButton(
            text = "Sign Up",
            onClick = { onSignUpClick(fullName, email, password) },
            enabled = isTermsAccepted,
        )

        Spacer(modifier = Modifier.height(176.dp))
    }
}
