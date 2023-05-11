package com.example.mvvp_flows.ui.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvp_flows.data.Quote
import com.example.mvvp_flows.data.QuoteRepository
import kotlinx.coroutines.launch

class QuotesViewModel(private val quoteRepository: QuoteRepository): ViewModel() {
    fun getQuotes() = quoteRepository.getQuotes();

    fun addQuote(quote: Quote) {
        viewModelScope.launch {
            quoteRepository.addQuote(quote);
        }
    }
}