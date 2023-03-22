package com.example.pizza_app_android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pizza_app_android.ui.theme.Pizza_app_androidTheme
import kotlin.text.Typography.quote

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pizza_app_androidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavController,
    appViewModel: RestaurantViewModel = viewModel()
){
    val uiState by appViewModel.uiState.collectAsState()
    //appViewModel.getPizzas()
    //appViewModel.update()
    //Text(text="Pizza Hut")
    TextButton(onClick = { navController.navigate(Screen.PizzaScreen.route) }) {
        Text(text = "Text Button")
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Pizza_app_androidTheme {
        Navigation()
    }
}