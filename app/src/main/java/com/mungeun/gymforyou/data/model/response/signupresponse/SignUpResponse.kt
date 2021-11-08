package com.mungeun.gymforyou.data.model.response.signupresponse

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("isSuccess")
    val isSuccess : String
)
