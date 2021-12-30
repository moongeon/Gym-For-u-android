package com.mungeun.data.mapper

import com.mungeun.data.response.gymresponse.Data
import com.mungeun.domain.model.gym.Gym



object GymMapper {
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
}