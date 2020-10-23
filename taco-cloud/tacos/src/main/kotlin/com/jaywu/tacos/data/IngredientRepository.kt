package com.jaywu.tacos.data

import com.jaywu.tacos.Ingredient

interface IngredientRepository {

    fun findAll(): Iterable<Ingredient>

    fun findById(id: String?): Ingredient?

    fun save(ingredient: Ingredient?): Ingredient?

}