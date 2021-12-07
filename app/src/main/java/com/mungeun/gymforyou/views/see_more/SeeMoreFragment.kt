package com.mungeun.gymforyou.views.see_more

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.mungeun.gymforyou.databinding.FragmentSeeMoreBinding
import com.mungeun.gymforyou.views.dialog.YesNoDialogFragment
import com.mungeun.gymforyou.views.dialog.onClickYesNo
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
        mBinding = FragmentSeeMoreBinding.inflate(inflater,container,false).apply {
            vm = viewModel
            lifecycleOwner = this@SeeMoreFragment
        }

        with(mBinding){
            toolbar.setNavigationOnClickListener {
                it.findNavController().navigateUp()
            }
        }


        with(viewModel){
            onClickLogout.observe(viewLifecycleOwner,{
                YesNoDialogFragment.newInstance("로그아웃","로그아웃 하시겠습니까?","실패","성공",
                object : onClickYesNo{
                    override fun clickYes() {
                        requireActivity().startActivity(Intent(requireActivity(), LoginActivity::class.java))
                        requireActivity().finish()
                    }

                    override fun clickNo() {

                    }

                }).
                show(requireActivity().supportFragmentManager,"dialog_schedule_hints")
//


            })
        }


        return mBinding.root
    }
}