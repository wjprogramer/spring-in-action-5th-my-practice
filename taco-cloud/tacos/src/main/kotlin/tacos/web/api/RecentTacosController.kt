package tacos.web.api

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.CollectionModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import tacos.data.jpa.JpaTacoRepository
import tacos.web.api.assemblers.TacoResourceAssembler
import tacos.web.api.resources.TacoResource

@RepositoryRestController
class RecentTacosController constructor(var tacoRepo: JpaTacoRepository) {

    @GetMapping(
        path = ["/tacos/recent"],
        produces = ["application/hal+json"]
    )
    fun recentTacos(): ResponseEntity<CollectionModel<TacoResource>> {
        val page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending())
        val tacos = tacoRepo.findAll(page).content
        val tacoResources = TacoResourceAssembler().toCollectionModel(tacos)

        tacoResources.add(
            linkTo<RecentTacosController>{ methodOn(RecentTacosController::class.java).recentTacos() }
                .withRel("recents")
        )

        return ResponseEntity(tacoResources, HttpStatus.OK)
    }

}