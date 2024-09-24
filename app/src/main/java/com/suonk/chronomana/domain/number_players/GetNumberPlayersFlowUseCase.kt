package com.suonk.chronomana.domain.number_players

import com.suonk.chronomana.model.data.repositories.number_players.NumberPlayersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNumberPlayersFlowUseCase @Inject constructor(private val numberPlayersRepository: NumberPlayersRepository) {

    fun invoke() = numberPlayersRepository.getNumberPlayersFlow()
}