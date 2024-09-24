package com.suonk.chronomana.domain.player.player_datas

import com.suonk.chronomana.model.data.entities.PlayerEntity
import com.suonk.chronomana.model.data.repositories.player.FirebasePlayerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPlayerByIdFlowUseCase @Inject constructor(private val firebasePlayerRepository: FirebasePlayerRepository) {

    fun invoke(playerId: String): Flow<PlayerEntity> {
        return firebasePlayerRepository.getPlayerDataFirebaseFirestoreByUserId(playerId)
    }
}