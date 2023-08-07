package com.example.booknotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booknotes.ui.theme.BookNotesTheme

@Composable
fun MyButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "Click Me!")
    }
}

class DBInitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppDatabase.getInstance(this)

        setContent {
            BookNotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyButton(onClick = {
                            // Create the Intent to start the target activity
                            val intent = Intent(this, MainActivity::class.java)

                            // Start the target activity
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}