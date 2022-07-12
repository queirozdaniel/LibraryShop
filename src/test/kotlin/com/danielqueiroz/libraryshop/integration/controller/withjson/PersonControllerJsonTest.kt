package com.danielqueiroz.libraryshop.integration.controller.withjson

import com.danielqueiroz.libraryshop.ConfigsTest
import com.danielqueiroz.libraryshop.integration.testcontainers.AbstractIntegrationTest
import com.danielqueiroz.libraryshop.integration.vo.AccountCredentialsVO
import com.danielqueiroz.libraryshop.integration.vo.PersonVO
import com.danielqueiroz.libraryshop.integration.vo.TokenVO
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerJsonTest : AbstractIntegrationTest() {

    private lateinit var specification: RequestSpecification
    private lateinit var objectMapper: ObjectMapper
    private lateinit var person: PersonVO

    @BeforeAll
    fun setupTests(){
        person = PersonVO()
        objectMapper = ObjectMapper()
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    }

    @Test
    @Order(0)
    fun `receive a valid token to tests`() {
        val user = AccountCredentialsVO(
            username = "daniqz",
            password = "admin123"
        )

        val token = RestAssured.given()
            .basePath("/auth/signin")
            .port(ConfigsTest.SERVER_PORT)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .body(user)
            .`when`()
            .post()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .`as`(TokenVO::class.java)
            .accessToken

        specification = RequestSpecBuilder()
            .addHeader(ConfigsTest.HEADER_PARAM_AUTHORIZATION, "Bearer $token")
            .setBasePath("/api/persons/v1")
            .setPort(ConfigsTest.SERVER_PORT)
            .addFilter(RequestLoggingFilter(LogDetail.ALL))
            .addFilter(ResponseLoggingFilter(LogDetail.ALL))
            .build()
    }

    @Test
    @Order(1)
    fun `create one person`() {
        mockPerson()
        val content = given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .body(person)
            .`when`()
            .post()
            .then().statusCode(200)
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
        assertEquals("Levi", createdPerson.firstName)
    }

    @Test
    @Order(2)
    fun `update  person info`() {

        person.lastName = "Ackerman - Humanity's Strongest Soldier"

        val content = given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .body(person)
            .pathParam("id", person.id)
            .`when`()
            .put("{id}")
            .then().statusCode(200)
            .extract()
            .body()
            .asString()

        val updatedPerson = objectMapper.readValue(content, PersonVO::class.java)

        assertNotNull(updatedPerson.id)
        assertNotNull(updatedPerson.firstName)
        assertNotNull(updatedPerson.lastName)
        assertNotNull(updatedPerson.address)
        assertNotNull(updatedPerson.gender)
        assertEquals(updatedPerson.id, person.id)
        assertEquals("Ackerman - Humanity's Strongest Soldier", updatedPerson.lastName)
    }

    @Test
    @Order(3)
    fun `find person by id`() {

        val content = given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .pathParam("id",person.id)
            .`when`()
            .get("{id}")
            .then().statusCode(200)
            .extract()
            .body()
            .asString()

        val returnedPerson = objectMapper.readValue(content, PersonVO::class.java)

        assertNotNull(returnedPerson.id)
        assertNotNull(returnedPerson.firstName)
        assertNotNull(returnedPerson.lastName)
        assertNotNull(returnedPerson.address)
        assertNotNull(returnedPerson.gender)
        assertEquals(returnedPerson.id, person.id)
        assertEquals("Ackerman - Humanity's Strongest Soldier", returnedPerson.lastName)
    }

    @Test
    @Order(4)
    fun `delete person by id`() {

        given()
            .spec(specification)
            .pathParam("id",person.id)
            .`when`()
            .delete("{id}")
            .then().statusCode(204)

    }

    @Test
    @Order(5)
    fun `find all people`() {

        val content = given()
            .spec(specification)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .`when`()
            .get()
            .then().statusCode(200)
            .extract()
            .body()
            .asString()

        val people = objectMapper.readValue(content, Array<PersonVO>::class.java)

        val firstPerson = people[0]

        assertNotNull(firstPerson.id)
        assertNotNull(firstPerson.firstName)
        assertNotNull(firstPerson.lastName)
        assertNotNull(firstPerson.address)
        assertNotNull(firstPerson.gender)
        assertEquals("Daniel", firstPerson.firstName)
        assertEquals("Queiroz da Silva", firstPerson.lastName)
    }

    private fun mockPerson() {
        person.firstName = "Levi"
        person.lastName = "Ackerman"
        person.address = "Paradis Island"
        person.gender = "Male"
    }


}