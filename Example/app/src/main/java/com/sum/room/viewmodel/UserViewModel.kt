package com.sum.room.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sum.room.UserDatabase
import com.sum.room.repository.UserRepository
import com.sum.room.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    var tempList = MutableLiveData<List<User>>()

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User){
        viewModelScope.launch( Dispatchers.IO){
            repository.addUser(user)

        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }


    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }

    fun searchDatabase(searchQuery:String){
        viewModelScope.launch(Dispatchers.IO){

            tempList.postValue(repository.searchDatabase(searchQuery))
            Log.v("ViewModel",repository.searchDatabase(searchQuery).toString())

        }

    }



}