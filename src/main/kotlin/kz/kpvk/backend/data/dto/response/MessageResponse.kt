package kz.kpvk.backend.data.dto.response

data class MessageResponse(
    val id: Long,
    val text: String,
    val authorId: String,
    val author: String,
    val createdAt: String
)
