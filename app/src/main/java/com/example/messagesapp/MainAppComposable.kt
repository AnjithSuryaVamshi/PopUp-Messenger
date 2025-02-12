package com.example.messagesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.messagesapp.feature.auth.signin.LoginScreen
import com.example.messagesapp.feature.auth.signup.SignupScreen
import com.example.messagesapp.feature.home.HomeScreen


@Composable
fun MainApp() {

    Surface(modifier = Modifier.fillMaxSize()) {
        val navController = rememberNavController()
        val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser
        val start = if (currentUser == null) "login" else "home"

        NavHost(navController = navController, startDestination = start) {
            composable("login"){
                LoginScreen(navController)
            }
            composable("signup"){
                SignupScreen(navController)
            }
            composable("home"){
                HomeScreen(navController)
            }
        }
    }
}