package com.example.mvvp_livedata.data

class BooksRepository private constructor(private val booksDao: FakeBooksDao) {
    companion object {
        private var instance: BooksRepository? = null

        fun getInstance(booksDao: FakeBooksDao) = instance ?: synchronized(this) {
            instance ?: BooksRepository(booksDao).also { instance = it }
        }
    }

    fun addBook(book: Book) = booksDao.addBook(book)

    fun getBooks() = booksDao.getBooks()
}