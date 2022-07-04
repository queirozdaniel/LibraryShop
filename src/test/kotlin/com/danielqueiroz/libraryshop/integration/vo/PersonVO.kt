package com.danielqueiroz.libraryshop.integration.vo

data class PersonVO(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)