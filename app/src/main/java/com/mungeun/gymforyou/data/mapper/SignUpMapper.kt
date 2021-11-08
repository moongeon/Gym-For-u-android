package com.mungeun.data.mapper

import com.mungeun.gymforyou.data.model.response.signupresponse.SignUpResponse
import com.mungeun.gymforyou.domain.model.SignUpt

object SignUpMapper {

    fun mapperToSignUpMapper(signUpResponse: SignUpResponse): SignUpt {
        return SignUpt(
            isSuccess = signUpResponse.isSuccess
        )
    }
}