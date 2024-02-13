package org.unizd.rma.peric.bookcase

import android.content.Context
import androidx.room.Room
import com.unizd.rma.Bboks.domain.interfaces.usecases.GetAllBooksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.unizd.rma.peric.bookcase.data.datasources.room.RoomBookDataSource
import org.unizd.rma.peric.bookcase.data.interfaces.BookDataSource
import org.unizd.rma.peric.bookcase.data.interfaces.BookDatabase
import org.unizd.rma.peric.bookcase.domain.interfaces.BookRepository
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.CreateBookUseCase
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.DeleteBookUseCase
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.GetBookUseCase
import org.unizd.rma.peric.bookcase.domain.interfaces.usecases.UpdateBookUseCase
import org.unizd.rma.peric.bookcase.domain.repositories.BookRepositoryImpl
import org.unizd.rma.peric.bookcase.domain.usecases.book.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent:: class)
object AppModule {

    @Provides
    @Singleton
    fun providesExpenseDataSource(
        @ApplicationContext
        context: Context
    ): BookDataSource {
        return RoomBookDataSource(
            dao = Room.databaseBuilder(
                context,
                BookDatabase::class.java,
                BookDatabase.DATABASE_NAME
            ).build().bookDao
        )
    }

    @Provides
    @Singleton
    fun providesBookRepository(
        dataSource: BookDataSource
    ): BookRepository {
        return BookRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetBookUseCase(
        repository: BookRepository
    ): GetAllBooksUseCase {
        return GetAllBooksUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesDeleteBookUseCase(repository: BookRepository): DeleteBookUseCase {
        return DeleteBookUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetBookUseCase(repository: BookRepository): GetBookUseCase {
        return GetBookUseCaseImpl(repository)

    }

    @Provides
    @Singleton
    fun providesUpdateBookUseCase(repository: BookRepository): UpdateBookUseCase{
        return UpdateBookUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun providesCreateBookUseCase(
        repository: BookRepository
    ): CreateBookUseCase {
        return CreateBookUseCaseImpl(repository)
    }
}