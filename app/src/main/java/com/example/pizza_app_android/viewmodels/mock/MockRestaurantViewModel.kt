package com.example.pizza_app_android.viewmodels.mock

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_app_android.Datasource
import com.example.pizza_app_android.PizzaApi
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import com.example.pizza_app_android.viewmodels.RestaurantViewModel
import com.example.pizza_app_android.viewmodels.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException



class MockRestaurantViewModel : RestaurantViewModel() {
    // for displaying all the products of our restaurant
    private val _uiState = MutableStateFlow(
        UiState(
            drinks = listOf(Product(1, "Coca cola", 2.0f), Product(2, "Fanta", 1.85f), Product(3,"Sprite", 1.7f)),
            pizzas = listOf(
                Product(1, "Margharita", 13f),
                Product(2, "Hawai", 11f),
                Product(3, "Regina", 10.5f),
                Product(4, "Calzone", 14f)
            ),
            chickens = listOf(
                Product(1, "Wings x8", 6.8f),
                Product(2, "Nuggets x4", 4f),
                Product(3, "Dips x4", 3.8f),
                ),
            sauces = listOf(
                Product(1, "Ketchup", 1f),
                Product(2, "Brazil", 1f),
                Product(3,"Mayonnaise", 1f),
                Product(4,"Andalouse", 1f)
            )
        )
    )

    // Override and expose the mock UI state
    override val uiState: StateFlow<UiState> = _uiState.asStateFlow()


    // for researching a pizza
    private val _searchtext = MutableStateFlow("")
    override val searchText = _searchtext.asStateFlow()

    private val _pizzas = MutableStateFlow(listOf<Product>());
    override val pizzas = searchText
        .combine(_pizzas){text,pizzas ->
            if(text.isBlank()){
                pizzas
            }else{
                pizzas.filter {
                    doesMatchSearchQuery(text,it)
                }
            }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _pizzas.value
        )
}

