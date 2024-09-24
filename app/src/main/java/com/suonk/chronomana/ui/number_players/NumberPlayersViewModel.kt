package com.suonk.chronomana.ui.number_players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suonk.chronomana.domain.number_players.SetNumberPlayersFlowUseCase
import com.suonk.chronomana.utils.EquatableCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberPlayersViewModel @Inject constructor(

    private val setNumberPlayersFlowUseCase: SetNumberPlayersFlowUseCase
) : ViewModel() {

    private val listOfPlayersMutableStateFlow = MutableStateFlow(listOfPlayers())
    val _listOfPlayersStateFlow: StateFlow<List<NumberPlayersViewState>> = listOfPlayersMutableStateFlow

    private fun setNumberPlayersFlow(numberOfPlayers: Int) {
        setNumberPlayersFlowUseCase.invoke(numberOfPlayers)
    }

    private fun listOfPlayers() = listOf(
        NumberPlayersViewState(
            text = "1",
            value = 1,
            cardBackground = "#5c995d",
            onClickCallback = EquatableCallback {
                setNumberPlayersFlow(1)
            }
        ),
        NumberPlayersViewState(
            text = "2",
            value = 2,
            cardBackground = "#5c9a99",
            onClickCallback = EquatableCallback {
                setNumberPlayersFlow(2)
            }
        ),
        NumberPlayersViewState(
            text = "3",
            value = 3,
            cardBackground = "#5d5c98",
            onClickCallback = EquatableCallback {
                setNumberPlayersFlow(3)
            }
        ),
        NumberPlayersViewState(
            text = "4",
            value = 4,
            cardBackground = "#995c98",
            onClickCallback = EquatableCallback {
                setNumberPlayersFlow(4)
            }
        ),
        NumberPlayersViewState(
            text = "5",
            value = 5,
            cardBackground = "#985c5c",
            onClickCallback = EquatableCallback {
                setNumberPlayersFlow(5)
            }
        ),
        NumberPlayersViewState(
            text = "6",
            value = 6,
            cardBackground = "#99995b",
            onClickCallback = EquatableCallback {
                setNumberPlayersFlow(6)
            }
        ),
    )
}