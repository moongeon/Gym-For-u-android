package com.mungeun.gymforyou.views.gym_detail.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mungeun.domain.model.gym.gym_detail.GymDetailChat
import com.mungeun.gymforyou.databinding.ListItemGymDetailChatBinding

class GymDetailChatAdapter : ListAdapter<GymDetailChat,RecyclerView.ViewHolder>(GymDetailChatDiffCallback()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GymDetailChatViewHolder(ListItemGymDetailChatBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       val item = getItem(position)
        (holder as GymDetailChatViewHolder).bind(item)
    }


    class GymDetailChatViewHolder(private val binding: ListItemGymDetailChatBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(item : GymDetailChat){
            var dataSetHashTag = listOf<String>(
                "퍼스널 트레이닝","PR 측정","3대 200이상")
            var chatHashTagAdapter = ChatHashTagAdapter()
            binding.data = item
            binding.recyclerviewGymDetailChatTag.adapter = chatHashTagAdapter
            chatHashTagAdapter.submitList(dataSetHashTag)
        }

    }

}


class GymDetailChatDiffCallback : DiffUtil.ItemCallback<GymDetailChat>(){
    override fun areItemsTheSame(oldItem: GymDetailChat, newItem: GymDetailChat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GymDetailChat, newItem: GymDetailChat): Boolean {
        return oldItem == newItem
    }

}