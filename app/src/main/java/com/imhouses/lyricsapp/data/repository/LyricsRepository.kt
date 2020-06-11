package com.imhouses.lyricsapp.data.repository

import com.imhouses.lyricsapp.data.datasource.RemoteDataSource

class LyricsRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getSuggestions(): List<String> {
        return emptyList()
    }

    suspend fun getLyrics(): String {
        return ""
    }
}