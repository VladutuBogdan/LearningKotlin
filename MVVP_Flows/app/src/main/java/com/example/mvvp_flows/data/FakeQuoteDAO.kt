package com.example.mvvp_flows.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FakeQuoteDAO {
    private val quotesList = mutableListOf<Quote>();
    private val quotes = MutableStateFlow<List<Quote>>(listOf());

    init {
        quotes.value = quotesList;
    }

    fun addQuote(quote: Quote) {
        quotesList.add(quote);
        quotes.value = quotesList;
    }

    fun getQuotes() = quotes as StateFlow<List<Quote>>;

}