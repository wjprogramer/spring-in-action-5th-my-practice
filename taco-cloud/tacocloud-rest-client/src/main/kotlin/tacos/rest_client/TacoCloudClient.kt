package tacos.rest_client

import mu.KotlinLogging
import org.springframework.core.ParameterizedTypeReference
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.client.Traverson
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.util.UriComponentsBuilder
import tacos.Ingredient
import tacos.Taco
import java.net.URI

@Service
class TacoCloudClient constructor(private var rest: RestTemplate, private var traverson: Traverson) {

    private val logger = KotlinLogging.logger {}

    //
    // GET examples
    //

    /**
     * Specify parameter as varargs argument
     */
    fun getIngredientById(ingredientId: String?): Ingredient? {
        if (ingredientId == null) return null

        return rest.getForObject("https://localhost:8082/ingredients/{id}",
            Ingredient::class.java, ingredientId)
    }

    /**
     * Alternate implementations...
     * The next three methods are alternative implementations of
     * getIngredientById() as shown in chapter 6. If you'd like to try
     * any of them out, comment out the previous method and uncomment
     * the variant you want to use.
     */

    /**
     * Specify parameters with a map
     */
    fun getIngredientByIdWithMap(ingredientId: String?): Ingredient? {
        val urlVariables = HashMap<String, String?>()
        urlVariables["id"] = ingredientId

        return rest.getForObject("https://localhost:8082/ingredients/{id}",
            Ingredient::class.java, urlVariables)
    }

    /**
     * Request with URI instead of String
     */
    fun getIngredientByIdWithURI(ingredientId: String?): Ingredient? {
        val urlVariables = HashMap<String, String?>()
        urlVariables["id"] = ingredientId

        val uri = UriComponentsBuilder
            .fromHttpUrl("https://localhost:8082/ingredients/{id}")
            .build(urlVariables)

        return rest.getForObject(uri, Ingredient::class.java)
    }

    /**
     * Use getForEntity() instead of getForObject()
     */
    fun getIngredientEntityById(ingredientId: String?): Ingredient? {
        val responseEntity =
            rest.getForEntity("https://localhost:8082/ingredients/{id}", Ingredient::class.java, ingredientId)

        logger.info("Fetched time: " +
            responseEntity.headers.date)

        return responseEntity.body
    }

    fun getAllIngredients(): List<Ingredient>? {
        return rest.exchange<List<Ingredient>>("https://localhost:8082/ingredients",
            HttpMethod.GET, null, object : ParameterizedTypeReference<List<Ingredient?>?>() {})
            .body
    }

    //
    // PUT examples
    //

    fun updateIngredient(ingredient: Ingredient) {
        rest.put("https://localhost:8082/api/ingredients/{id}",
            ingredient, ingredient.id)
    }

    //
    // POST examples
    //
    fun createIngredient(ingredient: Ingredient?): Ingredient? {
        return rest.postForObject("https://localhost:8082/ingredients",
            ingredient, Ingredient::class.java)
    }

    /**
     * Alternate implementations...
     * The next two methods are alternative implementations of
     * createIngredient() as shown in chapter 6. If you'd like to try
     * any of them out, comment out the previous method and uncomment
     * the variant you want to use.
     */

     fun createIngredientWithLocation(ingredient: Ingredient): URI? {
       return rest.postForLocation("https://localhost:8082/ingredients",
           ingredient, Ingredient::class.java)
     }

    fun createIngredientForEntity(ingredient: Ingredient): Ingredient? {
        val responseEntity =
            rest.postForEntity("https://localhost:8082/ingredients",
                ingredient,
                Ingredient::class.java)

        logger.info("New resource created at " +
                    responseEntity.headers.location)

        return responseEntity.body
    }

    //
    // DELETE examples
    //

    fun deleteIngredient(ingredient: Ingredient?) {
        if (ingredient == null) return

        rest.delete("https://localhost:8082/api/ingredients/{id}",
            ingredient.id)
    }

    //
    // Traverson with RestTemplate examples
    //

    fun getAllIngredientsWithTraverson(): Iterable<Ingredient?>? {
        val ingredientType: ParameterizedTypeReference<CollectionModel<Ingredient>> = object : ParameterizedTypeReference<CollectionModel<Ingredient>>() {}
        val ingredientRes = traverson
            .follow("ingredients")
            .toObject(ingredientType)

        return ingredientRes?.content ?: listOf()
    }

    fun addIngredient(ingredient: Ingredient?): Ingredient? {
        val ingredientsUrl = traverson
            .follow("ingredients")
            .asLink()
            .href
        return rest.postForObject(ingredientsUrl,
            ingredient,
            Ingredient::class.java)
    }

    fun getRecentTacosWithTraverson(): Iterable<Taco> {
        val tacoType: ParameterizedTypeReference<CollectionModel<Taco>> = object : ParameterizedTypeReference<CollectionModel<Taco>>() {}
        val tacoRes = traverson
            .follow("tacos")
            .follow("recents")
            .toObject(tacoType)

        // Alternatively, list the two paths in the same call to follow()
//    Resources<Taco> tacoRes =
//        traverson
//          .follow("tacos", "recents")
//          .toObject(tacoType);
        return tacoRes?.content ?: listOf()
    }
}