package com.example.pizza_app_android.ui.app_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.ProductList
import com.example.pizza_app_android.viewmodels.RestaurantViewModel

@Composable
fun DrinkScreen(navController: NavController,appViewModel: RestaurantViewModel = viewModel()){
    val uiState by appViewModel.uiState.collectAsState()
    appViewModel.getDrinks()
    Column {
        Text(text="Nos boissons",fontSize=32.sp)
        ProductList(uiState.drinks)
/*        Button(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, Color.Red),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
            *//*onClick ={appViewModel.addPizza(Product("Mozzarella",12f))},
            ){
            Text(text="Add a pizza",fontSize = 24.sp, color = Color.Red)
        }*/
    }
}