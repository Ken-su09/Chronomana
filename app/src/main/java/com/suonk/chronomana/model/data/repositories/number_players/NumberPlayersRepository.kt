package com.suonk.chronomana.model.data.repositories.number_players

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface NumberPlayersRepository {

    fun getNumberPlayersFlow(): Flow<Int>

    fun getNumberPlayersChannel(): Channel<Int>

    fun setNumberPlayersFlowAndChannel(numberPlayers: Int)
}