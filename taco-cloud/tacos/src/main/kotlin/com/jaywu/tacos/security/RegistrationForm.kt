package com.jaywu.tacos.security

import com.jaywu.tacos.User
import org.springframework.security.crypto.password.PasswordEncoder

data class RegistrationForm (
    private val username: String = "",
    private val password: String = "",
    private val fullname: String = "",
    private val street: String = "",
    private val city: String = "",
    private val state: String = "",
    private val zip: String = "",
    private val phone: String = "",
) {

    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
            username, passwordEncoder.encode(password),
            fullname, street, city, state, zip, phone)
    }

}