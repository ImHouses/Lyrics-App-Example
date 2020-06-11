package com.imhouses.lyricsapp.framework.model

data class SongModel(
    val title: String,
    val artist: ArtistModel,
    val album: AlbumModel
)