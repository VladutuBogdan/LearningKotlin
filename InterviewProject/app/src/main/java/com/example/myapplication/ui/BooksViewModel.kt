package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Book
import com.example.myapplication.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val bookRepository: BookRepository): ViewModel() {
    suspend fun getBooks() = bookRepository.getBooks()

    fun getSelectedBook(): Book = bookRepository.getSelectedBook()

    fun setSelectedBook(book: Book) = bookRepository.setSelectedBook(book)

    fun deleteBook(book: Book) =  bookRepository.deleteBook(book)
}