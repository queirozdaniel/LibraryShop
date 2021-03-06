package com.danielqueiroz.libraryshop.api.controller

import com.danielqueiroz.libraryshop.api.config.util.MediaType
import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import com.danielqueiroz.libraryshop.api.openapi.PersonControllerOpenAPI
import com.danielqueiroz.libraryshop.api.data.vo.v2.PersonVO as PersonVOV2
import com.danielqueiroz.libraryshop.domain.service.PersonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/persons/")
class PersonController(
    private val personService: PersonService
) : PersonControllerOpenAPI {

    @GetMapping("/v1/{id}", produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    override fun getPerson(@PathVariable id: Long): PersonVO {
        return personService.findById(id)
    }

    @GetMapping("/v1",produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    override fun getPersons(@RequestParam(value = "page", defaultValue = "0") page: Int,
                            @RequestParam(value = "limit", defaultValue = "12") limit: Int,
                            @RequestParam(value = "direction", defaultValue = "asc") direction: String): ResponseEntity<PagedModel<EntityModel<PersonVO>>> {

        val sortDirection: Sort.Direction = if ("desc".equals(direction, ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC
        val pageable: Pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"))
        return ResponseEntity.ok(personService.findAll(pageable))
    }

    @PostMapping("/v1",produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML], consumes = [MediaType.APPLICATION_JSON])
    override fun createPerson(@RequestBody person: PersonVO): PersonVO {
        return personService.create(person)
    }

    @PostMapping("/v2",produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML], consumes = [MediaType.APPLICATION_JSON])
    override fun createPersonV2(@RequestBody person: PersonVOV2): PersonVOV2 {
        return personService.createV2(person)
    }

    @PutMapping("/v1/{id}",produces = [MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML], consumes = [MediaType.APPLICATION_JSON])
    override fun updatePerson(@RequestBody person: PersonVO, @PathVariable id: Long): PersonVO {
        person.idValue = id
        return personService.update(person)
    }


    @PatchMapping("/v1/{id}")
    override fun disablePerson(@PathVariable id: Long): PersonVO {
        return personService.disablePerson(id)
    }

    @DeleteMapping("/v1/{id}")
    override fun deletePerson(@PathVariable id: Long): ResponseEntity<*> {
        personService.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }

}