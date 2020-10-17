package com.jaywu.tacocloud.data.jdbc

import com.jaywu.tacocloud.Ingredient
import com.jaywu.tacocloud.Taco
import com.jaywu.tacocloud.data.TacoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.jdbc.support.KeyHolder
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.sql.Types
import java.util.*


@Repository
class JdbcTacoRepository(): TacoRepository {

    private lateinit var jdbc: JdbcTemplate

    @Autowired
    constructor(jdbc: JdbcTemplate): this() {
        this.jdbc = jdbc
    }

    override fun save(taco: Taco): Taco {
        val tacoId: Long = saveTacoInfo(taco)
        taco.id = tacoId
        for (ingredient in taco.ingredients) {
            saveIngredientToTaco(ingredient, tacoId)
        }

        return taco
    }

    private fun saveTacoInfo(taco: Taco): Long {
        taco.createdAt = Date()
        val psc: PreparedStatementCreator = PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(
                listOf(
                        taco.name,
                        Timestamp(taco.createdAt!!.time)))
        val keyHolder: KeyHolder = GeneratedKeyHolder()
        jdbc.update(psc, keyHolder)
        return keyHolder.key!!.toLong()
    }

    private fun saveIngredientToTaco(
            ingredient: Ingredient, tacoId: Long) {
        jdbc.update(
                "insert into Taco_Ingredients (taco, ingredient) " +
                        "values (?, ?)",
                tacoId, ingredient.id)
    }

}