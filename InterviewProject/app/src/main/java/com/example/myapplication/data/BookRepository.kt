package com.example.myapplication.data


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
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


    suspend fun roomAddBook(book: Book) = bookDao.addBook(book);

    suspend fun getBooks(): Flow<List<Book>> {
        val roomBooks = bookDao.getAllBooks()

        return if (roomBooks.isNotEmpty()) {
            flowOf(roomBooks)
        } else {
            val books = getBooksFromApi();

            saveBooksToLocalDatabase(books);
            flowOf(books)
        }
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