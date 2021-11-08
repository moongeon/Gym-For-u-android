package com.mungeun.gymforyou.data.gym

import com.mungeun.gymforyou.data.api.GymApiService
import com.mungeun.gymforyou.data.mapper.GymMapper
import com.mungeun.gymforyou.domain.model.gym.Gym
import com.mungeun.gymforyou.domain.repository.GymRepository
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import javax.inject.Inject

class GymRepositoryImpl @Inject constructor(
    private val api: GymApiService,
    private val preferenceManger: PreferenceManger,
) : GymRepository {
    override suspend fun getGym(): List<Gym> {
//        return api.getGym()
        return api.getGym().map {
            GymMapper.mapperToGym(it)
        }
    }
}
