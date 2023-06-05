package com.example.myapplication.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@androidx.room.Database(entities = [Book::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class BookDatabase: RoomDatabase() {
    abstract fun BookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookDatabase? = null

        private val DATABASE = "book_database"

        fun getDatabase(context: Context): BookDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookDatabase::class.java,
                    DATABASE
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
