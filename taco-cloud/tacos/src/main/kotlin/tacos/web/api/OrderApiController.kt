package tacos.web.api

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import tacos.Order
import tacos.data.jpa.JpaOrderRepository


@RestController
@RequestMapping(path = ["/orders"],
    produces = ["application/json"])
@CrossOrigin(origins = ["*"])
class OrderApiController constructor(private var repo: JpaOrderRepository) {

    @GetMapping(produces = ["application/json"])
    fun allOrders(): Iterable<Order> {
        return repo.findAll()
    }

    @PostMapping(consumes = ["application/json"])
    @ResponseStatus(HttpStatus.CREATED)
    fun postOrder(@RequestBody order: Order): Order? {
        return repo.save(order)
    }

    @PutMapping(path = ["/{orderId}"], consumes = ["application/json"])
    fun putOrder(@RequestBody order: Order): Order? {
        return repo.save(order)
    }

    @PatchMapping(path = ["/{orderId}"], consumes = ["application/json"])
    fun patchOrder(@PathVariable("orderId") orderId: Long?,
                   @RequestBody patch: Order): Order {
        val order: Order = repo.findById(orderId!!).get()

        if (patch.deliveryName.isNotEmpty()) {
            order.deliveryName = patch.deliveryName
        }
        if (patch.deliveryStreet.isNotEmpty()) {
            order.deliveryStreet = patch.deliveryStreet
        }
        if (patch.deliveryCity.isNotEmpty()) {
            order.deliveryCity = patch.deliveryCity
        }
        if (patch.deliveryState.isNotEmpty()) {
            order.deliveryState = patch.deliveryState
        }
        if (patch.deliveryZip.isNotEmpty()) {
            order.deliveryZip = patch.deliveryState
        }
        if (patch.ccNumber.isNotEmpty()) {
            order.ccNumber = patch.ccNumber
        }
        if (patch.ccExpiration.isNotEmpty()) {
            order.ccExpiration = patch.ccExpiration
        }
        if (patch.ccCVV.isNotEmpty()) {
            order.ccCVV = patch.ccCVV
        }

        return repo.save(order)
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteOrder(@PathVariable("orderId") orderId: Long) {
        try {
            repo.deleteById(orderId)
        } catch (e: EmptyResultDataAccessException) {
            // ...
        }
    }

}