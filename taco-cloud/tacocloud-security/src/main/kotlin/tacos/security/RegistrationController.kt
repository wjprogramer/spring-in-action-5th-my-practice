package tacos.security

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tacos.data.jpa.UserRepository

@Controller
@RequestMapping("/register")
class RegistrationController(private val userRepo: UserRepository, private val passwordEncoder: PasswordEncoder) {

    @GetMapping
    fun registerForm(): String? {
        return "registration"
    }

    @PostMapping
    fun processRegistration(form: RegistrationForm): String? {
        userRepo.save(form.toUser(passwordEncoder))
        return "redirect:/login"
    }

}