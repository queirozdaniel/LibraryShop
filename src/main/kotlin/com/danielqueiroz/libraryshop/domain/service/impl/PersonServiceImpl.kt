package com.danielqueiroz.libraryshop.domain.service.impl

import com.danielqueiroz.libraryshop.domain.model.Person
import com.danielqueiroz.libraryshop.domain.service.PersonService
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServiceImpl : PersonService {

    private val logger = Logger.getLogger(PersonServiceImpl::class.java.name)

    override fun findById(id: Long): Person {
        logger.info("Finding one Person")
        return Person( 1, "Daniel", "Queiroz")
    }

    override fun findAll(): List<Person> {
        return listOf(Person(1,"Daniel"), Person(2,"Eren"), Person(3, "Mikasa"))
    }

    override fun create(person: Person): Person {
        return person
    }

    override fun update(person: Person): Person {
        return person
    }

    override fun delete(id: Long) {
        TODO("Not yet implemented")
    }

}