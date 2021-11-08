package com.mungeun.gymforyou.domain.model.gym

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val detail: String,
    val main: String,
    val zipno: String
) : Parcelable

