package org.unizd.rma.peric.bookcase.domain.repositories

import org.unizd.rma.peric.bookcase.data.interfaces.BookDataSource
import org.unizd.rma.peric.bookcase.domain.interfaces.BookRepository
import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

class BookRepositoryImpl constructor(private val bookDataSource: BookDataSource):
    BookRepository {
    override suspend fun getBooks(): List<BookResponseModel> {
        return bookDataSource.getAll()
    }

    override suspend fun getBook(id: Int): BookResponseModel? {
        return bookDataSource.getOne(id)
    }

    override suspend fun deleteBook(id: Int) {
        return bookDataSource.delete(id)
    }

    override suspend fun updateBook(
        id: Int,
        title: String,
        author: String,
        genre: String,
        releaseDate: String,
        coverImage: String
    ) {
        return bookDataSource.update(id, title, author, genre, releaseDate, coverImage)
    }


    override suspend fun createBook(data: BookRequestModel) {
        return bookDataSource.create(data)
    }
}