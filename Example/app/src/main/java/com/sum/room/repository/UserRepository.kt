package com.sum.room.repository

import androidx.lifecycle.LiveData
import com.sum.room.UserDao
import com.sum.room.model.User

class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend  fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

}