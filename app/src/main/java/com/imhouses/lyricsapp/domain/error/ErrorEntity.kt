package com.imhouses.lyricsapp.domain.error

sealed class ErrorEntity {

    abstract val originalException: Throwable

    data class ServiceUnavailable(override val originalException: Throwable): ErrorEntity()

    data class Network(override val originalException: Throwable) : ErrorEntity()

    data class Unknown(override val originalException: Throwable): ErrorEntity()
}