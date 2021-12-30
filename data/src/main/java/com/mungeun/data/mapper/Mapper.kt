package com.mungeun.data.mapper

import com.mungeun.data.response.gymresponse.Data
import com.mungeun.data.response.loginresponse.LoginResponse
import com.mungeun.domain.model.User
import com.mungeun.domain.model.gym.Gym
import com.mungeun.domain.model.token.Token
import com.mungeun.gymforyou.data.model.response.signupresponse.SignUpResponse


fun mapperToLogin(loginResponse : LoginResponse) : Token {
    return Token(
        accessToken = loginResponse.accessToken,
        refreshToken = loginResponse.refreshToken
    )
}


// 헬스장 데이터 mapper
fun mapperToGym(gymResponse: Data): Gym {
    return Gym(
        _id = gymResponse._id,
        address = gymResponse.address ,
        description = gymResponse.description,
        homepage = gymResponse.homepage,
        location = gymResponse.location ,
        name = gymResponse.name,
        thumbnail = gymResponse.thumbnail
    )
}


// 회원가입 데이터 mapper
fun mapperToSignUpMapper(signUpResponse: SignUpResponse): User {
    return User(
        email = signUpResponse.email,
        name = signUpResponse.name,
        phoneNumber = signUpResponse.phoneNumber,
        picto = signUpResponse.picto
    )
}