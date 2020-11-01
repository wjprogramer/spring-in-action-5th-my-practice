package tacos.web.api

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.hateoas.*
import org.springframework.hateoas.server.EntityLinks
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import tacos.Ingredient
import tacos.Ingredient.Companion.Type
import tacos.Order
import tacos.Taco
import tacos.User
import tacos.data.jpa.JpaIngredientRepository
import tacos.data.jpa.JpaTacoRepository
import tacos.data.jpa.UserRepository
import tacos.web.api.assemblers.TacoResourceAssembler
import tacos.web.api.resources.TacoResource
import java.security.Principal
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors
import javax.validation.Valid


/**
 * Ch6. RestController
 */
@RestController
@RequestMapping(
    path = ["/design"],
    produces = ["application/json", "text/xml"])    // 只會處理 accept header 包含 ...produces 的請求
@CrossOrigin(origins = ["*"])                       // 允許跨域請求
class DesignTacoController constructor(private var tacoRepo: JpaTacoRepository) {

    private val logger = KotlinLogging.logger {}

    @Autowired
    lateinit var entityLinks: EntityLinks

    /**
     * @return 6.2.1 return type => CollectionModel<EntityModel<Taco>>
     */
    @GetMapping("/recent")
    fun recentTacos(): CollectionModel<TacoResource> {
        val page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending())

        val tacos = tacoRepo.findAll(page).content
        val recentResources = TacoResourceAssembler().toCollectionModel(tacos)

//        val recentResources = CollectionModel.wrap(tacos)

        /** Hard code */
//        recentResources.add(
//            Link("https://localhost:8082/design/recent", "recents"))

        /** Deprecated */
//        ControllerLinkBuilder.linkTo(DesignTacoController::class.java)
//            .slash("recent")
//            .withRel("recents")

        recentResources.add(
            linkTo<DesignTacoController>{ methodOn(DesignTacoController::class.java).recentTacos() }
                .withRel("recents")
        )

        return recentResources
    }

    @GetMapping("/{id}")
    fun tacoById(@PathVariable("id") id: Long): ResponseEntity<Taco> {
        val optTaco = tacoRepo.findById(id)
        if (optTaco.isPresent) {
            return ResponseEntity<Taco>(optTaco.get(), HttpStatus.OK)
        }
        return ResponseEntity<Taco>(null, HttpStatus.NOT_FOUND)
    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postTaco(@RequestBody taco: Taco): Taco {
        return tacoRepo.save(taco)
    }

}
/**
 * 6.1 基本 Get
 */
// @GetMapping("/recent")
// fun recentTacos(): List<Taco> {
//     val page = PageRequest.of(
//         0, 12, Sort.by("createdAt").descending())
//     return tacoRepo.findAll(page).content
// }