package com.mungeun.gymforyou.views.intro

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.databinding.ActivityIntroBinding
import com.mungeun.gymforyou.views.login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        mBinding.lifecycleOwner = this

        // 이 함수는 두개의 애니메이션 리소스를 함수 인자를 받는데요.
        //
        //첫 번째 인자는, 새로 나타나는 화면이 취해야 하는 애니메이션
        //
        //두 번째 인자는, 지금 화면이 취하는 애니메이션입니다.
        lifecycleScope.launch {
            overridePendingTransition(
                R.anim.fade_in,
                R.anim.fade_out
            )
            mBinding.imageView2.startAnimation(
                AnimationUtils.loadAnimation(
                    this@IntroActivity, R.anim.splash_in
                )
            )
            delay(300)
            mBinding.imageView2.startAnimation(
                AnimationUtils.loadAnimation(
                    this@IntroActivity, R.anim.splash_out
                )
            )
            delay(600)
            startActivity(Intent(this@IntroActivity, LoginActivity::class.java))
            finish()
        }
    }

}