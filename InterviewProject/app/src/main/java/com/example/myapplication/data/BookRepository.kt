package com.example.myapplication.data


import kotlinx.coroutines.flow.StateFlow
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

    private val roomData: StateFlow<List<Book>> = bookDao.getAllBooks();

    suspend fun roomAddBook(book: Book) = bookDao.addBook(book);

    suspend fun getBooks(): StateFlow<List<Book>> {
        val books = roomData.value;

        if (!books.isNullOrEmpty()) {
            return roomData.value as StateFlow<List<Book>>;
        } else {
            val books = getBooksFromApi();

            saveBooksToLocalDatabase(books);

            return books as StateFlow<List<Book>>;
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