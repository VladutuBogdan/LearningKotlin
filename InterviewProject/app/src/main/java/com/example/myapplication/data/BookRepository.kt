package com.example.myapplication.data


import android.util.Log
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

class BookRepository private constructor(private val bookDao: BookDao){
    companion object {
        @Volatile private var istance: BookRepository? = null
        const val BASE_URL = "https://pastebin.com/raw/";

        private val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java);

        fun getIstance(bookDao: BookDao) = istance ?: synchronized(this) {
            istance ?: BookRepository(bookDao).also {
                istance = it
            }
        }
    }

    private lateinit var selectedBook: Book;
    private val repositoryScope = CoroutineScope(Dispatchers.IO);

    fun getSelectedBook(): Book = selectedBook;

    fun setSelectedBook(book: Book) {
        selectedBook = book;
    }

    suspend fun roomAddBook(book: Book) = bookDao.addBook(book);

    suspend fun getBooks(): StateFlow<List<Book>> {
        val booksList = mutableListOf<Book>()
        val books = MutableStateFlow<List<Book>>(emptyList())

        repositoryScope.launch {
            bookDao.getAllBooks().collect { roomBooks ->
                if (roomBooks.isNotEmpty()) {
                    Log.d(Utils.LOG_D, "Room request")
                    booksList.clear()
                    booksList.addAll(roomBooks)

                    books.emit(ArrayList(booksList))
                } else {
                    Log.d(Utils.LOG_D, "API Request")
                    val apiBooks = getBooksFromApi()
                    saveBooksToLocalDatabase(apiBooks)
                }
            }
        }

        return books.asStateFlow()
    }





    private suspend fun saveBooksToLocalDatabase(books: List<Book>) {
        // Save books to the local database
        for (book in books) {
            roomAddBook(book)
        }
    }

    private suspend fun getBooksFromApi(): List<Book> {
        val data = retrofitBuilder.getData().await();

        return data.books;
    }
}