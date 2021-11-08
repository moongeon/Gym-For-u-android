package com.mungeun.data.mapper

import com.mungeun.data.model.LoginResponse
import com.mungeun.domain.model.Login

object LoginMapper {

    fun mapperToLogin(loginResponse: LoginResponse): Login{
        return Login(
            id = loginResponse.id,
            passWord = loginResponse.passWord
        )
    }
}