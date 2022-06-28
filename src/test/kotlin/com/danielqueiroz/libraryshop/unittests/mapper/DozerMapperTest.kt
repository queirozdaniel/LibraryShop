package com.danielqueiroz.libraryshop.unittests.mapper

import com.danielqueiroz.libraryshop.api.data.vo.v1.PersonVO
import com.danielqueiroz.libraryshop.api.mapper.DozerMapper
import com.danielqueiroz.libraryshop.domain.model.Person
import com.danielqueiroz.libraryshop.unittests.mapper.mocks.MockPerson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DozerConverterTest {

    var inputObject: MockPerson? = null

    @BeforeEach
    fun setUp() {
        inputObject = MockPerson()
    }

    @Test
    fun `parse entity to ValueObject`() {
        val output: PersonVO = DozerMapper.parseObject(inputObject!!.mockEntity(), PersonVO::class.java)
        assertEquals(0, output.idValue)
        assertEquals("First Name Test0", output.firstName)
        assertEquals("Last Name Test0", output.lastName)
        assertEquals("Address Test0", output.address)
        assertEquals("Male", output.gender)
    }

    @Test
    fun `parse entity list to ValueObject list`() {
        val outputList: ArrayList<PersonVO> =
            DozerMapper.parseListObjects(inputObject!!.mockEntityList(), PersonVO::class.java) as ArrayList<PersonVO>

        val outputZero: PersonVO = outputList[0]

        assertEquals(0, outputZero.idValue)
        assertEquals("First Name Test0", outputZero.firstName)
        assertEquals("Last Name Test0", outputZero.lastName)
        assertEquals("Address Test0", outputZero.address)
        assertEquals("Male", outputZero.gender)

        val outputSeven: PersonVO = outputList[7]
        assertEquals(7.toLong(), outputSeven.idValue)
        assertEquals("First Name Test7", outputSeven.firstName)
        assertEquals("Last Name Test7", outputSeven.lastName)
        assertEquals("Address Test7", outputSeven.address)
        assertEquals("Female", outputSeven.gender)

        val outputTwelve: PersonVO = outputList[12]
        assertEquals(12.toLong(), outputTwelve.idValue)
        assertEquals("First Name Test12", outputTwelve.firstName)
        assertEquals("Last Name Test12", outputTwelve.lastName)
        assertEquals("Address Test12", outputTwelve.address)
        assertEquals("Male", outputTwelve.gender)
    }

    @Test
    fun `parse ValueObject to entity`() {

        val output: Person = DozerMapper.parseObject(inputObject!!.mockVO(), Person::class.java)

        assertEquals(0, output.id)
        assertEquals("First Name Test0", output.firstName)
        assertEquals("Last Name Test0", output.lastName)
        assertEquals("Address Test0", output.address)
        assertEquals("Male", output.gender)
    }

    @Test
    fun `parse ValueObject list to entity list`() {

        val outputList: ArrayList<Person> = DozerMapper.parseListObjects(inputObject!!.mockVOList(), Person::class.java) as ArrayList<Person>

        val outputZero: Person = outputList[0]
        assertEquals(0, outputZero.id)
        assertEquals("First Name Test0", outputZero.firstName)
        assertEquals("Last Name Test0", outputZero.lastName)
        assertEquals("Address Test0", outputZero.address)
        assertEquals("Male", outputZero.gender)

        val outputSeven: Person = outputList[7]
        assertEquals(7, outputSeven.id)
        assertEquals("First Name Test7", outputSeven.firstName)
        assertEquals("Last Name Test7", outputSeven.lastName)
        assertEquals("Address Test7", outputSeven.address)
        assertEquals("Female", outputSeven.gender)

        val outputTwelve: Person = outputList[12]
        assertEquals(12, outputTwelve.id)
        assertEquals("First Name Test12", outputTwelve.firstName)
        assertEquals("Last Name Test12", outputTwelve.lastName)
        assertEquals("Address Test12", outputTwelve.address)
        assertEquals("Male", outputTwelve.gender)
    }
}