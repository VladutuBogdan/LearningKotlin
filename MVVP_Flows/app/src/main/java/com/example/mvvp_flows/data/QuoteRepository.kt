package com.example.mvvp_flows.data

class QuoteRepository private constructor(private val quoteDAO: FakeQuoteDAO){

    companion object {
        @Volatile private var instance: QuoteRepository? = null;

        fun getInstance(quoteDAO: FakeQuoteDAO) =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(quoteDAO).also { instance = it }
            }
    }

    suspend fun addQuote(quote: Quote) = quoteDAO.addQuote(quote);

    fun getQuotes() = quoteDAO.getQuotes();
}