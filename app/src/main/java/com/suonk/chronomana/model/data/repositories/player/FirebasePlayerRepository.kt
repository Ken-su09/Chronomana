package com.suonk.chronomana.model.data.repositories.player

import com.suonk.chronomana.model.data.entities.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface FirebasePlayerRepository {

    fun addNewUserToFirebaseFirestore(player: PlayerEntity, userId: String)

    fun getPlayerDataFirebaseFirestoreByUserId(userId: String): Flow<PlayerEntity>
}