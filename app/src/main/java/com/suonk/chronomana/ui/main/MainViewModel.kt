package com.suonk.chronomana.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.suonk.chronomana.domain.number_players.GetNumberPlayersChannelAsFlowUseCase
import com.suonk.chronomana.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNumberPlayersChannelAsFlowUseCase: GetNumberPlayersChannelAsFlowUseCase
) : ViewModel() {


    val mainViewAction: LiveData<Event<MainViewAction>> = liveData {
        getNumberPlayersChannelAsFlowUseCase.invoke().collect { numberPlayers ->
            if (numberPlayers != 0) {
                emit(Event(MainViewAction.Navigate.NumberPlayers(numberPlayers)))
            }
        }
    }
}