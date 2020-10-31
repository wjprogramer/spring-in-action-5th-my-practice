package tacos.web.api.assemblers

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import tacos.Ingredient
import tacos.web.api.DesignTacoController
import tacos.web.api.resources.IngredientResource

class IngredientResourceAssembler:
    RepresentationModelAssemblerSupport<Ingredient, IngredientResource>(DesignTacoController::class.java, IngredientResource::class.java) {

    override fun instantiateModel(ingredient: Ingredient): IngredientResource {
        return IngredientResource(ingredient)
    }

    override fun toModel(ingredient: Ingredient): IngredientResource {
        return createModelWithId(ingredient.id ?: -1, ingredient)
    }

}