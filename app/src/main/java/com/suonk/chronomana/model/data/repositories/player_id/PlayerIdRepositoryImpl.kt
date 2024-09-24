package com.suonk.chronomana.model.data.repositories.player_id

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerIdRepositoryImpl @Inject constructor() : PlayerIdRepository {

    private val playerIdMutableStateFlow = MutableStateFlow("")
    private val playerIdChannel = Channel<String>(
        capacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )

    override fun getPlayerIdFlow(): Flow<String> {
        return playerIdMutableStateFlow
    }

    override fun getPlayerIdChannel(): Channel<String> {
        return playerIdChannel
    }

    override fun setPlayerIdFlowAndChannel(playerId: String) {
        playerIdMutableStateFlow.value = playerId
        playerIdChannel.trySend(playerId)
    }
}