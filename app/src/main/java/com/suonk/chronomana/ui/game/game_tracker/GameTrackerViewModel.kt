package com.suonk.chronomana.ui.game.game_tracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.chronomana.domain.number_players.GetNumberPlayersFlowUseCase
import com.suonk.chronomana.ui.selection.players_selection.PlayersSelectionViewState
import com.suonk.chronomana.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameTrackerViewModel @Inject constructor(
    private val getNumberPlayersFlowUseCase: GetNumberPlayersFlowUseCase
) : ViewModel() {

    private val _numberPlayersFlow = MutableStateFlow(1)

    private val _isRunningMutableStateFlow = MutableStateFlow(false)
    val isRunningStateFlow: StateFlow<Boolean> = _isRunningMutableStateFlow

    private val _listOfChronometersDataStateFlow = MutableStateFlow<GameTrackerViewState?>(null)
    val listOfChronometersDataStateFlow: StateFlow<GameTrackerViewState?> = _listOfChronometersDataStateFlow

    init {
        viewModelScope.launch {
            getNumberPlayersFlowUseCase.invoke().map { numberPlayers ->
                _numberPlayersFlow.value = numberPlayers

                GameTrackerViewState(
                    turn = 1,
                    currentIndex = 0,
                    globalElapsedTime = "Time: ${formatElapsedTime(0L)}",
                    globalElapsedTimeValue = 0L,
                    listOfPlayersData = List(numberPlayers) { index ->
                        PlayerData(
                            name = "Player ${index + 1}",
//                            elapsedTime = "Time: ${formatElapsedTime(0L)}",
                            elapsedTimeValue = 0L
                        )
                    }
                )
            }
                .collect {
                    _listOfChronometersDataStateFlow.value = it
                }
        }
    }

    fun setIsRunningStateFlow(isRunning: Boolean) {
        _isRunningMutableStateFlow.value = isRunning
    }

    fun endOfTurnClicked(newElapsedTime: Long) {
        val currentState = _listOfChronometersDataStateFlow.value

        val updatedList = currentState?.let {
            it.listOfPlayersData.mapIndexed { index, player ->
                if (index == it.currentIndex) {
                    player.copy(
                        elapsedTimeValue = newElapsedTime,
                    )
                } else {
                    player
                }
            }
        }
        val updatedIndex = currentState?.currentIndex?.let { index ->
            if (index + 1 >= _numberPlayersFlow.value) {
                0
            } else {
                currentState.currentIndex.plus(1)
            }
        }
        val updatedTurn = if (updatedIndex == 0) {
            currentState.turn.let { _ ->
                currentState.turn.plus(1)
            }
        } else {
            currentState?.turn
        }

        _listOfChronometersDataStateFlow.value = updatedTurn?.let { turn ->
            updatedIndex?.let { currentIndex ->
                updatedList?.let { listOfPlayersData ->
                    currentState.copy(turn = turn, currentIndex = currentIndex, listOfPlayersData = listOfPlayersData)
                }
            }
        }
    }

    private fun formatElapsedTime(elapsedTime: Long): String {
        val totalSeconds = elapsedTime / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60

        return String.format("%02d:%02d", minutes, seconds)
    }
}