package com.example.pizza_app_android.ui.app_screens


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.models.Login
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.UserViewModel

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun LoginScreen(navController: NavController,orderViewModel: OrderViewModel,userViewModel : UserViewModel = viewModel()){

    // need to fetch all authorized users



    var username by rememberSaveable() { mutableStateOf("") }
    var password by rememberSaveable() { mutableStateOf("") }
    val uiState by userViewModel.uiState.collectAsState()



    LaunchedEffect(uiState.loggedIn){
        if (uiState.loggedIn){
            navController.navigate(Screen.MenuScreen.route)
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text="Login screen", style= headerStyle)
        Column(modifier = Modifier
            .align(Alignment.Center)
            .padding(8.dp)) {
            TextField(value = username, onValueChange = { username=it }, label = {Text("Nom d'utilisateur")})
            TextField(value = password, onValueChange = { password=it }, label = {Text("Mot de passe")},visualTransformation = PasswordVisualTransformation() )
            Row(horizontalArrangement = Arrangement.SpaceEvenly){
                Button(
                    //modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.hsl(345f, 0.95f, 0.25f, 1f),
                        contentColor = Color.White
                    ),
                    onClick = {
                        userViewModel.authentificate(orderViewModel, Login(username,password));
                        //Log.i("Token","token ${token}")

                    }){
                    if(uiState.loggedIn){
                        Text(text="Passer au menu")
                    }
                    else{
                        Text(text="Envoyer")
                    }
                }
                Checkbox(checked =uiState.loggedIn, onCheckedChange = {})

            }
        }
    }

}