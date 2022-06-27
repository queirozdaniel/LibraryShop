package com.danielqueiroz.libraryshop.api.controller

import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import com.danielqueiroz.libraryshop.api.data.vo.v2.PersonVO as PersonVOV2
import com.danielqueiroz.libraryshop.domain.service.PersonService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/persons")
class PersonController(
    private val personService: PersonService
) {

    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, "application/x-yaml"])
    fun getPerson(@PathVariable id: Long): PersonVO {
        return personService.findById(id)
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE])
    fun getPersons(): List<PersonVO> {
        return personService.findAll()
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody person: PersonVO): PersonVO {
        return personService.create(person)
    }

    @PostMapping("/v2",produces = [MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPersonV2(@RequestBody person: PersonVOV2): PersonVOV2 {
        return personService.createV2(person)
    }

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@RequestBody person: PersonVO): PersonVO {
        return personService.update(person)
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long): ResponseEntity<*> {
        personService.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}