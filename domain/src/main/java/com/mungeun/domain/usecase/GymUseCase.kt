package com.mungeun.domain.usecase

import com.mungeun.domain.model.gym.Gym
import com.mungeun.domain.repository.GymRepository
import javax.inject.Inject

class GymUseCase @Inject constructor(private val repository: GymRepository) {

    suspend fun invoke(): List<Gym>{
        return repository.getGym()
    }
}