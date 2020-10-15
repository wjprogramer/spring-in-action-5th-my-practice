package com.jaywu.tacocloud

data class Ingredient(
    var id: String,
    var name: String,
    var type: Type
) {
    companion object {
        enum class Type {
            WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
        }
    }
}