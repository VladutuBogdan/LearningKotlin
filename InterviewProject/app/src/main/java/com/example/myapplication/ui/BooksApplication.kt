package com.example.myapplication

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.myapplication.data.Book

@Composable
fun BooksApplication(books: List<Book>) {
    LazyColumn() {
        items(
            items = books,
            itemContent = {
                BookListItem(book = it)
            }
        )
    }
}
