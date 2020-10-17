package com.jaywu.tacocloud.data.jdbc

import com.jaywu.tacocloud.Ingredient
import com.jaywu.tacocloud.data.IngredientRepository
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource


/**
 * Raw implementation of {@link IngredientRepository} for
 * comparison with {@link JdbcIngredientRepository} to illustrate
 * the power of using {@link JdbcTemplate}.
 */
class RawJdbcIngredientRepository(private val dataSource: DataSource): IngredientRepository {
    override fun findAll(): Iterable<Ingredient> {
        val ingredients: MutableList<Ingredient> = ArrayList()
        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            connection = dataSource.connection
            statement = connection.prepareStatement(
                    "select id, name, type from Ingredient")
            resultSet = statement.executeQuery()
            while (resultSet.next()) {
                val ingredient = Ingredient(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        Ingredient.Companion.Type.valueOf(resultSet.getString("type")))
                ingredients.add(ingredient)
            }
        } catch (e: SQLException) {
            // ??? What should be done here ???
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close()
                } catch (e: SQLException) {
                }
            }
            if (statement != null) {
                try {
                    statement.close()
                } catch (e: SQLException) {
                }
            }
            if (connection != null) {
                try {
                    connection.close()
                } catch (e: SQLException) {
                }
            }
        }
        return ingredients
    }

    // tag::rawfindOne[]
    override fun findById(id: String?): Ingredient? {
        var connection: Connection? = null
        var statement: PreparedStatement? = null
        var resultSet: ResultSet? = null
        try {
            connection = dataSource.connection
            statement = connection.prepareStatement(
                    "select id, name, type from Ingredient")
            statement.setString(1, id)
            resultSet = statement.executeQuery()
            var ingredient: Ingredient? = null
            if (resultSet.next()) {
                ingredient = Ingredient(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        Ingredient.Companion.Type.valueOf(resultSet.getString("type")))
            }
            return ingredient
        } catch (e: SQLException) {
            // ??? What should be done here ???
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close()
                } catch (e: SQLException) {
                }
            }
            if (statement != null) {
                try {
                    statement.close()
                } catch (e: SQLException) {
                }
            }
            if (connection != null) {
                try {
                    connection.close()
                } catch (e: SQLException) {
                }
            }
        }
        return null
    }

    override fun save(ingredient: Ingredient?): Ingredient? {
        // TODO: I only needed one method for comparison purposes, so
        //       I've not bothered implementing this one (yet).
        return null
    }
}