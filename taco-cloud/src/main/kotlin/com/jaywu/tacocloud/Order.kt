package com.jaywu.tacocloud

import javax.validation.constraints.NotBlank

import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*
import javax.validation.constraints.Digits
import javax.validation.constraints.Pattern
import kotlin.collections.ArrayList

data class Order(
        var id: Long = -1L,

        var placedAt: Date? = null,

        @field:NotBlank(message="Delivery name is required")
        var deliveryName: String = "",

        @field:NotBlank(message="Street is required")
        var deliveryStreet: String = "",

        @field:NotBlank(message="City is required")
        var deliveryCity: String = "",

        @field:NotBlank(message="State is required")
        var deliveryState: String = "",

        @field:NotBlank(message="Zip code is required")
        var deliveryZip: String = "",

        @field:CreditCardNumber(message="Not a valid credit card number")
        var ccNumber: String = "",

        @field:Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
        var ccExpiration: String = "",

        @field:Digits(integer=3, fraction=0, message="Invalid CVV")
        var ccCVV: String = "",

        var tacos: ArrayList<Taco> = arrayListOf()
) {

        fun addDesign(design: Taco) {
                tacos.add(design)
        }

}