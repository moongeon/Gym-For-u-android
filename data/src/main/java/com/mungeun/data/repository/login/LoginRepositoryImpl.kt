package com.mungeun.data.repository.login

import com.mungeun.data.api.ApiInterface
import com.mungeun.data.mapper.LoginMapper
import com.mungeun.domain.model.Login
import com.mungeun.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val api : ApiInterface): LoginRepository {

    override suspend fun getLoginInfo(id: String, passWord: String): Login {
        return LoginMapper.mapperToLogin(api.getLogin(id,passWord))
    }
}