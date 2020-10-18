package com.jaywu.tacocloud.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.StandardPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Bean
    fun encoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity?) {
        http!!
            .authorizeRequests()
                .antMatchers("/design", "/orders")
                    .access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")

            .and()
                .formLogin()
                    .loginPage("/login")

            .and()
                .logout()
                    .logoutSuccessUrl("/")

            .and()
                .csrf()
                    .ignoringAntMatchers("/h2-console/**")

            .and()
                .headers()
                    .frameOptions()
                        .sameOrigin()
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder())
    }
}

/**
 * 4.2.1 寫死的使用者
 */
//    override fun configure(auth: AuthenticationManagerBuilder?) {
//        auth!!
//                .inMemoryAuthentication()
//                    .withUser("buzz")
//                        .password("infinity")
//                        .authorities("ROLE_USER")
//                .and()
//                    .withUser("woody")
//                        .password("bullseye")
//                        .authorities("ROLE_USER")
//    }
/**
 * 4.2.2 JDBC
 */
//    @Autowired
//    private lateinit var dataSource: DataSource
//
//    override fun configure(auth: AuthenticationManagerBuilder?) {
//        auth!!
//            .jdbcAuthentication()
//            .dataSource(dataSource)
//            .usersByUsernameQuery(
//                "select username, password, enabled from Users " +
//                    "where username = ?"
//            )
//            .authoritiesByUsernameQuery(
//                "select username, authority from UserAuthorities " +
//                    "where username = ?"
//            )
//            .passwordEncoder(BCryptPasswordEncoder())
//    }

/**
 * 4.2.3 LDAP
 */
// Skip