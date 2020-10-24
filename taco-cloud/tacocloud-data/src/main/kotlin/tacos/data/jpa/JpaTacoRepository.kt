package tacos.data.jpa

import org.springframework.data.repository.CrudRepository
import tacos.Taco

interface JpaTacoRepository: CrudRepository<Taco, Long> {
}