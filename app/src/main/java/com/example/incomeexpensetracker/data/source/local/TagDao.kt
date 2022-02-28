package com.example.incomeexpensetracker.data.source.local
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.incomeexpensetracker.data.model.Tag
@Dao
interface TagDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTag(Tag: Tag)

    @Query("SELECT * FROM tags ORDER BY id DESC")
    fun getTags(): LiveData<List<Tag>>

    @Update
    suspend fun updateTag(Tag: Tag)

    @Delete
    suspend fun deleteTag(Tag: Tag)
}