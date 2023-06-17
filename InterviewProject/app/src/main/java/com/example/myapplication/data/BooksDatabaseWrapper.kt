package com.example.myapplication.data

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksDatabaseWrapper @Inject constructor(@ApplicationContext private val app: Context) {

    private val database: BookDatabase by lazy {
        createDatabase(app)
    }

    private fun createDatabase(app: Context): BookDatabase {
        return Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            BookDatabase.DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getBookDao() = database.BookDao()
}
