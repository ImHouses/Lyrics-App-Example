package com.imhouses.lyricsapp.ui.lyrics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imhouses.lyricsapp.domain.LyricsEntity
import com.imhouses.lyricsapp.domain.ResultEntity
import com.imhouses.lyricsapp.interactors.GetLyricsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LyricsDetailViewModel @Inject constructor(
    private val getLyricsInteractor: GetLyricsInteractor
) : ViewModel() {

    val lyricsLiveData: LiveData<ResultEntity<LyricsEntity>> = MutableLiveData()

    fun getLyrics(artistName: String, songTitle: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = getLyricsInteractor(artistName, songTitle)
        withContext(Dispatchers.Main) {
            (lyricsLiveData as MutableLiveData).value = result
        }
    }
}