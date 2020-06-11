package com.imhouses.lyricsapp.di

import com.imhouses.lyricsapp.di.modules.AppModule
import com.imhouses.lyricsapp.di.modules.DataSourceModule
import com.imhouses.lyricsapp.di.modules.ViewModelModule
import com.imhouses.lyricsapp.framework.remote.LyricsWebService
import com.imhouses.lyricsapp.ui.lyrics.LyricsDetailActivity
import com.imhouses.lyricsapp.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, DataSourceModule::class, ViewModelModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(lyricsDetailActivity: LyricsDetailActivity)

    fun exposeLyricsWebService(): LyricsWebService
}