package com.mungeun.gymforyou.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mungeun.gymforyou.data.Chat
import com.mungeun.gymforyou.databinding.FragmentChattingBinding
import com.mungeun.gymforyou.databinding.ListItemChattingBinding

class CattingAdapter : ListAdapter<Chat, RecyclerView.ViewHolder>(ChattingDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ChattingViewHolder(
            ListItemChattingBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = getItem(position)
        (holder as ChattingViewHolder).bind(chat)
    }


    class ChattingViewHolder(private val binding: ListItemChattingBinding ): RecyclerView.ViewHolder(binding.root){
        fun bind(item : Chat){
            binding.apply {
                mainTitle.text = item.mainTitle
                subTitle.text = item.subTitle
            }
        }

    }

}



private class ChattingDiffCallback : DiffUtil.ItemCallback<Chat>(){
    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
      return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
        return oldItem == newItem
    }


}