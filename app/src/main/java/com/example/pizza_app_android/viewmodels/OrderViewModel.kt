package com.example.pizza_app_android.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pizza_app_android.PizzaApi
import com.example.pizza_app_android.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrderViewModel : ViewModel() {
    var orderCredentials = Token("","",false,0)
    private var order_id = 0;
    private val orderSelections  = mutableListOf<Selection>();
    private val orderExtras  = mutableMapOf<ProductType,MutableList<Product>>();
    private val selection:MutableMap<String,Product> = mutableMapOf<String,Product>(
/*      ProductType.Drink.name to "no drink",
        ProductType.Sauce.name to "no sauce"*/
    );

    fun getOrderSelection():List<Selection>{
        return orderSelections;
    }
    fun getOrderExtras():MutableMap<ProductType,MutableList<Product>>{
        return orderExtras;
    }

    fun getSelection():MutableMap<String,Product>{
        return selection;
    }

    fun addMenuToOrder(){
        if (!selection.isEmpty()){
            val sauce=  selection[ProductType.Sauce.name]
            val drink=  selection[ProductType.Drink.name]
            val pizza=  selection[ProductType.Pizza.name]
            val chicken=  selection[ProductType.Chicken.name]
            Log.i("Selection","Current selection"+selection.toString())
            orderSelections.add(Selection(sauce,drink,pizza,chicken));
            selection.clear()
        }
    }

    fun applySelection(productType: ProductType,product: Product){
        selection[productType.name]= product;
        Log.i("Map", selection.toString())
    }

    fun showOrderContent(){
        Log.i("Order content","Menu's selected")
        Log.i("Order content",orderSelections.toString())
        Log.i("Order content","Extra's :")
        Log.i("Order content","")
        for((k,v) in orderExtras){
            Log.i("Order content","${k} ${v}")
        }

        Log.i("Order content","")
    }

    fun addExtra(productType: ProductType,product: Product){
        if(!orderExtras.containsKey(productType)){
            orderExtras[productType]= mutableListOf<Product>(product)
        }
        else{
            orderExtras[productType]!!.add(product)
/*            for(i in 1..productQuantity-1){
            }
            if(productQuantity==0){

            }
            else{
            }*/
        }
    }

    fun sendOrder(){
        // first we need to add a in the table order by giving our user_id
        addNewOrderInDb()

        Log.i("Send Order",orderCredentials.token)
        orderSelections.forEach {
            Log.i("Send Order",it.toString())
            Log.i("Send Order","${it.sauce?.id} ${it.drink?.id} ${it.pizza?.id} ${it.chicken?.id}")
            val menu = Menu(it.sauce?.id,it.drink?.id,it.pizza?.id,it.chicken?.id)
            val call: Call<Int> = PizzaApi.retrofitService.sendMenu("Bearer "+orderCredentials.token,menu)
            call.enqueue(object : Callback<Int?> {
                override fun onResponse(call: Call<Int?>, response: Response<Int?>) {
                    Log.i( "Send Order","Menu added to api")
                    if (response.isSuccessful){
                        val menu_id = response.body()
                        if(response != null){
                            Log.i("RESPO","wat ${response}")
                            val elementOfOrder = ElementOfOrder(order_id,menu_id!!)
                            addElementOfOrder(elementOfOrder)
                        }
                    }

                }

                override fun onFailure(call: Call<Int?>, t: Throwable) {
                    Log.i( "API","Error while trying to add a menu to api")
                }
            })
        }

        orderExtras.forEach {
            Log.i("Order",it.toString())
            val type = it.key
            val products:List<Product> = it.value
            products.forEach {
                var orderextra = OrderExtra(order_id)
                when(type){
                    ProductType.Drink-> orderextra = OrderExtra(order_id,it.id)
                    ProductType.Pizza-> orderextra = OrderExtra(order_id,null,it.id)
                    ProductType.Chicken-> orderextra = OrderExtra(order_id,null,null,it.id)
                    ProductType.Sauce-> orderextra = OrderExtra(order_id,null,null,null,it.id)
                }

                //Log.i("Login","Bearer "+orderCredientals.token)
                val call: Call<OrderExtra> = PizzaApi.retrofitService.sendExtra("Bearer "+orderCredentials.token,orderextra)
                call.enqueue(object : Callback<OrderExtra?> {
                    override fun onResponse(call: Call<OrderExtra?>, response: Response<OrderExtra?>) {
                        Log.i( "API","Item added to api")
                    }

                    override fun onFailure(call: Call<OrderExtra?>, t: Throwable) {
                        Log.i( "API","Error while trying to add a extra to api")
                    }
                })
            }
        }
    }

    fun addNewOrderInDb(){
        val call: Call<Int> = PizzaApi.retrofitService.addOrder("Bearer "+orderCredentials.token,orderCredentials.user_id)
        call.enqueue(object : Callback<Int?> {
            override fun onResponse(call: Call<Int?>, response: Response<Int?>) {
                Log.i( "Send Order","New order created in db")
                Log.i("YES","nothing ${response.body().toString()}")
                if (response.isSuccessful){
                    val response = response.body()
                    if (response != null){
                        order_id = response;
                    }
                }
            }

            override fun onFailure(call: Call<Int?>, t: Throwable) {
                Log.i( "API","Error while trying to add a menu to api")
            }
        })
    }
    fun addElementOfOrder(elementOfOrder: ElementOfOrder){
        val call: Call<Int> = PizzaApi.retrofitService.sendElementOfOrder("Bearer "+orderCredentials.token,elementOfOrder)
        call.enqueue(object : Callback<Int?> {
            override fun onResponse(call: Call<Int?>, response: Response<Int?>) {
                Log.i( "Send Order","Element of order added to api")
            }

            override fun onFailure(call: Call<Int?>, t: Throwable) {
                Log.i( "API","Error while trying to add a menu to api")
            }
        })
    }
    fun orderTotal():Float{
        var total = 0f;
        orderSelections.forEach {
            total += it.price;
        }
        orderExtras.forEach{
            val extras = it.value
            extras.forEach{
                total += it.price
            }
        }
        return total;
    }


}

