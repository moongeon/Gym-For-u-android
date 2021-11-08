package com.mungeun.gymforyou.data.mapper

import com.mungeun.gymforyou.data.model.response.gymresponse.Data
import com.mungeun.gymforyou.domain.model.gym.Gym


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