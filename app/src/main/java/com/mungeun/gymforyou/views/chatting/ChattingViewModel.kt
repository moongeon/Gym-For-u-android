package com.mungeun.gymforyou.views.chatting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mungeun.gymforyou.utilities.Event


class ChattingViewModel : ViewModel() {

    private val _newMessageCall = MutableLiveData<Event<Boolean>>()
    val newMessageCall: LiveData<Event<Boolean>>
        get() = _newMessageCall


    fun goNewMessage(){
        _newMessageCall.postValue(Event(true))
    }




}