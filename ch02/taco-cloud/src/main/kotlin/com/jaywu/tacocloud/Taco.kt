package com.jaywu.tacocloud

import javax.validation.constraints.Size
import javax.validation.constraints.NotNull

data class Taco(
    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    var name: String,

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: List<String>
)