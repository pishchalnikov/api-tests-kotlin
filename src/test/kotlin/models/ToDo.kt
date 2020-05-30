package models

import com.fasterxml.jackson.annotation.JsonProperty

const val ToDosURL = "/todos"

data class ToDo(
    @JsonProperty("userId")
    val userID: Int,

    @JsonProperty("id")
    val id: Int,

    @JsonProperty("title")
    val title: String,

    @JsonProperty("completed")
    val completed: Boolean
)
