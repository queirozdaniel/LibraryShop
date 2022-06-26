package com.danielqueiroz.libraryshop.api.controller

import com.danielqueiroz.libraryshop.domain.model.Person
import com.danielqueiroz.libraryshop.domain.service.PersonService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/persons")
class PersonController(
    private val personService: PersonService
) {

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPerson(@PathVariable id: Long): Person {
        return personService.findById(id)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPersons(): List<Person> {
        return personService.findAll()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody person: Person): Person {
        return personService.create(person)
    }

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@RequestBody person: Person): Person {
        return personService.update(person)
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long): ResponseEntity<*> {
        personService.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}