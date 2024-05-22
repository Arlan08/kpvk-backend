package kz.kpvk.backend.controller

import kz.kpvk.backend.data.dto.request.MessageRequest
import kz.kpvk.backend.data.dto.response.MessageResponse
import kz.kpvk.backend.service.MessageService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageController(
    val messageService: MessageService
) {

    @PostMapping("/messages")
    fun sendMessage(@RequestHeader("token") token: String, @RequestBody message: MessageRequest): MessageResponse {
        return messageService.sendMessage(token, message)
    }

    @GetMapping("/messages")
    fun getMessages(): List<MessageResponse> {
        return messageService.getMessages()
    }
}
