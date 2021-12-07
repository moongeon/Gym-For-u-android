package com.mungeun.gymforyou.views.chatting.chat_detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mungeun.gymforyou.databinding.ListItemNotificationMessageBinding
import com.mungeun.gymforyou.databinding.ListItemReceiverMessageBinding
import com.mungeun.gymforyou.databinding.ListItemSenderMessageBinding

class ChatDetailAdapter : ListAdapter<Message,RecyclerView.ViewHolder>(ChatDetailCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var itemView : View? = null
        when(viewType){
            0 -> {
                return ChatDetailSenderViewHolder(ListItemSenderMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                //itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_sender_message,parent,false)
            }
            1 -> {
                return ChatDetailReceiverViewHolder(ListItemReceiverMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
                //itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_receiver_message,parent,false)
            }
            2 -> {
                return ChatDetailNotificationViewHolder(ListItemNotificationMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//                itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_notification_message,parent,false)
//                Log.d("someone in or out","viewType : ${viewType}")
            }
            3 -> {
                return ChatDetailNotificationViewHolder(ListItemNotificationMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
//                itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_receiver_message,parent,false)
//                Log.d("someone in or out","viewType : ${viewType}")
            }
            else ->{
                return ChatDetailNotificationViewHolder(ListItemNotificationMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = getItem(position)
        if(holder is ChatDetailSenderViewHolder){
            holder.bind(chat)
        }
        else if (holder is ChatDetailReceiverViewHolder){
            holder.bind(chat)
        }
        else if (holder is ChatDetailNotificationViewHolder){
            holder.bind(chat)
        }

    }

    class ChatDetailSenderViewHolder(private val binding: ListItemSenderMessageBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Message){
            binding.txtMessage.text = item.messageContent
        }
    }

    class ChatDetailReceiverViewHolder(private val binding: ListItemReceiverMessageBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Message){
            binding.textViewMessage.text = item.messageContent
            binding.textViewName.text = item.userName
        }
    }

    class ChatDetailNotificationViewHolder(private val binding: ListItemNotificationMessageBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : Message){
            binding.textViewNotification.text = "${item.userName} 접속하였습니다."
        }
    }


}


private class ChatDetailCallback() : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }


}