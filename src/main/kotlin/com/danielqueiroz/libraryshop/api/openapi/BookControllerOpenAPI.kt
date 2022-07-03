package com.danielqueiroz.libraryshop.api.openapi

import com.danielqueiroz.libraryshop.api.data.vo.exception.ExceptionResponse
import com.danielqueiroz.libraryshop.api.data.vo.v1.BookVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Book", description = "Endpoints to managing Book")
interface BookControllerOpenAPI {

    @Operation(
        summary = "Find Book by Id", description = "Find Book by Id",
        tags = ["Book"],
        responses = [
            ApiResponse(description = "Success", responseCode = "200",content = [
                Content(schema = Schema(implementation = BookVO::class))
            ]),
            ApiResponse(description = "No Content", responseCode = "204", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
            ApiResponse(description = "Not Found", responseCode = "404",content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
        ]
    )
    fun getBookById(@PathVariable id: Long): BookVO

    @Operation(
        summary = "Find All Books", description = "Find All Books",
        tags = ["Book"],
        responses = [
            ApiResponse(
                description = "Success", responseCode = "200",
                content = [
                    Content(array =  ArraySchema(schema = Schema(implementation = BookVO::class)))
                ]
            ),
            ApiResponse(
                description = "No Content", responseCode = "204",
                content = [
                    Content(schema = Schema(implementation = Unit::class))
                ]
            ),
            ApiResponse(
                description = "Bad Request", responseCode = "400",
                content = [
                    Content(schema = Schema(implementation = ExceptionResponse::class))
                ]
            ),
            ApiResponse(
                description = "Unauthorized", responseCode = "401",
                content = [
                    Content(schema = Schema(implementation = ExceptionResponse::class))
                ]
            ),
            ApiResponse(
                description = "Not Found", responseCode = "404",content = [
                    Content(schema = Schema(implementation = ExceptionResponse::class))
                ]
            ),
        ]
    )
    fun getAllBooks(): List<BookVO>

    @Operation(
        summary = "Adds a new Book", description = "Adds a new Book",
        tags = ["Book"],
        responses = [
            ApiResponse(description = "Created", responseCode = "201",content = [
                Content(schema = Schema(implementation = BookVO::class))
            ]),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
            ApiResponse(description = "Not Found", responseCode = "404",content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
        ]
    )
    fun createBook(@RequestBody person: BookVO): BookVO

    @Operation(
        summary = "Updates a book information", description = "Updates a book information",
        tags = ["Book"],
        responses = [
            ApiResponse(description = "Success", responseCode = "200",content = [
                Content(schema = Schema(implementation = BookVO::class))
            ]),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
            ApiResponse(description = "Not Found", responseCode = "404",content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
        ]
    )
    fun updateBook(@RequestBody person: BookVO, @PathVariable id: Long): BookVO

    @Operation(
        summary = "Deletes a Book", description = "Deletes a Book",
        tags = ["Book"],
        responses = [
            ApiResponse(description = "No Content", responseCode = "204", content = [
                Content(schema = Schema(implementation = Unit::class))
            ]),
            ApiResponse(description = "Bad Request", responseCode = "400", content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
            ApiResponse(description = "Unauthorized", responseCode = "401", content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
            ApiResponse(description = "Not Found", responseCode = "404",content = [
                Content(schema = Schema(implementation = ExceptionResponse::class))
            ]),
        ]
    )
    fun deleteBook(@PathVariable id: Long): ResponseEntity<*>

}