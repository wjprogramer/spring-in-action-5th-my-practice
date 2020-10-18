package com.jaywu.tacocloud

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
data class User(
    private val username: String = "",
    private val password: String = "",
    private val fullname: String? = null,
    private val street: String? = null,
    private val city: String? = null,
    private val state: String? = null,
    private val zip: String? = null,
    private val phoneNumber: String? = null,
): UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null

    companion object {
        private const val serialVersionUID = 1L
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return listOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}