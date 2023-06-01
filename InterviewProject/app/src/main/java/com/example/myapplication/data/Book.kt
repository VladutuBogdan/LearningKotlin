package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "books")
data class Book (
    @PrimaryKey
    val title: String,
    val author: String,
    @TypeConverters(Converters::class) val isbns: List<String>,
    val wiki: String,
    @TypeConverters(Converters::class) val image: Image
)

data class Image(
    val url: String
)

data class BookList(
    val books: List<Book>
)