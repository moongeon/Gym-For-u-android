package com.mungeun.gymforyou.views.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mungeun.gymforyou.databinding.ListItemHomeBinding
import com.mungeun.gymforyou.domain.model.gym.Gym


class HomeAdapter :
    ListAdapter<Gym, RecyclerView.ViewHolder>(ChattingDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChattingViewHolder(
            ListItemHomeBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val gym = getItem(position)
        (holder as ChattingViewHolder).bind(gym)
    }


    class ChattingViewHolder(private val binding: ListItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                val direction = HomeFragmentDirections.actionHomeFragmentToGymDetailFragment(
                    binding.gym as Gym
                )
                it.findNavController().navigate(direction)
            }
        }

        fun bind(item: Gym) {
            binding.apply {
                gym = item
            }
        }

    }

}


private class ChattingDiffCallback : DiffUtil.ItemCallback<Gym>() {
    override fun areItemsTheSame(oldItem: Gym, newItem: Gym): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Gym, newItem: Gym): Boolean {
        return oldItem == newItem
    }


}