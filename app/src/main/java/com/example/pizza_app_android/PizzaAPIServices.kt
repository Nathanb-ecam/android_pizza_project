package com.example.pizza_app_android


import com.example.pizza_app_android.models.Menu
import com.example.pizza_app_android.models.Product
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
// android emulator
//private const val BASE_URL = "http://10.0.2.2:3000/api/"
//emulate on physical device
private const val BASE_URL = "http://192.168.1.36:3000/api/"
// api on linux server
//private const val BASE_URL ="http://192.168.11.136:3000/api/"

private val retrofit = Retrofit.Builder()
    //.addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


interface PizzaApiServices  {
    @GET("pizzas")
    suspend fun getPizzas():List<Product>


    @GET("pizza/{pizza_id}")
    suspend fun getPizzaById(@Path("pizza_id") searchById:String):Product

    @POST("pizza")
    fun addPizza(@Body pizza: Product?): Call<Product>

    @GET("menus")
    suspend fun getMenus():List<Menu>

    @GET("drinks")
    suspend fun getDrinks():List<Product>

    @GET("sauces")
    suspend fun getSauces():List<Product>

    @GET("chickens")
    suspend fun getChickens():List<Product>

    @GET("extras")
    suspend fun getExtras():List<Product>


}

// singleton to limit high ressources use
object PizzaApi {
    val retrofitService : PizzaApiServices by lazy {
        retrofit.create(PizzaApiServices::class.java)
    }
}