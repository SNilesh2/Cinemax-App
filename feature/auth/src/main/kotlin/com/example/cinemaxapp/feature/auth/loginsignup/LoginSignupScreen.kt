package com.example.cinemaxapp.feature.auth.loginsignup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.example.cinemaxapp.core.designsystem.component.CinemaxButton
import com.example.cinemaxapp.core.designsystem.component.CinemaxLogo
import com.example.cinemaxapp.core.designsystem.icon.CinemaxIcons
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme

@Composable
fun LoginSignupScreen(
    onSignUpClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onAppleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CinemaxTheme.colors.textBlack)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(160.dp))

        // Logo (Cinemax Branding)
        CinemaxLogo()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "CINEMAX",
            style = CinemaxTheme.typography.h1SemiBold,
            color = CinemaxTheme.colors.white,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enter your registered\nPhone Number to Sign Up",
            style = CinemaxTheme.typography.h5SemiBold,
            color = CinemaxTheme.colors.grey,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(64.dp))

        // Sign Up button
        CinemaxButton(
            text = "Sign Up",
            onClick = onSignUpClick,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // "I already have an account? Login"
        Row {
            Text(
                text = "I already have an account? ",
                style = CinemaxTheme.typography.h4Medium,
                color = CinemaxTheme.colors.grey,
            )
            Text(
                text = "Login",
                style = CinemaxTheme.typography.h4SemiBold,
                color = CinemaxTheme.colors.blueAccent,
                modifier = Modifier.clickable { onLoginClick() },
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // "Or Sign up with" divider
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, end = 36.dp),
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = CinemaxTheme.colors.soft
            )
            Text(
                text = "  Or Sign up with  ",
                style = CinemaxTheme.typography.h5Medium,
                color = CinemaxTheme.colors.grey,
            )
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = CinemaxTheme.colors.soft
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Social login icons
        Row() {
            SocialIcon(
                backgroundColor = Color(0xFFF3F8FB),
                onClick = onGoogleClick,
            ) {
                Icon(
                    painter = painterResource(id = CinemaxIcons.Google),
                    contentDescription = "Sign in with Google",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }

            Spacer(
                modifier = Modifier.width(24.dp)
            )

            SocialIcon(
                backgroundColor = CinemaxTheme.colors.soft,
                onClick = onAppleClick,
            ) {
                Icon(
                    painter = painterResource(id = CinemaxIcons.Apple),
                    contentDescription = "Sign in with Apple",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }

            Spacer(
                modifier = Modifier.width(24.dp)
            )

            SocialIcon(
                backgroundColor = Color(0xFF4267B2),
                onClick = onFacebookClick,
            ) {
                Icon(
                    painter = painterResource(id = CinemaxIcons.Facebook),
                    contentDescription = "Sign in with Facebook",
                    modifier = Modifier.size(24.dp),
                    tint = CinemaxTheme.colors.white
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}


//One circular icon button, reused for Google / Apple / Facebook.
@Composable
private fun SocialIcon(
    backgroundColor: Color,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .width(69.dp)
            .height(69.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginSignupScreenPreview() {
    CinemaxTheme {
        LoginSignupScreen()
    }
}