package com.danielqueiroz.libraryshop.api.exception

import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class InvalidJwtAuthenticationException(exception: String) : AuthenticationException(exception) {
}