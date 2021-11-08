package com.mungeun.gymforyou.views.intro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.mungeun.gymforyou.R
import com.mungeun.gymforyou.databinding.ActivityIntroBinding
import com.mungeun.gymforyou.views.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IntroActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        mBinding.lifecycleOwner = this

        lifecycleScope.launch {
            withContext(Dispatchers.Main){
                mBinding.imageView2.performClick()
            }
            delay(1500)
            move()
        }
    }
    private fun move(){
        startActivity(Intent(this,LoginActivity::class.java))
    }
}