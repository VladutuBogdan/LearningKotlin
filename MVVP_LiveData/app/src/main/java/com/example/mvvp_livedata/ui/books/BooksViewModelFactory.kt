package com.example.mvvp_livedata.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvp_livedata.data.BooksRepository

class BooksViewModelFactory(private val booksRepository: BooksRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BooksViewModel(booksRepository) as T
    }
}