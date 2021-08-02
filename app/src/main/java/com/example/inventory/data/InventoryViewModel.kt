package com.example.inventory.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InventoryViewModel(private val itemDao: ItemDao):ViewModel() {
    private fun insertItem(item: Item){

    }
}

class InventoryViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}