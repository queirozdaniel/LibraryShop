package com.danielqueiroz.libraryshop.domain.service

import com.danielqueiroz.libraryshop.domain.model.Person

interface PersonService {
    fun findById(id: Long): Person
}