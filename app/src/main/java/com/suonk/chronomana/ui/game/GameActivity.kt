package com.suonk.chronomana.ui.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suonk.chronomana.R
import com.suonk.chronomana.databinding.ActivityGameBinding
import com.suonk.chronomana.ui.game.action_log.ActionLogFragment
import com.suonk.chronomana.ui.game.game_tracker.GameTrackerFragment
import com.suonk.chronomana.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(GameTrackerFragment(), "GameTrackerFragment")
    }

    private fun replaceFragment(baseFragment: BaseFragment, tag: String) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container_game, baseFragment, tag).commit()
    }
}