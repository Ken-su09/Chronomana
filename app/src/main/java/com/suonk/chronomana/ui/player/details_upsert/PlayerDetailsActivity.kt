package com.suonk.chronomana.ui.player.details_upsert

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suonk.chronomana.R
import com.suonk.chronomana.databinding.ActivityPlayerDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityPlayerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_player_details, PlayerDetailsFragment(), "PlayerDetailsFragment")
            .addToBackStack(null).commit()
    }
}