package org.unizd.rma.peric.bookcase.domain.interfaces.usecases

import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel

interface CreateBookUseCase {
    suspend fun execute(book: BookRequestModel)
}