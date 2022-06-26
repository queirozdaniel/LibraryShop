package com.danielqueiroz.libraryshop.api.controller

import com.danielqueiroz.libraryshop.domain.model.Person
import com.danielqueiroz.libraryshop.domain.service.PersonService
import org.springframework.http.MediaType
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

    @GetMapping
    fun getPersons(): List<Person> {
        return personService.findAll()
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody person: Person): Person {
        return personService.create(person)
    }

    @PutMapping(value = ["/{id}"],consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@RequestBody person: Person): Person {
        return personService.update(person)
    }

    @DeleteMapping(value = ["/{id}"],consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePerson(@PathVariable id: Long) {
        personService.delete(id)
    }

}