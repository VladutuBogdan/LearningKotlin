package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksViewModel(private val bookRepository: BookRepository): ViewModel() {
    suspend fun getBooks() = withContext(Dispatchers.IO){
        bookRepository.getBooks()
    }

}