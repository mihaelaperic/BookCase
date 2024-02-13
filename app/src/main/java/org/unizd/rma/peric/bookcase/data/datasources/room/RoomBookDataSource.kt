package org.unizd.rma.peric.bookcase.data.datasources.room

import org.unizd.rma.peric.bookcase.data.datasources.room.entities.tobookResponseModel
import org.unizd.rma.peric.bookcase.data.datasources.room.entities.tobookRoomEntity
import org.unizd.rma.peric.bookcase.data.interfaces.BookDao
import org.unizd.rma.peric.bookcase.data.interfaces.BookDataSource
import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel

class RoomBookDataSource constructor(private val dao: BookDao) : BookDataSource {
    override suspend fun getAll(): List<BookResponseModel> {
        return dao.getAll().toList().map {
            it.tobookResponseModel()
        }
    }

    override suspend fun getOne(id: Int): BookResponseModel? {
        val entity = dao.getById(id)
        if (entity != null) {
            return entity.tobookResponseModel()
        }
        return null
    }

    override suspend fun delete(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun update(
        id: Int,
        title: String,
        author: String,
        genre: String,
        releaseDate: String,
        coverImage: String
    ) {
        dao.update(id, title, author, genre, releaseDate, coverImage)
    }


    override suspend fun create(data: BookRequestModel) {
        dao.insert(data.tobookRoomEntity())
    }

}