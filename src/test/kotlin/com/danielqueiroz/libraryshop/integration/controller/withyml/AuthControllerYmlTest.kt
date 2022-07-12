package com.danielqueiroz.libraryshop.integration.controller.withyml

import com.danielqueiroz.libraryshop.ConfigsTest
import com.danielqueiroz.libraryshop.api.data.vo.exception.ExceptionResponse
import com.danielqueiroz.libraryshop.integration.controller.withyml.mapper.YMLMapper
import com.danielqueiroz.libraryshop.integration.testcontainers.AbstractIntegrationTest
import com.danielqueiroz.libraryshop.integration.vo.AccountCredentialsVO
import com.danielqueiroz.libraryshop.integration.vo.TokenVO
import io.restassured.RestAssured
import io.restassured.config.EncoderConfig
import io.restassured.config.RestAssuredConfig
import io.restassured.http.ContentType
import org.junit.jupiter.api.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerYmlTest : AbstractIntegrationTest() {

    private lateinit var objectMapper: YMLMapper
    private lateinit var tokenVO: TokenVO

    @BeforeAll
    fun setupTests(){
        tokenVO = TokenVO()
        objectMapper = YMLMapper()
    }

    @Test
    @Order(0)
    fun `login with valid user`() {
        val user = AccountCredentialsVO(
            username = "daniqz",
            password = "admin123" // remenber, this is insert in migrations
        )

        tokenVO = RestAssured.given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig
                            .encoderConfig()
                            .encodeContentTypeAs(ConfigsTest.CONTENT_TYPE_YAML, ContentType.TEXT)
                    )
            )
            .basePath("/auth/signin")
            .port(ConfigsTest.SERVER_PORT)
            .accept(ConfigsTest.CONTENT_TYPE_YAML)
            .contentType(ConfigsTest.CONTENT_TYPE_YAML)
            .body(user, objectMapper)
            .`when`()
            .post()
            .then()
            .statusCode(200)
            .extract()
            .body()
            .`as`(TokenVO::class.java, objectMapper)

        Assertions.assertNotNull(tokenVO.accessToken)
        Assertions.assertNotNull(tokenVO.refreshToken)
    }

    @Test
    @Order(1)
    fun `refresh token with valid user`() {

        tokenVO = RestAssured.given()
            .config(
                RestAssuredConfig
                    .config()
                    .encoderConfig(
                        EncoderConfig
                            .encoderConfig()
                            .encodeContentTypeAs(ConfigsTest.CONTENT_TYPE_YAML, ContentType.TEXT)
                    )
            )
            .basePath("/auth/refresh/")
            .port(ConfigsTest.SERVER_PORT)
            .accept(ConfigsTest.CONTENT_TYPE_YAML)
            .contentType(ConfigsTest.CONTENT_TYPE_YAML)
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
            .`as`(TokenVO::class.java, objectMapper)

        Assertions.assertNotNull(tokenVO.accessToken)
        Assertions.assertNotNull(tokenVO.refreshToken)
    }

}