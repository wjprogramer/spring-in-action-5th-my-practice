package com.jaywu.tacocloud.data.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.core.type.TypeReference
import com.jaywu.tacocloud.Order
import com.jaywu.tacocloud.Taco
import com.jaywu.tacocloud.data.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class JdbcOrderRepository @Autowired constructor(jdbc: JdbcTemplate): OrderRepository {

    private var orderInserter: SimpleJdbcInsert = SimpleJdbcInsert(jdbc)
            .withTableName("Taco_Order")
            .usingGeneratedKeyColumns("id")

    private var orderTacoInserter: SimpleJdbcInsert = SimpleJdbcInsert(jdbc)
            .withTableName("Taco_Order_Tacos")

    private var objectMapper: ObjectMapper = ObjectMapper()

    override fun save(order: Order): Order {
        order.placedAt = Date()
        val orderId = saveOrderDetails(order)
        order.id = orderId
        val tacos: List<Taco> = order.tacos
        for (taco in tacos) {
            saveTacoToOrder(taco, orderId)
        }

        return order
    }

    /**
     * @return orderId
     */
    private fun saveOrderDetails(order: Order): Long {
        val values: MutableMap<String, Any?> = objectMapper.convertValue(order, object: TypeReference<MutableMap<String, Any?>>() {})
        values["placedAt"] = order.placedAt
        return orderInserter
                .executeAndReturnKey(values)
                .toLong()
    }

    private fun saveTacoToOrder(taco: Taco, orderId: Long) {
        val values: MutableMap<String, Any?> = HashMap()
        values["tacoOrder"] = orderId
        values["taco"] = taco.id
        orderTacoInserter.execute(values)
    }

}