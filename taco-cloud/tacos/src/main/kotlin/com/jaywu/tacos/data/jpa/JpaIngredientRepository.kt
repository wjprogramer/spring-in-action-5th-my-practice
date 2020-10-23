package com.jaywu.tacos.data.jpa

import com.jaywu.tacos.Ingredient
import org.springframework.data.repository.CrudRepository

interface JpaIngredientRepository: CrudRepository<Ingredient, String>