package com.example.mvvp_flows.ui.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvp_flows.data.Quote
import com.example.mvvp_flows.data.QuoteRepository

class QuotesViewModel(private val quoteRepository: QuoteRepository): ViewModel() {
    fun getQuotes() = quoteRepository.getQuotes();

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote);
}