package com.example.cinemaxapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cinemaxapp.core.designsystem.theme.CinemaxTheme
import com.example.cinemaxapp.feature.auth.login.LoginScreen
import com.example.cinemaxapp.feature.auth.loginsignup.LoginSignupScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinemaxTheme {
                LoginScreen { email, password ->
                    // TODO: Handle login with email and password
                }
            }
        }
    }
}

