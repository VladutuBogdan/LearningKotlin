package com.example.mvvp_flows.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvp_flows.data.QuoteRepository

class QuotesViewModelFactory(private val quoteRepository: QuoteRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesViewModel(quoteRepository) as T;
    }
}