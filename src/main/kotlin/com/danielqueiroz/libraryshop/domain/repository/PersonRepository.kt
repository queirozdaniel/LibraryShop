package com.danielqueiroz.libraryshop.domain.repository

import com.danielqueiroz.libraryshop.domain.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long?> {

}