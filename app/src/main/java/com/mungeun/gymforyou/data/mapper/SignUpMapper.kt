package com.mungeun.gymforyou.data.mapper

import com.mungeun.gymforyou.data.model.response.signupresponse.SignUpResponse
import com.mungeun.gymforyou.domain.model.User

object SignUpMapper {

    fun mapperToSignUpMapper(signUpResponse: SignUpResponse): User {
        return User(
            _id = signUpResponse._id,
            email = signUpResponse.email,
            name = signUpResponse.name,
            phoneNumber = signUpResponse.phoneNumber,
            picto = signUpResponse.picto
        )
    }
}