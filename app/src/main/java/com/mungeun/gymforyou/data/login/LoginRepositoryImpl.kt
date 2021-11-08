package com.mungeun.data.repository.login

import com.mungeun.gymforyou.data.api.LoginApiService
import com.mungeun.gymforyou.data.model.response.loginresponse.LoginResponse
import com.mungeun.gymforyou.domain.model.login.Login
import com.mungeun.gymforyou.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api : LoginApiService): LoginRepository {

    override suspend fun postLoginInfo(id: String, password: String): LoginResponse {
        return api.requestLogin(Login(id,password))
    }
}