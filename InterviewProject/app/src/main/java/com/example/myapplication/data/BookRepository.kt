package com.example.myapplication.data

import android.util.Log
import com.example.myapplication.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

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

    private fun addBook(book: Book) = bookDao.addBook(book)

    fun getBooks() = bookDao.getBooks()

    fun getBooksFromApi() {
        GlobalScope.launch(Dispatchers.IO) {
            val data = retrofitBuilder.getData().await();

            for (book in data.books) {
                Log.d(Utils.LOG_D, book.toString())
            }
        }
    }

    // TODO: Logica cand sa ia din DB si cand sa ia din API
}