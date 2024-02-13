package org.unizd.rma.peric.bookcase.domain.usecases.book

import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.CreateBookUseCase
import org.unizd.rma.peric.bookcase.domain.interfaces.BookRepository
import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel

class CreateBookUseCaseImpl constructor(private val bookRepository: BookRepository) :
    CreateBookUseCase {
    override suspend fun execute(book: BookRequestModel) {
        return bookRepository.createBook(book)
    }
}