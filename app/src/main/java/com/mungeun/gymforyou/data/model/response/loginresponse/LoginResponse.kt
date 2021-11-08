package com.mungeun.gymforyou.data.model.response.loginresponse

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("refreshToken")
    val refreshToken : String,
    @SerializedName("accessToken")
    val accessToken : String
)
