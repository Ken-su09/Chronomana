package com.suonk.chronomana.ui.selection.players_selection

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suonk.chronomana.databinding.ItemPlayerSelectionBinding

class PlayersSelectionListAdapter : ListAdapter<PlayersSelectionViewState, PlayersSelectionListAdapter.ViewHolder>(PlayersSelectionComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlayerSelectionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(private val binding: ItemPlayerSelectionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(player: PlayersSelectionViewState) {
            binding.numberPlayersText.text = player.playerNumber
            binding.playerName.text = player.name

            Glide.with(binding.playerImage)
                .load(player.image)
                .into(binding.playerImage)

            binding.mainLayout.setBackgroundColor(Color.parseColor(player.cardBackground))
//            binding.root.setBackgroundColor(Color.parseColor(player.cardBackground))
        }
    }

    object PlayersSelectionComparator : DiffUtil.ItemCallback<PlayersSelectionViewState>() {
        override fun areItemsTheSame(oldItem: PlayersSelectionViewState, newItem: PlayersSelectionViewState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlayersSelectionViewState, newItem: PlayersSelectionViewState): Boolean {
            return oldItem == newItem
        }
    }
}