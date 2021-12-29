package com.mungeun.gymforyou.views.login


import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.mungeun.gymforyou.MainActivity
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.base.BaseActivity
import com.mungeun.gymforyou.databinding.ActivityLoginBinding
import com.mungeun.gymforyou.utilities.EventObserver
import com.mungeun.gymforyou.utilities.message.SnackbarMessageManager
import com.mungeun.gymforyou.views.signup.SignUpActivity
import com.mungeun.gymforyou.widget.FadingSnackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var mBinding: ActivityLoginBinding

    private lateinit var snackbar: FadingSnackbar
    val snackbarMessageManager = SnackbarMessageManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // viewmodel dataBinding 설정
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        mBinding.vm = viewModel
        mBinding.lifecycleOwner = this
        // snackbar 설정
        snackbar = mBinding.snackbar


        with(viewModel) {
            successLogin.observe(this@LoginActivity, {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            })
            goSignUp.observe(this@LoginActivity, EventObserver {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            })
            viewModel.snackBarMessage.observe(this@LoginActivity, EventObserver {
                Snackbar.make(mBinding.snackbar, it.messageId.toString(), 600).show()
            })
            fetchState.observe(this@LoginActivity,{
                showToast(it.toString())
                hideProgress()
            })
        }


    }

    private fun initSnackbar() {
        //val snackbarLayout = requireActivity().findViewById<FadingSnackbar>(R.id.snackbar)

    }


}