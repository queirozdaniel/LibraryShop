package com.danielqueiroz.libraryshop.api.data.vo.v1

data class PersonVO(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)
