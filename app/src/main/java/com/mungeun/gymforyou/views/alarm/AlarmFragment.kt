package com.mungeun.gymforyou.views.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mungeun.domain.model.alarm.Alarm
import com.mungeun.gymforyou.databinding.FragmentAlarmBinding
import com.mungeun.gymforyou.utilities.autoCleared

class AlarmFragment : Fragment() {

    private val viewModel: AlarmViewModel by viewModels()
    private var mBinding by autoCleared<FragmentAlarmBinding>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBinding = FragmentAlarmBinding.inflate(inflater,container,false).apply {
            vm = viewModel
            lifecycleOwner = this@AlarmFragment
        }
        var adapter = AlarmAdapter()
        mBinding.rvAlarms.adapter = adapter
        var test = arrayListOf<Alarm>()
        test.add(
            Alarm(
                "asas", "", "", 0, false, "알림 메시지입니다. 알림 메시지 입니다.\n" +
                        "알림 메시지 입니다. 알림 메시지..", 1000, ""
            )
        )
        adapter.submitList(test)


        return mBinding.root
    }


}