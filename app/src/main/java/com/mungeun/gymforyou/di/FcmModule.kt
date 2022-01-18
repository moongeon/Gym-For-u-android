package com.mungeun.gymforyou.di

import com.mungeun.data.api.FCMNotificationApiService
import com.mungeun.data.fcm.FCMPushRepository
import com.mungeun.data.fcm.FCMPushRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FcmModule {

    @Provides
    @Singleton
    fun provideFCMService(): FCMNotificationApiService {
        return FCMNotificationApiService.create()
    }

    @Provides
    @Singleton
    fun provideFCMPushRepository(fcmNotificationApiService: FCMNotificationApiService): FCMPushRepository {
        return FCMPushRepositoryImpl(fcmNotificationApiService)
    }
}