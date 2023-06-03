package com.example.myapplication

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.data.Book
import com.example.myapplication.ui.BooksViewModel

@Composable
fun BooksApplication(viewModel: BooksViewModel, navController: NavController, books: List<Book>) {
    LazyColumn() {
        items(
            items = books,
            itemContent = {
                BookListItem(viewModel, navController, book = it)
            }
        )
    }
}
