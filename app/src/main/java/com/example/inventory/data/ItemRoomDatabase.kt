package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase: RoomDatabase() {

    abstract fun getDao(): ItemDao

    companion object{
        @Volatile
        private var database: ItemRoomDatabase? = null

        fun getDatabase(context: Context):ItemRoomDatabase{

        }
    }
}