package com.unizd.rma.Bboks.domain.interfaces.usecases

import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

interface GetAllBooksUseCase {
    suspend fun execute(): List<BookResponseModel>
}