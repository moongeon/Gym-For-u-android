package com.mungeun.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id : String,
    @SerializedName("passWord")
    val passWord : String
)
