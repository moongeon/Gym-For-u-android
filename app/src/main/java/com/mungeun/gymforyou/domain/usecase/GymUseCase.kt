package com.mungeun.domain.usecase

import com.mungeun.gymforyou.domain.model.gym.Gym
import com.mungeun.gymforyou.domain.repository.GymRepository
import javax.inject.Inject

class GymUseCase @Inject constructor(private val repository: GymRepository) {

    suspend fun invoke(): List<Gym>{
        return repository.getGym()
    }
}