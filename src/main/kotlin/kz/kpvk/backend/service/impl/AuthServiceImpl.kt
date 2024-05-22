package kz.kpvk.backend.service.impl

import kz.kpvk.backend.service.AuthService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthServiceImpl(
    private var tokens: MutableMap<String, String> = mutableMapOf<String, String>()
): AuthService {
    override fun authenticate(username: String): String {
        val token = UUID.randomUUID().toString()
        tokens[token] = username
        return token
    }

    override fun getUsername(token: String): String {
        return tokens[token] ?: "Неизвстный пользователь"
    }
}
