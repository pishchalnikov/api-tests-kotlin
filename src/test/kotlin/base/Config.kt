package base

import java.io.IOException
import java.util.Objects
import java.util.Properties

class Config() {
    private val properties = Properties()
    init {
        try {

            val resource = javaClass.classLoader.getResourceAsStream("config.properties")
            properties.load(Objects.requireNonNull(resource))
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }
    val baseURI: String = properties.getProperty("baseURI")
}
