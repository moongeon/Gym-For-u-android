package com.mungeun.data.api



import com.mungeun.data.response.loginresponse.LoginResponse
import com.mungeun.domain.model.login.Login
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("api/login")
    suspend fun requestLogin(
        @Body login: Login,
    ): LoginResponse



}




