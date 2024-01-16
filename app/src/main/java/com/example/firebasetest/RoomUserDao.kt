package com.example.firebasetest

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: RoomUser)

    @Update
    suspend fun updateUser(user: RoomUser)

    @Delete
    suspend fun deleteUser(user: RoomUser)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<RoomUser>>
}