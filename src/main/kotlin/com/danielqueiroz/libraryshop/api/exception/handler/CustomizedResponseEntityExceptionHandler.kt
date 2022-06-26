package com.danielqueiroz.libraryshop.api.exception.handler

import com.danielqueiroz.libraryshop.api.dto.exception.ExceptionResponse
import com.danielqueiroz.libraryshop.api.exception.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@RestControllerAdvice
class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        return ResponseEntity<ExceptionResponse>(
            ExceptionResponse(LocalDateTime.now(), ex.message, request.getDescription(false)),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleNotFoundException(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        return ResponseEntity<ExceptionResponse>(
            ExceptionResponse(LocalDateTime.now(), ex.message, request.getDescription(false)),
            HttpStatus.NOT_FOUND
        )
    }

}