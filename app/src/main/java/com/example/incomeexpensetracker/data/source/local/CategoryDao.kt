package com.example.incomeexpensetracker.data.source.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.incomeexpensetracker.data.model.Category

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(Category: Category)

    @Query("SELECT * FROM categories ORDER BY id DESC")
    fun getCategories(): LiveData<List<Category>>

    @Update
    suspend fun updateCategory(Category: Category)

    @Delete
    suspend fun deleteCategory(Category: Category)
}