package com.example.pizza_app_android

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_app_android.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class UiState(
    val drinks :List<Product>,
    val pizzas: List<Product>,
)

class RestaurantViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(
                listOf<Product>(),
                listOf<Product>(),
    ));

    val uiState : StateFlow<UiState> = _uiState.asStateFlow();

    fun update(){
        val latestPizzas = Datasource().fetchPizzas();
        val currentState = _uiState.value;
        _uiState.value = currentState.copy(pizzas = latestPizzas);
    }

    fun getPizzas(){
        viewModelScope.launch {
            val latestPizzas = PizzaApi.retrofitService.getPizzas()
            val currentState = _uiState.value;
            try{
                _uiState.value = currentState.copy(pizzas = latestPizzas["pizzas"]!!);
            }
            catch (e:java.lang.Exception){

            }

            //Log.d("Result",stringResult)
        }
    }

    fun addPizza(pizza: Product){
        val call: Call<Product> = PizzaApi.retrofitService.addPizza(pizza)
        call.enqueue(object : Callback<Product?>{
            override fun onResponse(call: Call<Product?>, response: Response<Product?>) {
                Log.i( "API","Item added to api")
            }

            override fun onFailure(call: Call<Product?>, t: Throwable) {
                Log.i( "API","Error while trying to add pizza to api")
            }
        }

        )

    }



    fun getDrinks(){
        viewModelScope.launch {
            val latestDrinks = PizzaApi.retrofitService.getDrinks()
            val currentState = _uiState.value;
            try{
                _uiState.value = currentState.copy(drinks = latestDrinks["drinks"]!!);
            }
            catch (e:java.lang.Exception){

            }

            //Log.d("Result",stringResult)
        }
    }
}

