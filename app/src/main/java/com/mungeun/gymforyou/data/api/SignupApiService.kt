package com.mungeun.gymforyou.data.api

import com.mungeun.gymforyou.domain.model.SignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApiService {
    @POST("api/users")
    suspend fun insertUserInfo(
        @Body signUp: SignUp,
    )
}