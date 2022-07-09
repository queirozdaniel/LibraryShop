package com.danielqueiroz.libraryshop.api.data.vo.v1

import jakarta.xml.bind.annotation.XmlRootElement
import java.util.Date

@XmlRootElement
data class TokenVO(
    val username: String? = null,
    val authenticated: Boolean? = null,
    val created: Date? = null,
    val expiration: Date? = null,
    val accessToken: String? = null,
    val refreshToken: String? = null
)