package com.suonk.chronomana.ui.selection

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.suonk.chronomana.R
import com.suonk.chronomana.databinding.ActivityPlayersSelectionBinding
import com.suonk.chronomana.ui.player.details_upsert.PlayerDetailsActivity
import com.suonk.chronomana.utils.Event.Companion.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersSelectionActivity : AppCompatActivity() {

    private val viewModel by viewModels<PlayersSelectionViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPlayersSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayUi()
    }

    private fun displayUi(){
        supportFragmentManager.beginTransaction().add(R.id.fragment_container_selection, PlayersSelectionFragment(), "PlayersSelectionFragment")
            .commit()
        viewModel.playersViewAction.observeEvent(this) { action ->
            when (action) {
                is PlayersViewAction.Navigate.PlayerDetails -> {
                    startActivity(Intent(this@PlayersSelectionActivity, PlayerDetailsActivity::class.java))
                    finish()
                }
            }
        }
    }
}