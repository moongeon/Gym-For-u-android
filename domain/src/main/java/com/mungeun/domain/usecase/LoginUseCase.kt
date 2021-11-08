package com.mungeun.domain.usecase

import com.mungeun.domain.model.Login
import com.mungeun.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    suspend fun invoke(
        id:String,
        passWord : String)
    : Login{
        return repository.getLoginInfo(id,passWord)
    }

}