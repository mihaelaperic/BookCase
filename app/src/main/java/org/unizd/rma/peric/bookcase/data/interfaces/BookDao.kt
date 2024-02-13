package org.unizd.rma.peric.bookcase.data.interfaces

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import org.unizd.rma.peric.bookcase.data.datasources.room.entities.BookRoomEntity

@Database(entities = [BookRoomEntity::class], version = 1)
abstract class BookDatabase: RoomDatabase(){
    abstract val bookDao: BookDao

    companion object {
        const val DATABASE_NAME = "books_db"
    }
}

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    suspend fun getAll(): List<BookRoomEntity>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getById(id: Int): BookRoomEntity?

    @Query("DELETE FROM books WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("UPDATE books SET title = :name, author = :author, genre = :category, releaseDate = :date, coverImage = :imageUri WHERE id = :id")
    suspend fun update(id: Int, name: String, author: String, category: String, date: String, imageUri: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookRoomEntity)
}