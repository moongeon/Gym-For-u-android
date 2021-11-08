package com.mungeun.gymforyou.views.see_more

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mungeun.gymforyou.databinding.FragmentSeeMoreBinding
import com.mungeun.gymforyou.views.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeMoreFragment : Fragment() {

    private lateinit var mBinding : FragmentSeeMoreBinding

    private val viewModel : SeeMoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentSeeMoreBinding.inflate(inflater,container,false)

        with(mBinding){
            vm = viewModel
            lifecycleOwner = this@SeeMoreFragment
        }

        with(viewModel){
            onClickLogout.observe(viewLifecycleOwner,{
                requireActivity().startActivity(Intent(requireActivity(),LoginActivity::class.java))
                requireActivity().finish()


            })
        }


        return mBinding.root
    }
}