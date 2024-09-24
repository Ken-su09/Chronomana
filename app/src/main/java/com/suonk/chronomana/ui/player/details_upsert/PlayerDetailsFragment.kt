package com.suonk.chronomana.ui.player.details_upsert

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.suonk.chronomana.R
import com.suonk.chronomana.databinding.FragmentNumberPlayersBinding
import com.suonk.chronomana.databinding.FragmentPlayerDetailsBinding
import com.suonk.chronomana.ui.number_players.NumberPlayersViewModel
import com.suonk.chronomana.ui.selection.PlayersSelectionFragment
import com.suonk.chronomana.utils.BaseFragment
import com.suonk.chronomana.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerDetailsFragment : BaseFragment(R.layout.fragment_player_details) {

    private val binding by viewBinding(FragmentPlayerDetailsBinding::bind)
    private val viewModel by viewModels<PlayerDetailsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.validate -> {
                    viewModel.setPlayerData(
                        binding.playerNameInputEditText.text?.toString(),
                        "https://static.wikia.nocookie.net/mtgsalvation_gamepedia/images/3/3f/X.svg/revision/latest?cb=20160121004212"
                    )
                    goToPlayersSelection()
                    true
                }

                else -> {
                    goToPlayersSelection()
                    true
                }
            }
        }
    }

    private fun goToPlayersSelection() {
        if (requireActivity() is PlayerDetailsActivity) {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container_player_details, PlayersSelectionFragment(),
                "PlayersSelectionFragment"
            )
                .addToBackStack(null).commit()
        }
    }
}