package com.danielqueiroz.libraryshop.api.mapper.custom

import com.danielqueiroz.libraryshop.api.data.vo.v2.PersonVO
import com.danielqueiroz.libraryshop.domain.model.Person
import org.springframework.stereotype.Component
import java.util.*

@Component
class PersonMapper {

    fun mapEntityToVO(person: Person): PersonVO {
        val vo = PersonVO()
        vo.id = person.id
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.address = person.address
        vo.gender = person.gender
        vo.birthDate = Date()
        return vo
    }

    fun mapVOToEntity(person: PersonVO): Person {
        val entity = Person()
        entity.id = person.id
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        return entity
    }

}