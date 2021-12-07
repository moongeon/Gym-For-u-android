package com.mungeun.gymforyou.views.gym_detail.review

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mungeun.gymforyou.databinding.ListItemReviewBinding
import com.mungeun.gymforyou.domain.model.gym.gym_detail.GymDetail

class ReviewAdapter : ListAdapter<GymDetail,RecyclerView.ViewHolder>(ReviewDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ReviewViewHolder(ListItemReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ReviewViewHolder).bind(item)
    }
    class ReviewViewHolder(private val binding : ListItemReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item : GymDetail){
            binding.data = item
        }
    }
}


class ReviewDiffCallback : DiffUtil.ItemCallback<GymDetail>(){
    override fun areItemsTheSame(oldItem: GymDetail, newItem: GymDetail): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GymDetail, newItem: GymDetail): Boolean {
       return oldItem == newItem
    }

}