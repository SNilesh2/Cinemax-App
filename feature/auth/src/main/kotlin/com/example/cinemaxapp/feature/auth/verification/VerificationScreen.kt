package com.example.cinemaxapp.feature.auth.verification

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinemaxapp.core.designsystem.component.CinemaxButton
import com.example.cinemaxapp.core.designsystem.component.CinemaxTextField
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme

private const val OTP_LENGTH = 4

@Composable
fun VerificationScreen(
    email: String = "example@gmail.com",
    onBackClick: () -> Unit = {},
    onContinueClick: (otp: String) -> Unit = {},
    onResendClick: () -> Unit = {}
) {

    var otp by remember { mutableStateOf("") }

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
            text = "Verifying Your Account",
            style = CinemaxTheme.typography.h2SemiBold,
            color = CinemaxTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        val annotatedText = buildAnnotatedString {
            withStyle(SpanStyle(color = CinemaxTheme.colors.whiteGrey)) {
                append("We have just sent you 4 digit code via your email ")
            }
            withStyle(SpanStyle(color = CinemaxTheme.colors.white, fontWeight = FontWeight.Bold)) {
                append(email)
            }
        }

        Text(
            text = annotatedText,
            style = CinemaxTheme.typography.h5Medium,
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier.height(32.dp))

        OtpInputRow(
            otp = otp,
            onOtpChange = { otp = it },
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Reusing your existing CinemaxButton component
        CinemaxButton(
            text = "Continue",
            onClick = { onContinueClick(otp) },
        )

        Spacer(modifier = Modifier.height(42.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Didn't receive code? ",
                style = CinemaxTheme.typography.h4Medium,
                color = CinemaxTheme.colors.grey,
            )
            Text(
                text = "Resend",
                style = CinemaxTheme.typography.h4SemiBold,
                color = CinemaxTheme.colors.blueAccent,
                modifier = Modifier.clickable { onResendClick() },
            )
        }
    }
}


@Composable
private fun OtpInputRow(
    otp: String,
    onOtpChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = modifier.fillMaxWidth()) {
        // Invisible field that actually receives input/focus
        BasicTextField(
            value = otp,
            onValueChange = { newValue ->
                if (newValue.length <= OTP_LENGTH && newValue.all { it.isDigit() }) {
                    onOtpChange(newValue)
                }
                if (newValue.length == OTP_LENGTH) {
                    keyboardController?.hide()
                }
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .size(1.dp), // effectively invisible, but still focusable
            textStyle = TextStyle(color = Color.Transparent),
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
            ),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    focusRequester.requestFocus()
                    keyboardController?.show()
                },
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            repeat(OTP_LENGTH) { index ->
                val char = otp.getOrNull(index)?.toString() ?: ""
                val isFocusedBox = index == otp.length

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .border(
                            width = if (isFocusedBox || char.isNotEmpty()) 2.dp else 0.dp,
                            color = if (isFocusedBox || char.isNotEmpty())
                                MaterialTheme.colorScheme.primary
                            else Color.Transparent,
                            shape = RoundedCornerShape(12.dp),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = char,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

