package com.mungeun.domain.usecase


import com.mungeun.domain.model.token.Token
import com.mungeun.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend fun invoke(
        id:String,
        password : String): Token {
        return repository.postLoginInfo(id,password)
    }

}