package com.mungeun.gymforyou.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mungeun.domain.usecase.GymUseCase
import com.mungeun.gymforyou.base.BaseViewModel
import com.mungeun.gymforyou.domain.model.gym.Gym
import com.mungeun.gymforyou.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gymUseCase: GymUseCase,
) : BaseViewModel() {


    private val _goGymDetail = MutableLiveData<Event<Boolean>>(Event(false))
    val goGymDetail: LiveData<Event<Boolean>>
        get() = _goGymDetail

    private val _gymList = MutableLiveData<List<Gym>>()
    val gymList: LiveData<List<Gym>>
        get() = _gymList

    init {
        viewModelScope.launch(exceptionHandler) {
                _gymList.value = gymUseCase.invoke()
        }

    }


    fun goGymDetail(gym : Gym) {
        var a = gym
        _goGymDetail.value = Event(true)
    }
}