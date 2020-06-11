package com.imhouses.lyricsapp.interactors

import com.imhouses.lyricsapp.data.datasource.RemoteDataSource
import com.imhouses.lyricsapp.domain.ResultEntity
import com.imhouses.lyricsapp.domain.SongEntity
import com.imhouses.lyricsapp.domain.error.ErrorHandler
import com.imhouses.lyricsapp.util.tryOrHandle
import javax.inject.Inject

class GetSuggestionsInteractor @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val errorHandler: ErrorHandler
) {
    suspend operator fun invoke(query: String): ResultEntity<List<SongEntity>> = tryOrHandle(errorHandler) {
        remoteDataSource.getSuggestions(query)
    }
}