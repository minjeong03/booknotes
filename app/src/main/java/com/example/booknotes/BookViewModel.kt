package com.example.booknotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.booknotes.network.BookNotesApi
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BookViewModel(private val database: AppDatabase) : ViewModel() {
    val allBooks: LiveData<List<Book>> = database.bookDao().getAllBooks()
    val _status: MutableLiveData<String> = MutableLiveData("")
    private fun getAllBooks() {
        viewModelScope.launch {
            val listResult = BookNotesApi.retrofitService.getBookNotes()
            _status.value = listResult
        }
    }
}

class BookViewModelFactory(private val database: AppDatabase) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            return BookViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}