package com.example.messagesapp.feature.auth.signin

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel : SignInViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    var email by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.value){
        when(uiState.value){
            is SignInState.Success -> {
                navController.navigate("home")
            }
            is SignInState.Error -> {
               Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
            else -> {


            }
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center

        )
        {
            Text("Login to Chat",modifier = Modifier.padding(16.dp), fontSize = 24.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = androidx.compose.ui.graphics.Color.Blue)
            Spacer(modifier = Modifier.height(24.dp))


            OutlinedTextField(value = email, onValueChange = {email = it},
                placeholder = { Text("Email") },
                label = { Text("Email") }
                )
            Spacer(modifier = Modifier.height(12.dp))


            OutlinedTextField(value = password, onValueChange = {password =it},
                placeholder = { Text("Password") },
                label = { Text("Password") },

                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
            )


            Spacer(modifier = Modifier.height(24.dp))

            if(uiState.value == SignInState.Loading){
                CircularProgressIndicator()
            }else{
                Button(onClick = { viewModel.signIn(email, password)},
                    enabled = password.isNotEmpty() && email.isNotEmpty() && (uiState.value == SignInState.Nothing || uiState.value == SignInState.Error)

                ) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = {navController.navigate("signup")}) {
                    Text("Don't have an account? Sign up")
                }

            }

        }

    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}