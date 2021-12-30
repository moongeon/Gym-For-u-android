package com.mungeun.domain.model.gym


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
@Parcelize
data class Gym(
    val _id: String,
    val address: Address,
    val description: String,
    val homepage: String,
    val location: Location,
    val name: String,
    val thumbnail: String
) : Parcelable