package com.jaywu.tacocloud.data.jpa

import com.jaywu.tacocloud.Order
import com.jaywu.tacocloud.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

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