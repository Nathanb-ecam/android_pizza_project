package com.example.pizza_app_android

import android.graphics.fonts.FontFamily
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.ui.app_screens.ProductListItem
import com.example.pizza_app_android.ui.theme.MyPalette
import com.example.pizza_app_android.viewmodels.OrderViewModel
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.util.Locale

@Composable
fun ProductList(productType: ProductType,products:List<Product>,navController:NavController){
    Column(
        modifier = Modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10))
            .clip(RoundedCornerShape(10))
            .background(MyPalette.White)
    ) {
        products.forEachIndexed { index, pizza ->
            ProductListItem(
                productType = ProductType.Pizza,
                suggestion = pizza,
                itemIndex = index,
                onItemClicked = {
                    val jsonProduct = Json.encodeToString(pizza)
                    val jsonProductType = Json.encodeToString(productType)
                    navController.navigate(
                        Screen.DetailScreen.withArgs(jsonProduct, jsonProductType)
                    )
                }
            )
            // Add a divider for all items except the last one
            if (index != (products.size - 1)) {
                Box(
                    modifier = Modifier
                        .height(0.2.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.2.dp)
                            .background(MyPalette.borderGray)
                    ) {}
                }
            }
        }
    }
}


@Composable
fun TitleRow(leftText : String, rightText : String? = null){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = leftText.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            fontSize = 20.sp, color = MyPalette.textBlack, fontWeight = FontWeight.SemiBold
        )
        rightText?.let{Text(text = rightText, fontSize = 15.sp, color = MyPalette.PrimaryColor, textDecoration = TextDecoration.Underline)}
    }
    Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
}


@Composable
fun ErrorCard(errorMessage : String){
    Card(
        modifier = Modifier.fillMaxWidth().padding(40.dp),
        backgroundColor = MyPalette.White,
        border = BorderStroke(1.dp, MyPalette.redInfo),
        shape = RoundedCornerShape(1.dp),
        elevation = 1.dp
    ){
        Text(errorMessage, textAlign = TextAlign.Center)
    }
}


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
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp).wrapContentHeight(), horizontalArrangement = Arrangement.SpaceBetween,verticalAlignment = Alignment.CenterVertically) {
            Text(text =product.name,style= paragraphStyle)
            Text(text="${product.price} $",style= paragraphStyle)
        }
    }
}

