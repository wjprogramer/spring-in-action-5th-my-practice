package tacos.web

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
 * Ch2~5. Controller (回傳 view)
 */
//@Controller
//@RequestMapping("/design")
//@SessionAttributes("order")
//class DesignTacoController @Autowired constructor(
//    private val ingredientRepo: JpaIngredientRepository,
//    private var tacoRepo: JpaTacoRepository,
//    private val userRepo: UserRepository
//) {
//
//    private val logger = KotlinLogging.logger {}
//
//    @ModelAttribute(name = "order")
//    fun order(): Order {
//        return Order()
//    }
//
//    @ModelAttribute(name = "taco")
//    fun taco(): Taco {
//        return Taco()
//    }
//
//    @GetMapping
//    fun showDesignForm(model: Model, principal: Principal): String {
//        loadIngredients(model)
//
//        val username = principal.name
//        val user: User? = userRepo.findByUsername(username)
//        model.addAttribute("user", user)
//
//        return "design"
//    }
//
//    @PostMapping
//    fun processDesign(
//        @Valid design: Taco,
//        errors: Errors,
//        model: Model,
//        @ModelAttribute order: Order
//    ): String {
//        if (errors.hasErrors()) {
//            loadIngredients(model)
//            return "design"
//        }
//
//        val saved = tacoRepo.save(design)
//        order.addDesign(saved)
//
//        logger.info("Processing design: $design")
//
//        return "redirect:/orders/current"
//    }
//
//    private fun filterByType(ingredients: List<Ingredient>, type: Type): MutableList<Ingredient>? {
//        return ingredients
//                .stream()
//                .filter { x: Ingredient -> x.type == type }
//                .collect(Collectors.toList())
//    }
//
//    private fun loadIngredients(model: Model) {
//        val ingredients: MutableList<Ingredient> = ArrayList()
//
//        ingredientRepo.findAll().forEach(Consumer { i: Ingredient -> ingredients.add(i) })
//
//        val types: Array<Type> = Ingredient.Companion.Type.values()
//        for (type in types) {
//            model.addAttribute(type.toString().toLowerCase(),
//                filterByType(ingredients, type))
//        }
//    }
//}

/**
 * Ch2. Hard Code
 */
//    @ModelAttribute
//    fun addIngredientsToModel(model: Model): String {
//        val ingredients = listOf(
//                Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                Ingredient("CHED", "Cheddar", Type.CHEESE),
//                Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                Ingredient("SLSA", "Salsa", Type.SAUCE),
//                Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        )
//
//        val types: Array<Type> = Type.values()
//        for (type in types) {
//            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type))
//        }
//        model.addAttribute("design", Taco())
//        return "design"
//    }
//
//    @GetMapping
//    fun showDesignForm(model: Model): String {
//        model.addAttribute("design", Taco())
//        return "design"
//    }
//
//    @PostMapping
//    fun processDesign(
//            @Valid @ModelAttribute("design") design: Taco,
//            errors: Errors,
//            model: Model
//    ): String {
//        if (errors.hasErrors()) {
//            return "design"
//        }
//
//        logger.info("Processing design: $design")
//
//        return "redirect:/orders/current"
//    }