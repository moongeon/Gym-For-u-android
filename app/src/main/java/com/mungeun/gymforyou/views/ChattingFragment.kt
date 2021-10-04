package com.mungeun.gymforyou.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mungeun.gymforyou.adapters.CattingAdapter
import com.mungeun.gymforyou.data.Chat
import com.mungeun.gymforyou.databinding.FragmentChattingBinding

class ChattingFragment : Fragment() {
    private lateinit var mBinding: FragmentChattingBinding

    private val dataSet = arrayListOf<Chat>().apply {
        add(Chat("타일런트", "Race.Zombie"))
    }
    private val cattingAdapter: CattingAdapter by lazy {
        CattingAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChattingBinding.inflate(inflater, container, false)
        binding.chattingList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cattingAdapter
        }
        mBinding = binding
        cattingAdapter.submitList(dataSet)

        return mBinding.root
    }
}