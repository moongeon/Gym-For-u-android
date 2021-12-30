package com.mungeun.domain.repository

interface SignUpRepository {
    suspend fun postSignUpInfo(
        email : String,
        password : String,
        name : String,
        phoneNumber : String,
        picto : String
    )

}