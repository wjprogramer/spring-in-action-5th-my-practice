package tacos.web.api.resources

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation
import tacos.Taco
import tacos.web.api.assemblers.IngredientResourceAssembler
import java.util.*

@Relation(value = "taco", collectionRelation = "tacos")
class TacoResource(taco: Taco): RepresentationModel<TacoResource>() {

    companion object {
        private val ingredientAssembler = IngredientResourceAssembler()
    }

    val name: String = taco.name

    val createdAt: Date? = taco.createdAt

    val ingredients: CollectionModel<IngredientResource> = ingredientAssembler.toCollectionModel(taco.ingredients)

}