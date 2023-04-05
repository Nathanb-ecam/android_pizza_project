package com.example.pizza_app_android

import android.graphics.fonts.FontFamily
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.viewmodels.OrderViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Composable
fun ProductList(productType: ProductType,products:List<Product>,navController:NavController){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(products) { product ->
            ProductCard(productType=productType,product = product,navController=navController)
        }
    }
}

@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun ProductCard(productType: ProductType,product: Product,navController:NavController){
    Card(modifier = Modifier.padding(8.dp).clickable {
        if(product.desc==null){
            product.desc=""
        }
        val jsonProduct = Json.encodeToString(product)
        val jsonProductType = Json.encodeToString(productType)
        //Log.i("",json)
        navController.navigate(Screen.DetailScreen.withArgs(jsonProduct,jsonProductType))
    }, elevation = 4.dp, backgroundColor = Color.LightGray){
        var productQuantity by remember{ mutableStateOf(0) }
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp).wrapContentHeight(), horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
            Column{
                Text(text =product.name,style= paragraphStyle)
                Text(text="${product.price} $",style= paragraphStyle)
            }
            Row(verticalAlignment = Alignment.CenterVertically){
                Button(
                    modifier=Modifier.height(35.dp).width(35.dp),
                    onClick = {
                            if(productQuantity==0) productQuantity else productQuantity--
                          },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.hsl(345f, 0.95f, 0.25f, 1f),
                        contentColor = Color.White
                    )){Text("-",textAlign = TextAlign.Center)}
                Text(text=productQuantity.toString())
                Button(
                    modifier=Modifier.height(35.dp).width(35.dp),
                    onClick = {
                        productQuantity++;
                              },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.hsl(345f, 0.95f, 0.25f, 1f),
                        contentColor = Color.White
                    )){Text(text="+", textAlign = TextAlign.Center)}
            }

        }
    }
}

