package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.BookRepository

class BooksViewModel(private val bookRepository: BookRepository): ViewModel() {
    suspend fun getBooks() = bookRepository.getBooks();
}