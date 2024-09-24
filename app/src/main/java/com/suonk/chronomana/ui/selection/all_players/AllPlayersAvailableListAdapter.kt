package com.suonk.chronomana.ui.selection.all_players

import android.R
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suonk.chronomana.databinding.ItemPlayersListBinding
import com.suonk.chronomana.utils.toCharSequence


class AllPlayersAvailableListAdapter : ListAdapter<AllPlayersAvailableViewState, AllPlayersAvailableListAdapter.ViewHolder>(AllPlayersComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPlayersListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ViewHolder(private val binding: ItemPlayersListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(player: AllPlayersAvailableViewState) {
            binding.playerName.text = player.name
            binding.playerScores.text = player.score.toCharSequence(binding.root.context)

            Glide.with(binding.playerImage)
                .load(player.image)
                .into(binding.playerImage)

            binding.root.setOnClickListener {
                player.onSelectPlayerCallback()
            }

            binding.moreInfoIcon.setOnClickListener {
                player.onClickAllInfoPlayerCallback()
            }
        }
    }

    object AllPlayersComparator : DiffUtil.ItemCallback<AllPlayersAvailableViewState>() {
        override fun areItemsTheSame(oldItem: AllPlayersAvailableViewState, newItem: AllPlayersAvailableViewState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AllPlayersAvailableViewState, newItem: AllPlayersAvailableViewState): Boolean {
            return oldItem == newItem
        }
    }
}