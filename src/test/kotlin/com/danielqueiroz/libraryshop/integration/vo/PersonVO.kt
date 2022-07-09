package com.danielqueiroz.libraryshop.integration.vo

import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement
data class PersonVO(
    var id: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
)