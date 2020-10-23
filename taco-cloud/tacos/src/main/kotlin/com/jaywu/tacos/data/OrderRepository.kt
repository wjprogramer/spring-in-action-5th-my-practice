package com.jaywu.tacos.data

import com.jaywu.tacos.Order

interface OrderRepository {
    fun save(order: Order): Order
}