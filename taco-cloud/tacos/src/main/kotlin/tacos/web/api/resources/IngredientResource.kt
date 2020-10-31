package tacos.web.api.resources

import org.springframework.hateoas.RepresentationModel
import tacos.Ingredient
import tacos.Taco
import java.util.*

class IngredientResource(ingredient: Ingredient): RepresentationModel<IngredientResource>() {

    val name: String = ingredient.name

    val type = ingredient.type

}