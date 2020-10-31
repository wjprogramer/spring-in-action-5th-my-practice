package tacos.web.api.resources

import org.springframework.hateoas.RepresentationModel
import tacos.Ingredient
import tacos.Taco
import java.util.*

class TacoResource(taco: Taco): RepresentationModel<TacoResource>() {

    val name: String = taco.name

    val createdAt: Date? = taco.createdAt

    val ingredients: List<Ingredient> = taco.ingredients

}