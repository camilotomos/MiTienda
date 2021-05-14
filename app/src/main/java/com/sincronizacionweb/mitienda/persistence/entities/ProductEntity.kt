package com.sincronizacionweb.mitienda.persistence.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sincronizacionweb.mitienda.models.ProductResponse

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_product")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "score")
    val score: Int,
    @ColumnInfo(name = "deliveryTime")
    val deliveryTime: Int,
    @ColumnInfo(name = "category")
    val category: Int,
    @ColumnInfo(name = "localImage")
    val localImage: Bitmap
){
    fun toProductResponse()= ProductResponse(id, name, image, description, price, score, deliveryTime, category)
}
