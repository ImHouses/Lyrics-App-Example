package com.imhouses.lyricsapp

import android.app.Application
import com.imhouses.lyricsapp.di.ApplicationComponent
import com.imhouses.lyricsapp.di.DaggerApplicationComponent
import com.imhouses.lyricsapp.di.modules.AppModule

class LyricsApp : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    val appComponent get () = applicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(BuildConfig.API_BASE_URL))
            .build()
    }
}