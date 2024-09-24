package com.suonk.chronomana.domain.players_list

import com.suonk.chronomana.model.data.repositories.players_list.FirebasePlayersListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPlayersListFlowUseCase @Inject constructor(private val firebasePlayersListRepository: FirebasePlayersListRepository) {

    fun invoke() = firebasePlayersListRepository.getPlayersListFirebaseFirestore()
//    fun invoke() = firebasePlayersListRepository.getPlayersAvailableFlow()
}