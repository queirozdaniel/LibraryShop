package com.danielqueiroz.libraryshop.domain.service

import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import com.danielqueiroz.libraryshop.api.data.vo.v2.PersonVO as PersonVOV2

interface PersonService {

    fun findById(id: Long): PersonVO
    fun findAll(pageable: Pageable): Page<PersonVO>
    fun create(person: PersonVO): PersonVO
    fun createV2(person: PersonVOV2): PersonVOV2
    fun update(person: PersonVO): PersonVO
    fun disablePerson(id: Long): PersonVO
    fun delete(id: Long)
}