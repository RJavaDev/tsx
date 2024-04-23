package uz.tsx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tsx.dto.chat.ChatUserDto;
import uz.tsx.service.ChatUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat_user")
@RequiredArgsConstructor
public class ChatUserController {

    private final ChatUserService chatUserService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public ChatUserDto addUser(@Payload ChatUserDto user) {
        return chatUserService.createOrGet(user);
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public ChatUserDto disconnectUser(@Payload ChatUserDto user) {
        chatUserService.disconnect(user);
        return user;
    }

    @GetMapping("/online/{chatRoomId}")
    public List<ChatUserDto> findConnectedUsers(@PathVariable Long chatRoomId) {
        return chatUserService.findConnectedUsers(chatRoomId);
    }

    @GetMapping("/get/{userId}/{roomId}")
    public ChatUserDto getChatUser(@PathVariable Long userId,
                                   @PathVariable Long roomId) {
        return chatUserService.get(userId, roomId);
    }
}
