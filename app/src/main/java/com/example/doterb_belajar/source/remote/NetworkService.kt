package com.example.doterb_belajar.source.remote

import com.example.doterb_belajar.model.Belajar
import com.example.doterb_belajar.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    @POST("oauth/token")
    @FormUrlEncoded
    fun refreshTokenAsync(
        @Query("token") token: String
    ): Deferred<Response<LoginResponse>>

    @POST("oauth/token")
    @FormUrlEncoded
    fun loginAsync(
        @Field("username") username: String,
        @Field("password") password: String
    ): Deferred<Response<LoginResponse>>

    @GET("/v1/sentra_belajars/")
    @FormUrlEncoded
    fun getBelajarListAsync(
        @Header("Authorization") auth: String
    ): Deferred<Response<Belajar>>
}