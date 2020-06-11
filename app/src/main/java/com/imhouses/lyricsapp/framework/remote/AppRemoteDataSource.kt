package com.imhouses.lyricsapp.framework.remote

import com.imhouses.lyricsapp.data.datasource.RemoteDataSource
import com.imhouses.lyricsapp.domain.LyricsEntity
import com.imhouses.lyricsapp.domain.SongEntity
import javax.inject.Inject

class AppRemoteDataSource @Inject constructor(
    private val lyricsWebService: LyricsWebService
) : RemoteDataSource {

    override suspend fun getSuggestions(query: String): List<SongEntity> {
        return lyricsWebService.getSuggestions(query).data.map { songModel ->
            SongEntity(
                songTitle = songModel.title,
                albumTitle = songModel.album.title,
                artistName = songModel.artist.name,
                albumThumb = songModel.album.albumThumb
            )
        }
    }

    override suspend fun getLyrics(artistName: String, songTitle: String): LyricsEntity {
        return lyricsWebService.getLyrics(artistName, songTitle).run {
            LyricsEntity(lyrics)
        }
    }
}
