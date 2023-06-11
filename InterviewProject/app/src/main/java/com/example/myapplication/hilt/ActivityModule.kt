package com.example.myapplication.hilt

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.BookDatabase
import com.example.myapplication.data.BookRepository
import com.example.myapplication.ui.BooksViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Singleton
    @Provides
    @Named("BooksRepository")
    fun provideBooksRepository(
        @ActivityContext context: Context
    ) = BookRepository.getIstance(BookDatabase.getDatabase(context).BookDao())


    @Singleton
    @Provides
    @Named("BooksViewModelFactory")
    fun provideBooksViewModelFactory(
        @Named("BooksRepository") booksRepository: BookRepository
    ): ViewModelProvider.Factory {
        return BooksViewModelFactory(booksRepository)
    }
}