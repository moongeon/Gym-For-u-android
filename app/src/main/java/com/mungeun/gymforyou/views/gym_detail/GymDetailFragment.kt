package com.mungeun.gymforyou.views.gym_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mungeun.gymforyou.databinding.FragmentGymDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GymDetailFragment
    : Fragment() {


    private lateinit var mBinding : FragmentGymDetailBinding
    private val viewModel: GymDetailViewModel by viewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = FragmentGymDetailBinding.inflate(inflater,container,false)
        mBinding.lifecycleOwner = this
        mBinding.vm = viewModel


        with(viewModel){
            backClick.observe(viewLifecycleOwner,{
                findNavController().navigateUp()
            })
        }




        return mBinding.root
    }



}