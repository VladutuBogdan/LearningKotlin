package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.data.Book

@Composable
fun BookListItem(book: Book) {
    Button(onClick = {
        // TODO: SELECT SELECTED BOOK IN STATEFLOW
    }) {
        Card(
            shape = RoundedCornerShape(5),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(4.dp)
            ) {
                BookImage(book)
                Text(
                    text = book.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(horizontal = 3.dp)
                )
            }
        }
    }
}

@Composable
fun BookImage(book: Book) {
    AsyncImage(
        model = book.image.url,
        contentDescription = book.title,
        modifier = Modifier.size(width = 2.dp, height = 2.dp),
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit
    )
}