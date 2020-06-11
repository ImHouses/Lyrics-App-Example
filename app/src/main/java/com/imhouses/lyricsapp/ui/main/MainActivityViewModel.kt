package com.imhouses.lyricsapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imhouses.lyricsapp.domain.ResultEntity
import com.imhouses.lyricsapp.domain.SongEntity
import com.imhouses.lyricsapp.interactors.GetSuggestionsInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val getSuggestionsInteractor: GetSuggestionsInteractor
) : ViewModel() {

    val suggestionsLiveData: LiveData<ResultEntity<List<SongEntity>>> = MutableLiveData()

    fun getSuggestions(query: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = getSuggestionsInteractor(query)
        withContext(Dispatchers.Main) {
            (suggestionsLiveData as MutableLiveData).value = result
        }
    }
}