package com.example.incomeexpensetracker.data.source.local
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Query
import androidx.room.Delete
import com.example.incomeexpensetracker.data.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
     @Query("SELECT * FROM accounts ORDER BY id DESC")
      fun getAccounts():LiveData <List<Account>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAccount(account: Account)

    @Update
    suspend fun updateAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)
}