package com.danielqueiroz.libraryshop.api.openapi

import com.danielqueiroz.libraryshop.api.data.vo.exception.ExceptionResponse
import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import com.danielqueiroz.libraryshop.api.data.vo.v2.PersonVO as PersonVOV2
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "People", description = "Endpoints to managing People")
interface PersonControllerOpenAPI {

    @Operation(
        summary = "Find People by Id", description = "Find People by Id",
        tags = ["People"],
        responses = [
            ApiResponse(description = "Success", responseCode = "200",content = [
                Content(schema = Schema(implementation = PersonVO::class))
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
    fun getPerson(@PathVariable id: Long): PersonVO

    @Operation(
        summary = "Find All People", description = "Find All People",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success", responseCode = "200",
                content = [
                    Content(array =  ArraySchema(schema = Schema(implementation = PersonVO::class)))
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
    fun getPersons(): List<PersonVO>

    @Operation(
        summary = "Adds a new Person Id", description = "Adds a new Person Id",
        tags = ["People"],
        responses = [
            ApiResponse(description = "Created", responseCode = "201",content = [
                Content(schema = Schema(implementation = PersonVO::class))
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
    fun createPerson(@RequestBody person: PersonVO): PersonVO

    @Operation(
        summary = "Adds a new Person Id", description = "Adds a new Person Id",
        tags = ["People"],
        responses = [
            ApiResponse(description = "Created", responseCode = "201",content = [
                Content(schema = Schema(implementation = PersonVOV2::class))
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
    fun createPersonV2(@RequestBody person: PersonVOV2): PersonVOV2

    @Operation(
        summary = "Updates a person information", description = "Updates a person information",
        tags = ["People"],
        responses = [
            ApiResponse(description = "Success", responseCode = "200",content = [
                Content(schema = Schema(implementation = PersonVO::class))
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
    fun updatePerson(@RequestBody person: PersonVO, @PathVariable id: Long): PersonVO

    @Operation(
        summary = "Deletes a Person", description = "Deletes a Person",
        tags = ["People"],
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
    fun deletePerson(@PathVariable id: Long): ResponseEntity<*>

}