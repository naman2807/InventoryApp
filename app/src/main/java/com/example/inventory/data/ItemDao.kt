package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface ItemDao {
    @Insert
    suspend fun insert(item: Item)
}