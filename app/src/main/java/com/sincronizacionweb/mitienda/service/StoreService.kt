package com.sincronizacionweb.mitienda.service

import com.sincronizacionweb.mitienda.models.LoginModel
import com.sincronizacionweb.mitienda.models.LoginResponseModel
import com.sincronizacionweb.mitienda.models.ProductResponse
import com.sincronizacionweb.mitienda.models.SignupModel
import retrofit2.Response
import retrofit2.http.*

interface StoreService {

    @POST("users/login")
    suspend fun login(@Body loginModel: LoginModel) : Response<LoginResponseModel>

    @POST("users/signup")
    suspend fun signup(@Body signupModel: SignupModel): Response<LoginResponseModel>

    @GET("products")
    suspend fun getAllProducts(): Response<List<ProductResponse>>

    @POST("users/autologin")
    suspend fun autoLogin(@Header("Authorization") token: String) : Response<Unit>

    @GET ("products")
    suspend fun getDrinks(@Query("category")category: Int): Response<List<ProductResponse>>
}