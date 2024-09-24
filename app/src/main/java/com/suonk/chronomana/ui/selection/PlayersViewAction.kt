package com.suonk.chronomana.ui.selection

sealed class PlayersViewAction {

    sealed class Navigate : PlayersViewAction() {
        class PlayerDetails(id: String) : Navigate()
    }
}