package com.example.inventory.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class InventoryViewModel(private val itemDao: ItemDao):ViewModel() {
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

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

    fun isEntryValid(itemName: String, itemPrice: String, itemCount: String): Boolean {
        if (itemName.isBlank() || itemPrice.isBlank() || itemCount.isBlank()) {
            return false
        }
        return true
    }

    fun addNewItem(itemName: String, itemPrice: String, itemQuantity: String){
        val item = getNewEntryItem(itemName, itemPrice, itemQuantity)
        insertItem(item)
    }

    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }

    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
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