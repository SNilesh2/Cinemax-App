package com.example.cinemaxapp.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cinemaxapp.feature.auth.login.LoginScreen
import com.example.cinemaxapp.feature.auth.loginsignup.LoginSignupScreen
import com.example.cinemaxapp.feature.auth.newpassword.NewPasswordScreen
import com.example.cinemaxapp.feature.auth.resetpassword.ResetPasswordScreen
import com.example.cinemaxapp.feature.auth.signup.SignUpScreen
import com.example.cinemaxapp.feature.auth.verification.VerificationScreen

/**
 * Type-safe Auth Route destinations for the feature:auth module.
 */
object AuthRoute {
    const val ROOT = "auth_graph"
    const val LOGIN_SIGNUP = "login_signup"
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
    const val RESET_PASSWORD = "reset_password"
    const val VERIFICATION = "verification"
    const val NEW_PASSWORD = "new_password"
}

/**
 * Extension on [NavGraphBuilder] to define the Authentication navigation graph.
 * Encapsulates all screen routes and navigation actions for feature:auth.
 *
 * @param navController The root or parent [NavHostController] managing app navigation.
 */
fun NavGraphBuilder.authGraph(
    navController: NavHostController,
) {
    navigation(
        route = AuthRoute.ROOT,
        startDestination = AuthRoute.LOGIN_SIGNUP,
    ) {
        // 1. LoginSignup Screen (Entry point)
        composable(route = AuthRoute.LOGIN_SIGNUP) {
            LoginSignupScreen(
                onSignUpClick = {
                    navController.navigate(AuthRoute.SIGN_UP)
                },
                onLoginClick = {
                    navController.navigate(AuthRoute.LOGIN)
                },
            )
        }

        // 2. Login Screen
        composable(route = AuthRoute.LOGIN) {
            LoginScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onForgotPasswordClick = {
                    navController.navigate(AuthRoute.RESET_PASSWORD)
                },
                onLoginClick = { email, password ->
                    // Handled by ViewModel / auth flow in future steps
                },
            )
        }

        // 3. Sign Up Screen
        composable(route = AuthRoute.SIGN_UP) {
            SignUpScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSignUpClick = { _, _, _ ->
                    // Navigate to Log in screen after sign up as per requirement
                    navController.navigate(AuthRoute.LOGIN)
                },
                onTermsClick = {
                    // Open Terms & Services if needed
                },
            )
        }

        // 4. Reset Password Screen
        composable(route = AuthRoute.RESET_PASSWORD) {
            ResetPasswordScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNextClick = { email ->
                    navController.navigate(AuthRoute.VERIFICATION)
                },
            )
        }

        // 5. Verification Screen
        composable(route = AuthRoute.VERIFICATION) {
            VerificationScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onContinueClick = { otp ->
                    navController.navigate(AuthRoute.NEW_PASSWORD)
                },
                onResendClick = {
                    // Handle OTP resend
                },
            )
        }

        // 6. New Password Screen
        composable(route = AuthRoute.NEW_PASSWORD) {
            NewPasswordScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onResetClick = { newPassword, confirmPassword ->
                    // After successful password reset, navigate to Log in screen
                    // and clear the reset flow off the backstack up to Login
                    navController.navigate(AuthRoute.LOGIN) {
                        popUpTo(AuthRoute.LOGIN_SIGNUP) {
                            inclusive = false
                        }
                    }
                },
            )
        }
    }
}

