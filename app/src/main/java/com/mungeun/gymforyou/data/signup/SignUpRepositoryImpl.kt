package com.mungeun.data.repository.login

import com.mungeun.data.mapper.SignUpMapper
import com.mungeun.gymforyou.data.api.LoginApiService
import com.mungeun.gymforyou.domain.model.SignUp
import com.mungeun.gymforyou.domain.model.SignUpt
import com.mungeun.gymforyou.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(private val api : LoginApiService): SignUpRepository {


    override suspend fun postSignUpInfo(
        email: String,
        password: String,
        name : String,
        phoneNumber: String,
        picto: String,
    ): SignUpt {
        return SignUpMapper.mapperToSignUpMapper(api.insertUserInfo(SignUp(
            email,password,name,phoneNumber,picto
        )))
    }
}
