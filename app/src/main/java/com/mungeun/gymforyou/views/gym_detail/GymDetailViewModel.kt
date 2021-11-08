package com.mungeun.gymforyou.views.gym_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mungeun.gymforyou.domain.model.gym.Gym
import com.mungeun.gymforyou.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GymDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _backClick = MutableLiveData<Event<Boolean>>()
    val backClick: LiveData<Event<Boolean>> get() = _backClick


    val gym = savedStateHandle.getLiveData<Gym>("gymData")

    var gymName = MutableLiveData<String>()
    var gymDescription = MutableLiveData<String>()
    var gymAdress = MutableLiveData<String>()
    var gymHomepage = MutableLiveData<String>()

    init {
        gymName.value = gym.value?.name
        gymDescription.value = gym.value?.description
        gymAdress.value = gym.value?.address?.detail
        gymHomepage.value = gym.value?.homepage
    }


    fun onClickBack() {
        _backClick.value = Event(true)
    }


}