package com.mungeun.gymforyou.views.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungeun.domain.usecase.GymUseCase
import com.mungeun.gymforyou.domain.model.gym.Gym
import com.mungeun.gymforyou.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.daum.mf.map.api.MapPOIItem
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gymUseCase: GymUseCase,
) : ViewModel() {


    private val _isGone = MutableLiveData<Event<Boolean>>(Event(false))
    val isGone: LiveData<Event<Boolean>>
        get() = _isGone

    val cardView = MutableLiveData("")

    private val _goAlarm = MutableLiveData<Event<Boolean>>(Event(false))
    val goAlarm: LiveData<Event<Boolean>>
        get() = _goAlarm

    private val _goGymDetail = MutableLiveData<Event<Boolean>>(Event(false))
    val goGymDetail: LiveData<Event<Boolean>>
        get() = _goGymDetail

    private val _gymList = MutableLiveData<Event<List<Gym>>>()
    val gymList: LiveData<Event<List<Gym>>>
        get() = _gymList


    init {
        viewModelScope.launch {
            try {
                val a = gymUseCase.invoke()

                _gymList.value = Event(a)

            } catch (ex: HttpException) {
                Log.d("", ex.stackTraceToString())
            }

        }

    }


    fun updateCardView(p1: MapPOIItem?) {
        if (p1 != null) {
            cardView.postValue(p1.itemName)
        }
    }

    fun goAlarm() {
        _goAlarm.value = Event(true)
    }

    fun goGymDetail() {
        _goGymDetail.value = Event(true)
    }
}