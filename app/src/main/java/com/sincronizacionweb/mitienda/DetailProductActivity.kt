package com.sincronizacionweb.mitienda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sincronizacionweb.mitienda.databinding.ActivityDetailProductBinding
import com.sincronizacionweb.mitienda.databinding.FragmentLoginBinding
import com.sincronizacionweb.mitienda.models.ProductResponse
import com.sincronizacionweb.mitienda.utils.URL
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.item_products.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailProductActivity : AppCompatActivity() {

    companion object{
        const val DetalleProductos = "DetalleProductos"
    }

    private lateinit var binding: ActivityDetailProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product: ProductResponse? = intent.getParcelableExtra<ProductResponse>(DetalleProductos)

        CoroutineScope(Dispatchers.Main).launch {
            binding.tvDetailProductName.text = product?.name
            binding.tvDetailProductDescription.text = product?.description
            binding.tvDetailProductScore.text = product?.score.toString()
            binding.tvDetailProductPrice.text = product?.price.toString()
            binding.tvDetailproductDeliveryTime.text = product?.deliveryTime.toString()
            binding.tvDetailproductCategory.text = product?.category.toString()

            val url = "$URL${product?.image}"
            Glide.with(this@DetailProductActivity).load(url).into(binding.ivDetailImage)
        }

    }
}