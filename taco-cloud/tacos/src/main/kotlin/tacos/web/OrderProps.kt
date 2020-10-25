package tacos.web

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Max
import javax.validation.constraints.Min

/**
 * Ch2~5. 使用 View
 */
//@Component
//@ConfigurationProperties(prefix = "taco.orders")
//@Validated
//class OrderProps {
//
//    @field:Min(value=5, message="must be between 5 and 25")
//    @field:Max(value=25, message="must be between 5 and 25")
//    var pageSize: Int = 20
//
//}