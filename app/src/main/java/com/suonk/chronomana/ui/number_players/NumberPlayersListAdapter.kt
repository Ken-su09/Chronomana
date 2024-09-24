package com.suonk.chronomana.ui.number_players

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suonk.chronomana.databinding.ItemNumberPlayersBinding


class NumberPlayersListAdapter : ListAdapter<NumberPlayersViewState, NumberPlayersListAdapter.ViewHolder>(NumberPlayersComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNumberPlayersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(private val binding: ItemNumberPlayersBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: NumberPlayersViewState) {
            binding.root.setCardBackgroundColor(Color.parseColor(item.cardBackground))
            binding.numberPlayersText.text = item.text

            binding.root.setOnClickListener {
                item.onClickCallback()
            }
        }
    }

    object NumberPlayersComparator : DiffUtil.ItemCallback<NumberPlayersViewState>() {
        override fun areItemsTheSame(oldItem: NumberPlayersViewState, newItem: NumberPlayersViewState): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NumberPlayersViewState, newItem: NumberPlayersViewState): Boolean {
            return oldItem == newItem
        }
    }
}