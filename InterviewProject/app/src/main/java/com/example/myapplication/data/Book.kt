package com.example.myapplication.data

data class Book (
    val title: String,
    val author: String,
    val isbns: List<String>,
    val wiki: String,
    val image: Image
)

data class Image(
    val url: String
)