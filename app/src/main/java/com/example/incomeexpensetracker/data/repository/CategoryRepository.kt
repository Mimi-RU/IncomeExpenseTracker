package com.example.incomeexpensetracker.data.repository

import com.example.incomeexpensetracker.data.model.Category
import com.example.incomeexpensetracker.data.source.local.CategoryDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped

    class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {

        val allCategory: Flow<List<Category>> = categoryDao.getCategories()

        suspend fun getCategoryById(id: Int) : Flow<Category> {
            return categoryDao.getCategoryById(id)
        }

        suspend fun insert(category: Category) {
            categoryDao.insertCategory(category)
        }

        suspend fun update(category: Category) {
            categoryDao.updateCategory(category)
        }

        suspend fun delete(category: Category) {
            categoryDao.deleteCategory(category)
        }
    }
