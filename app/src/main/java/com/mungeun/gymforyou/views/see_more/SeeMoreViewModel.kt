package com.mungeun.gymforyou.views.see_more

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mungeun.gymforyou.utilities.Event
import com.mungeun.gymforyou.utilities.preference.PreferenceManger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeMoreViewModel @Inject constructor(preferenceManger: PreferenceManger): ViewModel() {


    private val _onClickLogout = MutableLiveData<Event<Boolean>>()
    val onClickLogout: LiveData<Event<Boolean>>
        get() = _onClickLogout


    fun onClickLogout(){
        _onClickLogout.value = Event(true)
    }




}