package com.mungeun.gymforyou.domain.model.gym

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val latitude: Int,
    val longitude: Int
) : Parcelable