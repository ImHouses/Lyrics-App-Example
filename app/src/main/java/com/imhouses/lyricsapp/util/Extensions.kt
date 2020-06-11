package com.imhouses.lyricsapp.util

import com.imhouses.lyricsapp.domain.ResultEntity
import com.imhouses.lyricsapp.domain.error.ErrorHandler

inline suspend fun <T> tryOrHandle(errorHandler: ErrorHandler, block: ()->T): ResultEntity<T> = try {
    val result = block()
    ResultEntity.Success(result)
} catch(throwable: Throwable) {
    throwable.printStackTrace()
    ResultEntity.Error(errorHandler.getError(throwable))
}