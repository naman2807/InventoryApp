package com.example.inventory.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InventoryViewModel(private val itemDao: ItemDao):ViewModel() {
}

class InventoryViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("Not yet implemented")
    }

}