package com.mungeun.domain.repository

import com.mungeun.domain.model.token.Token


interface LoginRepository {
    suspend fun postLoginInfo(
        id: String,
        password: String
    ): Token

}