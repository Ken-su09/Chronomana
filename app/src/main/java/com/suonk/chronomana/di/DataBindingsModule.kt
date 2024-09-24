package com.suonk.chronomana.di

import com.suonk.chronomana.model.data.repositories.number_players.NumberPlayersRepository
import com.suonk.chronomana.model.data.repositories.number_players.NumberPlayersRepositoryImpl
import com.suonk.chronomana.model.data.repositories.player.FirebasePlayerRepository
import com.suonk.chronomana.model.data.repositories.player.FirebasePlayerRepositoryImpl
import com.suonk.chronomana.model.data.repositories.player_id.PlayerIdRepository
import com.suonk.chronomana.model.data.repositories.player_id.PlayerIdRepositoryImpl
import com.suonk.chronomana.model.data.repositories.players_list.FirebasePlayersListRepository
import com.suonk.chronomana.model.data.repositories.players_list.FirebasePlayersListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataBindingsModule {

    @Binds
    @Singleton
    abstract fun provideNumberPlayersRepository(impl: NumberPlayersRepositoryImpl): NumberPlayersRepository

    @Binds
    @Singleton
    abstract fun provideFirebasePlayersListRepository(impl: FirebasePlayersListRepositoryImpl): FirebasePlayersListRepository

    @Binds
    @Singleton
    abstract fun provideFirebasePlayer(impl: FirebasePlayerRepositoryImpl): FirebasePlayerRepository

    @Binds
    @Singleton
    abstract fun providePlayerIdRepositoryImpl(impl: PlayerIdRepositoryImpl): PlayerIdRepository
}