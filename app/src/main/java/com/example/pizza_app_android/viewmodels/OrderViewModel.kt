package com.example.pizza_app_android.viewmodels


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.pizza_app_android.models.Menu
import com.example.pizza_app_android.models.Product
import com.example.pizza_app_android.models.ProductType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow



class OrderViewModel : ViewModel() {
    private val orderMenus  = mutableListOf<Menu>();
    private val orderExtras  = mutableMapOf<ProductType,MutableList<Product>>();
    private val selection:MutableMap<String,String> = mutableMapOf<String,String>(
/*      ProductType.Drink.name to "no drink",
        ProductType.Sauce.name to "no sauce"*/
    );

    fun getOrderMenus():List<Menu>{
        return orderMenus;
    }
    fun getOrderExtras():MutableMap<ProductType,MutableList<Product>>{
        return orderExtras;
    }

    fun addMenuToOrder(){
        if (!selection.isEmpty()){
            val sauce=  selection[ProductType.Sauce.name]?:""
            val drink=  selection[ProductType.Drink.name]?:""
            val pizza=  selection[ProductType.Pizza.name]?:""
            val chicken=  selection[ProductType.Chicken.name]?:""
            Log.i("Selection","Current selection"+selection.toString())
            orderMenus.add(Menu(sauce,drink,pizza,chicken));
            selection.clear()
        }
    }

    fun applySelection(productType: ProductType,product: Product){
        selection[productType.name]= product.name;
        Log.i("Map", selection.toString())
    }

    fun showOrderContent(){
        Log.i("Order content","Menu's selected")
        Log.i("Order content",orderMenus.toString())
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


}

