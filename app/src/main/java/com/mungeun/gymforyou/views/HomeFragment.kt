package com.mungeun.gymforyou.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mungeun.gymforyou.databinding.FragmentHomeBinding
import net.daum.mf.map.api.MapView


class HomeFragment : Fragment() {

    private lateinit var mBinding : FragmentHomeBinding
    private lateinit var mapView : MapView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        mBinding = binding

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
                // 맵뷰 객체 생성
        mapView = MapView(activity)
        val mapViewContainer = mBinding.mapView as ViewGroup
        mapViewContainer.addView(mapView)
    }




}