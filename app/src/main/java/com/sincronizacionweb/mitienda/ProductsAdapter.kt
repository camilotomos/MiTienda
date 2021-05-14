package com.sincronizacionweb.mitienda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sincronizacionweb.mitienda.databinding.ItemProductsBinding
import com.sincronizacionweb.mitienda.listener.ListenerProduct
import com.sincronizacionweb.mitienda.models.ProductResponse
import com.sincronizacionweb.mitienda.utils.URL

class ProductsAdapter(val listProducts: List<ProductResponse>, val listener: ListenerProduct) : RecyclerView.Adapter<ProductsAdapter.MyViewHolder>(){

    private lateinit var binding: ItemProductsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
         val inflater = LayoutInflater.from(parent.context)
        binding = ItemProductsBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(listProducts[position])
    }

    override fun getItemCount(): Int = listProducts.size

    inner class MyViewHolder(private val itemProductsBinding: ItemProductsBinding, private val listenerProduct: ListenerProduct) : RecyclerView.ViewHolder(itemProductsBinding.root){

        fun bindItem(product:ProductResponse){
            itemProductsBinding.textviewitem.text = product.name
            val url = "$URL${product.image}"

            product.bitmap?.let {
                itemProductsBinding.imageviewitem.setImageBitmap(it)
            } ?:Glide.with(itemProductsBinding.root.context).load(url).into(binding.imageviewitem)

            itemProductsBinding.root.setOnClickListener {
                listenerProduct.onClickedProduct(product)
            }

        }

    }

}
//
//itemProductsBinding.root.setOnClickListener {
//    var intentToDetailProduct = Intent(itemProductsBinding.root.context, DetailProductActivity::class.java)
//    intentToDetailProduct.putExtra(DetailProductActivity.DetalleProductos, product)
//    itemProductsBinding.root.context.startActivity(intentToDetailProduct)
//}