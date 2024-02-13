package org.unizd.rma.peric.bookcase.domain.usecases.book

import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.GetBookUseCase
import org.unizd.rma.peric.bookcase.domain.interfaces.BookRepository
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

class GetBookUseCaseImpl constructor(private val bookRepository: BookRepository): GetBookUseCase {
    override suspend fun execute(id: Int): BookResponseModel? {
        return bookRepository.getBook(id)
    }
}