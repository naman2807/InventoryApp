package com.example.inventory.data

import androidx.room.Entity

@Entity(tableName = "item")
data class Item(
    val id: Int = 0,
    val itemName: String,
    val itemPrice: Double,
    val quantityInStock: Int) {
}