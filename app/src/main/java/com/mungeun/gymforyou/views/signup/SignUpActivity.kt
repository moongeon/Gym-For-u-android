package com.mungeun.gymforyou.views.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.databinding.ActivitySignUpBinding
import com.mungeun.gymforyou.views.email_signup.EmailSignUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var mBinding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // viewmodel dataBinding 설정
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        with(mBinding){
            vm = viewModel
            lifecycleOwner = this@SignUpActivity
            toolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }

        // snackbar 설정
        with(viewModel){
            goEmailSignUp.observe(this@SignUpActivity,{
                val intent: Intent = Intent(
                    this@SignUpActivity,
                    EmailSignUpActivity::class.java
                )

                startActivity(intent)

            })


        }
    }

}