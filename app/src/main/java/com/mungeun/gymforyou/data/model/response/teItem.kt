package com.mungeun.gymforyou.data.model.response

data class teItem(
    val _id: String,
    val address: Address,
    val createdAt: String,
    val description: String,
    val homepage: String,
    val location: Location,
    val name: String,
    val thumbnail: String,
    val updatedAt: String
)