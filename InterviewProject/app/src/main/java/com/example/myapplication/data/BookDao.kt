package com.example.myapplication.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BookDao {
    private val bookList = mutableListOf<Book>();
    private val books = MutableStateFlow<List<Book>>(emptyList());

    fun addBook(book: Book) {
        // TODO: Add books in database
    }

    fun getBooks() = books.asStateFlow();
}