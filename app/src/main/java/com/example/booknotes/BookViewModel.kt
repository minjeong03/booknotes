package com.example.booknotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.booknotes.network.BookNotesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.lang.IllegalArgumentException

class BookViewModel(private val database: AppDatabase) : ViewModel() {
    val allBooks: LiveData<List<Book>> = database.bookDao().getAllBooks()
    val _status: MutableLiveData<String> = MutableLiveData("")
    public fun getAllBooksFromServer(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var listResult = BookNotesApi.retrofitService.getBookNotes()
                database.bookDao().insertAll(listResult)
                withContext(Dispatchers.Main) {
                    _status.value = "Success: ${listResult.size} retrieved"
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _status.value = "Failure: ${e.message}"
                }
            }
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