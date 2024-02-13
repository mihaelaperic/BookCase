package org.unizd.rma.peric.bookcase.domain.usecases.book

import com.unizd.rma.Bboks.domain.interfaces.usecases.GetAllBooksUseCase
import org.unizd.rma.peric.bookcase.domain.interfaces.BookRepository
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

class GetAllBooksUseCaseImpl constructor(private val bookRepository: BookRepository) : GetAllBooksUseCase {
    override suspend fun execute(): List<BookResponseModel> {
        return bookRepository.getBooks()
    }

}