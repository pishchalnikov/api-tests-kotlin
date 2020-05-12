package tests

import base.BaseCase
import io.restassured.RestAssured.given
import models.ToDosURL
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class TestToDoAPI : BaseCase() {

    @ParameterizedTest
    @ValueSource(ints = [200])
    fun sizeToDoTest(size: Int) {
        given()
        .`when`()
                .get(ToDosURL)
        .then()
                .statusCode(200)
                .body("$.size()", equalTo(size))
    }
}