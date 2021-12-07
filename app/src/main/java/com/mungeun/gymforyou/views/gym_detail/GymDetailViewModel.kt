package com.mungeun.gymforyou.views.gym_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mungeun.gymforyou.domain.model.gym.Gym
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GymDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {



    val gym = savedStateHandle.getLiveData<Gym>("gymData")

    var gymName = MutableLiveData<String>()
    var gymDescription = MutableLiveData<String>()
    var gymthumbnail = MutableLiveData<String>()
    var gymAdress = MutableLiveData<String>()
    var gymHomepage = MutableLiveData<String>()

    init {
        gymName.value = gym.value?.name
        gymDescription.value = gym.value?.description
        gymAdress.value = gym.value?.address?.detail
        gymHomepage.value = gym.value?.homepage
        gymthumbnail.value = gym.value?.thumbnail
    }




}