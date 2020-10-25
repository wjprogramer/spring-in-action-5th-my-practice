package tacos.data.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import tacos.Taco

interface JpaTacoRepository: PagingAndSortingRepository<Taco, Long> {
}