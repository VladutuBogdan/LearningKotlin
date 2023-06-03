package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBook(book: Book)

    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<Book>>

    @Query("DELETE FROM books")
    fun deleteAllBooks()
}