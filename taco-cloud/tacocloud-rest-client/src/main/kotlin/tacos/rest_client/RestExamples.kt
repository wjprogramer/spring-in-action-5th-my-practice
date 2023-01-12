package tacos.rest_client

import mu.KotlinLogging
import org.apache.http.client.HttpClient
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.client.Traverson
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.util.ResourceUtils
import org.springframework.web.client.RestTemplate
import tacos.Ingredient
import tacos.Taco
import java.io.File
import java.io.FileInputStream
import java.net.URI
import java.security.KeyStore
import javax.net.ssl.SSLContext


@SpringBootConfiguration
@ComponentScan
open class RestExamples {

    private val logger = KotlinLogging.logger {}
    
    @Bean
    open fun restTemplate(): RestTemplate {
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(FileInputStream(File("mykeys.jks")),
            "letmin".toCharArray())

        val socketFactory = SSLConnectionSocketFactory(
            SSLContextBuilder()
                .loadTrustMaterial(null, TrustSelfSignedStrategy())
                .loadKeyMaterial(keyStore, "letmin".toCharArray())
                .build(),
            NoopHostnameVerifier.INSTANCE)

        val httpClient: HttpClient = HttpClients.custom().setSSLSocketFactory(
            socketFactory).build()

        val requestFactory: ClientHttpRequestFactory = HttpComponentsClientHttpRequestFactory(
            httpClient)

        return RestTemplate(requestFactory)
    }

    @Bean
    open fun fetchIngredients(tacoCloudClient: TacoCloudClient): CommandLineRunner? {
        return CommandLineRunner {
            logger.info("----------------------- GET -------------------------")
            logger.info("GETTING INGREDIENT BY IDE")
            logger.info("Ingredient:  " + tacoCloudClient.getIngredientById("CHED"))
            logger.info("GETTING ALL INGREDIENTS")
            val ingredients = tacoCloudClient.getAllIngredients() ?: listOf()
            logger.info("All ingredients:")
            for (ingredient in ingredients) {
                logger.info("   - $ingredient")
            }
        }
    }

    @Bean
    open fun putAnIngredient(tacoCloudClient: TacoCloudClient): CommandLineRunner? {
        return CommandLineRunner {
            logger.info("----------------------- PUT -------------------------")
            val before = tacoCloudClient.getIngredientById("LETC")
            logger.info("BEFORE:  $before")
            tacoCloudClient.updateIngredient(Ingredient("LETC", "Shredded Lettuce", Ingredient.Companion.Type.VEGGIES))
            val after = tacoCloudClient.getIngredientById("LETC")
            logger.info("AFTER:  $after")
        }
    }

    @Bean
    open fun addAnIngredient(tacoCloudClient: TacoCloudClient): CommandLineRunner? {
        return CommandLineRunner { args: Array<String?>? ->
            logger.info("----------------------- POST -------------------------")
            val chix = Ingredient("CHIX", "Shredded Chicken", Ingredient.Companion.Type.PROTEIN)
            val chixAfter = tacoCloudClient.createIngredient(chix)
            logger.info("AFTER=1:  $chixAfter")
        }
    }


    @Bean
    open fun deleteAnIngredient(tacoCloudClient: TacoCloudClient): CommandLineRunner? {
        return CommandLineRunner { args: Array<String?>? ->
            logger.info("----------------------- DELETE -------------------------")
            // start by adding a few ingredients so that we can delete them later...
            val beefFajita = Ingredient("BFFJ", "Beef Fajita", Ingredient.Companion.Type.PROTEIN)
            tacoCloudClient.createIngredient(beefFajita)
            val shrimp = Ingredient("SHMP", "Shrimp", Ingredient.Companion.Type.PROTEIN)
            tacoCloudClient.createIngredient(shrimp)

            var before = tacoCloudClient.getIngredientById("CHIX")
            logger.info("BEFORE:  $before")
            tacoCloudClient.deleteIngredient(before)
            var after = tacoCloudClient.getIngredientById("CHIX")
            logger.info("AFTER:  $after")

            before = tacoCloudClient.getIngredientById("BFFJ")
            logger.info("BEFORE:  $before")
            tacoCloudClient.deleteIngredient(before)
            after = tacoCloudClient.getIngredientById("BFFJ")
            logger.info("AFTER:  $after")

            before = tacoCloudClient.getIngredientById("SHMP")
            logger.info("BEFORE:  $before")
            tacoCloudClient.deleteIngredient(before)
            after = tacoCloudClient.getIngredientById("SHMP")
            logger.info("AFTER:  $after")
        }
    }

    //
    // Traverson examples
    //

    @Bean
    open fun traverson(): Traverson {
        return Traverson(
            URI.create("https://localhost:8082/api"), MediaTypes.HAL_JSON)
    }

    @Bean
    open fun traversonGetIngredients(tacoCloudClient: TacoCloudClient): CommandLineRunner? {
        return CommandLineRunner {
            logger.info("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------")
            val ingredients = tacoCloudClient.getAllIngredientsWithTraverson() ?: listOf()
            for (ingredient in ingredients) {
                logger.info("   -  $ingredient")
            }
        }
    }

    @Bean
    open fun traversonSaveIngredient(tacoCloudClient: TacoCloudClient): CommandLineRunner? {
        return CommandLineRunner {
            logger.info("----------------------- ALL INGREDIENTS AFTER SAVING PICO -------------------------")
            val pico = tacoCloudClient.addIngredient(
                Ingredient("PICO", "Pico de Gallo", Ingredient.Companion.Type.SAUCE))

            val allIngredients = tacoCloudClient.getAllIngredients() ?: listOf()
            for (ingredient in allIngredients) {
                logger.info("   -  $ingredient")
            }

            tacoCloudClient.deleteIngredient(pico)
        }
    }

    @Bean
    open fun traversonRecentTacos(tacoCloudClient: TacoCloudClient): CommandLineRunner? {
        return CommandLineRunner {
            logger.info("----------------------- GET RECENT TACOS WITH TRAVERSON -------------------------")
            val recentTacos: Iterable<Taco> = tacoCloudClient.getRecentTacosWithTraverson()
            for (taco in recentTacos) {
                logger.info("   -  $taco")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<RestExamples>(*args)
}