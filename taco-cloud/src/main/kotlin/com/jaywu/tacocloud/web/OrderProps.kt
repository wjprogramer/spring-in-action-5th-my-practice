package com.jaywu.tacocloud.web

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "taco.orders")
class OrderProps {

    var pageSize: Int = 20

}