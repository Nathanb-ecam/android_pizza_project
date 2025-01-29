package com.example.pizza_app_android.ui.app_screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pizza_app_android.*
import com.example.pizza_app_android.R
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.models.Selection
import com.example.pizza_app_android.ui.theme.MyPalette
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import com.example.pizza_app_android.viewmodels.OrderViewModel



@SuppressLint("DefaultLocale")
@OptIn(ExperimentalGraphicsApi::class)
@Composable
fun RecapScreen(
    orderViewModel : OrderViewModel
){
    //val uiState by appViewModel.uiState.collectAsState()
    val orderUIState by orderViewModel.uiState.collectAsState()

    //appViewModel.getPizzas()
    //appViewModel.getDrinks()
    val menus = orderUIState.orderUISelection;
    val extras = orderViewModel.getOrderExtras()

    if(menus.isEmpty() && extras.isEmpty()) return EmptyBasket()

    Surface(
        modifier = Modifier.fillMaxSize().background(MyPalette.LightBackGround)
    ){
        Column(modifier= Modifier
            .padding(16.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(modifier = Modifier
                //.height(500.dp)
                .fillMaxSize().weight(.9f)
                .verticalScroll(rememberScrollState()),
            ){
                if(menus.isNotEmpty()){

                    TitleRow("menus")

                    menus.forEachIndexed { menuIndex, menu ->
                        MenuCard(menu, menuIndex, orderViewModel = orderViewModel)
                        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))
                    }
                }

                Spacer(modifier =Modifier.fillMaxWidth().height(10.dp))
                if(extras.isNotEmpty()){
                    TitleRow(leftText = "extras")
                    OrderExtras(extras)
                    Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

                }
            }

            OrderBottomSection(orderViewModel)
        }
    }
}



@Composable
fun MenuCard(menu : Selection, menuIndex : Int,  orderViewModel: OrderViewModel){
    Column(
        modifier=Modifier
            .fillMaxSize()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10))
            .clip(RoundedCornerShape(10))
            .background(MyPalette.White)
            .padding(start = 10.dp,top = 14.dp, end = 10.dp, bottom = 4.dp )

    ){
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text="Menu n°${menuIndex + 1}:")
            Text(text="${menu.price} €", fontWeight = FontWeight.SemiBold)
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text="${menu.pizza?.name}", modifier = Modifier.padding(start = 20.dp))
        Text(text="${menu.chicken?.name}", modifier = Modifier.padding(start = 20.dp))
        Text(text="${menu.drink?.name}", modifier = Modifier.padding(start = 20.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Text(text="${menu.sauce?.name}", modifier = Modifier.padding(start = 20.dp))
            Button(
                onClick={orderViewModel.removeMenuFromOrder(menuIndex);},
                colors = ButtonDefaults.buttonColors(contentColor = MyPalette.White, backgroundColor = MyPalette.PrimaryColor)
            ){
                Text(text="Supprimer", fontSize = 10.sp)

            }
        }

    }
}

@Composable()
fun OrderExtras(extras:  MutableMap<ProductType, MutableList<Product>>){
    Column(
        modifier = Modifier.fillMaxSize()
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(10))
            .clip(RoundedCornerShape(4.dp))
            .background(MyPalette.White)
            .padding(horizontal = 5.dp, vertical = 10.dp)
    ){
        for ((type, products) in extras) {
            Text(text = "${type.name}(s):")
            Column(modifier = Modifier.padding(start = 20.dp)) {
                products.forEach {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = it.name)
                        Text(text = "${String.format("%.2f", it.price)}€", modifier = Modifier.padding(end = 10.dp))
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
            }
        }
    }
}


@Composable
fun OrderBottomSection(orderViewModel: OrderViewModel){
    Box(modifier=Modifier.fillMaxWidth()){
        Row(modifier=Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
            Text(text="Total: ${String.format("%.2f",orderViewModel.orderTotal())}€", fontWeight = FontWeight.SemiBold)
            val context = LocalContext.current
            Button(
                modifier=Modifier.wrapContentWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MyPalette.PrimaryColor,
                    contentColor = MyPalette.White
                ),
                onClick = {
                    orderViewModel.sendOrder();
                    Toast.makeText(context, "Commande envoyée", Toast.LENGTH_SHORT).show()

                }
            ){
                Text("Envoyer la commande".uppercase(), fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun EmptyBasket(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.empty_card),
            contentDescription = "empty card"
        )
        Text("Panier vide", fontSize = 20.sp ,fontWeight = FontWeight.Bold)
        Text(
            "Il semblerait que vous n'ayez rien ajouté au panier, aller aux onglets menus,pizzas ou boissons pour ajouter des éléments."
                ,fontSize = 15.sp
            , modifier = Modifier.padding(horizontal = 40.dp)
        )

    }

}