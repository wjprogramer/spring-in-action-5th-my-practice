package tacos.security

import tacos.data.jpa.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import tacos.User

@Service
class UserRepositoryUserDetailsService @Autowired constructor(private val userRepo: UserRepository) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User? = userRepo.findByUsername(username)
        if (user != null) {
            return user
        }
        throw UsernameNotFoundException(
            "User '$username' not found")
    }
}