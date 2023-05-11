package com.example.mvvp_flows.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class FakeQuoteDAO {
    private val quotesList = mutableListOf<Quote>();
    private val quotes = MutableStateFlow<List<Quote>>(emptyList());

    suspend fun addQuote(quote: Quote) {
        quotesList.add(quote);

        withContext(Dispatchers.IO) {
            quotes.emit(ArrayList(quotesList))
        }
    }

    fun getQuotes() = quotes.asStateFlow();
}