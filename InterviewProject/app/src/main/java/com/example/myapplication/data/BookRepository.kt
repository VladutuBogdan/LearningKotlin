package com.example.myapplication.data

class BookRepository private constructor(private val bookDao: BookDao){
    companion object {
        @Volatile private var istance: BookRepository? = null

        fun getIstance(bookDao: BookDao) = istance ?: synchronized(this) {
            istance ?: BookRepository(bookDao).also {
                istance = it
            }
        }
    }

    private fun addBook(book: Book) = bookDao.addBook(book)

    fun getBooks() = bookDao.getBooks()

    // TODO: Logica cand sa ia din DB si cand sa ia din API
}