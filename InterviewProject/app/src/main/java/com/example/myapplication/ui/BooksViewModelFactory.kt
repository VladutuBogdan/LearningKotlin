package com.example.myapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class BooksViewModelFactory @Inject constructor(private val bookRepository: BookRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BooksViewModel(bookRepository) as T
    }
}