package com.suonk.chronomana.ui.selection.players_selection

import com.suonk.chronomana.utils.EquatableCallback

data class PlayersSelectionViewState(
    val id: String,
    val playerNumber: String,
    val name: String,
    val image: String,
    val cardBackground: String,
    val onRemovePlayerCallback: EquatableCallback
)