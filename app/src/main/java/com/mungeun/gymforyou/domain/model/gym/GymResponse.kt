package com.mungeun.gymforyou.domain.model.gym

import com.google.gson.annotations.SerializedName
import com.mungeun.gymforyou.data.model.response.gymresponse.Data

data class GymResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("isSuccess")
    val isSuccess: Boolean
)