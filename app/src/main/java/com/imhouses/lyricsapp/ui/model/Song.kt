package com.imhouses.lyricsapp.ui.model

import android.os.Parcelable
import com.imhouses.lyricsapp.domain.SongEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Song(
    val songTitle: String,
    val albumTitle: String,
    val artistName: String,
    val albumThumb: String
) : Parcelable {

    companion object {
        fun fromEntity(songEntity: SongEntity): Song = Song(
            songEntity.songTitle,
            songEntity.albumTitle,
            songEntity.artistName,
            songEntity.albumThumb
        )
    }
}