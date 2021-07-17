package com.arsy.testscreeningsatu.data

import com.arsy.testscreeningsatu.R

object DummyDataEvent {
    private val eventNames = arrayOf(
        "Java Jazz Festival",
        "We The Fest",
        "Synchronize Fest",
        "Kick Fest"
    )

    private val eventDates = arrayOf(
        "1 Maret 2019",
        "19 Juli 2019",
        "4 Oktober 2019",
        "31 Oktober 2019"
    )

    private val eventImages = intArrayOf(
        R.drawable.javajazz,
        R.drawable.wethefest,
        R.drawable.synchronizefest,
        R.drawable.kickfest
    )

    val listData: ArrayList<Event>
        get() {
            val list = arrayListOf<Event>()
            for (position in eventNames.indices) {
                val event = Event()
                event.name = eventNames[position]
                event.date = eventDates[position]
                event.photo = eventImages[position]
                list.add(event)
            }
            return list
        }
}
