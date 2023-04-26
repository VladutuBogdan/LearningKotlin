package com.example.mvvp_livedata.data

class FakeDatabase private constructor() {
    val booksDao = FakeBooksDao()

    companion object {
        private var instance: FakeDatabase? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: FakeDatabase().also { instance = it }
        }
    }
}