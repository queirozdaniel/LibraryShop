package com.danielqueiroz.libraryshop.api.controller

import com.danielqueiroz.libraryshop.api.config.util.MediaType
import com.danielqueiroz.libraryshop.api.data.vo.v1.AccountCredentialsVO
import com.danielqueiroz.libraryshop.api.openapi.AuthControllerOpenAPI
import com.danielqueiroz.libraryshop.domain.service.impl.AuthServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController : AuthControllerOpenAPI {

    @Autowired
    lateinit var authService: AuthServiceImpl

    @PostMapping(value = ["/signin"], consumes = [com.danielqueiroz.libraryshop.api.config.util.MediaType.APPLICATION_JSON, com.danielqueiroz.libraryshop.api.config.util.MediaType.APPLICATION_XML, com.danielqueiroz.libraryshop.api.config.util.MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    override fun signin(@RequestBody data: AccountCredentialsVO): ResponseEntity<*> {
        return if (data.username.isNullOrBlank() || data.password.isNullOrBlank())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request")
        else authService.signin(data)
    }

    @PutMapping(value = ["/refresh/{username}"], consumes = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    override fun refreshToken(@PathVariable username: String,
                     @RequestHeader("Authorization") refreshToken: String): ResponseEntity<*> {
        return if (refreshToken.isNullOrBlank() || username.isNullOrBlank())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request")
        else authService.refreshToken(username, refreshToken)
    }

}