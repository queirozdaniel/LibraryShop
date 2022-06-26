package com.danielqueiroz.libraryshop.domain.service

import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import com.danielqueiroz.libraryshop.domain.model.Person

interface PersonService {

    fun findById(id: Long): PersonVO
    fun findAll(): List<PersonVO>
    fun create(person: PersonVO): PersonVO
    fun update(person: PersonVO): PersonVO
    fun delete(id: Long)
}