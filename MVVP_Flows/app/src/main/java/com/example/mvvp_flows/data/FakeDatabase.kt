package com.example.mvvp_flows.data

class FakeDatabase private constructor() {
    val quoteDao = FakeQuoteDAO();

    companion object {
        @Volatile private var instance: FakeDatabase? = null;

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: FakeDatabase().also { instance = it }
            }
    }
}