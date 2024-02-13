package org.unizd.rma.peric.bookcase.domain.interfaces

import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

interface BookRepository {
    suspend fun getBooks(): List<BookResponseModel>
    suspend fun getBook(id: Int): BookResponseModel?
    suspend fun deleteBook(id: Int)
    suspend fun updateBook(id: Int, title:String, author:String, genre:String, releaseDate:String, coverImage:String)
    suspend fun createBook(data: BookRequestModel)
}