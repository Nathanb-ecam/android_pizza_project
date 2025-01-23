package com.example.pizza_app_android.ui.app_screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_app_android.R
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.paragraphStyle
import com.example.pizza_app_android.titleStyle
import com.example.pizza_app_android.ui.theme.MyPalette
import com.example.pizza_app_android.viewmodels.OrderViewModel


@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun DetailScreen(
    productType: ProductType,
    product: Product,
    appViewModel: RestaurantViewModel,
    orderViewModel : OrderViewModel
){

    Surface(
        modifier = Modifier.fillMaxSize().background(MyPalette.LightBackGround)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ProductCard(product, productType, orderViewModel)
        }


    }
}

fun getIconForProductType(productType: ProductType): Int {
    return when (productType) {
        ProductType.Drink -> R.drawable.drink_icon
        ProductType.Pizza -> R.drawable.pizza_icon
        ProductType.Chicken -> R.drawable.drink_icon
        ProductType.Sauce -> R.drawable.drink_icon
        ProductType.Extra -> R.drawable.extra_icon
    }
}

@Composable
fun ProductCard(p : Product, productType: ProductType, orderViewModel: OrderViewModel){
    val icon_ressources_id = getIconForProductType(productType)
    val context = LocalContext.current
    val buttonColors = ButtonDefaults.buttonColors(backgroundColor = MyPalette.PrimaryColor, contentColor = MyPalette.White )


    Column(
        modifier = Modifier.fillMaxWidth(.8f).clip(RoundedCornerShape(35)).background(MyPalette.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon_ressources_id),
            contentDescription = "drink icon",
            modifier = Modifier.width(40.dp)
        )
        Text(p.name, color = MyPalette.textBlack, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Text(p.price.toString(), color = MyPalette.textGray, fontSize = 20.sp)
        Text(p.desc, color = MyPalette.textLightGray)
        Button(
            modifier = Modifier.fillMaxWidth(.8f),
            colors = buttonColors,
            elevation = null,
            onClick = {
                orderViewModel.addExtra(productType,p);
                //orderViewModel.showOrderContent();
                Toast.makeText(context, "${p.name} ajouté à la commande", Toast.LENGTH_SHORT).show()
            }
        ){
            Text("Ajouter à la commande")
        }
    }


}



@Composable
fun PizzaCard(pizza: Product){
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp, backgroundColor = Color.LightGray){
        Box(modifier = Modifier
            .height(150.dp)
            .width(150.dp)) {
            Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = pizza.name ,
                    fontSize = 24.sp,
                    //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))
                )
                Text(text=pizza.price.toString(),fontSize =20.sp)
            }

        }
    }
}



/*@Composable
fun DrinkSection(formula: Formula,drinks : List<Drink>){
    for(i in 0..formula.drink_quantity ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            ComponentList(formula = formula,drinks,index = i)
        }
    }
}*/


