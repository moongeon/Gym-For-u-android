package com.mungeun.domain.usecase

import com.mungeun.gymforyou.data.model.response.loginresponse.LoginResponse
import com.mungeun.gymforyou.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend fun invoke(
        id:String,
        password : String)
    : LoginResponse {
        return repository.postLoginInfo(id,password)
    }

}