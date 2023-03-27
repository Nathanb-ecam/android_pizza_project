import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.ProductList
import com.example.pizza_app_android.viewmodels.RestaurantViewModel

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
        ProductList(uiState.pizzas)
/*        Button(
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, Color.Red),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
            *//*onClick ={appViewModel.addPizza(Product("Mozzarella",12f))},
            ){
            Text(text="Add a pizza",fontSize = 24.sp, color = Color.Red)
        }*/
    }


}


