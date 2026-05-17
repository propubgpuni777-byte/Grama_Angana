package com.gramaangana.ui.maintenance

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gramaangana.data.MaintenanceItem
import com.gramaangana.databinding.ItemMaintenanceBinding

class MaintenanceAdapter(
    private val onPledge: (MaintenanceItem) -> Unit
) : ListAdapter<MaintenanceItem, MaintenanceAdapter.VH>(DIFF) {

    inner class VH(val binding: ItemMaintenanceBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemMaintenanceBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            tvItemName.text = item.itemName
            tvAmounts.text  = "Pledged: ₹${item.pledgedAmount} / Target: ₹${item.targetAmount}"
            val pct = ((item.pledgedAmount.toFloat() / item.targetAmount) * 100)
                .toInt().coerceIn(0, 100)
            progressBar.progress = pct
            tvPercent.text = "$pct%"
            btnPledge.isEnabled = item.pledgedAmount < item.targetAmount
            btnPledge.setOnClickListener { onPledge(item) }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<MaintenanceItem>() {
            override fun areItemsTheSame(a: MaintenanceItem, b: MaintenanceItem) = a.id == b.id
            override fun areContentsTheSame(a: MaintenanceItem, b: MaintenanceItem) = a == b
        }
    }
}
