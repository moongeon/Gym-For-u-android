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


        viewModel.successLogin.observe(this, {
            //Snackbar.make(mBinding.snackbar, "테스트", 300).show()
            //snackbar.show("테스트","테스트",)
            startActivity(Intent(this, MainActivity::class.java))
        })

        viewModel.goSignUp.observe(this,EventObserver{
            startActivity(Intent(this,SignUpActivity::class.java))
        })

        viewModel.snackBarMessage.observe(this, EventObserver{
            Snackbar.make(mBinding.snackbar, it.messageId.toString(), 600).show()
        })

//            mBinding.vm.successLogin.observe(viewLifecycleOwner,EventObserver{
//
//            })


    }

    private fun initSnackbar() {
        //val snackbarLayout = requireActivity().findViewById<FadingSnackbar>(R.id.snackbar)

    }


}