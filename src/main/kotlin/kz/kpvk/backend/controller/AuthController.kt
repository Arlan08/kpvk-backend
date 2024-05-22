package kz.kpvk.backend.controller

import kz.kpvk.backend.service.AuthService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    val authService: AuthService
) {

    @PostMapping("/auth/{username}")
    fun auth(@PathVariable username: String): String {
        return authService.authenticate(username)
    }
}
