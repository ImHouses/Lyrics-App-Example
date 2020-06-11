package com.imhouses.lyricsapp.framework.remote

import com.imhouses.lyricsapp.framework.model.LyricsModel
import com.imhouses.lyricsapp.framework.model.SongModel
import com.imhouses.lyricsapp.util.DataWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface LyricsWebService {

    @GET("/v1/{artistName}/{songTitle}")
    suspend fun getLyrics(
        @Path("artistName") artistName: String,
        @Path("songTitle") songTitle: String
    ): LyricsModel

    @GET("/suggest/{query}")
    suspend fun getSuggestions(@Path("query") query: String): DataWrapper<List<SongModel>>
}