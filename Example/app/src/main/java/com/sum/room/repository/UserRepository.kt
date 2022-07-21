package com.sum.room.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sum.room.UserDao
import com.sum.room.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserRepository(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.readAllData()
    val searchData = MutableLiveData<List<User>>()

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

    suspend fun searchDatabase(searchQuery: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val tempList = userDao.searchDatabase(searchQuery)
            withContext(Dispatchers.Main) {
                searchData.value = tempList
            }
        }
    }

}