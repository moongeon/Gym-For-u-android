package com.mungeun.gymforyou.views.chatting.chat_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mungeun.gymforyou.databinding.FragmentChatDetailBinding
import com.mungeun.gymforyou.utilities.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatDetailFragment : Fragment() {

    private val viewModel: ChatDetailViewModel by viewModels()
    private var mBinding by autoCleared<FragmentChatDetailBinding>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var adapter = ChatDetailAdapter()
        mBinding = FragmentChatDetailBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = this@ChatDetailFragment
            vm = viewModel
            recyclerViewReceiver.adapter = adapter
        }

        with(viewModel) {
            message.observe(viewLifecycleOwner, {
                adapter.submitList(it.toList())
                mBinding.recyclerViewReceiver.scrollToPosition(it.size - 1)
            })

        }



        return mBinding.root

    }
}