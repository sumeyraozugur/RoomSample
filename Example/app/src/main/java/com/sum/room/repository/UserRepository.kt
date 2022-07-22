package com.sum.room.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.sum.room.UserDao
import com.sum.room.model.User

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()


    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    suspend fun searchDatabase(searchQuery: String): List<User> {
        return userDao.searchDatabase(searchQuery)
        Log.v("Repo", userDao.searchDatabase(searchQuery).toString())


    }

}