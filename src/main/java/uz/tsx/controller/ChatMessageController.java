package uz.tsx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.tsx.dto.chat.ChatMessageDto;
import uz.tsx.dto.chat.ChatNotificationDto;
import uz.tsx.service.ChatMessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessageDto chatMessage) {
        ChatMessageDto savedMsg = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipient().getId().toString(), "/queue/messages",
                new ChatNotificationDto(
                        savedMsg.getId(),
                        savedMsg.getSender(),
                        savedMsg.getRecipient(),
                        savedMsg.getContent()
                )
        );
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessageDto>> findChatMessage(@PathVariable Long senderId,
                                                                @PathVariable Long recipientId) {
        return ResponseEntity.ok(
                chatMessageService.findChatMessages(senderId, recipientId)
        );
    }
}
