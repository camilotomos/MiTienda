package com.sincronizacionweb.mitienda.utils

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences("my_preference", Context.MODE_PRIVATE)
    }

    fun addString(key: String, value: String){
        sharedPreferences!!.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return if (sharedPreferences!!.contains(key)){
            sharedPreferences!!.getString(key,null)
        }else null
    }

    fun deleteString(key: String){
        sharedPreferences!!.edit().remove(key).apply()
    }

}