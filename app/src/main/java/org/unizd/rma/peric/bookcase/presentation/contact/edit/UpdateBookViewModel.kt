package org.unizd.rma.peric.bookcase.presentation.contact.edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.UpdateBookUseCase
import javax.inject.Inject


@HiltViewModel
class UpdateBookViewModel @Inject constructor(
    private val updateBookUseCase: UpdateBookUseCase
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


    suspend fun updateBook(id: String) {
        val ide = id.toInt()
        try {
            updateBookUseCase.execute(ide, _title.value, _author.value, _genre.value, _releaseDate.value, _coverImage.value)
        } catch (e: Exception) {
            _errorMessage.value = "Error ${e.message}"
        }
    }

}
