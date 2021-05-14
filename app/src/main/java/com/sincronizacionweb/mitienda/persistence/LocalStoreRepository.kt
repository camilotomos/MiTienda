package com.sincronizacionweb.mitienda.persistence

import android.content.Context
import com.sincronizacionweb.mitienda.models.ProductResponse
import com.sincronizacionweb.mitienda.persistence.dao.ProductDao

class LocalStoreRepository(context: Context) {

    private lateinit var productDao: ProductDao

    init {
        StoreBD.getInstance(context)?.let { storeBD->
            productDao = storeBD.productDao()
        }
    }

    fun insertProducts(listProducts: List<ProductResponse>){
        try {
            productDao.insertProducts(listProducts.map {
            it.toProductEnity()
            })
        }catch (e: Exception){
            throw e
        }
    }

    fun getAllProducts(): List<ProductResponse>{
        try {
            return productDao.getAllProducts().map {
                it.toProductResponse()
            }
        }catch (e: Exception){
            throw e
        }
    }
}