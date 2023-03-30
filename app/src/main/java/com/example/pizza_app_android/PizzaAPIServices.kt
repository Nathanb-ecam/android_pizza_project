package com.example.pizza_app_android


import com.example.pizza_app_android.models.*
import retrofit2.Retrofit
import retrofit2.http.GET
/*import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType*/
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "http://10.0.2.2:3000/api/"

private val retrofit = Retrofit.Builder()
    //.addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface PizzaApiServices  {
    @GET("pizzas")
    suspend fun getPizzas():List<Pizza>

    @GET("pizza/{pizza_id}")
    suspend fun getPizzaById(@Path("pizza_id") searchById:String):Pizza

    @POST("pizzas")
    fun addPizza(@Body pizza: Pizza?): Call<Pizza>

    @GET("menus")
    suspend fun getMenus():List<Menu>
    @POST("menus")
    fun addMenu(@Body menu: Menu?): Call<Menu>

    @GET("drinks")
    suspend fun getDrinks():List<Drink>

    @GET("sauces")
    suspend fun getSauces():List<Sauce>

    @GET("chickens")
    suspend fun getChickens():List<Chicken>

}

// singleton to limit high ressources use
object PizzaApi {
    val retrofitService : PizzaApiServices by lazy {
        retrofit.create(PizzaApiServices::class.java)
    }
}