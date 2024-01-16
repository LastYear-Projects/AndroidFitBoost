package com.example.firebasetest.user.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.firebasetest.user.model.RoomUser
import com.example.firebasetest.user.data.RoomUserDatabase
import com.example.firebasetest.user.repository.RoomUserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomUserViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<RoomUser>>
    private val repository: RoomUserRepository

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val currentUserLiveData = MutableLiveData<RoomUser>()
    init{
        val userDao = RoomUserDatabase.getDatabase(application).userDao()
        repository = RoomUserRepository(userDao)
        readAllData = repository.readAllData
    }

    init {
        fetchCurrentUser()
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

    fun getUser(userId: Int): LiveData<RoomUser> {
        return repository.getUser(userId)
    }

    private fun fetchCurrentUser() {
        val user = firebaseAuth.currentUser
        if (user != null) {
            // You may need to customize this logic based on your user management
            // For simplicity, assuming email is unique and using it as the identifier
            viewModelScope.launch(Dispatchers.IO) {
                val roomUser = repository.getUserByEmail(user.email.toString())
                currentUserLiveData.postValue(roomUser)
            }
        }
    }

    fun getCurrentUserLiveData(): LiveData<RoomUser> {
        return currentUserLiveData
    }
}