package uz.tsx.service;

import uz.tsx.dto.chat.ChatMessageDto;

import java.util.List;

public interface ChatMessageService {
//    List<ChatDto> getAllMessages(Long userId1, Long userId2);
//
//    List<UserDto> getCountUnreadMessages(Long userId);
//
//    ChatDto getUnreadMessage(Long userId1, Long userId2);

    ChatMessageDto save(ChatMessageDto dto);

    List<ChatMessageDto> findChatMessages(Long senderId, Long recipientId);
}
