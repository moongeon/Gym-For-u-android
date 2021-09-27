package com.mungeun.gymforyou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mungeun.gymforyou.databinding.FragmentChattingBinding
import com.mungeun.gymforyou.databinding.FragmentHomeBinding

class ChattingFragment : Fragment() {
    private lateinit var mBinding : FragmentChattingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChattingBinding.inflate(inflater,container,false)
        mBinding = binding

        return mBinding.root
    }
}