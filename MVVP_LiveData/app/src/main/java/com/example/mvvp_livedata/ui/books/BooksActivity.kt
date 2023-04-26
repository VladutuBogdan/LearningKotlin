package com.example.mvvp_livedata.ui.books

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mvvp_livedata.R
import com.example.mvvp_livedata.data.Book
import com.example.mvvp_livedata.data.BooksRepository
import com.example.mvvp_livedata.data.FakeDatabase
import com.google.android.material.textfield.TextInputEditText

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.books_activity)

        val initializedViewModel = initializeViewModel()

        updateUI(initializedViewModel)
        setListeners(initializedViewModel)
    }

    fun initializeViewModel(): BooksViewModel {
        val booksRepository = BooksRepository.getInstance(FakeDatabase.getInstance().booksDao)
        val factory = BooksViewModelFactory(booksRepository)
        return ViewModelProviders.of(this, factory).get(BooksViewModel::class.java)
    }

    fun updateUI(viewModel: BooksViewModel) {

        viewModel.getBooks().observe(this, Observer { books ->
            val stringBuilder = StringBuilder()

            books.forEach { book ->
                stringBuilder.append("$book\n")
            }

            val myTextView = findViewById<TextView>(R.id.textView)
            myTextView.text = stringBuilder.toString()
        })
    }

    fun setListeners(viewModel: BooksViewModel) {
        val addButton = findViewById<Button>(R.id.button_add_book)

        addButton.setOnClickListener {
            val bookName = findViewById<TextInputEditText>(R.id.bookName)
            val authorName = findViewById<TextInputEditText>(R.id.authorName)
            val book = Book(bookName.text.toString(), authorName.text.toString())
            viewModel.addBook(book)

            bookName.setText("")
            authorName.setText("")
        }
    }
}
