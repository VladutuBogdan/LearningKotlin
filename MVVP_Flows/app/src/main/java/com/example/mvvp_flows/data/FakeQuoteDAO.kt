package com.example.mvvp_flows.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FakeQuoteDAO {
    private val quotesList = mutableListOf<Quote>();
    private val quotes = MutableStateFlow<List<Quote>>(mutableListOf());

    fun addQuote(quote: Quote) {
        quotesList.add(quote);
        quotes.value = quotesList.map { it };
    }

    fun getQuotes() = quotes.asStateFlow();
}