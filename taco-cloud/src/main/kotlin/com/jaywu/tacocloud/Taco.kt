package com.jaywu.tacocloud

import java.util.*
import javax.validation.constraints.Size
import javax.validation.constraints.NotNull

data class Taco(
        var id: Long = -1L,

        var createdAt: Date? = null,

        @field:NotNull
        @field:Size(min = 5, message = "Name must be at least 5 characters long")
        var name: String = "",

        @field:NotNull
        @field:Size(min = 1, message = "You must choose at least 1 ingredient")
        var ingredients: List<String> = listOf(),
)