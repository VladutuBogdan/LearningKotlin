package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.data.Book
import com.example.myapplication.ui.BooksViewModel
import com.example.myapplication.utils.Utils

@Composable
fun BookInfo(viewModel: BooksViewModel, navController: NavController) {
    val book = viewModel.getSelectedBook();

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookImageFragment(book)
        Text(
            text = book.title,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = book.author,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = book.wiki,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        book.isbns.forEach { isbn ->
            Text(
                text = isbn,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            shape = RoundedCornerShape(5),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            ),
            modifier = Modifier.padding(5.dp),
            onClick = {
                navController.navigate(Utils.AppRoutes.HOME.route)
            }) {
            Text(
                text = Utils.AppStrings.GO_BACK.string,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}


@Composable
fun BookImageFragment(book: Book) {
    AsyncImage(
        model = book.image.url,
        contentDescription = book.title,
        modifier = Modifier.size(width = 130.dp, height = 130.dp),
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit
    )
}
