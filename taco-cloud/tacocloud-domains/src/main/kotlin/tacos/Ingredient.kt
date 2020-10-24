package tacos

import javax.persistence.*


@Entity
data class Ingredient(
    @field:Id
    var id: String,

    var name: String,

    @Enumerated(EnumType.STRING)
    var type: Type?
) {

    constructor(): this("", "", null) {

    }

    companion object {
        enum class Type(var value: String) {
            WRAP(""),
            PROTEIN("PROTEIN"),
            VEGGIES("VEGGIES"),
            CHEESE("CHEESE"),
            SAUCE("SAUCE");

            companion object {
                fun valueOf(value: String): Type? = values().find { it.value == value }

                fun parse(value: String): Type? {
                    for (item in values()) {
                        if (item.value == value) {
                            return item
                        }
                    }
                    return null
                }
            }
        }
    }
}