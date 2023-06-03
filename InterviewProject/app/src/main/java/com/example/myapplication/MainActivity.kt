package com.example.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.BookDatabase
import com.example.myapplication.data.BookRepository
import com.example.myapplication.ui.BooksViewModel
import com.example.myapplication.ui.BooksViewModelFactory
import com.example.myapplication.utils.Utils
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
                            val navController = rememberNavController()
                            NavHost(navController, startDestination = Utils.AppRoutes.HOME.route) {
                                composable("home") {
                                    BooksApplication(viewModel, navController, books)
                                }
                                composable("book") {
                                    BookInfo(viewModel, navController)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}