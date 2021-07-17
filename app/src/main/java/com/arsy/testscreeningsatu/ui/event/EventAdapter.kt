package com.arsy.testscreeningsatu.ui.event

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsy.testscreeningsatu.data.Event
import com.arsy.testscreeningsatu.databinding.ItemsEventBinding
import com.arsy.testscreeningsatu.ui.SelectActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class EventAdapter(private val listEvents: ArrayList<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemsEventBinding = ItemsEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(itemsEventBinding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = listEvents[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = listEvents.size

    class EventViewHolder(private val binding: ItemsEventBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            with(binding) {
                tvItemTitle.text = event.name
                tvItemDate.text = event.date

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, SelectActivity::class.java)
                    intent.putExtra(SelectActivity.EXTRA_EVENT, event.name)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(event.photo)
                    .apply(
                        RequestOptions().override(55, 55)
                    )
                    .into(imgPoster)
            }
        }
    }
}