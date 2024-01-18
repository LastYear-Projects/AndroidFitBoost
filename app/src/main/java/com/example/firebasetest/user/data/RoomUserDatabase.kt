package com.example.firebasetest.user.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.firebasetest.user.model.RoomUser
@Database(entities = [RoomUser::class], version = 5, exportSchema = false)
abstract class RoomUserDatabase : RoomDatabase() {

    abstract fun userDao(): RoomUserDao

    companion object {
        @Volatile
        private var INSTANCE: RoomUserDatabase? = null

        fun getDatabase(context: Context): RoomUserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomUserDatabase::class.java,
                    "user_database2"
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }
}
