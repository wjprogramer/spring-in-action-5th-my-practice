package com.jaywu.tacocloud.data.jpa

import com.jaywu.tacocloud.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {

    fun findByUsername(username: String): User?

}