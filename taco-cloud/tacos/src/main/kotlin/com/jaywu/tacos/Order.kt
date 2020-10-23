package com.jaywu.tacos

import org.hibernate.validator.constraints.CreditCardNumber
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
@Table(name = "Taco_Order")
data class Order(
    @field:Id
        @field:GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

    var placedAt: Date? = null,

    @field:NotBlank(message = "Delivery name is required")
        var deliveryName: String = "",

    @field:NotBlank(message = "Street is required")
        var deliveryStreet: String = "",

    @field:NotBlank(message = "City is required")
        var deliveryCity: String = "",

    @field:NotBlank(message = "State is required")
        var deliveryState: String = "",

    @field:NotBlank(message = "Zip code is required")
        var deliveryZip: String = "",

    @field:CreditCardNumber(message = "Not a valid credit card number")
        var ccNumber: String = "",

    @field:Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
                message = "Must be formatted MM/YY")
        var ccExpiration: String = "",

    @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
        var ccCVV: String = "",

    @ManyToMany(targetEntity = Taco::class)
        @JoinTable(
            name = "Taco_Order_Tacos ",
            joinColumns = [ JoinColumn(name = "tacoOrder") ],
            inverseJoinColumns = [ JoinColumn(name = "taco") ]
        )
        var tacos: MutableList<Taco> = mutableListOf(),

    @ManyToOne
        @field:JoinColumn(name = "userId")
        var user: User? = null
): Serializable {

        companion object {
                private const val serialVersionUID = 1L
        }

        fun addDesign(design: Taco) {
                tacos.add(design)
        }

        @PrePersist
        fun placedAt() {
                placedAt = Date()
        }

}