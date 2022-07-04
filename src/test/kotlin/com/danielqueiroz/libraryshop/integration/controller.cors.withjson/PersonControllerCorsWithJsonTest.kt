package com.danielqueiroz.libraryshop.integration.controller.cors.withjson

import com.danielqueiroz.libraryshop.ConfigsTest
import com.danielqueiroz.libraryshop.integration.testcontainers.AbstractIntegrationTest
import com.danielqueiroz.libraryshop.integration.vo.PersonVO
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerCorsWithJsonTest() : AbstractIntegrationTest() {

    private lateinit var specification: RequestSpecification
    private lateinit var objectMapper: ObjectMapper
    private lateinit var person: PersonVO

    @BeforeAll
    fun setupTests(){
        objectMapper = ObjectMapper()
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        person = PersonVO()
    }

    @Test
    @Order(1)
    fun `create person`() {
        mockPerson()
        specification = RequestSpecBuilder()
            .addHeader(
                ConfigsTest.HEADER_PARAM_ORIGIN,
                ConfigsTest.ORIGIN_LOCALHOST
            )
            .setBasePath("/api/persons/v1")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        val content = given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .body(person)
            .`when`()
            .post()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString()

        val createdPerson = objectMapper.readValue(content, PersonVO::class.java)

        person = createdPerson

        assertNotNull(createdPerson.id)
        assertNotNull(createdPerson.firstName)
        assertNotNull(createdPerson.lastName)
        assertNotNull(createdPerson.address)
        assertNotNull(createdPerson.gender)
        assertTrue(createdPerson.id!! > 0)

        assertEquals(person.firstName, createdPerson.firstName)
        assertEquals(person.lastName, createdPerson.lastName)
    }

    @Test
    @Order(2)
    fun `create person with invalid origin`() {
        mockPerson()
        specification = RequestSpecBuilder()
            .addHeader(
                ConfigsTest.HEADER_PARAM_ORIGIN,
                ConfigsTest.ORIGIN_INVALID
            )
            .setBasePath("/api/persons/v1")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        val content = given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .body(person)
            .`when`()
            .post()
            .then()
            .statusCode(403)
            .extract()
            .body()
            .asString()

        assertEquals("Invalid CORS request", content)
    }

    @Test
    @Order(3)
    fun `find person by id`() {
        mockPerson()
        specification = RequestSpecBuilder()
            .addHeader(
                ConfigsTest.HEADER_PARAM_ORIGIN,
                ConfigsTest.ORIGIN_LOCALHOST
            )
            .setBasePath("/api/persons/v1")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        val content = given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .pathParam("id", person.id)
            .`when`()["{id}"]
            .then()
            .statusCode(200)
            .extract()
            .body()
            .asString()

        val createdPerson = objectMapper.readValue(content, PersonVO::class.java)

        assertNotNull(createdPerson.id)
        assertNotNull(createdPerson.firstName)
        assertNotNull(createdPerson.lastName)
        assertNotNull(createdPerson.address)
        assertNotNull(createdPerson.gender)
        assertTrue(createdPerson.id!! > 0)

        assertEquals(person.firstName, createdPerson.firstName)
        assertEquals(person.lastName, createdPerson.lastName)
    }

    @Test
    @Order(4)
    fun `find person by id with invalid origin`() {
        mockPerson()
        specification = RequestSpecBuilder()
            .addHeader(
                ConfigsTest.HEADER_PARAM_ORIGIN,
                ConfigsTest.ORIGIN_INVALID
            )
            .setBasePath("/api/persons/v1")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()

        val content = given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .pathParam("id", person.id)
            .`when`()["{id}"]
            .then()
            .statusCode(403)
            .extract()
            .body()
            .asString()

        assertEquals("Invalid CORS request", content)
    }

    private fun mockPerson() {
        person.firstName = "Eren"
        person.lastName = "Jeager"
        person.address = "Paradis Island"
        person.gender = "Male"
    }

}