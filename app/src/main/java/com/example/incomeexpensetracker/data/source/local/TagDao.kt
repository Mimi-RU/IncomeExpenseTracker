package com.example.incomeexpensetracker.data.source.local

import androidx.room.*
import com.example.incomeexpensetracker.data.model.Tag
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {

    @Query("SELECT * FROM tags ORDER BY id DESC")
    fun getTags(): Flow<List<Tag>>

    @Query("SELECT * FROM tags WHERE id = :id")
    fun getTagById(id: Int): Flow<Tag>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(Tag: Tag)

    @Update
    suspend fun updateTag(Tag: Tag)

    @Delete
    suspend fun deleteTag(Tag: Tag)
}