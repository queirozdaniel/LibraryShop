package com.danielqueiroz.libraryshop.domain.service.impl

import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import com.danielqueiroz.libraryshop.api.data.vo.v2.PersonVO as PersonVOV2
import com.danielqueiroz.libraryshop.api.exception.ResourceNotFoundException
import com.danielqueiroz.libraryshop.api.mapper.DozerMapper
import com.danielqueiroz.libraryshop.api.mapper.custom.PersonMapper
import com.danielqueiroz.libraryshop.domain.model.Person
import com.danielqueiroz.libraryshop.domain.repository.PersonRepository
import com.danielqueiroz.libraryshop.domain.service.PersonService
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonServiceImpl(
    private val repository: PersonRepository,
    private val mapperV2: PersonMapper
) : PersonService {

    private val logger = Logger.getLogger(PersonServiceImpl::class.java.name)

    override fun findById(id: Long): PersonVO {
        logger.info("Finding person with id[$id]")
        val person = repository.findById(id).orElseThrow {
            ResourceNotFoundException("No records found for this id: $id")
        }
        return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    override fun findAll(): List<PersonVO> {
        logger.info("Finding all persons")
        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }

    override fun create(person: PersonVO): PersonVO {
        logger.info("Create a person with name ${person.firstName} ${person.lastName}")
        val entity = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
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

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    override fun delete(id: Long) {
        logger.info("Delete person with id[$id]")
        val entity = repository.findById(id).orElseThrow {
            ResourceNotFoundException("No records found for this id: $id")
        }
        repository.delete(entity)
    }

}