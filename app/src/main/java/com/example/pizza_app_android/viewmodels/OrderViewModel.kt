package com.example.pizza_app_android.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pizza_app_android.PizzaApi
import com.example.pizza_app_android.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class OrderViewModel : ViewModel() {
    var orderCredentials = Token("","")

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
        Log.i("Send Order",orderCredentials.token)
        orderSelections.forEach {
            Log.i("Send Order",it.toString())
            Log.i("Send Order","${it.sauce?.id} ${it.drink?.id} ${it.pizza?.id} ${it.chicken?.id}")
            val menu = Menu(it.sauce?.id,it.drink?.id,it.pizza?.id,it.chicken?.id)
            val call: Call<Menu> = PizzaApi.retrofitService.sendMenu(orderCredentials.token,menu)
            call.enqueue(object : Callback<Menu?> {
                override fun onResponse(call: Call<Menu?>, response: Response<Menu?>) {
                    Log.i( "Send Order","Menu added to api")
                }

                override fun onFailure(call: Call<Menu?>, t: Throwable) {
                    Log.i( "API","Error while trying to add a menu to api")
                }
            })
        }

        orderExtras.forEach {
            Log.i("Order",it.toString())
            val products:List<Product> = it.value
            products.forEach {
                val orderextra = OrderExtra(1,1,it.id)
                val call: Call<OrderExtra> = PizzaApi.retrofitService.sendExtra(orderextra)
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

