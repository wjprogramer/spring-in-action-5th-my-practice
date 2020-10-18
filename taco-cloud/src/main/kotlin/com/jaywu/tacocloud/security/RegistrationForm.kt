package com.jaywu.tacocloud.security

import com.jaywu.tacocloud.User
import org.springframework.security.crypto.password.PasswordEncoder

data class RegistrationForm (
    private val username: String = "",
    private val password: String = "",
    private val fullname: String? = null,
    private val street: String? = null,
    private val city: String? = null,
    private val state: String? = null,
    private val zip: String? = null,
    private val phone: String? = null,
) {

    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            username, passwordEncoder.encode(password),
            fullname, street, city, state, zip, phone)
    }

}