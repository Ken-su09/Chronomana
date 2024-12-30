package com.suonk.chronomana.ui.number_players

import androidx.lifecycle.ViewModel
import com.suonk.chronomana.domain.number_players.SetNumberPlayersFlowUseCase
import com.suonk.chronomana.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NumberPlayersViewModel @Inject constructor(private val setNumberPlayersFlowUseCase: SetNumberPlayersFlowUseCase) : ViewModel() {

    //TODO : Send invalid message to the user if numberPlayers is null or not a digit

    val toastMessageSingleLiveEvent = SingleLiveEvent<String>()

    fun setNumberPlayers(numberPlayers: String?) {
        if (numberPlayers?.toIntOrNull() == null || numberPlayers.toIntOrNull() == 0) {
            toastMessageSingleLiveEvent.setValue("Your choice is not valid")
        } else {
            numberPlayers.toIntOrNull()?.let {
                setNumberPlayersFlowUseCase.invoke(it)
            }
        }
    }
}