package com.mungeun.domain.repository

import com.mungeun.domain.model.Login

interface LoginRepository {
    suspend fun getLoginInfo(
        id: String,
        passWord: String
    ): Login

}