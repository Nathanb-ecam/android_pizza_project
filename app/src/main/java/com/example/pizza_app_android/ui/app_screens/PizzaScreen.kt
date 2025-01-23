import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.ProductList
import com.example.pizza_app_android.Screen
import com.example.pizza_app_android.headerStyle
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.ui.app_screens.ProductListItem
import com.example.pizza_app_android.ui.theme.MyPalette
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Composable
fun PizzaScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel(),

    ){
    val searchText by appViewModel.searchText.collectAsState()
    val pizzas by appViewModel.pizzas.collectAsState()


    val whiteOutlineTextFieldStyles = TextFieldDefaults.outlinedTextFieldColors(textColor = MyPalette.borderLightWhite, placeholderColor = MyPalette.borderLightWhite, cursorColor = MyPalette.borderLightWhite, focusedLabelColor = MyPalette.borderLightWhite, unfocusedLabelColor = MyPalette.borderLightWhite, focusedBorderColor = MyPalette.borderLightWhite,unfocusedBorderColor = MyPalette.borderLightWhite)

    Surface(modifier = Modifier.fillMaxSize().background(MyPalette.LightBackGround)){


        Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = appViewModel::onSearchTextChange,
                colors = whiteOutlineTextFieldStyles,
                placeholder = {Text(text="Search")},
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

            ProductList(ProductType.Pizza, pizzas, navController)
        }



    }


}


