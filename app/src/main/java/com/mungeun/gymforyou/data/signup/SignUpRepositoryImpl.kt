package com.mungeun.data.repository.login

import com.mungeun.gymforyou.data.api.SignupApiService
import com.mungeun.gymforyou.domain.model.SignUp
import com.mungeun.gymforyou.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val api: SignupApiService) :
    SignUpRepository {


    override suspend fun postSignUpInfo(
        email: String,
        password: String,
        name: String,
        phoneNumber: String,
        picto: String,
    ) {
            api.insertUserInfo(
                SignUp(
                    email, password, name, phoneNumber, picto
                )
            )

    }
}
