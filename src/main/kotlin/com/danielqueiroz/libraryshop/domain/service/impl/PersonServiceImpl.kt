package com.danielqueiroz.libraryshop.domain.service.impl

import com.danielqueiroz.libraryshop.api.exception.ResourceNotFoundException
import com.danielqueiroz.libraryshop.domain.model.Person
import com.danielqueiroz.libraryshop.domain.repository.PersonRepository
import com.danielqueiroz.libraryshop.domain.service.PersonService
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServiceImpl(
    private val repository: PersonRepository
) : PersonService {

    private val logger = Logger.getLogger(PersonServiceImpl::class.java.name)

    override fun findById(id: Long): Person {
        logger.info("Finding person with id[$id]")
        return repository.findById(id).orElseThrow {
            ResourceNotFoundException("No records found for this id: $id")
        }
    }

    override fun findAll(): List<Person> {
        logger.info("Finding all persons")
        return repository.findAll()
    }

    override fun create(person: Person): Person {
        logger.info("Create a person with name ${person.firstName} ${person.lastName}")
        return repository.save(person)
    }

    override fun update(person: Person): Person {
        logger.info("Update infos for person with id[${person.id}]")
        val entity = repository.findById(person.id!!).orElseThrow {
            ResourceNotFoundException("No records found for this id: ${person.id}")
        }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return repository.save(entity)
    }

    override fun delete(id: Long) {
        logger.info("Delete person with id[$id]")
        val entity = repository.findById(id).orElseThrow {
            ResourceNotFoundException("No records found for this id: $id")
        }
        repository.delete(entity)
    }

}