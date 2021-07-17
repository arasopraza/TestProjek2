package com.arsy.testscreeningsatu.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.arsy.testscreeningsatu.databinding.ActivitySelectBinding
import com.arsy.testscreeningsatu.ui.event.EventActivity
import com.arsy.testscreeningsatu.ui.guest.GuestActivity


class SelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.name.text = intent.getStringExtra(EXTRA_NAME)

        binding.btnEvent.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

        binding.btnGuest.setOnClickListener {
            val moveIntent = Intent(this, GuestActivity::class.java)
            startActivity(moveIntent)
        }

        binding.btnEvent.text = intent.getStringExtra(EXTRA_EVENT)
        binding.btnGuest.text = intent.getStringExtra(EXTRA_GUEST)
    }

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_EVENT = "event"
        const val EXTRA_GUEST = "guest"
    }
}