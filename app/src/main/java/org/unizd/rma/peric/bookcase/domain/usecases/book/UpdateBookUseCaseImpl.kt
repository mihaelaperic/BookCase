package org.unizd.rma.peric.bookcase.domain.usecases.book

import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.UpdateBookUseCase
import org.unizd.rma.peric.bookcase.domain.interfaces.BookRepository
import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel

class UpdateBookUseCaseImpl constructor(private val bookRepository: BookRepository):
    UpdateBookUseCase {
    override suspend fun execute(
        id: Int,
        title: String,
        author: String,
        genre: String,
        releaseDate: String,
        coverImage: String
    ) {
        return bookRepository.updateBook(id, title, author, genre, releaseDate, coverImage)
    }

}