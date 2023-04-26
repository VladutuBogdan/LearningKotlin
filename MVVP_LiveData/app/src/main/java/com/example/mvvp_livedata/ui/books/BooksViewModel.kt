package com.example.mvvp_livedata.ui.books

import androidx.lifecycle.ViewModel
import com.example.mvvp_livedata.data.Book
import com.example.mvvp_livedata.data.BooksRepository

class BooksViewModel(private val booksRepository: BooksRepository): ViewModel() {
    fun getBooks() = booksRepository.getBooks();

    fun addBook(book: Book) = booksRepository.addBook(book);
}