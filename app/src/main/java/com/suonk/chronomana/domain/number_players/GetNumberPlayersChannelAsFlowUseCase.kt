package com.suonk.chronomana.domain.number_players

import com.suonk.chronomana.model.data.repositories.number_players.NumberPlayersRepository
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNumberPlayersChannelAsFlowUseCase @Inject constructor(private val numberPlayersRepository: NumberPlayersRepository) {

    fun invoke() = numberPlayersRepository.getNumberPlayersChannel().receiveAsFlow()
}