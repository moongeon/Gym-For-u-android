package com.mungeun.data.api

import com.mungeun.data.model.LoginResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiInterface {
    @POST("auth/login")
    suspend fun getLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ):LoginResponse

}