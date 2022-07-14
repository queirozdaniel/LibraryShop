package com.danielqueiroz.libraryshop.api.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import jakarta.xml.bind.annotation.XmlRootElement
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "firstName", "lastName", "address", "gender")
@XmlRootElement
data class PersonVO(
    @Mapping("id")
    @field:JsonProperty("id")
    var idValue: Long? = null,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
    var enabled: Boolean = true
) : RepresentationModel<PersonVO>()
