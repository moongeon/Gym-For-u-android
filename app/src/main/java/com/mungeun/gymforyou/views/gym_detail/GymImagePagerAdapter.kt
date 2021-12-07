package com.mungeun.gymforyou.views.gym_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mungeun.gymforyou.databinding.ListItemGymDetailSliderBinding

class GymImagePagerAdapter :
    ListAdapter<test, RecyclerView.ViewHolder>(GymImagePagerDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GymImagePagerViewHolder(
            ListItemGymDetailSliderBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val image = getItem(position)
        (holder as GymImagePagerAdapter.GymImagePagerViewHolder).bind(image)
    }


    inner class GymImagePagerViewHolder(private val binding: ListItemGymDetailSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(url: test) {
            binding.apply {
                this.url = url.a
            }
        }
    }
}


class GymImagePagerDiffCallback : DiffUtil.ItemCallback<test>() {
    override fun areItemsTheSame(oldItem: test, newItem: test): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: test, newItem: test): Boolean {
        return oldItem == newItem
    }

}