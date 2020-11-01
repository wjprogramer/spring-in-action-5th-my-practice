package tacos.web.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.PagedModel
import org.springframework.hateoas.server.EntityLinks
import org.springframework.hateoas.server.RepresentationModelProcessor
import tacos.Taco

@Configuration
class SpringDataRestConfiguration {

    @Bean
    fun tacoProcessor(links: EntityLinks): RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> {
        return RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> { model ->
            model.add(
                links.linkFor(Taco::class.java)
                    .slash("recent")
                    .withRel("recents"))
            model
        }
    }

}