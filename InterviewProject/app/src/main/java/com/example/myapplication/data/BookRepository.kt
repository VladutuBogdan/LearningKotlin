package com.example.myapplication.data


import android.util.Log
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.await
import javax.inject.Inject

class BookRepository @Inject constructor(private val booksDatabaseWrapper: BooksDatabaseWrapper, private val apiInterface: ApiInterface){
    private lateinit var selectedBook: Book
    private val repositoryScope = CoroutineScope(Dispatchers.IO)

    private val bookDao = booksDatabaseWrapper.getBookDao()
    fun getSelectedBook(): Book = selectedBook

    fun setSelectedBook(book: Book) {
        selectedBook = book
    }

    fun deleteBook(book: Book) = bookDao.deleteBook(book.title)

    fun roomAddBook(book: Book) = bookDao.addBook(book)

    suspend fun getBooks(): StateFlow<List<Book>> {
        val booksList = mutableListOf<Book>()
        val books = MutableStateFlow<List<Book>>(emptyList())

        repositoryScope.launch {
            bookDao.getAllBooks().collect { roomBooks ->
                if (roomBooks.isNotEmpty()) {
                    Log.d(Utils.LOG_D, "Room request")
                    booksList.clear()
                    booksList.addAll(roomBooks)

                    books.value = ArrayList(booksList)
                } else {
                    Log.d(Utils.LOG_D, "API Request")
                    val apiBooks = getBooksFromApi()
                    saveBooksToLocalDatabase(apiBooks)
                }
            }
        }

        return books.asStateFlow()
    }

    private fun saveBooksToLocalDatabase(books: List<Book>) {
        // Save books to the local database
        for (book in books) {
            roomAddBook(book)
        }
    }

    private suspend fun getBooksFromApi(): List<Book> {
        val data = apiInterface.getData().await()

        return data.books
    }
}