package com.example.pizza_app_android.ui.app_screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.ProductList
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel

@Composable
fun DrinkScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel()
){
    val uiState by appViewModel.uiState.collectAsState()
    //appViewModel.addPizza()
    //appViewModel.update()
    //appViewModel.getDrinks()
    Surface(){
        Column {
            Text(text="Nos boissons", style=headerStyle)
            ProductList(ProductType.Drink,uiState.drinks,navController=navController)
        }
    }
}