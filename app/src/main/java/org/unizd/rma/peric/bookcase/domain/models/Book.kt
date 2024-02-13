package org.unizd.rma.peric.bookcase.domain.models

import android.net.Uri


data class BookResponseModel(
    val id: Int,
    val title: String,
    val author: String,
    val releaseDate: String,
    val genre: String,
    val coverImage: Uri
)

data class BookRequestModel(
    val id: Int? = null,
    val title: String,
    val author: String,
    val releaseDate: String,
    val genre: String,
    val coverImage: String
)