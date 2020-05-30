package tests

import base.BaseCase
import io.restassured.RestAssured.given
import java.util.stream.Stream
import models.ToDo
import models.ToDosURL
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class TestToDoAPI : BaseCase() {

    companion object {
        @JvmStatic
        private fun todoProvider(): Stream<ToDo> = Stream.of(
                ToDo(
                        userID = 1,
                        id = 1,
                        title = "delectus aut autem",
                        completed = false
                )
        )
    }

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

    @ParameterizedTest
    @MethodSource("todoProvider")
    fun getToDoByIDTest(expectedToDo: ToDo) {
        val todo = given()
            .`when`()
                    .get("$ToDosURL/${expectedToDo.id}")
            .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .jsonPath()
                    .getObject(".", ToDo::class.java)
        assertEquals(expectedToDo, todo)
    }

    @ParameterizedTest
    @ValueSource(ints = [1])
    fun getToDoByUserIDTest(userID: Int) {
        val actualToDos = given()
            .`when`()
                .get("$ToDosURL?userId=$userID")
            .then()
                .statusCode(200)
            .extract()
                .body()
                .jsonPath()
                .getList(".", ToDo::class.java)
        actualToDos.forEach { todo ->
            assertEquals(userID, todo.userID)
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = [true, false])
    fun getToDoByCompletedTest(completed: Boolean) {
        val actualToDos = given()
            .`when`()
                .get("$ToDosURL?completed=$completed")
            .then()
                .statusCode(200)
            .extract()
                .body()
                .jsonPath()
                .getList(".", ToDo::class.java)
        actualToDos.forEach { todo ->
            assertEquals(completed, todo.completed)
        }
    }

    @ParameterizedTest
    @MethodSource("todoProvider")
    fun getToDoByTitleTest(expectedToDo: ToDo) {
        val actualToDos = given()
            .`when`()
                .get("$ToDosURL?title=${expectedToDo.title}")
            .then()
                .statusCode(200)
            .extract()
                .body()
                .jsonPath()
                .getList(".", ToDo::class.java)
        actualToDos.forEach { todo ->
            assertEquals(expectedToDo, todo)
        }
    }
}
