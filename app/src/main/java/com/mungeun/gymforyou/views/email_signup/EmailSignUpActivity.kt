package com.mungeun.gymforyou.views.email_signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.base.BaseActivity
import com.mungeun.gymforyou.databinding.ActivityEmailSignupBinding
import com.mungeun.gymforyou.views.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmailSignUpActivity : BaseActivity() {
    private lateinit var mBinding : ActivityEmailSignupBinding
    private val viewModel: EmailSignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_email_signup)
        mBinding.vm = viewModel
        mBinding.lifecycleOwner = this
        mBinding.toolbar.setNavigationOnClickListener {
            finish()
        }
        initViewModelCallback()

    }

    private fun initViewModelCallback() {
        with(viewModel) {

            isEmailEmpty.observe(this@EmailSignUpActivity, Observer {
                mBinding.etEmail.error = getString(R.string.enter_email_text)
            })
            isPwEmpty.observe(this@EmailSignUpActivity, Observer {
                mBinding.etPw.error = getString(R.string.enter_password_text)
            })
            isPwConfirmEmpty.observe(this@EmailSignUpActivity, Observer {
                mBinding.etPwConfirm.error = getString(R.string.enter_password_confirm_text)
            })
            pwNotMatch.observe(this@EmailSignUpActivity, Observer {
                Snackbar.make(mBinding.snackbar, R.string.password_confirm_not_same_text, 600).show()
            })
            fetchState.observe(this@EmailSignUpActivity,{
                showToast(it.toString())
                hideProgress()
            })
            signUp.observe(this@EmailSignUpActivity, Observer {
            })
            signUpSuccess.observe(this@EmailSignUpActivity, Observer {

                val intent: Intent = Intent(
                    this@EmailSignUpActivity,
                    LoginActivity::class.java
                )
                showToast("회원가입성공")
                //Snackbar.make(mBinding.snackbar, "회원가입성공!!", 600).show()
                startActivity(intent)
                finish()
            })

        }
    }


}
