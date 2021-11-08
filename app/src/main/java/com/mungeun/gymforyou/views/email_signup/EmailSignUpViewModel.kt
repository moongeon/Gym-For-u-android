package com.mungeun.gymforyou.views.email_signup

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mungeun.domain.usecase.SignUpUseCase
import com.mungeun.gymforyou.utilities.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmailSignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) :
    ViewModel() {
    val email: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")
    val pwConfirm: MutableLiveData<String> = MutableLiveData("")
    val name: MutableLiveData<String> = MutableLiveData("")
    val phoneNm: MutableLiveData<String> = MutableLiveData("")

    //중복클릭시간차이
    private val MIN_CLICK_INTERVAL: Long = 2000

    //마지막으로 클릭한 시간
    private var mLastClickTime: Long = 0

    private val _isEmailEmpty = MutableLiveData<Event<Boolean>>()
    private val _isPwEmpty = MutableLiveData<Event<Boolean>>()
    private val _isPwConfirmEmpty = MutableLiveData<Event<Boolean>>()
    private val _pwNotMatch = MutableLiveData<Event<Boolean>>()
    private val _signUp = MutableLiveData<Event<Boolean>>()
    private val _signUpSuccess = MutableLiveData<Event<Boolean>>()

    val isLoading = MutableLiveData(false)


    val isEmailEmpty: LiveData<Event<Boolean>> get() = _isEmailEmpty
    val isPwEmpty: LiveData<Event<Boolean>> get() = _isPwEmpty
    val isPwConfirmEmpty: LiveData<Event<Boolean>> get() = _isPwConfirmEmpty
    val pwNotMatch: LiveData<Event<Boolean>> get() = _pwNotMatch
    val signUp: LiveData<Event<Boolean>> get() = _signUp
    val signUpSuccess: LiveData<Event<Boolean>> get() = _signUpSuccess

    fun onSignUpClick() {
        val currentClickTime = SystemClock.uptimeMillis()
        val elapsedTime: Long = SystemClock.uptimeMillis() - mLastClickTime
        mLastClickTime = currentClickTime
        if (elapsedTime <= MIN_CLICK_INTERVAL)
            return
        val email: String = email.value.toString().trim()
        val pw: String = pw.value.toString().trim()
        val pwConfirm: String = pwConfirm.value.toString().trim()
        val name: String = name.value.toString().trim()
        val phoneNm: String = phoneNm.value.toString().trim()
        when {
            email.isBlank() -> {
                _isEmailEmpty.value = Event(true)
            }
            pw.isBlank() -> {
                _isPwEmpty.value = Event(true)
            }
            pwConfirm.isBlank() -> {
                _isPwConfirmEmpty.value = Event(true)
            }
            pw != pwConfirm -> {
                _pwNotMatch.value = Event(true)
            }
            name.isBlank() -> {
                _pwNotMatch.value = Event(true)
            }
            phoneNm.isBlank() -> {
                _pwNotMatch.value = Event(true)
            }
            else -> {
                viewModelScope.launch {
                    isLoading.value = true
                    val response = signUpUseCase.invoke(email,
                        pw,
                        name,
                        phoneNm,
                        ""
                    )
                    isLoading.value = false
                    if (response.isSuccess == "true") {
                        _signUpSuccess.value = Event(true)
                    } else print("회원가입실패")
                }


            }
        }
    }

}