package kz.kpvk.backend.service

import kz.kpvk.backend.data.dto.request.MessageRequest
import kz.kpvk.backend.data.dto.response.MessageResponse

interface MessageService {
    fun sendMessage(token: String, message: MessageRequest): MessageResponse
    fun getMessages(): List<MessageResponse>
}
