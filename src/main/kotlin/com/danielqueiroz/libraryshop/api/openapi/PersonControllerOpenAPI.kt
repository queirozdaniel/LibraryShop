package com.danielqueiroz.libraryshop.api.openapi

import com.danielqueiroz.libraryshop.api.data.vo.exception.ExceptionResponse
import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
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
    fun getPersons(@RequestParam(value = "page", defaultValue = "0") page: Int,
                   @RequestParam(value = "limit", defaultValue = "12") limit: Int,
                   @RequestParam(value = "direction", defaultValue = "asc") direction: String
                   ): ResponseEntity<Page<PersonVO>>

    @Operation(
        summary = "Adds a new Person", description = "Adds a new Person",
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
        summary = "Adds a new Person", description = "Adds a new Person",
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
        summary = "Disable person by id", description = "Disable person by id",
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
    fun disablePerson(@PathVariable id: Long): PersonVO

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