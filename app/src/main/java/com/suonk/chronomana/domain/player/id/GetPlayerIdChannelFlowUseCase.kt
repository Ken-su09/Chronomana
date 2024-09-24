package com.suonk.chronomana.domain.player.id

import com.suonk.chronomana.model.data.repositories.player_id.PlayerIdRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPlayerIdChannelFlowUseCase @Inject constructor(private val playerIdRepository: PlayerIdRepository) {

    fun invoke(): Flow<String> {
        return playerIdRepository.getPlayerIdChannel().receiveAsFlow()
    }
}