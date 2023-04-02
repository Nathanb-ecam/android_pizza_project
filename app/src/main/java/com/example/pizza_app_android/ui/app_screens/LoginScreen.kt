package com.example.pizza_app_android.ui.app_screens


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.models.User
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import com.example.pizza_app_android.viewmodels.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController,userViewModel : UserViewModel = viewModel()){

    // need to fetch all authorized users



    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val uiState by userViewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text="Login screen", fontSize = 32.sp)
        Column(modifier = Modifier
            .align(Alignment.Center)
            .padding(8.dp)) {
            TextField(value = username, onValueChange = { username=it }, label = {Text("Nom d'utilisateur")})
            TextField(value = password, onValueChange = { password=it }, label = {Text("Mot de passe")})
            Button(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    //userViewModel.authentificate(User(username,password));
                    Log.i("API",uiState.loggedIn.toString())
                    if (uiState.loggedIn){
                        navController.navigate(Screen.HomeScreen.route)
                    }
                }){
                Text(text="Envoyer")
            }
        }
    }
}