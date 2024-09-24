package com.suonk.chronomana.model.data.repositories.number_players

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NumberPlayersRepositoryImpl @Inject constructor() : NumberPlayersRepository {

    private val numberPlayersFlow = MutableStateFlow(0)

    private val numberPlayersChannel = Channel<Int>(
        capacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    override fun getNumberPlayersFlow() = numberPlayersFlow

    override fun getNumberPlayersChannel() = numberPlayersChannel

    override fun setNumberPlayersFlowAndChannel(numberPlayers: Int) {
        numberPlayersFlow.value = numberPlayers
        numberPlayersChannel.trySend(numberPlayers)
    }
}