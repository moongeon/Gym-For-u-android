package com.mungeun.gymforyou.domain.repository

import com.mungeun.gymforyou.domain.model.gym.Gym

interface GymRepository {

    suspend fun getGym(): List<Gym>
}