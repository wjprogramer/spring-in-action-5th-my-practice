package com.jaywu.tacocloud.web

import com.jaywu.tacocloud.Order
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

private val logger = KotlinLogging.logger {}

@Controller
@RequestMapping("/orders")
class OrderController {

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        model.addAttribute("order", Order())
        return "orderForm"
    }

    @PostMapping
    fun processOrder(order: Order): String {
        logger.info { "Order submitted: $order" }
        return "redirect:/"
    }

}