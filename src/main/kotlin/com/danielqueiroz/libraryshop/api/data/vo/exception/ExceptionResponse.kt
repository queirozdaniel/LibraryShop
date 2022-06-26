package com.danielqueiroz.libraryshop.api.data.vo.exception

import java.time.LocalDateTime

data class ExceptionResponse(
    val timestamp: LocalDateTime,
    val message: String?,
    val details: String?
)