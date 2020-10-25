package tacos.web.api

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.hateoas.server.EntityLinks
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

    @GetMapping("/recent")
    fun recentTacos(): List<Taco> {
        val page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending())
        return tacoRepo.findAll(page).content
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