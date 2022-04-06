package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.Tag
import com.example.incomeexpensetracker.data.source.local.TagDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TagRepository @Inject constructor(private val tagDao: TagDao) {

    val allTag: Flow<List<Tag>> = tagDao.getTags()

    suspend fun getTagById(id: Int) : Flow<Tag> {
        return tagDao.getTagById(id)
    }

    suspend fun insert(tag: Tag) {
        tagDao.insertTag(tag)
    }

    suspend fun update(tag: Tag) {
        tagDao.updateTag(tag)
    }

    suspend fun delete(tag: Tag) {
        tagDao.deleteTag(tag)
    }
}