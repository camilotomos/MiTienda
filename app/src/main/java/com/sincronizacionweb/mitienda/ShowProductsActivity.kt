package com.sincronizacionweb.mitienda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.sincronizacionweb.mitienda.databinding.ActivityShowProductsBinding
import com.sincronizacionweb.mitienda.listener.ListenerProduct
import com.sincronizacionweb.mitienda.models.ProductResponse
import com.sincronizacionweb.mitienda.persistence.LocalStoreRepository
import com.sincronizacionweb.mitienda.service.StoreRepository
import kotlinx.android.synthetic.main.activity_show_products.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import java.net.ConnectException

class ShowProductsActivity : AppCompatActivity(), ListenerProduct {

    private lateinit var binding: ActivityShowProductsBinding
    private lateinit var localRepository: LocalStoreRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewproducts.layoutManager =
            GridLayoutManager(this@ShowProductsActivity, 2)
        localRepository = LocalStoreRepository(this@ShowProductsActivity)
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val products = withContext(Dispatchers.Default) {
                        val storeRepository = StoreRepository()
                        val internalProducts = storeRepository.getAllProducts()
                        localRepository.insertProducts(internalProducts)
                        internalProducts
                    }

                    binding.recyclerviewproducts.adapter =
                        ProductsAdapter(products, this@ShowProductsActivity)

                } catch (e: Exception) {
                    if (e is ConnectException){
                        val productsLocal = withContext(Dispatchers.Default){
                            localRepository.getAllProducts()
                        }
                        binding.recyclerviewproducts.adapter =
                            ProductsAdapter(productsLocal, this@ShowProductsActivity)

                    }else {
                        Toast.makeText(this@ShowProductsActivity, "${e.message}", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }



        binding.buttonDrinks.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val products = withContext(Dispatchers.Default){
                        val storeRepository = StoreRepository()
                        storeRepository.getDrinks(1)
                    }

                    binding.recyclerviewproducts.layoutManager = GridLayoutManager(this@ShowProductsActivity,2)
                    binding.recyclerviewproducts.adapter = ProductsAdapter(products, this@ShowProductsActivity)

                }catch (e: Exception){
                    Toast.makeText(this@ShowProductsActivity, "${e.message}", Toast.LENGTH_LONG).show()
                }
            }

        }

        binding.buttonCake.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val products = withContext(Dispatchers.Default){
                        val storeRepository = StoreRepository()
                        storeRepository.getDrinks(2)
                    }

                    binding.recyclerviewproducts.layoutManager = GridLayoutManager(this@ShowProductsActivity,2)
                    binding.recyclerviewproducts.adapter = ProductsAdapter(products, this@ShowProductsActivity)

                }catch (e: Exception){
                    Toast.makeText(this@ShowProductsActivity, "${e.message}", Toast.LENGTH_LONG).show()
                }
            }

        }

        binding.buttonPie.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val products = withContext(Dispatchers.Default){
                        val storeRepository = StoreRepository()
                        storeRepository.getDrinks(3)
                    }

                    binding.recyclerviewproducts.layoutManager = GridLayoutManager(this@ShowProductsActivity,2)
                    binding.recyclerviewproducts.adapter = ProductsAdapter(products, this@ShowProductsActivity)

                }catch (e: Exception){
                    Toast.makeText(this@ShowProductsActivity, "${e.message}", Toast.LENGTH_LONG).show()
                }
            }

        }

        binding.buttonall.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val products = withContext(Dispatchers.Default) {
                        val storeRepository = StoreRepository()
                        storeRepository.getAllProducts()
                    }

                    binding.recyclerviewproducts.layoutManager =
                        GridLayoutManager(this@ShowProductsActivity, 2)
                    binding.recyclerviewproducts.adapter =
                        ProductsAdapter(products, this@ShowProductsActivity)

                } catch (e: Exception) {
                    Toast.makeText(this@ShowProductsActivity, "${e.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        binding.buttonalogout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    override fun onClickedProduct(product: ProductResponse) {
        startActivity(Intent(this, DetailProductActivity::class.java).apply {
            putExtra("DetalleProductos", product)
        })

    }
}