package com.danielqueiroz.libraryshop.api.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import jakarta.xml.bind.annotation.XmlRootElement
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDateTime

@JsonPropertyOrder("id", "author", "launchDate", "price", "title")
@XmlRootElement
data class BookVO(

    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,
    var author: String = "",
    var launchDate: LocalDateTime? = null,
    var price: Double = 0.0,
    var title: String = ""

) : RepresentationModel<BookVO>()