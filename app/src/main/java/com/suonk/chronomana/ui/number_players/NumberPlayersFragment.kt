package com.suonk.chronomana.ui.number_players

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.suonk.chronomana.R
import com.suonk.chronomana.databinding.FragmentNumberPlayersBinding
import com.suonk.chronomana.utils.BaseFragment
import com.suonk.chronomana.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NumberPlayersFragment : BaseFragment(R.layout.fragment_number_players) {

    private val binding by viewBinding(FragmentNumberPlayersBinding::bind)
    private val viewModel by viewModels<NumberPlayersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NumberPlayersListAdapter()
        binding.numberPlayersList.adapter = adapter

        safeCollectFlow(flow = viewModel._listOfPlayersStateFlow) { players ->
            binding.numberPlayersList.setLayoutManager(GridLayoutManager(requireContext(), 3))
            adapter.submitList(players)
        }
    }
}
