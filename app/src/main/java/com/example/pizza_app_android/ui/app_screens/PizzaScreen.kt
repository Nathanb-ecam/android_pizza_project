import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.models.Pizza
import com.example.pizza_app_android.RestaurantViewModel
import com.example.pizza_app_android.Screen

@Composable
fun PizzaScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel()
){
    val uiState by appViewModel.uiState.collectAsState()
    appViewModel.getPizzas()
    Text(text="Nos pizzas")
    PizzaList(uiState.pizzas,navController = navController)
}

@Composable
fun PizzaList(pizzas:List<Pizza>, navController: NavController){

    LazyColumn{
        items(pizzas) { pizza ->
            PizzaCard(pizza, navController)
        }
    }
}

@Composable
fun PizzaCard(pizza: Pizza, navController: NavController, modifier: Modifier = Modifier){
    Card(modifier = modifier.padding(0.dp), elevation = 0.dp){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            ClickableText(
                text = AnnotatedString(pizza.name) ,
                style = TextStyle(fontSize = 20.sp),
                //navController.navigate(Screen.DetailScreen.withArgs(pizza.name))
                onClick = {navController.navigate(Screen.DetailScreen.route)},// need to navigate to content of the quote
                modifier = Modifier.padding(8.dp)
            )
            Text(text=pizza.price.toString())
        }
    }
}