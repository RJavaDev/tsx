package uz.tsx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.tsx.service.ChatMessageService;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final ChatMessageService chatMessageService;

    @Autowired
    public MessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

//    @GetMapping("/get/all")
//    public List<ChatDto> getUserMessages(@RequestParam("userId") Long userId) {
//        return chatService.getAllMessages(SecurityUtils.getUserId(), userId);
//    }
//
//    @GetMapping("/count-unread")
//    public List<UserDto> countUnreadMessages() {
//        return chatService.getCountUnreadMessages(SecurityUtils.getUserId());
//    }
//
//    @GetMapping("/unread-message")
//    public ChatDto getUnreadMessage(@RequestParam("userId") Long userId) {
//        return chatService.getUnreadMessage(SecurityUtils.getUserId(), userId);
//    }
//
//
//    @MessageMapping("/count-unread")
//    @SendTo("/topic/count-unread")
//    public List<UserDto> countUnreadMessages(WebSocketPayload webSocketPayload) {
//        return chatService.getCountUnreadMessages(webSocketPayload.getUserId());
//    }
}
