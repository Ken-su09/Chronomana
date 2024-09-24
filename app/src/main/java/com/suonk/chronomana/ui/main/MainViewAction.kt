package com.suonk.chronomana.ui.main

sealed class MainViewAction {

    sealed class Navigate : MainViewAction() {
        class NumberPlayers(val numberPlayers: Int) : Navigate()
    }
}