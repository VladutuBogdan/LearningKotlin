package com.example.mvvp_livedata.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FakeBooksDao {
    private val booksList = mutableListOf<Book>()
    private val books = MutableLiveData<List<Book>>()

    init {
        books.value = booksList;
    }

    fun addBook(book: Book) {
        booksList.add(book)
        books.value = booksList
    }

    fun getBooks() = books.value as LiveData<List<Book>>
}