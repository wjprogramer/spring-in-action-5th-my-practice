package com.jaywu.tacocloud.web

import com.jaywu.tacocloud.Ingredient
import com.jaywu.tacocloud.Ingredient.Companion.Type
import com.jaywu.tacocloud.Taco
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.util.stream.Collectors

@Slf4j
@Controller
@RequestMapping("/design")
class DesignTacoController {

    @GetMapping
    open fun showDesignForm(model: Model): String? {
        val ingredients = listOf(
                Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                Ingredient("CARN", "Carnitas", Type.PROTEIN),
                Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                Ingredient("LETC", "Lettuce", Type.VEGGIES),
                Ingredient("CHED", "Cheddar", Type.CHEESE),
                Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                Ingredient("SLSA", "Salsa", Type.SAUCE),
                Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        )

        val types: Array<Type> = Type.values()
        for (type in types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type))
        }
        model.addAttribute("design", Taco())
        return "design"
    }

    private fun filterByType(
            ingredients: List<Ingredient>, type: Type): MutableList<Any>? {
        return ingredients
                .stream()
                .filter { x: Ingredient -> x.type == type }
                .collect(Collectors.toList<Any>())
    }
}