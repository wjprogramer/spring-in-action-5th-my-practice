package com.jaywu.tacos.data.jpa

import com.jaywu.tacos.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

}