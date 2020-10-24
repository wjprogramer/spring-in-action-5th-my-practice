package tacos.data.jpa

import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import tacos.Order
import tacos.User

interface JpaOrderRepository: CrudRepository<Order, Long> {

    fun findByUserOrderByPlacedAtDesc(user: User, pageable: Pageable): List<Order>

//    fun findByDeliveryZip(deliveryZip: String): List<Order>
//
//    fun readOrdersByDeliveryZipAndPlacedAtBetween(deliveryZip: String, startDate: Date, endDate: Date): List<Order>
//
//    // AllIgnoresCase
//    fun findByDeliveryStateAndDeliveryCity(deliveryState: String, deliveryCity: String): List<Order>
//
//    fun findByDeliveryCityOrderByDeliveryState(city: String): List<Order>
//
//    @Query("Order o where o.deliveryCity = 'Seattle'")
//    fun readOrdersDeliveredInSeattle()

}