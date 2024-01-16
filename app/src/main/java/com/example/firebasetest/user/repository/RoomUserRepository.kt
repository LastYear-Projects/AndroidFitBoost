package com.example.firebasetest.user.repository

import androidx.lifecycle.LiveData
import com.example.firebasetest.user.model.RoomUser
import com.example.firebasetest.user.data.RoomUserDao

class RoomUserRepository(private val userDao: RoomUserDao) {
    val readAllData: LiveData<List<RoomUser>> = userDao.readAllData()

    suspend fun addUser(user: RoomUser){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: RoomUser){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: RoomUser){
        userDao.deleteUser(user)
    }

    fun getUser(userId: Int): LiveData<RoomUser> {
        return userDao.getUserById(userId)
    }

    suspend fun getUserByEmail(email: String): RoomUser {
        return userDao.getUserByEmail(email)
    }
}