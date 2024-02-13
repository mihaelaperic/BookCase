package org.unizd.rma.peric.bookcase.presentation.contact.details

import android.net.Uri
import android.view.View
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.GetBookUseCase
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel
import javax.inject.Inject

@HiltViewModel
class DetailsBookViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase
) : ViewModel() {

    var book: BookResponseModel? = BookResponseModel(
        id = 1,
        title = "",
        author = "",
        genre = "",
        releaseDate = "",
        coverImage = Uri.parse("")
    )


    suspend fun loadBookDetails(id: String) {
        try {
            book = getBookUseCase.execute(id.toInt())
        } catch (e: Exception) {
            book = null
        }
    }

}