package org.unizd.rma.peric.bookcase.data.datasources.room.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.unizd.rma.peric.bookcase.domain.models.BookRequestModel
import org.unizd.rma.peric.bookcase.domain.models.BookResponseModel


@Entity(tableName = "books")
data class BookRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val author: String,
    val releaseDate: String,
    val genre: String,
    val coverImage: String

)

fun BookRoomEntity.tobookResponseModel(): BookResponseModel {
    return BookResponseModel(
        id = id!!,
        title = title,
        author = author,
        genre = genre,
        releaseDate = releaseDate,
        coverImage = Uri.parse(coverImage)
    )
}

fun BookRequestModel.tobookRoomEntity(): BookRoomEntity {
    return BookRoomEntity(
        id = id,
        title = title,
        author = author,
        genre = genre,
        releaseDate = releaseDate,
        coverImage = coverImage
    )
}