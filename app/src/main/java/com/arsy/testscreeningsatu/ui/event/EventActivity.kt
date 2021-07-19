package com.arsy.testscreeningsatu.ui.event

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arsy.testscreeningsatu.R
import com.arsy.testscreeningsatu.data.DummyDataEvent
import com.arsy.testscreeningsatu.data.Event
import com.arsy.testscreeningsatu.databinding.ActivityEventBinding
import com.arsy.testscreeningsatu.ui.guest.MapsFragment

class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    private var listEvents: ArrayList<Event> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationIcon(R.drawable.btn_back_normal)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.event_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, MapsFragment())
                .addToBackStack(null)
                .commit()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}