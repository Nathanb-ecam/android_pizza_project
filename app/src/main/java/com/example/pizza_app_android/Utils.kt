package com.example.pizza_app_android

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizza_app_android.models.Product

@Composable
fun ProductList(products:List<Product>){
    products.forEach{
        Log.i("API","${it.Id} ${it.Name} ${it.Cost}")
    }
    LazyColumn(
        modifier = Modifier.fillMaxWidth().height(600.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(products) { product ->
            ProductCard(product = product)
        }
    }
}

@Composable
fun ProductCard(product: Product, modifier: Modifier = Modifier){
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp, backgroundColor = Color.LightGray){
        Row(modifier = Modifier.fillMaxSize().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = AnnotatedString("product.Name") ,//
                style = TextStyle(fontSize = 20.sp),
                //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))

            )
            Text(text=product.Cost.toString(),fontSize =18.sp)
        }
    }
}