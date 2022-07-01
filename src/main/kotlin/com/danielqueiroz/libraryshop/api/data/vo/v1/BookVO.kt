package com.danielqueiroz.libraryshop.api.data.vo.v1

import java.time.LocalDateTime

data class BookVO(
    var key: Long = 0,
    var author: String = "",
    var launchDate: LocalDateTime? = null,
    var price: Double = 0.0,
    var title: String = ""
)