package tacos.web

import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import tacos.Order
import tacos.User
import tacos.data.jpa.JpaOrderRepository
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrderController @Autowired constructor(private val orderRepo: JpaOrderRepository, private val props: OrderProps) {

    @GetMapping("/current")
    fun orderForm(@AuthenticationPrincipal user: User,
                  @ModelAttribute order: Order): String {
        if (order.deliveryName.isEmpty()) {
            order.deliveryName = user.fullname
        }
        if (order.deliveryStreet.isEmpty()) {
            order.deliveryStreet = user.street
        }
        if (order.deliveryCity.isEmpty()) {
            order.deliveryCity = user.city
        }
        if (order.deliveryState.isEmpty()) {
            order.deliveryState = user.state
        }
        if (order.deliveryZip.isEmpty()) {
            order.deliveryZip = user.zip
        }

        return "orderForm"
    }

    @PostMapping
    fun processOrder(
        @Valid order: Order, errors: Errors, sessionStatus: SessionStatus,
        @AuthenticationPrincipal user: User
    ): String {
        if (errors.hasErrors()) {
            return "orderForm"
        }

        order.user = user

        orderRepo.save(order)
        sessionStatus.setComplete()

        logger.info { "Order submitted: $order" }
        return "redirect:/"
    }

    @GetMapping
    fun ordersForUser(
        @AuthenticationPrincipal user: User,
        model: Model
    ): String {

        val pageable = PageRequest.of(0,  props.pageSize)
        model.addAttribute("orders",
            orderRepo.findByUserOrderByPlacedAtDesc(user, pageable))

        return "orderList"
    }

}

/**
 * Ch2
 */
//    @GetMapping("/current")
//    fun orderForm(model: Model): String {
//        model.addAttribute("order", Order())
//        return "orderForm"
//    }
//    @PostMapping
//    fun processOrder(@Valid order: Order, errors: Errors): String {
//        if (errors.hasErrors()) {
//            return "orderForm"
//        }
//
//        logger.info { "Order submitted: $order" }
//        return "redirect:/"
//    }