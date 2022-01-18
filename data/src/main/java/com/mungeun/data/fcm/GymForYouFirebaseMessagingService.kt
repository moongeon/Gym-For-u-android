package com.mungeun.data.fcm

import com.mungeun.data.api.FCMNotificationApiService
import com.mungeun.domain.model.fcm.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * FCM 메시지 push
 * */
interface FCMPushRepository {
    suspend fun fcmPush(pushNotification : PushNotification): Response<ResponseBody>

}

@Singleton
class FCMPushRepositoryImpl @Inject constructor(private val fcmNotificationApiService : FCMNotificationApiService)  : FCMPushRepository{
    override suspend fun fcmPush(pushNotification: PushNotification): Response<ResponseBody> {
        return fcmNotificationApiService.postNotification(pushNotification)

    }

}
