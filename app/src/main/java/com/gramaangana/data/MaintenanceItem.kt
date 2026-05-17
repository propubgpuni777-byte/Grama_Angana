package com.gramaangana.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "maintenance_items")
data class MaintenanceItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemName: String,
    val targetAmount: Int,
    val pledgedAmount: Int = 0
)
