package com.mungeun.data.signup

import com.mungeun.data.api.SignupApiService
import com.mungeun.domain.model.SignUp
import com.mungeun.domain.repository.SignUpRepository
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
