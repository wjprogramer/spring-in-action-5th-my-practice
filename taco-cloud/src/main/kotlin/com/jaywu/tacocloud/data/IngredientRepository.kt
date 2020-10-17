package com.jaywu.tacocloud.data

import com.jaywu.tacocloud.Ingredient

interface IngredientRepository {

    fun findAll(): Iterable<Ingredient>

    fun findById(id: String?): Ingredient?

    fun save(ingredient: Ingredient?): Ingredient?

}