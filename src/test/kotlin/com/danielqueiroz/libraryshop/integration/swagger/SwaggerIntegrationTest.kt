package com.danielqueiroz.libraryshop.integration.swagger

import com.danielqueiroz.libraryshop.ConfigsTest
import com.danielqueiroz.libraryshop.integration.testcontainers.AbstractIntegrationTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest() : AbstractIntegrationTest() {

    @Test
    fun `show display Swagger UI Page`() {
        val content = given()
            .basePath("/swagger-ui/index.html")
            .port(ConfigsTest.SERVER_PORT)
            .`when`()
            .get()
            .then()
            .statusCode(200)
            .extract().body().asString()

        assertTrue(content.contains("Swagger UI"))
    }

}