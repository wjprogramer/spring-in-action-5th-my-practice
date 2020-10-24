package tacos.data.jpa

import org.springframework.data.repository.CrudRepository
import tacos.Ingredient

interface JpaIngredientRepository: CrudRepository<Ingredient, String>