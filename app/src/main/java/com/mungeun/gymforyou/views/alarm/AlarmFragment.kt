package com.mungeun.gymforyou.views.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mungeun.gymforyou.databinding.FragmentAlarmBinding
import com.mungeun.gymforyou.domain.model.alarm.Alarm

class AlarmFragment : Fragment() {

    private lateinit var mBinding : FragmentAlarmBinding
    private val viewModel: AlarmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = FragmentAlarmBinding.inflate(inflater,container,false)
        mBinding.vm = viewModel
        mBinding.lifecycleOwner = this
        var adapter = AlarmAdapter()
        mBinding.rvAlarms.adapter = adapter
        var test = arrayListOf<Alarm>()
        test.add(Alarm("asas","","",0,false,"asasasasas",1000,""))
        adapter.submitList(test)


        return mBinding.root
    }



}