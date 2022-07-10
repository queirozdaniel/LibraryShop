package com.danielqueiroz.libraryshop.infra.security

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT
import com.danielqueiroz.libraryshop.api.data.vo.v1.TokenVO
import com.danielqueiroz.libraryshop.api.exception.InvalidJwtAuthenticationException
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Base64
import java.util.Date

@Service
class JwtTokenProvider {

    @Value("\${security.jwt.token.secret-key}")
    private var secretKey = "limonad@"

    @Value("\${security.jwt.token.expire-length}")
    private var validityInMilliseconds: Long = 3600000

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var algorithm: Algorithm

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }

    fun createAccessToken(username: String, roles: List<String?>) : TokenVO {
        val created = Date()
        val expiration = Date(created.time + validityInMilliseconds)
        val accessToken = getAccessToken(username, roles, created, expiration)
        val refreshToken = getRefreshToken(username, roles, created)

        return TokenVO(
            username,
            authenticated = true,
            created, expiration, accessToken, refreshToken
        )
    }

    fun refreshToken(refreshToken: String) : TokenVO {
        var token: String = ""
        if(refreshToken.contains("Bearer ")) token = refreshToken.substring("Bearer ".length)

        val verifier: JWTVerifier = JWT.require(algorithm).build()
        val decodedJWT: DecodedJWT = verifier.verify(token)

        val username = decodedJWT.subject
        val roles: List<String>  = decodedJWT.getClaim("roles").asList(String::class.java)

        return createAccessToken(username, roles)
    }

    private fun getRefreshToken(username: String, roles: List<String?>, created: Date): String? {
        val validityRefreshToken = Date(created.time + validityInMilliseconds * 3)
        return JWT.create()
            .withClaim("roles", roles)
            .withExpiresAt(validityRefreshToken)
            .withSubject(username)
            .sign(algorithm)
            .trim()
    }

    private fun getAccessToken(username: String, roles: List<String?>, now: Date, validity: Date): String? {
        val issuerUrl: String = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
        return JWT.create()
            .withClaim("roles", roles)
            .withIssuedAt(now)
            .withExpiresAt(validity)
            .withSubject(username)
            .withIssuer(issuerUrl)
            .sign(algorithm)
            .trim()
    }

    fun getAuthentication(token: String) : Authentication {
        val decodeJWT: DecodedJWT = decodeToken(token)
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(decodeJWT.subject)
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun decodeToken(token: String): DecodedJWT {
        val algorithm = Algorithm.HMAC256(secretKey.toByteArray())
        val verifier: JWTVerifier = JWT.require(algorithm).build()

        return verifier.verify(token)
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization")

        return if(!bearerToken.isNullOrBlank() && bearerToken.startsWith("Bearer")) {
            bearerToken.substring("Bearer ".length)
        } else null
    }

    fun validateToken(token: String) : Boolean{
        try {
            val decodedJWT = decodeToken(token)

            if (decodedJWT.expiresAt.before(Date())) return false
            return true
        } catch (ex: Exception) {
            throw InvalidJwtAuthenticationException("Invalid or expired token!")
        }
    }

}