package com.gramaangana.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MaintenanceDao {

    @Query("SELECT * FROM maintenance_items ORDER BY id DESC")
    fun getAll(): Flow<List<MaintenanceItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MaintenanceItem)

    @Query("UPDATE maintenance_items SET pledgedAmount = pledgedAmount + :amount WHERE id = :id")
    suspend fun pledge(id: Int, amount: Int)
}
