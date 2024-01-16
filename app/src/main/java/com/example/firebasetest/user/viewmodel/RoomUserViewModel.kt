package com.example.firebasetest.user.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.firebasetest.user.model.RoomUser
import com.example.firebasetest.user.data.RoomUserDatabase
import com.example.firebasetest.user.repository.RoomUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomUserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<RoomUser>>
    private val repository: RoomUserRepository

    init{
        val userDao = RoomUserDatabase.getDatabase(application).userDao()
        repository = RoomUserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: RoomUser){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun updateUser(user: RoomUser){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: RoomUser){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteUser(user)
        }
    }
}