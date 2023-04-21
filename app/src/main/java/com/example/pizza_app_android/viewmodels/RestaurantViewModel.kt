package com.example.pizza_app_android.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_app_android.Datasource
import com.example.pizza_app_android.PizzaApi
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

data class UiState(
    val drinks :List<Product>,
    val pizzas: List<Product>,
    val chickens: List<Product>,
    val sauces: List<Product>,
)

class RestaurantViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(
                listOf<Product>(),//Product(0,"Loading drinks",0f))
                listOf<Product>(),
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
            try{
                val latestPizzas = PizzaApi.retrofitService.getPizzas()
                val currentState = _uiState.value;
                _uiState.value = currentState.copy(pizzas = latestPizzas);
            }
            catch (e:SocketTimeoutException){

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
            try{
                val latestDrinks = PizzaApi.retrofitService.getDrinks()
                val currentState = _uiState.value;
                _uiState.value = currentState.copy(drinks = latestDrinks);
            }
            catch (e: SocketTimeoutException){
                Log.i("Error",e.toString());
            }

            //Log.d("Result",stringResult)
        }
    }


    fun getChickens(){
        viewModelScope.launch {
            try{
                val latestChickens = PizzaApi.retrofitService.getChickens()
                val currentState = _uiState.value;
                _uiState.value = currentState.copy(chickens = latestChickens);
            }
            catch (e:SocketTimeoutException){

            }

            //Log.d("Result",stringResult)
        }
    }


    fun getSauces(){
        viewModelScope.launch {
            try{
                val latestSauces = PizzaApi.retrofitService.getSauces()
                val currentState = _uiState.value;
                _uiState.value = currentState.copy(sauces = latestSauces);
            }
            catch (e:SocketTimeoutException){

            }

            //Log.d("Result",stringResult)
        }
    }
/*    fun getExtras(){
        viewModelScope.launch {
            try{
                val latestExtras = PizzaApi.retrofitService.getExtras()
                val currentState = _uiState.value;
                _uiState.value = currentState.copy(extras = latestExtras);
            }
            catch (e:SocketTimeoutException){

            }

            //Log.d("Result",stringResult)
        }
    }*/
}

