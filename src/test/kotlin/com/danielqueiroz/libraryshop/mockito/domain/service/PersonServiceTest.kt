package com.danielqueiroz.libraryshop.mockito.domain.service

import com.danielqueiroz.libraryshop.api.exception.ResourceNotFoundException
import com.danielqueiroz.libraryshop.domain.model.Person
import com.danielqueiroz.libraryshop.domain.repository.PersonRepository
import com.danielqueiroz.libraryshop.domain.service.impl.PersonServiceImpl
import com.danielqueiroz.libraryshop.unittests.mapper.mocks.MockPerson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class PersonServiceTest {

    private lateinit var inputObject: MockPerson

    @InjectMocks
    private lateinit var service: PersonServiceImpl

    @Mock
    private lateinit var repository: PersonRepository

    @BeforeEach
    fun setUpMock() {
        inputObject = MockPerson()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `find person by id`() {
        val id: Long = 1
        val person = inputObject.mockEntity(id.toInt())
        person.id = id
        `when`(repository.findById(id)).thenReturn(Optional.of(person))

        val result = service.findById(id)

        assertNotNull(result)
        assertNotNull(result.idValue)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/persons/$id"))
        assertEquals(person.firstName, result.firstName)
        assertEquals(person.lastName, result.lastName)
        assertEquals(person.address, result.address)
        assertEquals(person.gender, result.gender)
    }

    @Test
    fun `must return not found status when person is not found`() {
        val id: Long = 0
        val expection = assertThrows(
            ResourceNotFoundException::class.java
        ) {
            service.findById(id)
        }
        val message = "No records found for this id: $id"
        assertEquals(message, expection.message)
    }

    @Test
    fun `find all people`() {
        val list: List<Person> = inputObject.mockEntityList()
        `when`(repository.findAll()).thenReturn(list)
        val persons = service.findAll()
        assertNotNull(persons)
        assertEquals(14, persons.size)
        val personOne = persons[1]
        assertNotNull(personOne)
        assertNotNull(personOne.idValue)
        assertNotNull(personOne.links)
        assertTrue(personOne.links.toString().contains("/persons/${personOne.idValue}"))
        assertEquals("Address Test1", personOne.address)
        assertEquals("First Name Test1", personOne.firstName)
        assertEquals("Last Name Test1", personOne.lastName)
        assertEquals("Female", personOne.gender)
        val personFour = persons[4]
        assertNotNull(personFour)
        assertNotNull(personFour.idValue)
        assertNotNull(personFour.links)
        assertTrue(personFour.links.toString().contains("/persons/${personFour.idValue}"))
        assertEquals("Address Test4", personFour.address)
        assertEquals("First Name Test4", personFour.firstName)
        assertEquals("Last Name Test4", personFour.lastName)
        assertEquals("Male", personFour.gender)
        val personSeven = persons[7]
        assertNotNull(personSeven)
        assertNotNull(personSeven.idValue)
        assertNotNull(personSeven.links)
        assertTrue(personSeven.links.toString().contains("/persons/${personSeven.idValue}"))
        assertEquals("Address Test7", personSeven.address)
        assertEquals("First Name Test7", personSeven.firstName)
        assertEquals("Last Name Test7", personSeven.lastName)
        assertEquals("Female", personSeven.gender)
    }

    @Test
    fun `create person`() {
        val entity = inputObject.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 1L

        val vo = inputObject.mockVO(1)
        vo.idValue = 1L

        `when`(repository.save(entity)).thenReturn(persisted)
        val result = service.create(vo)

        assertNotNull(result)
        assertNotNull(result.idValue)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/persons/${result.idValue}"))
        assertEquals(entity.address, result.address)
        assertEquals(entity.firstName, result.firstName)
        assertEquals(entity.lastName, result.lastName)
        assertEquals(entity.gender, result.gender)
    }

    @Test
    fun `update person`() {
        val entity = inputObject.mockEntity(1)
        entity.id = 1L

        val persisted = entity.copy()
        persisted.id = 1L

        val vo = inputObject.mockVO(1)
        vo.idValue = 1L

        `when`(repository.findById(1L)).thenReturn(Optional.of(entity))

        `when`(repository.save(entity)).thenReturn(persisted)

        val result = service.update(vo)
        assertNotNull(result)
        assertNotNull(result.idValue)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/persons/${result.idValue}"))
        assertEquals(persisted.address, result.address)
        assertEquals(persisted.firstName, result.firstName)
        assertEquals(persisted.lastName, result.lastName)
        assertEquals(persisted.gender, result.gender)
    }

    @Test
    fun `delete person`() {
        val person = inputObject.mockEntity(1)
        person.id = 1L
        `when`(repository.findById(1L)).thenReturn(Optional.of(person))
        service.delete(1L)
    }
}