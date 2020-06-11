package com.imhouses.lyricsapp.di.modules

import com.imhouses.lyricsapp.data.datasource.RemoteDataSource
import com.imhouses.lyricsapp.framework.remote.AppRemoteDataSource
import com.imhouses.lyricsapp.framework.remote.LyricsWebService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(lyricsWebService: LyricsWebService): RemoteDataSource =
        AppRemoteDataSource(lyricsWebService)
}