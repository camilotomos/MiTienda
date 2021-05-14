package com.sincronizacionweb.mitienda.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sincronizacionweb.mitienda.persistence.entities.ProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<ProductEntity>)

    @Query("SELECT * FROM product")
    fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE category = :id")
    fun getAllProductsById(id: Int): List<ProductEntity>
}