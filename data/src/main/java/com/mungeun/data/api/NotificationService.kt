package com.mungeun.data.api

import com.mungeun.domain.model.fcm.PushNotification
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface FCMNotificationApiService {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>

    companion object {
        const val BASE_URL = "https://fcm.googleapis.com"
        const val SERVER_KEY =
            "AAAANpeVCKs:APA91bGjzK6BisYzIiGPcmrDmxoiXlGLQtzbSANWIgpyVLedTUzfzLMX8gP6FNKHXVybH77KbHEGzGR5Hc_jilCCiNcMHCRz5m7uZS3U6bg-e4evJHnmuwoD3sJePbwRJ6cohEa_FsjL"
        const val CONTENT_TYPE = "application/json"
        fun create(): FCMNotificationApiService {
            val logger = HttpLoggingInterceptor().apply {
                level =
                    HttpLoggingInterceptor.Level.BASIC
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FCMNotificationApiService::class.java)
        }
    }

}



