package org.unizd.rma.peric.bookcase.domain.interfaces.usecases

import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

interface GetBookUseCase {
    suspend fun execute(id: Int): BookResponseModel?
}