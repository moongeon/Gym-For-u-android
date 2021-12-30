package com.mungeun.data.gym

import com.mungeun.data.api.GymApiService
import com.mungeun.data.mapper.GymMapper
import com.mungeun.domain.model.gym.Gym
import com.mungeun.domain.repository.GymRepository
import javax.inject.Inject

class GymRepositoryImpl @Inject constructor(
    private val api: GymApiService
) : GymRepository {
    override suspend fun getGym(): List<Gym> {
//        return api.getGym()
        return api.getGym().map {
            GymMapper.mapperToGym(it)
        }
    }
}
