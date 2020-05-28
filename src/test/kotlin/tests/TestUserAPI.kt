package tests

import base.BaseCase
import io.restassured.RestAssured.given
import models.User
import models.usersURL
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class TestUserAPI : BaseCase() {

    companion object {
        @JvmStatic
        private fun userProvider(): Stream<User> = Stream.of(
                User(
                        id = 1,
                        name = "Leanne Graham",
                        username = "Bret",
                        email = "Sincere@april.biz",
                        address = User.Address(
                                street = "Kulas Light",
                                suite = "Apt. 556",
                                city = "Gwenborough",
                                zipcode = "92998-3874",
                                geo = User.Address.Geo(
                                        lat = "-37.3159",
                                        lng = "81.1496"
                                )
                        ),
                        phone = "1-770-736-8031 x56442",
                        website = "hildegard.org",
                        company = User.Company(
                                name = "Romaguera-Crona",
                                catchPhrase = "Multi-layered client-server neural-net",
                                bs = "harness real-time e-markets"
                        )
                )
        )
    }

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

    @ParameterizedTest
    @MethodSource("userProvider")
    fun getUserByIDTest(expectedUser: User) {
        val user = given()
            .`when`()
                .get("$usersURL/${expectedUser.id}")
            .then()
                .statusCode(200)
            .extract()
                .body()
                .jsonPath()
                .getObject(".", User::class.java)
        Assertions.assertEquals(expectedUser, user)
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    fun getUserByUserNameTest(expectedUser: User) {
        val user = given()
            .`when`()
                .get("$usersURL?username=${expectedUser.username}")
            .then()
                .statusCode(200)
            .extract()
                .body()
                .jsonPath()
                .getList(".", User::class.java)
                .first()
        Assertions.assertEquals(expectedUser, user)
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    fun getUserByEmailTest(expectedUser: User) {
        val user = given()
            .`when`()
                .get("$usersURL?email=${expectedUser.email}")
            .then()
                .statusCode(200)
            .extract()
                .body()
                .jsonPath()
                .getList(".", User::class.java)
                .first()
        Assertions.assertEquals(expectedUser, user)
    }

    @ParameterizedTest
    @MethodSource("userProvider")
    fun getUserByPhoneTest(expectedUser: User) {
        val user = given()
            .`when`()
                .get("$usersURL?phone=${expectedUser.phone}")
            .then()
                .statusCode(200)
            .extract()
                .body()
                .jsonPath()
                .getList(".", User::class.java)
                .first()
        Assertions.assertEquals(expectedUser, user)
    }
}