package com.example.pizza_app_android.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizza_app_android.Datasource
import com.example.pizza_app_android.PizzaApi
import com.example.pizza_app_android.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

data class UiState(
    val drinks :List<Drink>,
    val pizzas: List<Pizza>,
    val chickens: List<Chicken>,
    val sauces: List<Sauce>,
)

class RestaurantViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState(
                listOf(Drink(0,"Loading drinks",0f)),
                listOf(Pizza(20,"Loading pizzas",0f)),
                listOf(Chicken(0,"Loading chickens",0f)),
                listOf(Sauce(0,"Loading sauces",0f))
    ));

    val uiState : StateFlow<UiState> = _uiState.asStateFlow();


    fun fetchAll(){
        getPizzas()
        //getDrinks()
    }
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
                _uiState.value = currentState.copy(pizzas = latestPizzas);
            }
            catch (e:SocketTimeoutException){//

            }
  /*          val call:Call<List<Pizza>> = PizzaApi.retrofitService.getPizzas()
            call.enqueue(object : Callback<List<Pizza>?>{
                override fun onResponse(
                    call: Call<List<Pizza>?>,
                    response: Response<List<Pizza>?>
                ) {
                    if (response.isSuccessful){
                        Log.i("API",response.body()!!.toString())
                    }
                }

                override fun onFailure(call: Call<List<Pizza>?>, t: Throwable) {
                    Log.i("API Error","failed to get pizzas")
                }
            }
            )*/
        }
    }

    fun addPizza(pizza: Pizza){
        val call: Call<Pizza> = PizzaApi.retrofitService.addPizza(pizza)
        call.enqueue(object : Callback<Pizza?>{
            override fun onResponse(call: Call<Pizza?>, response: Response<Pizza?>) {
                Log.i( "API","Item added to api")
            }

            override fun onFailure(call: Call<Pizza?>, t: Throwable) {
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
}

