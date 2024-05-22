package kz.kpvk.backend.service

interface AuthService {
    fun authenticate(username: String): String
    fun getUsername(token: String): String
}
