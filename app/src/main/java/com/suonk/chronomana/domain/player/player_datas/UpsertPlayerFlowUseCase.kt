package com.suonk.chronomana.domain.player.player_datas

import com.suonk.chronomana.model.data.entities.PlayerEntity
import com.suonk.chronomana.model.data.repositories.player.FirebasePlayerRepository
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpsertPlayerFlowUseCase @Inject constructor(private val firebasePlayerRepository: FirebasePlayerRepository) {

    fun invoke(name: String, image: String) {
        val id = UUID.randomUUID().toString()

        firebasePlayerRepository.addNewUserToFirebaseFirestore(
            PlayerEntity(
                id = id,
                name = name,
                wins = 0,
                defeats = 0,
                image = image,
                averageTimePerTurn = 0,
                averageTimePerGame = 0,
                highestTimePerTurn = 0,
                highestTimePerGame = 0,
                firstGameDate = 0
            ), id
        )
    }
}