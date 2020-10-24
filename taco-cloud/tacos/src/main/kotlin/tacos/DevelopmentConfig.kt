package tacos

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import tacos.data.jpa.JpaIngredientRepository
import tacos.data.jpa.UserRepository

@Profile(value = ["!prod"])
@Configuration
class DevelopmentConfig {

    @Bean
    fun dataLoader(repo: JpaIngredientRepository,
                   userRepo: UserRepository, encoder: PasswordEncoder): CommandLineRunner? {
        // user repo for ease of testing with a built-in user
        return CommandLineRunner {
//            // FIXME: has problem
//            repo.save(Ingredient("FLTO", "Flour Tortilla", Ingredient.Companion.Type.WRAP))
//            repo.save(Ingredient("COTO", "Corn Tortilla", Ingredient.Companion.Type.WRAP))
//            repo.save(Ingredient("GRBF", "Ground Beef", Ingredient.Companion.Type.PROTEIN))
//            repo.save(Ingredient("CARN", "Carnitas", Ingredient.Companion.Type.PROTEIN))
//            repo.save(Ingredient("TMTO", "Diced Tomatoes", Ingredient.Companion.Type.VEGGIES))
//            repo.save(Ingredient("LETC", "Lettuce", Ingredient.Companion.Type.VEGGIES))
//            repo.save(Ingredient("CHED", "Cheddar", Ingredient.Companion.Type.CHEESE))
//            repo.save(Ingredient("JACK", "Monterrey Jack", Ingredient.Companion.Type.CHEESE))
//            repo.save(Ingredient("SLSA", "Salsa", Ingredient.Companion.Type.SAUCE))
//            repo.save(Ingredient("SRCR", "Sour Cream", Ingredient.Companion.Type.SAUCE))
//
//            userRepo.save(User("habuma", encoder.encode("password"),
//                "Craig Walls", "123 North Street", "Cross Roads", "TX",
//                "76227", "123-123-1234"))
        }
    }
}