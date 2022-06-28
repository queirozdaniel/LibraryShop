package com.danielqueiroz.libraryshop.mockito.domain.service

import com.danielqueiroz.libraryshop.domain.repository.PersonRepository
import com.danielqueiroz.libraryshop.domain.service.impl.PersonServiceImpl
import com.danielqueiroz.libraryshop.unittests.mapper.mocks.MockPerson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertNotNull
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
    fun findById() {
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
    fun findAll() {
    }

    @Test
    fun create() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}