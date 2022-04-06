package com.example.incomeexpensetracker.ui.tag

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.data.model.Tag
import com.example.incomeexpensetracker.data.repository.CategoryRepository
import com.example.incomeexpensetracker.data.repository.TagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(private val tagRepository: TagRepository) :
    ViewModel() {

    val id: MutableState<Int> = mutableStateOf(0)
    val name: MutableState<String> = mutableStateOf("")

    // << All Tags
    private val _allTags = MutableStateFlow<List<Tag>>(emptyList())

    val allTags = _allTags

    fun getAllTags() {
        viewModelScope.launch {
            tagRepository.allTag.collect {
                _allTags.value = it
            }
        }
    }
    // All Tags >>

    //  << Get Tag By Id
    private val _selectedTag = MutableStateFlow<Tag?>(null)

    val selectedTag = _selectedTag

    fun getTagById(id: Int) {
        viewModelScope.launch {
            tagRepository.getTagById(id).collect { tag ->
                selectedTag.value = tag
            }
        }
    }

    fun updateTagFields(selectedTag: Tag?) {
        if (selectedTag != null) {
            id.value = selectedTag.id
            name.value = selectedTag.name
        } else {
            id.value = 0
            name.value = ""
        }
    }
    // Get Tag By Id >>

    // << Insert
    private suspend fun insertTag() {
        viewModelScope.launch { Dispatchers.IO }
        val tag = Tag(
            id = 0,
            name = name.value,
        )
        tagRepository.insert(tag)
    }

    fun storeTag() = viewModelScope.launch {
        insertTag()
    }
    // Insert >>

    // << Update
    private suspend fun _updateTag() {
        viewModelScope.launch { Dispatchers.IO }
        val tag = Tag(
            id = id.value,
            name = name.value,
        )
        tagRepository.update(tag)
    }

    fun updateTag() = viewModelScope.launch {
        _updateTag()
    }
    // Update >>

    // << Delete
    private suspend fun _deleteTag() {
        viewModelScope.launch { Dispatchers.IO }
        val tag = Tag(
            id = id.value,
            name = name.value,
        )
        tagRepository.delete(tag)
    }

    fun deleteTag() = viewModelScope.launch {
        _deleteTag()
    }
    // Delete >>


}