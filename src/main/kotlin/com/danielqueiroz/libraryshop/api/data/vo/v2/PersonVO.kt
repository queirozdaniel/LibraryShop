package com.danielqueiroz.libraryshop.api.data.vo.v2

import java.util.*

data class PersonVO(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
    var birthDate: Date? = null
)
