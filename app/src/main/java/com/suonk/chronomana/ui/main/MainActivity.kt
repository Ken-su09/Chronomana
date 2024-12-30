package com.suonk.chronomana.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.suonk.chronomana.R
import com.suonk.chronomana.databinding.ActivityMainBinding
import com.suonk.chronomana.ui.game.GameActivity
import com.suonk.chronomana.ui.game.game_tracker.GameTrackerFragment
import com.suonk.chronomana.ui.number_players.NumberPlayersFragment
import com.suonk.chronomana.ui.selection.PlayersSelectionFragment
import com.suonk.chronomana.utils.Event.Companion.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayUI()
    }

    private fun displayUI() {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, NumberPlayersFragment(), "NumberPlayersFragment").commit()

        viewModel.mainViewAction.observeEvent(this) { action ->
            when (action) {
                is MainViewAction.Navigate.NumberPlayers -> {
                    startActivity(Intent(this, GameActivity::class.java))
                    finish()
                }
            }
        }
    }
}