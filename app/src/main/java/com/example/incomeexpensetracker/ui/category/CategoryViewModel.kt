package com.example.incomeexpensetracker.ui.category

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val name: MutableState<String> = mutableStateOf("")

    // << All Categories
    private val _allCategories = MutableStateFlow<List<Category>>(emptyList())

    val allCategories = _allCategories

    fun getAllCategories() {
        viewModelScope.launch {
            categoryRepository.allCategory.collect {
                _allCategories.value = it
            }
        }
    }
    // All Categories >>

    //  << Get Category By Id
    private val _selectedCategory = MutableStateFlow<Category?>(null)

    val selectedCategory = _selectedCategory

    fun getCategoryById(id: Int) {
        viewModelScope.launch {
            categoryRepository.getCategoryById(id).collect { category ->
                selectedCategory.value = category
            }
        }
    }

    fun updateCategoryFields(selectedCategory: Category?) {
        if (selectedCategory != null) {
            id.value = selectedCategory.id
            name.value = selectedCategory.name
        } else {
            id.value = 0
            name.value = ""
        }
    }
    // Get Category By Id >>

    // << Insert
    private suspend fun insertCategory() {
        viewModelScope.launch { Dispatchers.IO }
        val category = Category(
            id = 0,
            name = name.value,
        )
        categoryRepository.insert(category)
    }

    fun storeCategory() = viewModelScope.launch {
        insertCategory()
    }
    // Insert >>

    // << Update
    private suspend fun _updateCategory() {
        viewModelScope.launch { Dispatchers.IO }
        val category = Category(
            id = id.value,
            name = name.value,
        )
        categoryRepository.update(category)
    }

    fun updateCategory() = viewModelScope.launch {
        _updateCategory()
    }
    // Update >>

    // << Delete
    private suspend fun _deleteCategory() {
        viewModelScope.launch { Dispatchers.IO }
        val category = Category(
            id = id.value,
            name = name.value,
        )
        categoryRepository.delete(category)
    }

    fun deleteCategory() = viewModelScope.launch {
        _deleteCategory()
    }
    // Delete >>


}