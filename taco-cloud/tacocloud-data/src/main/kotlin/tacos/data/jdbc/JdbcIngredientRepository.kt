package tacos.data.jdbc

//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.jdbc.core.JdbcTemplate
//import org.springframework.jdbc.core.RowMapper
//import org.springframework.stereotype.Repository
//import tacos.Ingredient
//import tacos.data.IngredientRepository
//import java.sql.ResultSet
//import java.sql.SQLException
//
//@Repository
//class JdbcIngredientRepository(): IngredientRepository {
//
//    private lateinit var jdbc: JdbcTemplate
//
//    @Autowired
//    constructor(jdbc: JdbcTemplate): this() {
//        this.jdbc = jdbc
//    }
//
//    override fun findAll(): Iterable<Ingredient> {
//        return jdbc.query("select id, name, type from Ingredient",
//            this::mapRowToIngredient)
//    }
//
//    /**
//     * 目前是使用 reflect, lambda (anonymous function)
//     * 如果要使用 explicit function 寫法如下:
//     *
//     * ```
//     * jdbc.queryForObject("...", RowMapper { rs: ResultSet, _: Int -> Ingredient(
//     *      rs.getString("id"),
//     *      rs.getString("name"),
//     *      Ingredient.Companion.Type.valueOf(rs.getString("type"))
//     * ), id)
//     * ```
//     */
//    override fun findById(id: String?): Ingredient? {
//        /// FIXME: 突然不能用了
////        return jdbc.queryForObject("select id, name, type from Ingredient where id=?",
////                this::mapRowToIngredient, id)
//        return jdbc.queryForObject("...", RowMapper { rs: ResultSet, _: Int -> Ingredient(
//             rs.getString("id"),
//             rs.getString("name"),
//             Ingredient.Companion.Type.valueOf(rs.getString("type")))
//        }, id)
//    }
//
//    override fun save(ingredient: Ingredient?): Ingredient? {
//        jdbc.update(
//                "insert into Ingredient (id, name, type) values (?, ?, ?)",
//                ingredient?.id,
//                ingredient?.name,
//                ingredient?.toString()
//        )
//        return ingredient
//    }
//
//    @Throws(SQLException::class)
//    private fun mapRowToIngredient(rs: ResultSet, rowNum: Int): Ingredient {
//        return Ingredient(
//                rs.getString("id"),
//                rs.getString("name"),
//                Ingredient.Companion.Type.valueOf(rs.getString("type"))
//        )
//    }
//
//}