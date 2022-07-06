package com.danielqueiroz.libraryshop.api.controller

import com.danielqueiroz.libraryshop.api.data.vo.v1.AccountCredentialsVO
import com.danielqueiroz.libraryshop.api.openapi.AuthControllerOpenAPI
import com.danielqueiroz.libraryshop.domain.service.impl.AuthServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController : AuthControllerOpenAPI {

    @Autowired
    lateinit var authService: AuthServiceImpl

    @PostMapping(value = ["/signin"])
    override fun signin(@RequestBody data: AccountCredentialsVO): ResponseEntity<*> {
        return if (data.username.isNullOrBlank() || data.password.isNullOrBlank())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request")
            else authService.signin(data)
    }

}