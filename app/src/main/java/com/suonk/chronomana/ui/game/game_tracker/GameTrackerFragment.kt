package com.suonk.chronomana.ui.game.game_tracker

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.suonk.chronomana.R
import com.suonk.chronomana.databinding.FragmentGameTrackerBinding
import com.suonk.chronomana.databinding.FragmentNumberPlayersBinding
import com.suonk.chronomana.ui.number_players.NumberPlayersViewModel
import com.suonk.chronomana.utils.BaseFragment
import com.suonk.chronomana.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameTrackerFragment : BaseFragment(R.layout.fragment_game_tracker) {

    private val binding by viewBinding(FragmentGameTrackerBinding::bind)
    private val viewModel by viewModels<GameTrackerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var globalPauseOffset = 0L
        var individualPauseOffset = 0L

        binding.startButton.setOnClickListener {
            binding.globalChronometer.base = SystemClock.elapsedRealtime() - globalPauseOffset
            binding.individualChronometer.base = SystemClock.elapsedRealtime() - individualPauseOffset

            binding.globalChronometer.start()
            binding.individualChronometer.start()
            viewModel.setIsRunningStateFlow(true)
        }
        binding.stopButton.setOnClickListener {
            globalPauseOffset = SystemClock.elapsedRealtime() - binding.globalChronometer.base
            individualPauseOffset = SystemClock.elapsedRealtime() - binding.individualChronometer.base

            binding.globalChronometer.stop()
            binding.individualChronometer.stop()
            viewModel.setIsRunningStateFlow(false)
        }
        binding.resetButton.setOnClickListener {
            binding.globalChronometer.stop()
            binding.individualChronometer.stop()
            binding.globalChronometer.base = SystemClock.elapsedRealtime()
            binding.individualChronometer.base = SystemClock.elapsedRealtime()
//                viewModel.setIsRunningStateFlow(false)
        }

        safeCollectFlow(flow = viewModel.isRunningStateFlow) { isRunning ->
            binding.endTurn.isClickable = isRunning
            binding.endTurn.isEnabled = isRunning
        }

        safeCollectFlow(flow = viewModel.listOfChronometersDataStateFlow) { gameTrackerViewStates ->
            gameTrackerViewStates?.let {
                binding.turnValue.text = "Turn ${it.turn}"
                binding.playerTurnToPlay.text = "${it.listOfPlayersData[it.currentIndex].name}"

                binding.individualChronometer.base = SystemClock.elapsedRealtime() - it.listOfPlayersData[it.currentIndex].elapsedTimeValue

                binding.endTurn.setOnClickListener { _ ->
                    viewModel.endOfTurnClicked(SystemClock.elapsedRealtime() - binding.individualChronometer.base)
                    binding.individualChronometer.stop()
                    binding.individualChronometer.start()
                }
            }
        }
    }
}