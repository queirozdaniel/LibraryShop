package com.danielqueiroz.libraryshop.integration.controller.withjson

import com.danielqueiroz.libraryshop.ConfigsTest
import com.danielqueiroz.libraryshop.api.data.vo.exception.ExceptionResponse
import com.danielqueiroz.libraryshop.integration.testcontainers.AbstractIntegrationTest
import com.danielqueiroz.libraryshop.integration.vo.AccountCredentialsVO
import com.danielqueiroz.libraryshop.integration.vo.TokenVO
import io.restassured.RestAssured
import org.junit.jupiter.api.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerJsonTest : AbstractIntegrationTest() {

    private lateinit var tokenVO: TokenVO

    @BeforeAll
    fun setupTests(){
        tokenVO = TokenVO()
    }

    @Test
    @Order(0)
    fun `login with valid user`() {
        val user = AccountCredentialsVO(
            username = "daniqz",
            password = "admin123" // remenber, this is insert in migrations
        )

        tokenVO = RestAssured.given()
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

        Assertions.assertNotNull(tokenVO.accessToken)
        Assertions.assertNotNull(tokenVO.refreshToken)
    }

    @Test
    @Order(1)
    fun `refresh token with valid user`() {

        tokenVO = RestAssured.given()
            .basePath("/auth/refresh/")
                .port(ConfigsTest.SERVER_PORT)
                .contentType(ConfigsTest.CONTENT_TYPE_JSON)
                .pathParam("username",tokenVO.username)
            .header(
                ConfigsTest.HEADER_PARAM_AUTHORIZATION,
                "Bearer ${tokenVO.refreshToken}"
            )
            .`when`()
                .put("{username}")
                .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .`as`(TokenVO::class.java)

        Assertions.assertNotNull(tokenVO.accessToken)
        Assertions.assertNotNull(tokenVO.refreshToken)
    }

    @Test
    fun `login with invalid user`() {
        val user = AccountCredentialsVO(
            username = "daniqz",
            password = "1231222"
        )

        val response = RestAssured.given()
            .basePath("/auth/signin")
            .port(ConfigsTest.SERVER_PORT)
            .contentType(ConfigsTest.CONTENT_TYPE_JSON)
            .body(user)
            .`when`()
            .post()
            .then()
            .statusCode(403)
            .extract()
            .body()
            .`as`(ExceptionResponse::class.java)

        Assertions.assertNotNull(response.message)
        Assertions.assertEquals("Invalid username or password",response.message)
    }

}