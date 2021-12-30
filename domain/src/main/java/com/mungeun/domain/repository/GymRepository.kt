package com.mungeun.domain.repository

import com.mungeun.domain.model.gym.Gym

interface GymRepository {

    suspend fun getGym(): List<Gym>
}