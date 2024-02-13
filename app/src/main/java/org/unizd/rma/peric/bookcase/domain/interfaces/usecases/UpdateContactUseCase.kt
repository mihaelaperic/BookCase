package org.unizd.rma.peric.bookcase.domain.interfaces.usecases


interface UpdateBookUseCase {
    suspend fun execute(id: Int, title:String, author:String, genre:String, releaseDate:String, coverImage:String)
}