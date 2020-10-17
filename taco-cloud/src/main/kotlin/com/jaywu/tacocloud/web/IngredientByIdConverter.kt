package com.jaywu.tacocloud.web

import com.jaywu.tacocloud.Ingredient
import com.jaywu.tacocloud.data.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component


@Component
class IngredientByIdConverter @Autowired constructor(private var ingredientRepo: IngredientRepository): Converter<String, Ingredient> {

    override fun convert(id: String): Ingredient? {
        return ingredientRepo.findById(id)
    }

}