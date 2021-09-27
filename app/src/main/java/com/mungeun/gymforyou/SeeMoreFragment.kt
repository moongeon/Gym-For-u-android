package com.mungeun.gymforyou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mungeun.gymforyou.databinding.FragmentHomeBinding
import com.mungeun.gymforyou.databinding.FragmentSeeMoreBinding

class SeeMoreFragment : Fragment() {

    private lateinit var mBinding : FragmentSeeMoreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSeeMoreBinding.inflate(inflater,container,false)
        mBinding = binding

        return mBinding.root
    }
}