package com.jaywu.tacocloud.data

import com.jaywu.tacocloud.Order

interface OrderRepository {
    fun save(order: Order): Order
}