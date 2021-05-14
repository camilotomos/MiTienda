package com.sincronizacionweb.mitienda.service

import com.sincronizacionweb.mitienda.models.LoginModel
import com.sincronizacionweb.mitienda.models.LoginResponseModel
import com.sincronizacionweb.mitienda.models.ProductResponse
import com.sincronizacionweb.mitienda.models.SignupModel

class StoreRepository {

    private var storeService: StoreService

    init {
        val serviceFactory = ServiceFactory()
        storeService = serviceFactory.getInstanceStoreService()
    }

    suspend fun login(loginModel: LoginModel): LoginResponseModel{
        val response = storeService.login(loginModel)
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }

    }

    suspend fun signup(signupModel: SignupModel): LoginResponseModel{
        val response = storeService.signup(signupModel)
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }

    }

    suspend fun getAllProducts(): List<ProductResponse>{
        val response = storeService.getAllProducts()
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }
    }

    suspend fun autologin(token: String): Boolean{
        val response = storeService.autoLogin("Bearer $token")
        return response.isSuccessful
    }

    suspend fun getDrinks(category: Int): List<ProductResponse>{
        val response = storeService.getDrinks(category)
        if (response.isSuccessful){
            return response.body()!!
        }else{
            throw Exception(response.message())
        }
    }

}