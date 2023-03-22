package com.example.pizza_app_android.ui.app_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DrinkScreen(navController: NavController){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text="Drinks", fontSize = 17.sp)
    }
}