package com.suonk.chronomana.ui.selection.all_players

import com.suonk.chronomana.utils.EquatableCallback
import com.suonk.chronomana.utils.NativeText

data class AllPlayersAvailableViewState(
    val id: String,
    val name: String,
    val score: NativeText,
    val image: String,
    val onSelectPlayerCallback: EquatableCallback,
    val onClickAllInfoPlayerCallback: EquatableCallback
)