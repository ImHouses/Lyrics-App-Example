package com.imhouses.lyricsapp.framework.model

import com.google.gson.annotations.SerializedName

data class AlbumModel(
    val title: String,
    @SerializedName("cover_big") val albumThumb: String
)