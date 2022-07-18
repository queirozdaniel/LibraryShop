package com.danielqueiroz.libraryshop.domain.service.impl

import com.danielqueiroz.libraryshop.api.controller.PersonController
import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import com.danielqueiroz.libraryshop.api.exception.ResourceNotFoundException
import com.danielqueiroz.libraryshop.api.mapper.DozerMapper
import com.danielqueiroz.libraryshop.api.mapper.custom.PersonMapper
import com.danielqueiroz.libraryshop.domain.model.Person
import com.danielqueiroz.libraryshop.domain.repository.PersonRepository
import com.danielqueiroz.libraryshop.domain.service.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Logger
import com.danielqueiroz.libraryshop.api.data.vo.v2.PersonVO as PersonVOV2

@Service
class PersonServiceImpl : PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var mapperV2: PersonMapper

    @Autowired
    private lateinit var assembler: PagedResourcesAssembler<PersonVO>

    private val logger = Logger.getLogger(PersonServiceImpl::class.java.name)

    override fun findById(id: Long): PersonVO {
        logger.info("Finding person with id[$id]")
        val person = repository.findById(id).orElseThrow {
            ResourceNotFoundException("No records found for this id: $id")
        }
        val personVO = DozerMapper.parseObject(person, PersonVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.idValue).withSelfRel()
        personVO.add(withSelfRel)

        return personVO
    }

    override fun findAll(pageable: Pageable): PagedModel<EntityModel<PersonVO>> {
        logger.info("Finding all persons")
        val persons = repository.findAll(pageable)
        val vos = persons.map { p -> DozerMapper.parseObject(p, PersonVO::class.java) }
        vos.forEach { it.add(linkTo(PersonController::class.java).slash(it.idValue).withSelfRel()) }
        return assembler.toModel(vos)
    }

    override fun create(person: PersonVO): PersonVO {
        logger.info("Create a person with name ${person.firstName} ${person.lastName}")
        val entity = DozerMapper.parseObject(person, Person::class.java)
        val personVO =  DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.idValue).withSelfRel()
        personVO.add(withSelfRel)

        return personVO
    }

    override fun createV2(person: PersonVOV2): PersonVOV2 {
        logger.info("Create a person/v2 with name ${person.firstName} ${person.lastName}")
        val entity = mapperV2.mapVOToEntity(person)
        return mapperV2.mapEntityToVO(repository.save(entity))
    }

    override fun update(person: PersonVO): PersonVO {
        logger.info("Update infos for person with id[${person.idValue}]")
        val entity = repository.findById(person.idValue!!).orElseThrow {
            ResourceNotFoundException("No records found for this id: ${person.idValue}")
        }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        val personVO =  DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.idValue).withSelfRel()
        personVO.add(withSelfRel)

        return personVO
    }

    @Transactional
    override fun disablePerson(id: Long): PersonVO {
        logger.info("Disabling person with id[$id]")

        val person = repository.findById(id).orElseThrow {
            ResourceNotFoundException("No records found for this id: $id")
        }
        repository.disablePerson(id)

        val personVO = DozerMapper.parseObject(person, PersonVO::class.java)

        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.idValue).withSelfRel()
        personVO.add(withSelfRel)

        return personVO
    }

    override fun delete(id: Long) {
        logger.info("Delete person with id[$id]")
        val entity = repository.findById(id).orElseThrow {
            ResourceNotFoundException("No records found for this id: $id")
        }
        repository.delete(entity)
    }

}