package com.example.booknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.booknotes.ui.theme.BookNotesTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var appDatabase = AppDatabase.getInstance(this)
        var bookViewModelFactory = BookViewModelFactory(appDatabase)
        bookViewModel = ViewModelProvider(this, bookViewModelFactory).get(BookViewModel::class.java)

        setContent {
            BookNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    bookViewModel.getAllBooksFromServer()
                    val dbText = bookViewModel._status.observeAsState("none").value
                    val date:Date = Date()
                    val debugCards = listOf(
                        Book(1, "Debug", "Testing", dbText, date.toString())
                    )
                    val books = bookViewModel.allBooks.observeAsState(initial = emptyList()).value
                    BookList(debugCards)
                    BookList(books)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BookNotesTheme {
        val books = listOf(
            Book(1, "Title 1", "Author 1", "Description 1", "1"),
            Book(2,"Title 2", "Author 2", "Description 2", "2"),
            Book(3, "Title 3", "Author 3", "Description 3", "3"),
            Book(4, "Title 4", "Author 4", "Description 4", "4"),
        )

        BookList(books)
    }
}