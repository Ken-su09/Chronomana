package com.suonk.chronomana.model.data.repositories.player_id

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow

interface PlayerIdRepository {

    fun getPlayerIdFlow(): Flow<String>

    fun getPlayerIdChannel(): Channel<String>

    fun setPlayerIdFlowAndChannel(playerId: String)
}