package com.mungeun.data.response.gymresponse

import com.google.gson.annotations.SerializedName
import com.mungeun.domain.model.gym.Address
import com.mungeun.domain.model.gym.Location

data class Data(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("address")
    val address: Address,
    @SerializedName("description")
    val description: String,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: String
)