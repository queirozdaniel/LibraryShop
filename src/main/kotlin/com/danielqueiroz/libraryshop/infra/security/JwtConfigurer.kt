package com.danielqueiroz.libraryshop.infra.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtConfigurer(
    @field:Autowired private val tokenProvider: JwtTokenProvider
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {

    override fun configure(builder: HttpSecurity?) {
        val customFilter = JwtTokenFilter(tokenProvider)
        builder?.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

}