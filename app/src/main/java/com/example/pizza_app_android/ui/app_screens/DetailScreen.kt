package com.example.pizza_app_android.ui.app_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(description:String){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text=description, fontSize = 17.sp)
    }
}