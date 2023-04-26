package com.example.mvvp_livedata.data

data class Book(val book: String, val author: String) {
    override fun toString(): String {
        return "$book - $author"
    }
}