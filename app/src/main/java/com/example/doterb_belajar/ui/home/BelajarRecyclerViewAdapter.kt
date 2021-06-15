package com.example.doterb_belajar.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.doterb_belajar.databinding.BelajarItemBinding
import com.example.doterb_belajar.model.Belajar

class BelajarRecyclerViewAdapter :
    PagingDataAdapter<Belajar.Result.Data, BelajarViewHolder>(DiffUtilCallback) {

    companion object DiffUtilCallback : DiffUtil.ItemCallback<Belajar.Result.Data>() {
        override fun areItemsTheSame(
            oldItem: Belajar.Result.Data,
            newItem: Belajar.Result.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Belajar.Result.Data,
            newItem: Belajar.Result.Data
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: BelajarViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BelajarViewHolder {
        val viewBinding = BelajarItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return BelajarViewHolder(viewBinding)
    }

}

class BelajarViewHolder(private val binding: BelajarItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(data: Belajar.Result.Data?) {
        binding.belajarItem = data
    }
}