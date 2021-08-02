package com.example.inventory.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao):ViewModel() {
    private fun insertItem(item: Item){
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    private fun getNewEntryItem(itemName: String, itemPrice: String, itemQuantity: String): Item{
        return Item(
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemQuantity.toInt()
        )
    }

    fun addNewItem(itemName: String, itemPrice: String, itemQuantity: String){
        val item = getNewEntryItem(itemName, itemPrice, itemQuantity)
        insertItem(item)
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