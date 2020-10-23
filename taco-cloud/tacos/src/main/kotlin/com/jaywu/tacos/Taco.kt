package com.jaywu.tacos

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class Taco(
    @field:Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

    @field:Column(name = "createdAt")
        var createdAt: Date? = null,

    @field:NotNull
        @field:Size(min = 5, message = "Name must be at least 5 characters long")
        var name: String = "",

    @field:NotNull
        @field:Size(min = 1, message = "You must choose at least 1 ingredient")
        @field:ManyToMany(targetEntity = Ingredient::class)
        @JoinTable(
                name = "Taco_Ingredients",
                joinColumns = [ JoinColumn(name = "taco") ],
                inverseJoinColumns = [ JoinColumn(name = "ingredient") ]
        )
        var ingredients: List<Ingredient> = arrayListOf(),
) {
        @PrePersist
        fun createdAt() {
                createdAt = Date()
        }
}