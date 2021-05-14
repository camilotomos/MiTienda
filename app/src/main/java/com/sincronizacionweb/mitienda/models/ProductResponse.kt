package com.sincronizacionweb.mitienda.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sincronizacionweb.mitienda.persistence.entities.ProductEntity
import com.sincronizacionweb.mitienda.utils.URL
import kotlinx.android.parcel.Parcelize
import java.net.URL

@Parcelize
data class ProductResponse(
        @SerializedName("_id")
        val id: String,
        val name: String,
        val image: String,
        val description: String,
        val price: Int,
        val score: Int,
        @SerializedName("delivery_time")
        val deliveryTime: Int,
        val category: Int,
        val bitmap: Bitmap? = null
):Parcelable
{
        fun toProductEnity() = ProductEntity(id, name, image, description, price, score, deliveryTime, category, urlToBitmap("$URL${image}"))

        private fun urlToBitmap(url: String): Bitmap{
                val openStream = URL(url).openStream()
                return BitmapFactory.decodeStream(openStream)
        }

}