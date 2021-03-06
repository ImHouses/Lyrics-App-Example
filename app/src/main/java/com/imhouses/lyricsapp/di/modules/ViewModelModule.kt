package com.imhouses.lyricsapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imhouses.lyricsapp.ui.lyrics.LyricsDetailViewModel
import com.imhouses.lyricsapp.ui.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun postListViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LyricsDetailViewModel::class)
    internal abstract fun lyricsDetailViewModel(viewModel: LyricsDetailViewModel): ViewModel

    //Add more ViewModels here
}