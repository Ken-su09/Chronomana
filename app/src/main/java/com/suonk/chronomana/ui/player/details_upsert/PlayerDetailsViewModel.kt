package com.suonk.chronomana.ui.player.details_upsert

import androidx.lifecycle.ViewModel
import com.suonk.chronomana.domain.player.player_datas.UpsertPlayerFlowUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerDetailsViewModel @Inject constructor(

    private val upsertPlayerFlowUseCase: UpsertPlayerFlowUseCase
) : ViewModel() {

    fun setPlayerData(name: String?, image: String){
        name?.let {
            upsertPlayerFlowUseCase.invoke(it, image)
        }
    }
}