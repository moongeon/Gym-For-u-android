package com.mungeun.gymforyou.data.mapper

import com.mungeun.domain.model.User
import com.mungeun.gymforyou.data.model.response.signupresponse.SignUpResponse

object SignUpMapper {

    fun mapperToSignUpMapper(signUpResponse: SignUpResponse): User {
        return User(
            email = signUpResponse.email,
            name = signUpResponse.name,
            phoneNumber = signUpResponse.phoneNumber,
            picto = signUpResponse.picto
        )
    }
}