package com.imhouses.lyricsapp.framework.error

import com.imhouses.lyricsapp.domain.error.ErrorEntity
import com.imhouses.lyricsapp.domain.error.ErrorHandler
import retrofit2.HttpException
import java.io.IOException

class AppErrorHandler : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity = when(throwable) {
        is HttpException -> ErrorEntity.Network(throwable)
        is IOException -> ErrorEntity.Network(throwable)
        else -> ErrorEntity.Unknown(throwable)
    }
}