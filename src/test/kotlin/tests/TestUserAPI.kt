package tests

import base.BaseCase
import io.restassured.RestAssured.given
import models.usersURL
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TestUserAPI : BaseCase() {

    @ParameterizedTest
    @ValueSource(ints = [10])
    fun sizeUserTest(size: Int) {
        given()
        .`when`()
                .get(usersURL)
        .then()
                .statusCode(200)
                .body("$.size()", equalTo(size))
    }
}