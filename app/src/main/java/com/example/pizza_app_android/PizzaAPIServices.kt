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
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
// android emulator
//private const val BASE_URL = "http://10.0.2.2:80/api/"
//emulate on physical device
//private const val BASE_URL = "http://172.17.33.250:3000/api/"
// api on linux server
private const val BASE_URL ="https://pat.infolab.ecam.be:64336/api/"

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
    suspend fun getMenus():List<Selection>

    @GET("drinks")
    suspend fun getDrinks():List<Product>

    @GET("sauces")
    suspend fun getSauces():List<Product>

    @GET("chickens")
    suspend fun getChickens():List<Product>

    @GET("extras")
    suspend fun getExtras():List<Product>

    @POST("menus")
    fun sendMenu(@Header("Authorization") token: String,@Body menu: Menu): Call<Int>
    @POST("orderelements")
    fun sendElementOfOrder(@Header("Authorization") token: String,@Body elementOfOrder: ElementOfOrder): Call<Int>
    @POST("orders/{user_id}")
    fun addOrder(@Header("Authorization") token: String,@Path("user_id") user_id : Int): Call<Int>

    @POST("orderextras")
    fun sendExtra(@Header("Authorization") token: String,@Body extra: OrderExtra): Call<OrderExtra>

    @POST("login")
    fun login(@Body login: Login) : Call<Token>
}

// singleton to limit high ressources use
object PizzaApi {
    val retrofitService : PizzaApiServices by lazy {
        retrofit.create(PizzaApiServices::class.java)
    }
}