package com.jaywu.tacocloud.data.jpa

import com.jaywu.tacocloud.Ingredient
import org.springframework.data.repository.CrudRepository

interface JpaIngredientRepository: CrudRepository<Ingredient, String>