import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.ProductList
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.viewmodels.RestaurantViewModel


@Composable
fun PizzaScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel(),

    ){
    appViewModel.getPizzas()
    val searchText by appViewModel.searchText.collectAsState()
    val pizzas by appViewModel.pizzas.collectAsState()
    Surface(){
        Column {
            Text(text="Nos pizzas",style= headerStyle)

            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(16.dp) ) {
                TextField(
                    value = searchText,
                    onValueChange = appViewModel::onSearchTextChange,
                    placeholder = {Text(text="Search")}
                )
                ProductList(ProductType.Pizza,pizzas,navController=navController)
            }

        }

    }


}


