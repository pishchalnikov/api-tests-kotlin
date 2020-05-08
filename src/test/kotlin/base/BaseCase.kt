package base

import io.restassured.RestAssured
import io.restassured.parsing.Parser

abstract class BaseCase {
    init {
        RestAssured.baseURI = Config().baseURI
        RestAssured.defaultParser = Parser.JSON
    }
}