import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.RestaurantViewModel
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.models.Product

@Composable
fun PizzaScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel(),

){
    val uiState by appViewModel.uiState.collectAsState()
    //appViewModel.addPizza()
    //appViewModel.update()
    appViewModel.getPizzas()
    Column {
        Text(text="Nos pizzas",fontSize=32.sp)
        PizzaList(uiState.pizzas,navController = navController)
        Button(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, Color.Red),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
            onClick ={appViewModel.addPizza(Product("Mozzarella",12f))},
            ){
            Text(text="Add a pizza",fontSize = 24.sp, color = Color.Red)
        }
    }


}

@Composable
fun PizzaList(pizzas:List<Product>, navController: NavController){

    LazyColumn(
        modifier = Modifier.fillMaxWidth().height(600.dp),
        contentPadding = PaddingValues(16.dp)
    ){
        items(pizzas) { pizza ->
            PizzaCard(pizza, navController)
        }
    }
}

@Composable
fun PizzaCard(pizza: Product, navController: NavController, modifier: Modifier = Modifier){
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp, backgroundColor = Color.LightGray){
        Row(modifier = Modifier.fillMaxSize().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = AnnotatedString(pizza.name) ,
                style = TextStyle(fontSize = 20.sp),
                //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))

            )
            Text(text=pizza.price.toString(),fontSize =18.sp)
        }
    }
}


@Composable
fun AddPizza(
    pizza: Product,
    appViewModel: RestaurantViewModel = viewModel()
){
    appViewModel.addPizza(pizza)
}
