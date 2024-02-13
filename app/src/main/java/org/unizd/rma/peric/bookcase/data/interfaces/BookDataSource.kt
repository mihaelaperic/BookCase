package org.unizd.rma.peric.bookcase.data.interfaces

import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

interface BookDataSource {
    suspend fun getAll(): List<BookResponseModel>

    suspend fun getOne(id: Int): BookResponseModel?

    suspend fun delete(id: Int)

    suspend fun update(id: Int, title:String, author: String, genre:String, releaseDate:String, coverImage:String)

    suspend fun create(data: BookRequestModel)
}