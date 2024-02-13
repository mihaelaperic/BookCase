package org.unizd.rma.peric.bookcase.presentation.contact.list

import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.unizd.rma.Bboks.domain.interfaces.usecases.GetAllBooksUseCase
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.DeleteBookUseCase
import javax.inject.Inject


data class BookListResponseModel(
    val id: String,
    val title: String,
    val author: String,
    val genre: String,
    val releaseDate: String,
    val coverImage: Uri
)
fun BookResponseModel.tobookListResponseModel()
    : BookListResponseModel {
    return BookListResponseModel(
            id = id.toString(),
            title = title,
            author = author,
            genre = genre,
            releaseDate = releaseDate,
            coverImage =  coverImage
        )
    }

@HiltViewModel
class ListBooksViewModel @Inject constructor(
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val deleteBookUseCase: DeleteBookUseCase
) : ViewModel() {

    private val _errorMessage = mutableStateOf("")
    private val _books = mutableStateListOf<BookListResponseModel>()

    val errorMessage : String
        get() = _errorMessage.value

    val books: List<BookListResponseModel>
        get() = _books.toList()

    suspend fun getBooks() {
        try {
            _books.clear()
            val list = getAllBooksUseCase.execute()
            _books.addAll(list.map {
                it.tobookListResponseModel()
            })
        }catch (e: Exception) {
            _errorMessage.value = "Greška ${e.message}"
        }
    }


    suspend fun deleteBookById(id: String) {
        val ide = id.toInt()
        deleteBookUseCase.execute(ide)
        getBooks()


    }
}