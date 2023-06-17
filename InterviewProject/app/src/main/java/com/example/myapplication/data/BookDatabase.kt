package com.example.myapplication.data

import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@androidx.room.Database(entities = [Book::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BookDatabase: RoomDatabase() {
    abstract fun BookDao(): BookDao

    companion object {
        const val DATABASE = "book_database"
    }

}
