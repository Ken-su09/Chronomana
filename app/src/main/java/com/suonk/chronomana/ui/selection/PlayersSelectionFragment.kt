package com.suonk.chronomana.ui.selection

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.suonk.chronomana.R
import com.suonk.chronomana.databinding.FragmentPlayersSelectionBinding
import com.suonk.chronomana.ui.main.MainActivity
import com.suonk.chronomana.ui.selection.all_players.AllPlayersAvailableListAdapter
import com.suonk.chronomana.ui.selection.players_selection.PlayersSelectionListAdapter
import com.suonk.chronomana.utils.BaseFragment
import com.suonk.chronomana.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersSelectionFragment : BaseFragment(R.layout.fragment_players_selection) {

    private val binding by viewBinding(FragmentPlayersSelectionBinding::bind)
    private val viewModel by viewModels<PlayersSelectionViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayPlayersSelection()
        displayAllPlayersList()

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.validate -> {

                    true
                }

                else -> {
                    true
                }
            }
        }
    }

    private fun displayPlayersSelection() {
        val adapter = PlayersSelectionListAdapter()
        binding.playersSelection.adapter = adapter

        safeCollectFlow(flow = viewModel.playersSelectionListFlow) { playersSelection ->
            Log.i("SetPlayersSelection", "Passe par là")
            binding.playersSelection.setLayoutManager(GridLayoutManager(requireContext(), 3))
            adapter.submitList(playersSelection)
        }
    }

    private fun displayAllPlayersList() {
        val adapter = AllPlayersAvailableListAdapter()
        binding.allPlayersList.adapter = adapter

        safeCollectFlow(flow = viewModel.allPlayersAvailableListFlow) { allPlayersAvailable ->
            adapter.submitList(allPlayersAvailable)
        }
    }
}