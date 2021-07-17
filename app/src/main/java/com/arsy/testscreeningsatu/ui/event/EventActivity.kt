package com.arsy.testscreeningsatu.ui.event

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsy.testscreeningsatu.R
import com.arsy.testscreeningsatu.data.DummyDataEvent
import com.arsy.testscreeningsatu.data.Event
import com.arsy.testscreeningsatu.databinding.ActivityEventBinding

class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    private var listEvents: ArrayList<Event> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.event)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listEvents.addAll(DummyDataEvent.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding.rvEvent.layoutManager = LinearLayoutManager(this)
        val eventAdapter = EventAdapter(listEvents)
        binding.rvEvent.adapter = eventAdapter
        binding.rvEvent.setHasFixedSize(true)
    }
}