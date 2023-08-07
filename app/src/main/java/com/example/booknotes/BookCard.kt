package com.example.booknotes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text

@Composable
fun DebugText(str:String) {
    Text(
        text = str,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun BookCard(title:String, author: String, description: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = author,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun BookList(books: List<Book>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(books) { book ->
            BookCard(
                title = book.title,
                author = book.author,
                description = book.description
            )
        }
    }
}