package com.jaywu.tacocloud

import javax.validation.constraints.NotBlank

import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*
import javax.validation.constraints.Digits
import javax.validation.constraints.Pattern

data class Order(
        var id: Long = -1L,

        var createdAt: Date? = null,

        @field:NotBlank(message="Name is required")
        var name: String = "",

        @field:NotBlank(message="Street is required")
        var street: String = "",

        @field:NotBlank(message="City is required")
        var city: String = "",

        @field:NotBlank(message="State is required")
        var state: String = "",

        @field:NotBlank(message="Zip code is required")
        var zip: String = "",

        @field:CreditCardNumber(message="Not a valid credit card number")
        var ccNumber: String = "",

        @field:Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
        var ccExpiration: String = "",

        @field:Digits(integer=3, fraction=0, message="Invalid CVV")
        var ccCVV: String = ""
) {

}