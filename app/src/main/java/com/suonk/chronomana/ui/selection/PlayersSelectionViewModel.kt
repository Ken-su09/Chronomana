package com.suonk.chronomana.ui.selection

import androidx.lifecycle.ViewModel
import com.suonk.chronomana.domain.number_players.GetNumberPlayersFlowUseCase
import com.suonk.chronomana.ui.selection.all_players.AllPlayersAvailableViewState
import com.suonk.chronomana.ui.selection.players_selection.PlayersSelectionViewState
import com.suonk.chronomana.utils.EquatableCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PlayersSelectionViewModel @Inject constructor(
    private val getNumberPlayersFlowUseCase: GetNumberPlayersFlowUseCase,
) : ViewModel() {

    private val _allPlayersAvailableListMutableStateFlow = MutableStateFlow<List<AllPlayersAvailableViewState>>(emptyList())
    val allPlayersAvailableListFlow: StateFlow<List<AllPlayersAvailableViewState>> = _allPlayersAvailableListMutableStateFlow

    private val _playersSelectionListMutableStateFlow = MutableStateFlow<List<PlayersSelectionViewState>>(emptyList())
    val playersSelectionListFlow: StateFlow<List<PlayersSelectionViewState>> = _playersSelectionListMutableStateFlow

    private val listOfPlayersSelection = listOf(
        PlayersSelectionViewState(
            id = "",
            playerNumber = "P1",
            name = "",
            image = "",
            cardBackground = "#5c995d",
            onRemovePlayerCallback = EquatableCallback {}),
        PlayersSelectionViewState(
            id = "",
            playerNumber = "P2",
            name = "",
            image = "",
            cardBackground = "#5c9a99",
            onRemovePlayerCallback = EquatableCallback {}),
        PlayersSelectionViewState(
            id = "",
            playerNumber = "P3",
            name = "",
            image = "",
            cardBackground = "#5d5c98",
            onRemovePlayerCallback = EquatableCallback {}),
        PlayersSelectionViewState(
            id = "",
            playerNumber = "P4",
            name = "",
            image = "",
            cardBackground = "#995c98",
            onRemovePlayerCallback = EquatableCallback {}),
        PlayersSelectionViewState(
            id = "",
            playerNumber = "P5",
            name = "",
            image = "",
            cardBackground = "#985c5c",
            onRemovePlayerCallback = EquatableCallback {}),
        PlayersSelectionViewState(
            id = "",
            playerNumber = "P6",
            name = "",
            image = "",
            cardBackground = "#99995b",
            onRemovePlayerCallback = EquatableCallback {}),
    )

//    init {
//        viewModelScope.launch {
//            combine(getNumberPlayersFlowUseCase.invoke(), getPlayersListFlowUseCase.invoke()) { numberPlayers, playersList ->
//                _playersSelectionListMutableStateFlow.value = listOfPlayersSelection.take(numberPlayers)
//
//                _allPlayersAvailableListMutableStateFlow.value = playersList.map { player ->
//                    AllPlayersAvailableViewState(
//                        id = player.id,
//                        name = player.name,
//                        score = NativeText.Arguments(R.string.player_scores, listOf(player.wins, player.defeats)),
//                        image = player.image,
//                        onSelectPlayerCallback = EquatableCallback {
//                            updatePlayerSelection(player.id, player.name, player.image)
//                        },
//                        onClickAllInfoPlayerCallback = EquatableCallback {
//                            setPlayerIdFlowUseCase.invoke(player.id)
//                        })
//                }
//            }.collect { }
//        }
//    }

    private fun updatePlayerSelection(id: String, name: String, image: String) {
        val currentPlayersSelectionList = _playersSelectionListMutableStateFlow.value
        val currentPlayersAvailableList = _allPlayersAvailableListMutableStateFlow.value

        val emptySlotIndex = currentPlayersSelectionList.indexOfFirst { it.id.isEmpty() }
        val slotIndexToRemove = currentPlayersAvailableList.indexOfFirst { it.id == id }

        if (emptySlotIndex != -1 && slotIndexToRemove != -1) {
            val updatedPlayer = currentPlayersSelectionList[emptySlotIndex].copy(
                id = id,
                name = name,
                image = image
            )

            val updatedSelectionList = currentPlayersSelectionList.toMutableList()
            updatedSelectionList[emptySlotIndex] = updatedPlayer

            val updatedPlayersAvailableList = currentPlayersAvailableList.toMutableList()
            updatedPlayersAvailableList.removeAt(slotIndexToRemove)

            _playersSelectionListMutableStateFlow.value = updatedSelectionList
            _allPlayersAvailableListMutableStateFlow.value = updatedPlayersAvailableList
        }
    }

    private fun removePlayersSelectionListMutableStateFlow(id: String) {
        for (player in _playersSelectionListMutableStateFlow.value) {
            if (player.id == id) {
                _playersSelectionListMutableStateFlow.value += PlayersSelectionViewState(
                    id = id,
                    playerNumber = player.playerNumber,
                    name = "",
                    image = "",
                    cardBackground = player.cardBackground,
                    onRemovePlayerCallback = EquatableCallback {
                        removePlayersSelectionListMutableStateFlow(id)
                    })
            }
        }
    }
}