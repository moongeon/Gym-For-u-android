package com.mungeun.domain.model.fcm

data class PushNotification(
    val data: NotificationData,
    val to: String
)
