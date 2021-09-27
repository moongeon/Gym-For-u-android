package com.mungeun.gymforyou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mungeun.gymforyou.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var mBinding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        mBinding = binding

        return mBinding.root
    }


}