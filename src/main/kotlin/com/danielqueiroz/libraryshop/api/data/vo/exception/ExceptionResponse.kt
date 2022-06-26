package com.danielqueiroz.libraryshop.api.dto.exception

import java.time.LocalDateTime

data class ExceptionResponse(
    val timestamp: LocalDateTime,
    val message: String?,
    val details: String?
)