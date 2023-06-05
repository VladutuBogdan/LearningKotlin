package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.data.Book
import com.example.myapplication.ui.BooksViewModel
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BookListItem(viewModel: BooksViewModel, navController: NavController, book: Book) {
    Button(
        shape = RoundedCornerShape(5),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray
        ),
        modifier = Modifier.padding(horizontal = 3.dp),
        onClick = {
            viewModel.setSelectedBook(book)
            navController.navigate(Utils.AppRoutes.BOOK.route)
    }) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(4.dp)
            ) {
                BookImage(book)
                Text(
                    text = book.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(horizontal = 3.dp)
                )
            }
        }
    }
    Button(
        shape = RoundedCornerShape(5),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray
        ),
        modifier = Modifier.padding(5.dp),
        onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.deleteBook(book)
            }
        }) {
        Text(
            text = Utils.AppStrings.DELETE.string,
            style = TextStyle(
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(horizontal = 3.dp)
        )
    }
}

@Composable
fun BookImage(book: Book) {
    AsyncImage(
        model = book.image.url,
        placeholder = painterResource(R.drawable.loading),
        contentDescription = book.title,
        modifier = Modifier.size(width = 100.dp, height = 80.dp),
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit
    )
}