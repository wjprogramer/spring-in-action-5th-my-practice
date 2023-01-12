package tacos.web.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tacos.Ingredient
import tacos.data.IngredientRepository
import tacos.data.jpa.JpaIngredientRepository
import java.net.URI
import java.util.Optional


@RestController
@RequestMapping(
    path = ["/ingredients"],
    produces = ["application/json"])
@CrossOrigin(origins = ["*"])
class IngredientController @Autowired constructor(private var repo: JpaIngredientRepository) {

    @GetMapping
    fun allIngredients(): Iterable<Ingredient?>? {
        return repo.findAll()
    }

    @GetMapping("/{id}")
    fun byId(@PathVariable id: String): Optional<Ingredient?>? {
        return repo.findById(id)
    }

    @PutMapping("/{id}")
    fun updateIngredient(@PathVariable id: String, @RequestBody ingredient: Ingredient) {
        check(ingredient.id == id) { "Given ingredient's ID doesn't match the ID in the path." }
        repo.save(ingredient)
    }

    @PostMapping
    fun postIngredient(@RequestBody ingredient: Ingredient): ResponseEntity<Ingredient?>? {
        val saved = repo.save(ingredient)
        val headers = HttpHeaders()
        headers.location = URI.create("http://localhost:8080/ingredients/" + ingredient.id)
        return ResponseEntity(saved, headers, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun deleteIngredient(@PathVariable id: String) {
        repo.deleteById(id)
    }
    
}