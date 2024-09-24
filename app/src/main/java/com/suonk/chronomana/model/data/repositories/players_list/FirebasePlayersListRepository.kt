package com.suonk.chronomana.model.data.repositories.players_list

import com.suonk.chronomana.model.data.entities.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface FirebasePlayersListRepository {

    fun getPlayersListFirebaseFirestore(): Flow<List<PlayerEntity>>

    fun getPlayersAvailableFlow(): Flow<List<PlayerEntity>>
}