package com.mungeun.gymforyou.views.chatting.newchatting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mungeun.gymforyou.databinding.NewChattingFragmentBinding

class NewChattingFragment : Fragment() {


    private val viewModel: NewChattingViewModel by viewModels()
    private lateinit var mBinding : NewChattingFragmentBinding
   // private val args : NewChattingFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = NewChattingFragmentBinding.inflate(inflater,container,false)
        mBinding = binding
        mBinding.vm = viewModel

       // var a = args.chatId

        return mBinding.root
    }



}