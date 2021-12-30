package com.mungeun.gymforyou.views.gym_detail.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mungeun.gymforyou.databinding.FragmentReviewBinding
import com.mungeun.domain.model.gym.gym_detail.GymDetail


class ReviewFragment : Fragment() {

    private lateinit var mBinding: FragmentReviewBinding

    var dataSet = listOf<GymDetail>(
        GymDetail(
            "브로콜리",
            "근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중...",
            "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"
        ),
        GymDetail(
            "브로콜리",
            "근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중...",
            "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"
        ),
        GymDetail(
            "브로콜리",
            "근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중...",
            "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"
        ),
        GymDetail(
            "브로콜리",
            "근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중에서 깨끗하고 친절, 근처 헬스장 중...",
            "https://noticon-static.tammolo.com/dgggcrkxq/image/upload/v1638101071/noticon/gpr07ptl1x6evhew7li7.png"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var reviewAdapter = ReviewAdapter()
        mBinding = FragmentReviewBinding.inflate(inflater, container, false).apply {
            recyclerviewGymdetailReview.adapter = reviewAdapter
        }
        reviewAdapter.submitList(dataSet)


        return mBinding.root
    }


}