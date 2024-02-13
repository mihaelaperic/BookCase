package org.unizd.rma.peric.bookcase.domain.interfaces.usecases

interface DeleteBookUseCase {
    suspend fun execute(id: Int)
}