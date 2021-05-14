package com.sincronizacionweb.mitienda.listener

import com.sincronizacionweb.mitienda.models.ProductResponse

interface ListenerProduct {

    fun onClickedProduct(product: ProductResponse)

}