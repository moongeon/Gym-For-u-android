package com.mungeun.gymforyou.domain.repository

import com.mungeun.gymforyou.data.model.response.loginresponse.LoginResponse

interface LoginRepository {
    suspend fun postLoginInfo(
        id: String,
        password: String
    ): LoginResponse

}