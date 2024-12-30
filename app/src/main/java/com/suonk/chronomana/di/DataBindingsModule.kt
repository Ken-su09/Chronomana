package com.suonk.chronomana.di

import com.suonk.chronomana.model.data.repositories.number_players.NumberPlayersRepository
import com.suonk.chronomana.model.data.repositories.number_players.NumberPlayersRepositoryImpl
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
}