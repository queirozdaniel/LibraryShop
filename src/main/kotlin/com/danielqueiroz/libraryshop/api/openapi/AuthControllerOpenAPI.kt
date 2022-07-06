package com.danielqueiroz.libraryshop.api.openapi

import com.danielqueiroz.libraryshop.api.data.vo.v1.AccountCredentialsVO
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "Authentication Endpoint")
interface AuthControllerOpenAPI {

    @Operation(summary = "Authenticates an user and return a token")
    fun signin(@RequestBody data: AccountCredentialsVO): ResponseEntity<*>

}