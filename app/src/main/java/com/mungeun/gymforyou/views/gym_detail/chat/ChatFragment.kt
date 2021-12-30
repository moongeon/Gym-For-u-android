package com.mungeun.gymforyou.views.gym_detail.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mungeun.domain.model.gym.gym_detail.GymDetailChat
import com.mungeun.gymforyou.databinding.FragmentChatBinding
import com.mungeun.gymforyou.utilities.autoCleared


class ChatFragment : Fragment() {

    private var mBinding by autoCleared<FragmentChatBinding>()



    var dataSet = listOf<GymDetailChat>(
        GymDetailChat(
            "브로콜리",
            "근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중...",
            "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"
        ),
        GymDetailChat(
            "브로콜리",
            "근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중...",
            "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"
        ),
        GymDetailChat(
            "브로콜리",
            "근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중...",
            "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"
        ),
        GymDetailChat(
            "브로콜리",
            "근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중...",
            "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var gymDetailChatAdapter = GymDetailChatAdapter()
       mBinding = FragmentChatBinding.inflate(inflater,container,false).apply {
           recyclerviewGymdetailChat.adapter = gymDetailChatAdapter
       }
        gymDetailChatAdapter.submitList(dataSet)

        return mBinding.root
    }


}