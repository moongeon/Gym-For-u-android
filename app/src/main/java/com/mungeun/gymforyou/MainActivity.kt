package com.mungeun.gymforyou

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mungeun.gymforyou.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController

        //Setup bottom navigation
        NavigationUI.setupWithNavController(mBinding.bottomNav, navController)
        // 홈, 채팅, 더보기 일때만 바텀네비게이션 표시
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if (destination.id == R.id.homeFragment || destination.id == R.id.chattingFragment || destination.id == R.id.seeMoreFragment) {
                mBinding.bottomNav.visibility = View.VISIBLE
            } else {
                mBinding.bottomNav.visibility = View.GONE
            }
        }



    }
}