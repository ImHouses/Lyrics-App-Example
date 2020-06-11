package com.imhouses.lyricsapp.domain

import com.imhouses.lyricsapp.domain.error.ErrorEntity

sealed class ResultEntity<T> {

    data class Success<T>(val data: T) : ResultEntity<T>()

    data class Error<T>(val errorEntity: ErrorEntity) : ResultEntity<T>()
}