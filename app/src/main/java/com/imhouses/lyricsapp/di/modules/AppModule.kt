package com.imhouses.lyricsapp.di.modules

import com.google.gson.Gson
import com.imhouses.lyricsapp.domain.error.ErrorHandler
import com.imhouses.lyricsapp.framework.error.AppErrorHandler
import com.imhouses.lyricsapp.framework.remote.LyricsWebService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideLyricsWebService(retrofit: Retrofit): LyricsWebService {
        return retrofit.create(LyricsWebService::class.java)
    }

    @Singleton
    @Provides
    fun provideErrorHandler(): ErrorHandler = AppErrorHandler()
}