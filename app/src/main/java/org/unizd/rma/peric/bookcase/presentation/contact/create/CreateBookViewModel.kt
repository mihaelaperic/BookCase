package org.unizd.rma.peric.bookcase.presentation.contact.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.CreateBookUseCase
import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CreateBookViewModel @Inject constructor(
    private val createbookUseCase: CreateBookUseCase
) : ViewModel() {

    private val _errorMessage = mutableStateOf("")
    private val _title = mutableStateOf("")
    private val _author = mutableStateOf("")
    private val _genre = mutableStateOf("")
    private val _releaseDate = mutableStateOf("")
    private val _coverImage = mutableStateOf("")


    val name : String
        get() = _title.value

    val author : String
        get() = _author.value

    val genre : String
        get() = _genre.value

    val releaseDate : String
        get() = _releaseDate.value

    val coverImage : String
        get() = _coverImage.value

    val errorMessage : String
        get() = _errorMessage.value

    fun onNameChange(newName: String) {
        _title.value = newName
    }

    fun onAuthorChange(newAuthor: String){
        _author.value = newAuthor
    }

    fun onGenreChange(newGenre: String) {
        _genre.value = newGenre
    }

    fun onDateChange(newDate: String) {
        _releaseDate.value = newDate
    }

    fun onImageUriChange(newImageUri: String) {
        _coverImage.value = newImageUri
    }


    fun resetFields() {
        _title.value = ""
        _author.value = ""
        _genre.value = ""
        _releaseDate.value = ""
        _coverImage.value = ""
    }


    suspend fun createBook() {
        try {
            createbookUseCase.execute(BookRequestModel(null, _title.value, _author.value, _genre.value, _releaseDate.value, _coverImage.value))
            resetFields()
        } catch (e: Exception) {
            _errorMessage.value = "Error ${e.message}"
        }
    }

}

private fun formatDate(date: Date): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}
