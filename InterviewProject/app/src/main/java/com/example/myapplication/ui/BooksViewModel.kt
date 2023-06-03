package com.example.myapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Book
import com.example.myapplication.data.BookRepository
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class BooksViewModel(private val bookRepository: BookRepository): ViewModel() {
    suspend fun getBooks() = bookRepository.getBooks()

    fun getSelectedBook(): Book = bookRepository.getSelectedBook();

    fun setSelectedBook(book: Book) = bookRepository.setSelectedBook(book);

    fun deleteBook(book: Book) =  bookRepository.deleteBook(book)
}