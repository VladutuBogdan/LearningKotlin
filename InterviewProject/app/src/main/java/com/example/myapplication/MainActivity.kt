package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.data.BookDatabase
import com.example.myapplication.data.BookRepository
import com.example.myapplication.ui.BooksViewModel
import com.example.myapplication.ui.BooksViewModelFactory
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use hilt
        val booksRepository = BookRepository.getIstance(BookDatabase.getDatabase(this).BookDao())
        val booksViewModelFactory = BooksViewModelFactory(booksRepository)

        val viewModel = ViewModelProviders.of(this, booksViewModelFactory).get(BooksViewModel::class.java)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getBooks().collect { books ->
                    setContent {
                        Surface(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            BooksApplication(books)
                        }
                    }
                }
            }
        }
    }
}