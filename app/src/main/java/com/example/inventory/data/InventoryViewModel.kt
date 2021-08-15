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

    fun sellItem(item: Item){
        if(item.quantityInStock > 0){
            val newItem = item.copy(quantityInStock = item.quantityInStock - 1)
        }

    }
}

// Responsible for checking when the activity/fragment is in active state. When they are in active state
// it immediately provides the viewModel instance to it. Responsible for handling lifecycle events.
class InventoryViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InventoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InventoryViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

/*
Data class: copy()
The copy() function is provided by default to all the instances of data classes. This function
is used to copy an object for changing some of its properties, but keeping the rest of the
properties unchanged.

For example, consider the User class and its instance jack as shown below. If you want to create
a new instance with only updating the age property, its implementation would be as follows:

Example


// Data class
data class User(val name: String = "", val age: Int = 0)

// Data class instance
val jack = User(name = "Jack", age = 1)

// A new instance is created with its age property changed, rest of the properties unchanged.
val olderJack = jack.copy(age = 2)
 */