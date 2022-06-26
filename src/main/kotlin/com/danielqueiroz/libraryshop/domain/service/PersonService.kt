package com.danielqueiroz.libraryshop.domain.service

import com.danielqueiroz.libraryshop.domain.model.Person

interface PersonService {

    fun findById(id: Long): Person
    fun findAll(): List<Person>
    fun create(person: Person): Person
    fun update(person: Person): Person
    fun delete(id: Long)
}