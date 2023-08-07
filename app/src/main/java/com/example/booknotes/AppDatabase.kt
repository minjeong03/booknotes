package com.example.booknotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version =1)
abstract class AppDatabase2 : RoomDatabase() {
    abstract fun bookDao() : BookDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "books.db"
                    ).build()
                }
            }
            return instance!!
        }
    }
}