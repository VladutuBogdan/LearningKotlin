package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment;
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.data.Book

class BookInfoFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.book_fragment, container, false);

        // TODO: GET BOOK FROM STATEFLOW - SELECTED BOOK
        val book = Book("", "", listOf(), "", com.example.myapplication.data.Image(""));

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            Column() {
                BookImageFragment(book)
                Text(
                    text = book.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(horizontal = 3.dp)
                )

                Text(
                    text = book.author,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(horizontal = 3.dp)
                )

                Text(
                    text = book.wiki,
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    ),
                    modifier = Modifier.padding(horizontal = 3.dp)
                )

                book.isbns.forEach {
                    Text(
                        text = it,
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                        ),
                        modifier = Modifier.padding(horizontal = 3.dp)
                    )
                }
            }

        }

        return view;
    }
}

@Composable
fun BookImageFragment(book: Book) {
    AsyncImage(
        model = book.image.url,
        contentDescription = book.title,
        modifier = Modifier.size(width = 10.dp, height = 10.dp),
        alignment = Alignment.Center,
        contentScale = ContentScale.Fit
    )
}