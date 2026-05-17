package com.gramaangana.ui.eventboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gramaangana.databinding.ItemEventBinding
import com.gramaangana.model.BookingRequest

class EventAdapter : ListAdapter<BookingRequest, EventAdapter.VH>(DIFF) {

    inner class VH(val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvEventPurpose.text   = "🎯 ${item.purpose}"
            tvEventTime.text      = "🕐 ${item.timeSlot}"
            tvEventOrganizer.text = "👤 ${item.requesterName}"
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<BookingRequest>() {
            override fun areItemsTheSame(a: BookingRequest, b: BookingRequest) = a.id == b.id
            override fun areContentsTheSame(a: BookingRequest, b: BookingRequest) = a == b
        }
    }
}
