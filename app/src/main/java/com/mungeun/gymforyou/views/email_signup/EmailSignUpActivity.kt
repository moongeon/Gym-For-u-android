package com.mungeun.gymforyou.views.email_signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
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
          it.findNavController().navigateUp()
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
//            isLottieLoading.observe(this@EmailSignUpActivity, Observer { loading ->
//                if (loading) showProgressDialog()
//                else hideProgressDialog()
//            })
        }
    }

//    private fun signUp(email: String, password: String) {
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this@EmailSignUpActivity) { task ->
//                if (task.isSuccessful) {
//                    emailLogin(email = email, password = password)
//                } else {
//                    showToast("회원가입 실패")
//                }
//            }
//    }

//    private fun emailLogin(email: String, password: String) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    uuid = auth.currentUser?.uid.toString()
//                    viewModel.insertUser(
//                        User(
//                            id = uuid,
//                            name = "무전" + getTimestamp().toString()
//                                .subSequence(0, 6),
//                            fcm = fcm,
//                            email = email,
//                            pw = password,
//                            image = "https://firebasestorage.googleapis.com/v0/b/nomoneytrip-63056.appspot.com/o/logo.PNG?alt=media&token=af7fe080-92fa-4fba-bab9-e9c1c3b85380"
//                        )
//                    )
//                }
//            }
//    }
}
