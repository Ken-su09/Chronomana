package com.suonk.chronomana.ui.number_players

import com.suonk.chronomana.utils.EquatableCallback

data class NumberPlayersViewState(
    val text: String,
    val value: Int,
    val cardBackground: String,
    val onClickCallback: EquatableCallback
)