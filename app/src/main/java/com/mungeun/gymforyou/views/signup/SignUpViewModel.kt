package com.mungeun.gymforyou.views.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mungeun.gymforyou.utilities.Event


class SignUpViewModel : ViewModel() {
    private val _serviceAgree = MutableLiveData(false)
    private val _personalInfoAgree = MutableLiveData(false)
    private val _allAgree = MutableLiveData(false)
    private val _goEmailSignUp = MutableLiveData<Event<Boolean>>()

    val serviceAgree: LiveData<Boolean> get() = _serviceAgree
    val personalInfoAgree: LiveData<Boolean> get() = _personalInfoAgree
    val allAgree: LiveData<Boolean> get() = _allAgree
    val goEmailSignUp: LiveData<Event<Boolean>> get() = _goEmailSignUp


    fun goPhoneAuth() {
        if (allAgree.value!!) {
            _goEmailSignUp.postValue(Event(true))
        }
    }

    fun agreeAll() {
        if (!(serviceAgree.value!! && personalInfoAgree.value!!)) { // 모두 동의안 된 경우
            _serviceAgree.value = true
            _personalInfoAgree.value = true
            _allAgree.value = true
        } else {
            _serviceAgree.value = false
            _personalInfoAgree.value = false
            _allAgree.value = false
        }
    }

    fun agreeService() {
        _serviceAgree.value = (!serviceAgree.value!!)
        _allAgree.value = serviceAgree.value!! && personalInfoAgree.value!!
    }

    fun agreePersonalInfo() {
        _personalInfoAgree.value = (!personalInfoAgree.value!!)
        _allAgree.value = serviceAgree.value!! && personalInfoAgree.value!!
    }


}