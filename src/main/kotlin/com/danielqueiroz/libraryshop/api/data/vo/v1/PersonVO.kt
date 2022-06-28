package com.danielqueiroz.libraryshop.api.data.vo.v1

import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

data class PersonVO(
    @Mapping("id")
    var idValue: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = ""
) : RepresentationModel<PersonVO>()
