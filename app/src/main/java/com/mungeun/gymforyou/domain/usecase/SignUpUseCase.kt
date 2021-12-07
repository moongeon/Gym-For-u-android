package com.mungeun.domain.usecase

import com.mungeun.gymforyou.domain.model.User
import com.mungeun.gymforyou.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: SignUpRepository) {
    suspend fun invoke(
       email : String,
       password : String,
       name : String,
       phoneNumber : String,
       picto : String)
    : User {
        return repository.postSignUpInfo(
            email,
            password,
            name,
            phoneNumber,
            picto)
    }

}