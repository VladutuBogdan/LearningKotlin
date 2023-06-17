package com.example.myapplication.hilt

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.ApiInterface
import com.example.myapplication.data.BookRepository
import com.example.myapplication.data.BooksDatabaseWrapper
import com.example.myapplication.ui.BooksViewModelFactory
import com.example.myapplication.utils.Utils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {
    @Provides
    fun provideBooksRepository(
        booksDatabaseWrapper: BooksDatabaseWrapper,
        apiService: ApiInterface
    ): BookRepository = BookRepository(booksDatabaseWrapper, apiService)

    @Provides
    fun provideBooksViewModelFactory(
        booksRepository: BookRepository
    ): ViewModelProvider.Factory {
        return BooksViewModelFactory(booksRepository)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    // Provide bindings relevant to SingletonComponent
    @Singleton
    @Provides
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonFactory)
            .baseUrl(Utils.BASE_URL)
            .build()
    }
}