package com.mungeun.gymforyou.domain.repository

import com.mungeun.gymforyou.domain.model.SignUpt

interface SignUpRepository {
    suspend fun postSignUpInfo(
        email : String,
        password : String,
        name : String,
        phoneNumber : String,
        picto : String
    ): SignUpt

}