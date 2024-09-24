package com.suonk.chronomana.model.data.entities

import java.time.LocalDateTime

data class PlayerEntity(
    val id: String = "",
    val name: String = "",
    val wins: Int = 0,
    val defeats: Int = 0,
    val image: String = "",
    val averageTimePerTurn: Long = 0L,
    val averageTimePerGame: Long = 0L,
    val highestTimePerTurn: Long = 0L,
    val highestTimePerGame: Long = 0L,
    val firstGameDate: Long = 0L
)