package com.example.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.BooksViewModel
import com.example.myapplication.utils.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: BooksViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getBooks().collect { books ->
                    setContent {
                        Surface(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            val navController = rememberNavController()
                            NavHost(navController, startDestination = Utils.AppRoutes.HOME.route) {
                                composable(Utils.AppRoutes.HOME.route) {
                                    BooksApplication(viewModel, navController, books)
                                }
                                composable(Utils.AppRoutes.BOOK.route) {
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
