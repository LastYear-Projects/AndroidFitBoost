package com.example.firebasetest.user.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.firebasetest.user.model.RoomUser

@Dao
interface RoomUserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: RoomUser)

    @Update
    suspend fun updateUser(user: RoomUser)

    @Delete
    suspend fun deleteUser(user: RoomUser)

    @Query("SELECT * FROM user_table3 ORDER BY id ASC")
    fun readAllData(): LiveData<List<RoomUser>>

    @Query("SELECT * FROM user_table3 WHERE id = :userId")
    fun getUserById(userId: Int): LiveData<RoomUser>

    @Query("SELECT * FROM user_table3 WHERE email = :email")
    suspend fun getUserByEmail(email: String): RoomUser
}