package kz.kpvk.backend.service.impl

import kz.kpvk.backend.data.dto.request.MessageRequest
import kz.kpvk.backend.data.dto.response.MessageResponse
import kz.kpvk.backend.data.entity.MessageEntity
import kz.kpvk.backend.data.repository.MessageRepository
import kz.kpvk.backend.service.AuthService
import kz.kpvk.backend.service.MessageService
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class MessageServiceImpl(
    val messageRepository: MessageRepository,
    val authService: AuthService
): MessageService {
    override fun sendMessage(token: String, message: MessageRequest): MessageResponse {
        val createMessage =  MessageEntity(
            userId = token,
            text = message.text
        )

        val savedMessage =  messageRepository.saveAndFlush(createMessage)
        return toDto(savedMessage)
    }

    override fun getMessages(): List<MessageResponse> {
        val sort = Sort.by(MessageEntity::createdAt.name).ascending()
        val messageList = ArrayList<MessageResponse>()
        val messages =  messageRepository.findAll(sort)
        for (message in messages) {
            val dto: MessageResponse = toDto(message)
            messageList.add(dto)
        }
        return messageList
    }

    private fun toDto(entity: MessageEntity): MessageResponse {
        return MessageResponse(
            id = entity.id!!,
            text = entity.text,
            authorId = entity.userId,
            author = authService.getUsername(entity.userId),
            createdAt = entity.createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        )
    }
}
