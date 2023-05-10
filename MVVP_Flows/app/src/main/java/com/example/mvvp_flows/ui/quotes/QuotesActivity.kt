package com.example.mvvp_flows.ui.quotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.mvvp_flows.R
import com.example.mvvp_flows.data.FakeDatabase
import com.example.mvvp_flows.data.Quote
import com.example.mvvp_flows.data.QuoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuotesActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.xml.activity_quotes)

    val initializedViewModel = initializeViewModel()

    updateUI(initializedViewModel)
    setListeners(initializedViewModel)
}

fun initializeViewModel(): QuotesViewModel {
    val quotesRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
    val factory = QuotesViewModelFactory(quotesRepository)
    return ViewModelProviders.of(this, factory).get(QuotesViewModel::class.java)
}

fun updateUI(viewModel: QuotesViewModel) {
    GlobalScope.launch {
        viewModel.getQuotes().collect{ quotes ->
            val stringBuilder = StringBuilder()

            quotes.forEach { book ->
                stringBuilder.append("$book\n")
            }

            val myTextView = findViewById<TextView>(R.id.textView_quotes)
            myTextView.text = stringBuilder.toString()
        }
    }
}

fun setListeners(viewModel: QuotesViewModel) {
    val addButton = findViewById<Button>(R.id.button_add_quote)

    addButton.setOnClickListener {
        val quoteText = findViewById<EditText>(R.id.editText_quote)
        val author = findViewById<EditText>(R.id.editText_author)
        val book = Quote(quoteText.text.toString(), author.text.toString())
        viewModel.addQuote(book)

        quoteText.setText("")
        author.setText("")
    }
}
}
