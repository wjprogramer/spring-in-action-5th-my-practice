package com.jaywu.tacocloud

import javax.validation.constraints.NotBlank

import org.hibernate.validator.constraints.CreditCardNumber
import javax.validation.constraints.Digits
import javax.validation.constraints.Pattern

class Order {
    @NotBlank(message="Name is required")
    lateinit var name: String

    @NotBlank(message="Street is required")
    lateinit var street: String

    @NotBlank(message="City is required")
    lateinit var city: String

    @NotBlank(message="State is required")
    lateinit var state: String

    @NotBlank(message="Zip code is required")
    lateinit var zip: String

    @CreditCardNumber(message="Not a valid credit card number")
    lateinit var ccNumber: String

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted MM/YY")
    lateinit var ccExpiration: String

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    lateinit var ccCVV: String
}