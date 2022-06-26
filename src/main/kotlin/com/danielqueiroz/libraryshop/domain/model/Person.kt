package com.danielqueiroz.libraryshop.domain.model

data class Person(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)
