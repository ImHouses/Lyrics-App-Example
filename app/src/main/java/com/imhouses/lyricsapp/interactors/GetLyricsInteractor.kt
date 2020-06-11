package com.imhouses.lyricsapp.interactors

import com.imhouses.lyricsapp.data.datasource.RemoteDataSource
import com.imhouses.lyricsapp.domain.LyricsEntity
import com.imhouses.lyricsapp.domain.ResultEntity
import com.imhouses.lyricsapp.domain.error.ErrorHandler
import com.imhouses.lyricsapp.util.tryOrHandle
import javax.inject.Inject

class GetLyricsInteractor @Inject constructor (
    private val remoteDataSource: RemoteDataSource,
    private val errorHandler: ErrorHandler
) {
    suspend operator fun invoke(artistName: String, songTitle: String): ResultEntity<LyricsEntity> = tryOrHandle(errorHandler) {
        remoteDataSource.getLyrics(artistName, songTitle)
    }
}