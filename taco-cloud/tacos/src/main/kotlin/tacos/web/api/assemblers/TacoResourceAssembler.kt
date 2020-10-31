package tacos.web.api.assemblers

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import tacos.Taco
import tacos.web.api.DesignTacoController
import tacos.web.api.resources.TacoResource

class TacoResourceAssembler: RepresentationModelAssemblerSupport<Taco, TacoResource>(DesignTacoController::class.java, TacoResource::class.java) {

    override fun instantiateModel(taco: Taco): TacoResource {
        return TacoResource(taco)
    }

    override fun toModel(taco: Taco): TacoResource {
        return createModelWithId(taco.id ?: -1, taco)
    }

}