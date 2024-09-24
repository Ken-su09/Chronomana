package com.suonk.chronomana.domain.player.id

import com.suonk.chronomana.model.data.repositories.player_id.PlayerIdRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetPlayerIdFlowUseCase @Inject constructor(private val playerIdRepository: PlayerIdRepository) {

    fun invoke(playerId: String) {
        playerIdRepository.setPlayerIdFlowAndChannel(playerId = playerId)
    }
}