package com.mungeun.gymforyou.data.api

import android.util.Log
import com.mungeun.gymforyou.data.model.response.loginresponse.LoginResponse
import com.mungeun.gymforyou.data.model.response.signupresponse.SignUpResponse
import com.mungeun.gymforyou.domain.model.SignUp
import com.mungeun.gymforyou.domain.model.login.Login
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("api/login")
    suspend fun requestLogin(
        @Body login: Login,
    ): LoginResponse


    @POST("api/users")
    suspend fun insertUserInfo(
        @Body signUp: SignUp,
    ): SignUpResponse


    companion object {
        private const val BASE_URL =
            "http://qazxswedc.iptime.org:3000/"

        fun create(preferenceManger: PreferenceManger): LoginApiService {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BASIC
            }
            val interceptor = Interceptor { chain ->
                with(chain) {
                    val newRequest = request().newBuilder()
                        .addHeader("Authorization", "Bearer " + preferenceManger.accessToken)
                        .build()
                    proceed(newRequest)
                }
            }
            Log.d("LoginApiService", "Token " + preferenceManger.accessToken)
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginApiService::class.java)
        }
    }

}




