package com.example.messagesapp.feature.auth.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun SignupScreen(navController: NavController){
    val viewModel : SignUpViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    var email by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    var name by remember{
        mutableStateOf("")
    }
    var Confirmpassword by remember{
        mutableStateOf("")
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.value) {
        when(uiState.value){
            is SignUpState.Success -> {
                navController.navigate("home")
            }
            is SignUpState.Error -> {
                Toast.makeText(context,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
            else->{

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
            Text("SignUp to Chat",modifier = Modifier.padding(16.dp), fontSize = 24.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, color = androidx.compose.ui.graphics.Color.Blue)
            Spacer(modifier = Modifier.height(24.dp))


            OutlinedTextField(value = name, onValueChange = {name = it},
               placeholder = { Text("Username") },
                label = { Text("Username") }
            )
            Spacer(modifier = Modifier.height(12.dp))


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
            Spacer(modifier = Modifier.height(12.dp))


            OutlinedTextField(value = Confirmpassword, onValueChange = {Confirmpassword =it},
                placeholder = { Text("Confirm Password") },
                label = { Text("Confirm Password") },
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                isError = password != Confirmpassword && password.isEmpty() && Confirmpassword.isEmpty()
            )


            Spacer(modifier = Modifier.height(24.dp))

            if(uiState.value == SignUpState.Loading){
                androidx.compose.material3.CircularProgressIndicator()
            }else{
                Button(onClick = { viewModel.signUp(name, email, password)},
                    enabled = password == Confirmpassword && password.isNotEmpty() && Confirmpassword.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty()
                ) {
                    Text("SignUp")
                }
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = {navController.popBackStack()}) {
                    Text("Already have an account? Sign In")
                }

            }



        }

    }
}