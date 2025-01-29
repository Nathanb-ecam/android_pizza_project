package com.example.pizza_app_android.ui.app_screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.pizza_app_android.BuildConfig
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
    orderViewModel : OrderViewModel,
    navController : NavController
){

    Surface(
        modifier = Modifier.fillMaxSize().background(MyPalette.LightBackGround)
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ProductCard(product, productType, orderViewModel, navController)
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
fun ProductCard(p : Product, productType: ProductType, orderViewModel: OrderViewModel, navController: NavController){
    val icon_ressources_id = getIconForProductType(productType)
    val context = LocalContext.current
    val buttonColors = ButtonDefaults.buttonColors(backgroundColor = MyPalette.PrimaryColor, contentColor = MyPalette.White )


    Column(
        modifier = Modifier.fillMaxWidth(.85f)
            .clip(RoundedCornerShape(20))
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp))
            .background(MyPalette.White)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!p.image_path.isNullOrEmpty()) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(BuildConfig.API_BASE_URL + "uploads/" + p.image_path)
                    .crossfade(true)
                    .build(),
                contentDescription = "Image from VPS",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    //.clip(CircleShape)
                    //.border(2.dp, MyPalette.borderGray, CircleShape)
                //    .padding(8.dp)
                ,
                loading = {
                    CircularProgressIndicator(color = MyPalette.PrimaryColor)
                },
                error = {
                    Image(
                        painter = painterResource(icon_ressources_id),
                        contentDescription = "Error",
                        modifier = Modifier.size(50.dp)
                    )
                }
            )
        }
        //else Image(painter = painterResource(icon_ressources_id), contentDescription = "no-image-so-icon", modifier = Modifier.width(60.dp).height(60.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            p.name,
            modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Left,
            color = MyPalette.textBlack, fontWeight = FontWeight.SemiBold, fontSize = 22.sp
        )
        Text(p.desc,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left, color = MyPalette.textLightGray,
            fontSize = 18.sp
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
        ){
            Row(verticalAlignment = Alignment.CenterVertically){
                Text("Size:")
                Spacer(modifier = Modifier.width(5.dp))
                Row(){
                    Text("S", textDecoration = TextDecoration.Underline)
                    Spacer(modifier = Modifier.width(2.dp))
                    Text("M")
                    Spacer(modifier = Modifier.width(2.dp))
                    Text("L")
                }
            }
            Text(p.price.toString() + "€",
                color = MyPalette.textGray, fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = buttonColors,
            shape = RoundedCornerShape(5.dp),
            elevation = null,
            onClick = {
                orderViewModel.addExtra(productType,p);
                //orderViewModel.showOrderContent();
                Toast.makeText(context, "${p.name} ajouté à la commande", Toast.LENGTH_SHORT).show()
                navController.navigateUp()
            }
        ){
            Text("Ajouter", fontSize = 16.sp)
        }
    }


}


@Composable
fun ProductHadNoImage(){

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


