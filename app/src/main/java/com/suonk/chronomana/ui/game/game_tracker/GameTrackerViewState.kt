package com.suonk.chronomana.ui.game.game_tracker

data class GameTrackerViewState(
    val turn: Int,
    val currentIndex: Int,
    val globalElapsedTime: String,
    val globalElapsedTimeValue: Long,
    val listOfPlayersData: List<PlayerData>
)

data class PlayerData(
    val name: String,
    val elapsedTimeValue: Long,
)