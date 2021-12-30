package com.mungeun.gymforyou.views.alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mungeun.domain.model.alarm.Alarm
import com.mungeun.gymforyou.databinding.ListItemAlarmBinding


class AlarmAdapter : ListAdapter<Alarm, AlarmAdapter.ViewHolder>(Diffcallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemAlarmBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ViewHolder(private val binding: ListItemAlarmBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: Alarm) {
            binding.item = item

            binding.executePendingBindings()

        }

    }


}
// areItemsTheSame와 areContentsTheSame의 차이는
private class Diffcallback : DiffUtil.ItemCallback<Alarm>() {
    override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
        return oldItem == newItem
    }


}