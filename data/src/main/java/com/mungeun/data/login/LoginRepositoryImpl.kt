package com.mungeun.data.login

import com.mungeun.data.api.LoginApiService
import com.mungeun.data.mapper.mapperToLogin
import com.mungeun.domain.model.login.Login
import com.mungeun.domain.model.token.Token
import com.mungeun.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api : LoginApiService): LoginRepository {

    override suspend fun postLoginInfo(id: String, password: String): Token {
            return mapperToLogin(api.requestLogin(Login(id,password)))
}
}