package com.sum.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sum.room.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user:User)

    @Delete
    suspend fun deleteUser(user:User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE firstName  like '%' || :searchQuery || '%'  ") //WHERE firstName  like '%' || :searchQuery || lastName like '%'
    suspend fun searchDatabase(searchQuery:String) : List<User>
}