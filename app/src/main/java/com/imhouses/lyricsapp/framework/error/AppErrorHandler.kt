package com.imhouses.lyricsapp.framework.error

import com.imhouses.lyricsapp.domain.error.ErrorEntity
import com.imhouses.lyricsapp.domain.error.ErrorHandler
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

class AppErrorHandler : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity = when {
        throwable is SocketTimeoutException -> ErrorEntity.ServiceUnavailable(throwable)
        throwable is HttpException && throwable.code() == 404 -> ErrorEntity.ServiceUnavailable(throwable)
        throwable is HttpException -> ErrorEntity.Network(throwable)
        throwable is IOException -> ErrorEntity.Network(throwable)
        else -> ErrorEntity.Unknown(throwable)
    }
}