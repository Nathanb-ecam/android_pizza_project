package com.example.pizza_app_android.ui.app_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.viewmodels.OrderViewModel
import com.example.pizza_app_android.viewmodels.RestaurantViewModel

@Composable
fun HomeScreen(navController: NavController, orderViewModel: OrderViewModel= viewModel(), appViewModel: RestaurantViewModel=viewModel()){
    appViewModel.fetchAll()
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text="Home screen", fontSize = 17.sp)
    }
}

