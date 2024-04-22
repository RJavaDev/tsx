package uz.tsx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tsx.dto.chat.ChatRoomDto;
import uz.tsx.service.ChatRoomService;

@RestController
@RequestMapping("/api/v1/chatroom")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @GetMapping("/create-or-get/{announceId}")
    public ChatRoomDto createOrGetChatroom(@PathVariable Long announceId) {
        return chatRoomService.createOrGet(announceId);
    }
}
