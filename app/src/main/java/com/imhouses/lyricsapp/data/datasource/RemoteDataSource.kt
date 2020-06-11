package com.imhouses.lyricsapp.data.datasource

import com.imhouses.lyricsapp.domain.LyricsEntity
import com.imhouses.lyricsapp.domain.SongEntity

interface RemoteDataSource {

    suspend fun getSuggestions(query: String): List<SongEntity>

    suspend fun getLyrics(artistName: String, songTitle: String): LyricsEntity
}