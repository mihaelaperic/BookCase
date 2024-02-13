package org.unizd.rma.peric.bookcase.domain.usecases.book

import org.unizd.rma.peric.bookcase.domain.interfaces.BookRepository
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.DeleteBookUseCase

class DeleteBookUseCaseImpl constructor(private val bookRepository: BookRepository) :
    DeleteBookUseCase {
    override suspend fun execute(id: Int) {
        return bookRepository.deleteBook(id)
    }
}