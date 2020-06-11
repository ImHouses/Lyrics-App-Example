package com.imhouses.lyricsapp.domain.error

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity
}