package com.example.myapplication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.Book

@Composable
fun BooksApplication(books: List<Book>) {
    LazyColumn() {
        items(
            items = books,
            itemContent = {
                BookListItem(book = it)
                Spacer(modifier = Modifier.width(10.dp))
            }
        )
    }
}
