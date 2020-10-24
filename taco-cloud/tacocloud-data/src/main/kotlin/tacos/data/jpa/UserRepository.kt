package tacos.data.jpa

import org.springframework.data.repository.CrudRepository
import tacos.User

interface UserRepository: CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

}