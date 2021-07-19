package com.arsy.testscreeningsatu.ui.guest

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arsy.testscreeningsatu.R
import com.arsy.testscreeningsatu.data.Guest
import com.arsy.testscreeningsatu.databinding.ItemsGuestBinding
import com.arsy.testscreeningsatu.ui.SelectActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GuestAdapter : RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {
    private  val listGuests = ArrayList<Guest>()

    fun setGuests(guests: List<Guest>?) {
        if (guests == null) return
        this.listGuests.clear()
        this.listGuests.addAll(guests)

        this.notifyDataSetChanged()
    }

    fun clear() {
        listGuests.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val itemsGuestBinding = ItemsGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(itemsGuestBinding)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val guest = listGuests[position]
        holder.bind(guest)
    }

    override fun getItemCount(): Int = listGuests.size

    class GuestViewHolder(private val binding: ItemsGuestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(guest: Guest) {
            with(binding) {
                tvItemName.text = guest.name
                tvItemBirtdate.text = guest.birthdate

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, SelectActivity::class.java)
                    intent.putExtra(SelectActivity.EXTRA_GUEST, guest.name)
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(R.drawable.kickfest)
                    .apply(
                        RequestOptions().override(55, 55))
                    .into(imgPoster)
            }
        }
    }
}