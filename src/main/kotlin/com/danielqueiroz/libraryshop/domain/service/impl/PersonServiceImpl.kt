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

}