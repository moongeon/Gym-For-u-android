package com.mungeun.gymforyou.views.gym_detail.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mungeun.gymforyou.databinding.ListItemGymDetailChatHashTagBinding


class ChatHashTagAdapter : ListAdapter<String, RecyclerView.ViewHolder>(ChatHashTagDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChatHashTagViewHolder(
            ListItemGymDetailChatHashTagBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ChatHashTagViewHolder).bind(item)
    }


    class ChatHashTagViewHolder(private val binding: ListItemGymDetailChatHashTagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.item = item
            binding.executePendingBindings()
        }

    }

}


class ChatHashTagDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}