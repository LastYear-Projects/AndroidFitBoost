package com.example.firebasetest

import androidx.lifecycle.LiveData

class RoomUserRepository(private val userDao:RoomUserDao) {
    val readAllData: LiveData<List<RoomUser>> = userDao.readAllData()

    suspend fun addUser(user: RoomUser){
        userDao.addUser(user)
    }

    suspend fun updateUser(user:RoomUser){
        userDao.updateUser(user)
    }
}